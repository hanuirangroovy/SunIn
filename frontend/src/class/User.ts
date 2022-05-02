export default class User {
  userSeq: number; // 기본키
  userId: string; // 아이디
  username: string; // 이름
  email: string; // 이메일
  userNickname: string; // 닉네임
  profileImageUrl: string; // 프로필사진
  providerType: string; // 제공자(GOOGLE, KAKAO, NAVER)
  roleType: string; // ENUM("ADMIN", "USER")
  suninDays: number; // 썬인
  createdAt: string; // 가입일자
  modifiedAt: string; // 수정일자
  follower: []; // 팔로워
  address: string; // 주소
  introduction: string; // 자기소개
  phoneNumber: string; // 전화번호

  constructor() {
    this.userSeq = -1;
    this.userId = "";
    this.username = "";
    this.email = "";
    this.userNickname = "";
    this.profileImageUrl = "";
    this.providerType = "";
    this.roleType = "USER";
    this.suninDays = 0;
    this.createdAt = "";
    this.modifiedAt = "";
    this.follower = [];
    this.address = "";
    this.introduction = "";
    this.phoneNumber = "";
  }

  // constructor(data: any) {
  //   this.userSeq = data.user_seq || -1;
  //   this.userId = data.user_id || "";
  //   this.username = data.username || "";
  //   this.email = data.email || "";
  //   this.userNickname = data.user_nickname || "";
  //   this.profileImageUrl = data.profile_image_url || "";
  //   this.providerType = data.provider_type || "";
  //   this.roleType = data.role_type || "USER";
  //   this.suninDays = data.sunin_days || 0;
  //   this.createdAt = data.created_at || "";
  //   this.modifiedAt = data.modifeid_at || "";
  //   this.follower = data.follower || [];
  //   this.address = data.address || "";
  //   this.introduction = data.introduction || "";
  //   this.phoneNumber = data.phone_number || "";
  // }
}
