import React from "react";
import {
  Grid,
  Divider,
  Icon,
  Item,
  Label,
  Container,
  Button,
  Image
} from "semantic-ui-react";
import Navbar from "src/component/Navbar";
import Menubar from "src/component/Menubar";
import { useEffect, useState } from "react";
import styles from "styles/signup.module.css";
import allAxios from "src/lib/allAxios";
import { useRouter } from "next/router";
import InfiniteScroll from "react-infinite-scroll-component";
import FeedList from "src/component/feed/FeedList";
import IsLogin from "src/lib/customIsLogin";
import userAxios from "src/lib/userAxios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import StompSend from "src/component/StompSend";

function Profiles({ id }: any) {
  const router = useRouter();

  // 로그인 유저
  const isLogin = IsLogin;
  const [nowUser, setNowUser] = useState({
    id: 0,
    nickName: "",
  });
  const [isFollowing, setIsFollowing] = useState(false);

  // 프로필 유저
  const [user, setUser] = useState({
    intro: "",
    feedCount: 0,
    follower: 0,
    following: 0,
    image: "",
    nickName: "",
    sunin: 0,
  });

  // 프로필 유저 피드
  const [list, setList]: any = useState([]);

  const [pages, setPage] = useState(0); // 넌 보류

  function getUser() {
    allAxios
      .get(`/api/v1/users/profile/${id}`)
      .then(({ data }) => {
        setUser({
          intro: data.introduction,
          feedCount: data.feed_count,
          follower: data.follwer_count,
          following: data.follwing_count,
          image: data.image,
          nickName: data.nick_name,
          sunin: data.sunin_days,
        });
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
        router.push("/");
      });
  }

  useEffect(() => {
    allAxios
      .get(`/api/v1/users/profile/${id}`)
      .then(({ data }) => {
        setUser({
          intro: data.introduction,
          feedCount: data.feed_count,
          follower: data.follwer_count,
          following: data.follwing_count,
          image: data.image,
          nickName: data.nick_name,
          sunin: data.sunin_days,
        });
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
        router.push("/");
      });

    allAxios.get(`/feed/person/${id}`).then(({ data }) => {
      setList(data);
    });

    if (isLogin) {
      userAxios
        .get(`/api/v1/users`)
        .then(({ data }) => {
          setNowUser({
            id: data.body.user.user_seq,
            nickName: data.body.user.user_nickname,
          });
          getFollowingUsers(data.body.user.user_seq);
        })
        .catch(() => {
          alert("잠시 후 다시 시도해주세요...");
        });
    }
  }, []);

  function loadFeed() {
    setPage(pages + 1);
  }

  const getFollowingUsers = async (inputId: any) => {
    if (nowUser) {
      await allAxios
        .get(`/follower/followingList/${inputId}`, {
          params: {
            userId: inputId,
          },
        })
        .then(({ data }) => {
          data.map((followingusers: Number) => {
            if (followingusers === Number(id)) {
              setIsFollowing(true);
            }
          });
        })
        .catch(() => {
          alert("잠시 후 다시 시도해주세요...");
        });
    }
  };

  function goFollowing() {
    if (nowUser.id) {
      const body: any = new FormData();
      body.append("followerMember", `${Number(id)}`);
      body.append("userId", `${nowUser.id}`);

      const fromUserId = localStorage.getItem("userId");
      const messages = `${nowUser.nickName}님이 팔로워를 하였습니다.`;

      const socket = new SockJS("http://i6c210.p.ssafy.io:8080/stomp");
      const stompClient = Stomp.over(socket);
      allAxios
        .post(`/follower`, body)
        .then(() => {
          setIsFollowing(true);
          getUser()

          setTimeout(() => {
            stompClient.send(`/send/`+fromUserId+`/`+Number(id)+`/`+messages);
          }, 1000);

        })
        
        .catch(() => {
          // alert("잠시 후 다시 시도해주세요.");
        });
    }
  }

  function goUnfollowing() {
    if (nowUser.id) {
      allAxios
        .delete(`/follower`, {
          params: {
            followerMember: Number(id),
            userId: nowUser.id,
          },
        })
        .then(() => {
          setIsFollowing(false);
          getUser()
        })
        .catch((e: any) => {
          alert("잠시 후 다시 시도해주세요.");
        });
    }
  }

  const [userInfo, setUserInfo]: any = useState([]);

  let suninImage = "/images/suninimage/씨앗.png";

  if (userInfo["sunin_days"]) {
    suninImage =
      userInfo["sunin_days"] < 3
        ? "/images/suninimage/씨앗.png"
        : userInfo["sunin_days"] < 6
        ? "/images/suninimage/새싹.png"
        : userInfo["sunin_days"] < 9
        ? "/images/suninimage/꽃.png"
        : userInfo["sunin_days"] < 12
        ? "/images/suninimage/나무.png"
        : "/images/suninimage/큰나무.png";
  }

  return (
    <>
      <Navbar />
      <Container className={styles.con}>
        <Grid columns={2} padded stackable>
          <Grid.Column width={4}>
            <Menubar />
          </Grid.Column>
          <Grid.Column width={10}>
            <div className={styles.headeralign}>
              <Item.Group divided>
                <Item>
                  <Item.Image src={user.image} />

                  <Item.Content>
                    <Item.Header>
                      <span>{user.nickName}</span>
                      <span> | </span>
                     {/* <div className={styles.imagefile}> */}
                        <Image
                        className={styles.imagefile}
                       src={suninImage}
                       width="40px" height="40px"
                        inline
                        />
                     {/* </div> */}
                      {/* <Icon name="lemon outline" /> */}
                      <span className="cinema">{user.sunin}</span>
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      {nowUser.id === Number(id) || !nowUser.id ? (
                        ""
                      ) : isFollowing ? (
                        <Button color="green" onClick={goUnfollowing}>
                          UnFollow
                        </Button>
                      ) : (
                        <Button onClick={goFollowing}>Follow</Button>
                      )}
                    </Item.Header>
                    <Item.Description>
                      <span>{user.intro}</span>
                    </Item.Description>
                    <Item.Extra>
                      <Label>
                        <Icon name="write" />
                        <span>Feed {user.feedCount}</span>
                      </Label>
                      <Label>
                        <Icon name="sign-in" />
                        <span>Follower {user.follower}</span>
                      </Label>
                      <Label>
                        <Icon name="sign-out" />
                        <span>Following {user.following}</span>
                      </Label>
                    </Item.Extra>
                  </Item.Content>
                </Item>
              </Item.Group>
            </div>
            <Divider />
            <InfiniteScroll
              style={{ overflow: "hidden" }}
              dataLength={list.length}
              next={loadFeed}
              hasMore={true}
              loader={undefined}>
              <FeedList list={list} />
            </InfiniteScroll>
          </Grid.Column>
          <Grid.Column width={2} />
        </Grid>
      </Container>
    </>
  );
}
export async function getServerSideProps(context: any) {
  const id = context.params.id;

  return { props: { id } };
}
export default Profiles;
