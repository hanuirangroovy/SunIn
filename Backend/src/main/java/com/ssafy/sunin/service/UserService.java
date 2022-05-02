package com.ssafy.sunin.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.payload.request.feed.ImageUpdate;
import com.ssafy.sunin.payload.request.user.UserUpdateRequest;
import com.ssafy.sunin.payload.response.user.*;
import com.ssafy.sunin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService{

    private final UserRepository userRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.url}")
    private String url;

    private final AmazonS3 amazonS3;

    private final FollowerService followerService;

    private final FeedService feedService;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    public UserDetailProfile getUserDetailProfile(Long userId){
        User user = userRepository.findProfileByUserSeq(userId);

        int follwer = followerService.countFollower(userId);
        int follwing = followerService.countFollowing(userId);
        int feedCount = feedService.feedCount(userId).intValue();
        UserProfile userProfile = UserProfile.userProfile(user.getUserSeq(),follwer,follwing,feedCount);

        return UserDetailProfile.userProfileDto(user, userProfile);
    }

    public UserSideProfile getUserSideProfile(Long userId){
        User user = userRepository.findProfileByUserSeq(userId);

        return UserSideProfile.userSideProfile(user);
    }

    public List<UserRank> getUserListLank(){
        return userRepository.findTop100AllBy(Sort.by(Sort.Order.desc("suninDays"))).stream()
                .map(user -> UserRank.builder()
                    .id(user.getUserSeq())
                    .nickName(user.getUserNickname())
                    .suninDays(user.getSuninDays())
                    .build())
                .collect(Collectors.toList());
    }

    public UserUpdateResponse updateUser(UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userUpdateRequest.getUserId()).get();
        String image = profileUpload(userUpdateRequest.getImage());
        user.setUserProfileModifed(userUpdateRequest,image);

        userRepository.save(user);
        return UserUpdateResponse.userUpdate(user);
    }

    public UserDetailProfile updateUserImage(ImageUpdate imageUpdate){
        User user = userRepository.findProfileByUserSeq(imageUpdate.getId());
        String file = profileUpload(imageUpdate.getImage());
        user.setUserProfileImageModified(file);
        userRepository.save(user);
        return UserDetailProfile.userProfile(user);
    }

    public UserDetailProfile delteUserImage(Long userId){
        User user = userRepository.findProfileByUserSeq(userId);
//        user.setNormalProfileImage();

        return UserDetailProfile.userProfile(user);
    }

    private String profileUpload(MultipartFile file) {
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

        return String.format(url + "/%s",fileName);
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