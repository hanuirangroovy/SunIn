import axios from "axios";
import { useRouter } from "next/router";
import { useEffect } from "react";
import SockJS from "sockjs-client";
import ToastMessage from "src/component/ToastMessage";
import Stomp from "webstomp-client";

export default function Authentication() {
  const router = useRouter();

  if (typeof window !== "undefined") {
    localStorage.removeItem("token");
    localStorage.setItem("token", String(router.query.token));
  }

  useEffect(() => {
    router.push("/");
    setTimeout(userA, 3000);
  }, []);

  function userA() {
    const token = localStorage.getItem("token");

    axios
      .get("http://i6c210.p.ssafy.io:8080/api/v1/users", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then(({ data }) => {
        localStorage.setItem("userId", data.body.user.user_seq);
        connect();
      })
      .catch(() => {
        alert("로그인 정보 가져오기 실패");
        router.push("/");
      });
  }

  function connect() {
    const userId = localStorage.getItem("userId");
    const socket = new SockJS("http://i6c210.p.ssafy.io:8080/stomp");
    const stompClient = Stomp.over(socket);

    stompClient.connect(
      {},
      (frame: any) => {
        stompClient.connected = true;
        stompClient.subscribe(`/sub/` + userId, (res: any) => {
          ToastMessage(res.body);
        });
      },
      (error: any) => {
        stompClient.connected = false;
      }
    );
  }
  return <></>;
}
