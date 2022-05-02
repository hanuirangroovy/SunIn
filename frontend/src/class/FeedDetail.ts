export default class FeedDetail {
  feedId: string; // 피드 ID
  userInfo: {}; // 작성자
  content: string; // 내용
  filePath: []; // 이미지, 동영상
  hashtags: []; // 해시태그
  likes: number; // 좋아요 수
  likeUser: []; // 좋아요 누른 사람
  comments: {}; // 댓글
  createdDate: string; // 작성일
  modifiedDate: string; // 수정일

  // constructor() {
  //   this.feedId = "";
  //   this.userInfo = {};
  //   this.content = "";
  //   this.filePath = [];
  //   this.hashtags = [];
  //   this.likes = 0;
  //   this.likeUser = [];
  //   this.comments = {};
  //   this.createdDate = "";
  //   this.modifiedDate = "";
  // }

  constructor(data: any) {
    this.feedId = data.id || "";
    this.userInfo = data.user || {};
    this.content = data.content || "";
    this.filePath = data.file_path || [];
    this.hashtags = data.hashtags || [];
    this.likes = data.likes || 0;
    this.likeUser = data.like_user || [];
    this.comments = data.comments || {};
    this.createdDate = data.created_date || "";
    this.modifiedDate = data.modified_date || "";
  }
}
