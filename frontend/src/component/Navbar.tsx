import { Dropdown, Menu } from "semantic-ui-react";
import Link from "next/link";
import styles from "../../styles/Navbar.module.css";
import Alarm from "./Alarm";
import Searchbar from "./Searchbar";
import IsLogin from "../lib/customIsLogin";
import { useRouter } from "next/router";
import LoginModal from "./login/loginModal";
import { useEffect, useState } from "react";
import Image from "next/image";
import userAxios from "src/lib/userAxios";
import User from "src/class/User";

export default function Navbar() {
  const router = useRouter();

  function goLogout() {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    router.reload();
  }

  const isLogin = IsLogin;

  const [userInfo, setUserInfo]: any = useState([]);

  useEffect(() => {
    if (isLogin) {
      userAxios
        .get(`/api/v1/users`)
        .then(({ data }) => {
          setUserInfo(data.body.user);
        })
        .catch((e: any) => {
          alert("로그인 시간이 만료되었습니다.");
          autoLogout();
        });
    }
  }, []);

  function autoLogout() {
    if (isLogin && !userInfo.username) {
      goLogout();
    }
  }

  return (
    <div className={styles.fixed}>
      <Menu fixed="top" color="teal" inverted secondary>
        <Menu.Item link>
          <Link href="/">
            <a>
              <Image
                src="/images/로고만.png"
                alt="logo"
                width={40}
                height={40}
              />
            </a>
          </Link>
        </Menu.Item>

        <Menu.Item className={styles.item} link>
          <Dropdown className={styles.item} text="Feed" icon={null} simple>
            <Dropdown.Menu>
              <Dropdown.Item
                onClick={() => {
                  router.push("/feed");
                }}>
                Latest
              </Dropdown.Item>
              <Dropdown.Item
                onClick={() => {
                  router.push("/feed/likes");
                }}>
                Likes
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        </Menu.Item>

        {userInfo.username && (
          <Menu.Item className={styles.item} link>
            <Dropdown className={styles.item} text="Follow" icon={null} simple>
              <Dropdown.Menu>
                <Dropdown.Item
                  onClick={() => {
                    router.push(`/feed/flatest`, undefined, { shallow: true });
                  }}>
                  Latest
                </Dropdown.Item>
                <Dropdown.Item
                  onClick={() => {
                    router.push("/feed/flikes", undefined, { shallow: true });
                  }}>
                  Likes
                </Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
          </Menu.Item>
        )}

        {/* <Menu.Item className={styles.item} link>
          <Link href="/feed">
            <a>Feed</a>
          </Link>
        </Menu.Item>

        <Menu.Item className={styles.item} link>
          <Link href="/follow">
            <a>Follow</a>
          </Link>
        </Menu.Item> */}

        {userInfo.username ? (
          <Menu.Item className={styles.item} link>
            <Link href={`/profile/${userInfo["user_seq"]}`}>
              <a>Profile</a>
            </Link>
          </Menu.Item>
        ) : (
          ""
        )}

        <Menu.Item position="right">
          <Searchbar />
        </Menu.Item>
        <Menu.Item />

        {isLogin ? (
          <Menu.Item>
            <Alarm />
          </Menu.Item>
        ) : (
          ""
        )}

        {isLogin ? (
          <Menu.Item name="logout">
            <Link href="/">
              <a onClick={goLogout}>Logout</a>
            </Link>
          </Menu.Item>
        ) : (
          <Menu.Item name="login">
            {/* <Link href="/login"><a>Login</a></Link> */}
            <LoginModal />
          </Menu.Item>
        )}
      </Menu>
    </div>
  );
}
