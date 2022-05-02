import SockJS from "sockjs-client";
import { setTimeout } from "timers";
import Stomp from "webstomp-client";

const StompSend = (toUserId: any, nickname: any) => {
  const fromUserId = localStorage.getItem("userId");
  const messages = `${nickname}님이 팔로워를 하였습니다.`;

  const socket = new SockJS("http://i6c210.p.ssafy.io:8080/stomp");
  const stompClient = Stomp.over(socket);

  try {
    setTimeout(() => {
      stompClient.send(`/send/${fromUserId}/${toUserId}/${messages}`);
    }, 300);
  } catch {
    console.log("예외 발생");
  }
};

export default StompSend;
