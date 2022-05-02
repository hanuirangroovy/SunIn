package com.ssafy.sunin.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.payload.request.feed.*;
import com.ssafy.sunin.payload.response.comment.CommentDto;
import com.ssafy.sunin.payload.response.feed.*;
import com.ssafy.sunin.payload.response.user.UserDetailProfile;
import com.ssafy.sunin.repository.FeedRepository;
import com.ssafy.sunin.repository.FollowerRepository;
import com.ssafy.sunin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.url}")
    private String url;

    private final AmazonS3 amazonS3;

    @Override
    public FeedCollections writeImageFeed(FeedWrite feedWrite) {
        List<String> fileList = new ArrayList<>();
        List<MultipartFile> files = feedWrite.getFiles();
        if (files != null) {
            AwsFile(feedWrite.getFiles(), fileList);
        }

        FeedCollections feedCollections = FeedCollections.setFeedCollection(feedWrite,fileList);
        FeedCollections feedCollection = feedRepository.save(feedCollections);
        suninDays(feedWrite.getUserId());

        return feedCollection;
    }


    private void suninDays(Long userId) {
        User user = userRepository.findProfileByUserSeq(userId);
        user.setSuninDayIncrease();
        userRepository.save(user);
    }

    private List<String> AwsFile(List<MultipartFile> files, List<String> list) {
        files.forEach(file -> {
            String fileName = createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try (InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }
            list.add(String.format(url + "/%s", fileName));
        });

        return list;
    }

    @Override
    public FeedCommentDto getDetailFeed(String id) {
        FeedCollections feedCollections = feedRepository.findByIdAndFlagTrue(new ObjectId(String.valueOf(id)));
        User user = userRepository.findProfileByUserSeq(feedCollections.getUserId());

        Map<Object, Comment> commentsMap = feedRepository.findFeedSortIdByIdAndFlagTrue(feedCollections.getId()).getComments();
        List<Comment> commentList = new ArrayList<>();
        commentList.addAll(commentsMap.values());

        // 아이디 중복 처리
        Set<Long> setUser = commentList.stream()
                .map(Comment::getWriter)
                .collect(Collectors.toSet());

        // 댓글의 유저의 프로필 정보
        Map<Long, User> userMap = userRepository.findAllSetByUserSeqIn(setUser).stream()
                .collect(Collectors.toMap(
                        User::getUserSeq,
                        o -> o
                ));

        commentList.sort(Comparator.comparing(Comment::getGroup)
                .thenComparing(Comment::getDepth));
        List<CommentDto> comments = CommentDto.mapCommentDto(commentList,userMap);
        LinkedHashMap<Object,CommentDto> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < comments.size(); i++) {
            linkedHashMap.put(comments.get(i).getId(),comments.get(i));
        }

        return FeedCommentDto.feedCommentDto(feedCollections, user, linkedHashMap);
    }

    @Override
    public FeedCollections updateFeed(FeedUpdate feedUpdate) {
        FeedCollections feedCollections = feedRepository.findByIdAndUserIdAndFlagTrue(new ObjectId(feedUpdate.getId()), feedUpdate.getUserId());
        List<String> fileNames = new ArrayList<>();
        AwsFile(feedUpdate.getFiles(),fileNames);
        feedCollections.setFileModified(fileNames);
        feedCollections.setFeedModified(feedUpdate);

        return feedRepository.save(feedCollections);
    }

    @Override
    public FeedCollections deleteFeed(String id, Long userId) {
        FeedCollections feedCollections = feedRepository.findByIdAndUserIdAndFlagTrue(new ObjectId(id), userId);
        feedCollections.setFeedDelete();
        User user = userRepository.findById(feedCollections.getUserId()).get();
        user.setSunindDaysDecrease(user);
        userRepository.save(user);
        return feedRepository.save(feedCollections);
    }

    @Override
    public List<FeedDto> getFollowerLatestFeed(Long userId) {
        List<Long> followers = followerRepository.getFollowingList(userId);
        Map<Long, User> userMap = userRepository.findAllListByUserSeqIn(followers).stream()
                .collect(Collectors.toMap(
                        User::getUserSeq,
                        o -> o
                ));

        List<FeedCollections> feedCollections = feedRepository.getFollowerLatesFeed(followers);
        return FeedDto.mapFeedDto(feedCollections,userMap);
    }

    @Override
    public List<FeedDto> getFollowerLikeFeed(Long userId) {
        List<Long> followers = followerRepository.getFollowingList(userId);
        Map<Long, User> userMap = userRepository.findAllListByUserSeqIn(followers).stream()
                .collect(Collectors.toMap(
                        User::getUserSeq,
                        o -> o
                ));

        List<FeedCollections> feedCollections = feedRepository.getFollowerLikeFeed(followers);
        return FeedDto.mapFeedDto(feedCollections,userMap);
    }

    @Override
    public List<FeedDto> getPersonalFeed(Long userId) {
        List<FeedCollections> feedCollections = feedRepository.getPersonalFeed(userId);
        User user = userRepository.findProfileByUserSeq(userId);

        return FeedDto.personFeedDto(feedCollections,user);
    }

    @Override
    public List<FeedDto> getLatestFeed(Pageable pageable) {
         List<FeedCollections> feedCollection = feedRepository.findAllByFlagTrue(pageable);

         Set<Long> users = feedCollection.stream()
                 .map(FeedCollections::getUserId)
                 .collect(Collectors.toSet());

         Map<Long, User> userMap = userRepository.findAllSetByUserSeqIn(users).stream()
                 .collect(Collectors.toMap(
                         User::getUserSeq,
                         o -> o
                 ));

         return FeedDto.mapFeedDto(feedCollection,userMap);
    }

    @Override
    public List<FeedDto> getLikeFeed(Pageable pageable) {
        List<FeedCollections> feedCollection = feedRepository.findAllByFlagTrue(pageable);

         Set<Long> users = feedCollection.stream()
                 .map(FeedCollections::getUserId)
                 .collect(Collectors.toSet());

         Map<Long, User> userMap = userRepository.findAllSetByUserSeqIn(users).stream()
                 .collect(Collectors.toMap(
                         User::getUserSeq,
                         o -> o
                 ));

         return FeedDto.mapFeedDto(feedCollection,userMap);
    }

    @Override
    public FeedCollections likeFeed(FeedLike feedLike) {
        FeedCollections feedCollections = feedRepository.findByIdAndFlagTrue(new ObjectId(feedLike.getId()));
        Map<Long, Object> users = new HashMap<>(feedCollections.getLikeUser());

        int like = feedCollections.getLikes();

        if (users.containsKey(feedLike.getUserId())) {
            users.remove(feedLike.getUserId());
            like--;
        } else {
            users.put(feedLike.getUserId(), true);
            like++;
        }

        feedCollections.setLikeModified(like, users);

        return feedRepository.save(feedCollections);
    }

    @Override
    public List<UserDetailProfile> getLikeUserList(String id) {
        FeedCollections feedCollections = feedRepository.findByIdAndFlagTrue(new ObjectId(id));
        Set<Long> list = feedCollections.getLikeUser().keySet();

        return userRepository.findFollowerSetByUserSeqIn(list)
                .stream()
                .map(user -> UserDetailProfile.builder()
                        .id(user.getUserSeq())
                        .nickName(user.getUserNickname())
                        .image(user.getProfileImageUrl())
                        .build()).collect(Collectors.toList());

    }

    @Override
    public Long feedCount(Long userId) {
        return feedRepository.getFeedCount(userId);
    }

    @Override
    public FeedSearch getSearchList(Pageable pageable,String content) {
        List<String> contentList = new ArrayList<>();
        Set<String> set = new HashSet<>();
        List<FeedCollections> feedCollections = new ArrayList<>();
        Map<Object,FeedCollections> map = new HashMap<>();
        List<Integer> idx = new ArrayList<>();

        if(content.length() == 0){
            return null;
        }else if(content.contains("#") && 2 <= content.length()) {
            content = content.substring(1);
            // 현재는 모든 피드 검색
            List<FeedCollections> feedList = feedRepository.findAllByFlagTrue();

            boolean[] check = new boolean[feedList.size()];
            // 해당 해시태그만 포함하는 피드만 가져와야함
            for (int i = 0; i < feedList.size(); i++) {
                // 해당 해시태그 가져오기
                List<String> hashtags = feedList.get(i).getHashtags();
                if (!hashtags.isEmpty()) {
                    // 배열에 들어간 해시태그 돌아서
                    for (int j = 0; j < hashtags.size(); j++) {
                        if (check[i]) continue;
                        // 해시태그의 첫글자가 content로 시작한다면
                        if (hashtags.get(j).startsWith(content)) {
                            // 중복제거를위해 set에 넣고
                            set.add(hashtags.get(j));
                            check[i] = true;
                            // 해당 피드 넣고
                            map.put(i,feedList.get(i));
                            idx.add(i);
                        }
                    }
                }
            }

            if(30 <= idx.size()){
                for (int i = idx.size()-1; i > idx.size()-30 ; i--) {
                    feedCollections.add(map.get(idx.get(i)));
                }
            }else{
                for (int i = idx.size()-1; i >=0; i--) {
                    feedCollections.add(map.get(idx.get(i)));
                }
            }
        }else {
            feedCollections = feedRepository.findByContentContainsAndFlagTrue(content,pageable);

            for (int i = 0; i < feedCollections.size(); i++) {
                set.add(feedCollections.get(i).getContent());
            }
        }

        contentList.addAll(set);
        Collections.sort(contentList);

        Set<Long> users = feedCollections.stream()
                .map(FeedCollections::getUserId)
                .collect(Collectors.toSet());

        Map<Long, User> userMap = userRepository.findAllSetByUserSeqIn(users).stream()
                .collect(Collectors.toMap(
                        User::getUserSeq,
                        o -> o
                ));

        List<FeedDto> feedDtos = FeedDto.mapFeedDto(feedCollections,userMap);
        return FeedSearch.feedSearch(feedDtos,contentList);

    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }
}