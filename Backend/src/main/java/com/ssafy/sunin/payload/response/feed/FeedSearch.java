package com.ssafy.sunin.payload.response.feed;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
// id, content, hashtags
public class FeedSearch {
    private List<FeedDto> feedDtos;
    private List<String> contentList;

    public static FeedSearch feedSearch(List<FeedDto> feedDtos, List<String> contentList){
        return FeedSearch.builder()
                .feedDtos(feedDtos)
                .contentList(contentList)
                .build();
    }

    @Builder
    public FeedSearch(List<FeedDto> feedDtos, List<String> contentList) {
        this.feedDtos = feedDtos;
        this.contentList = contentList;
    }
}
