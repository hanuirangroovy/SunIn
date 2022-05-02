# 화면설계 명세

> ### 🌞 이 문서는 ..
>
> ### 와이어프레임 형상의 `각 페이지의 흐름`과
>
> ### 각 테이블에 사용될 `DB`를 정리한 문서

# 화면설계서

<div align="center">
<img src="image.png">
<span>이미지를 클릭하면 크게 보입니다.</span>
</div>

# 프로젝트에 적용한 DB

- 해시태그 - mongodb
- 댓글 좋아요 - mysql
- 댓글 - 회원테이블과 피드 테이블과 관계가 맺어져 있고 조인연산이 필요하여 mysql로 구현
- 피드 - SNS 특성상 빠른 피드조회 연산이 가능하도록 mongodb를 사용
- 피드 좋아요 - mongodb
- 개인회원 - mysql
- 기업회원 - mysql
- 팔로워 - mysql
