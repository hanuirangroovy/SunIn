import {
  Header,
  Menu,
  MenuItem,
  Icon,
  Divider,
  Grid,
  GridRow,
  Image,
} from "semantic-ui-react";
import Link from "next/link";
import IsLogin from "../lib/customIsLogin";
// import GetUserData from 'src/lib/getUserData'
import { useEffect, useState } from "react";
import userAxios from "src/lib/userAxios";
import { useRouter } from "next/router";
import ChatModal from "./chat/chatModal";
import User from "src/class/User";
import styles from "../../styles/menubar.module.css";

export default function Menuvar() {
  const isLogin = IsLogin;
  const [userInfo, setUserInfo]: any = useState([]);
  const router = useRouter();

  function goProfile() {
    if (userInfo) {
      router.push(`/profile/${userInfo["user_seq"]}`);
    }
  }

  useEffect(() => {
    if (isLogin) {
      userAxios
        .get(`/api/v1/users`, {})
        .then(({ data }) => {
          setUserInfo(data.body.user);
        })
        .catch(() => {});
    }
  }, []);

  let suninImage = "/images/suninimage/씨앗.png";

  if (userInfo["sunin_days"]) {
    suninImage =
      userInfo["sunin_days"] < 1
        ? "/images/suninimage/씨앗.png"
        : userInfo["sunin_days"] < 10
        ? "/images/suninimage/새싹.png"
        : userInfo["sunin_days"] < 30
        ? "/images/suninimage/튤립.png"
        : userInfo["sunin_days"] < 50
        ? "/images/suninimage/나무.png"
        : "/images/suninimage/큰나무.png";
  }

  // let userData = null;
  // const tester = getUserData
  // console.log('test', tester)

  // async function loadUserData() {
  //   const pending = GetUserData();
  //   const result = await pending;
  //   return result;
  // }

  // useEffect(() => {
  //   userData = loadUserData();
  //   console.log('값이 있어야됨', userData)
  // }, [])

  return (
    <>
      <Menu vertical>
        <MenuItem className={styles.con}>
          <Grid>
            <GridRow>
              {/* {userInfo?
                <img src={userInfo['profileImageUrl']} alt="프로필사진" width="50px" onClick={goProfile} style={{cursor: "pointer", textShadow:"123"}} title="내 프로필로 이동합니다."/>
              :
              <img src="/images/디폴트프로필사진.png" alt="프로필사진" width="50px"/>
              }
              <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
              {userInfo?
              userInfo['username']:
              "비회원"}
              <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
              {userInfo?
              `${userInfo['suninDays']}일`:
              "0일"}

              {userInfo?
              <>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
              <Link href="/info"><a title="회원정보 수정 페이지로 이동합니다."><Icon name='cog' size='large' color='grey' circular/></a></Link>
              </>
              :
              ""
              } */}
              {userInfo.username ? (
                <>
                  <img
                    src={userInfo["profile_image_url"]}
                    alt="프로필사진"
                    width="50px"
                    onClick={goProfile}
                    style={{ cursor: "pointer", textShadow: "123" }}
                    title="내 프로필로 이동합니다."
                  />
                  <br />
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <span>
                    <br />{" "}
                    <span style={{ fontSize: "20px" }}>
                      {userInfo["username"]}{" "}
                    </span>{" "}
                    <br />
                    &nbsp;&nbsp; {userInfo["sunin_days"]}일
                  </span>
                  {/* {userInfo["username"]} */}
                  <br />
                  {/* &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; */}
                  {/* <span>{userInfo["sunin_days"]}일</span> */}
                  {/* {userInfo["sunin_days"]}일 */}
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <Image
                    src={suninImage}
                    width="50px"
                    height="50px"
                    title="씨앗->새싹->꽃->작은 나무->큰 나무"
                  />
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <br />
                  {/* &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; */}
                </>
              ) : (
                <>
                  <img
                    src="/images/디폴트프로필사진.png"
                    alt="프로필사진"
                    width="50px"
                  />
                  <br />
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 비회원
                  <br />
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 0일
                </>
              )}
            </GridRow>
          </Grid>
        </MenuItem>

        {userInfo.username ? (
          <Menu.Item name="profile" className={styles.con}>
            <div className={styles.setting}>
              <Link href={`/profile/${userInfo["user_seq"]}`}>
                <a className={styles.title}> 프로필</a>
              </Link>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <Link href="/profile/info">
                <a title="회원정보 수정 페이지로 이동합니다.">
                  <Icon
                    className={styles.setting}
                    name="cog"
                    size="small"
                    color="grey"
                    circular
                  />
                </a>
              </Link>
            </div>
          </Menu.Item>
        ) : (
          ""
        )}

        <Menu.Item className={styles.setting} name="personal" link>
          <div className={styles.title}>
            <Link href="/feed">
              <a className={styles.title}>피드</a>
            </Link>
          </div>
        </Menu.Item>

        <Menu.Item name="rank">
          <div>
            <Link href="/rank">
              <a className={styles.title}>랭킹</a>
            </Link>
          </div>
        </Menu.Item>
        {isLogin ? (
          <Menu.Item name="createfeed">
            <div>
              <Link href="/feed/create">
                <a className={styles.title}>피드 작성하기</a>
              </Link>
            </div>
          </Menu.Item>
        ) : (
          ""
        )}

        {isLogin ? (
          <Menu.Item name="chat">
            <div className={styles.title}>채팅</div>
            <br />
            <ChatModal />
          </Menu.Item>
        ) : (
          ""
        )}
      </Menu>
    </>
  );
}
