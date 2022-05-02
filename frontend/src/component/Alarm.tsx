import axios from "axios";
import { useEffect, useState } from "react";
import {
  Button,
  Header,
  Icon,
  Segment,
  TransitionablePortal,
} from "semantic-ui-react";
import styles from "../../styles/alarm.module.css";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import { List, Image } from "semantic-ui-react";
import { useRouter } from "next/router";

export default function Alarm() {

  const [open, setOpen] = useState(false);
  const [alarmList, setAlarmList]: any = useState([]);
  const router = useRouter();

  function handleClick() {
    setOpen(!open);
    alram();
  }
  function handleClose() {
    setOpen(false);
  }
  const FromUserId = 2;
  const toUserId = 1;
  const listUser = [FromUserId, toUserId];
  const message = "";
  useEffect(() => {
    // connect()
  });

  function alram() {
    // 보내는 사람
    const toUserId = localStorage.getItem("userId");
    const socket = new SockJS("http://i6c210.p.ssafy.io:8080/stomp");
    const stompClient = Stomp.over(socket);

    axios
      // 메시지를 받아야함
      .get(`http://i6c210.p.ssafy.io:8080/alram/` + toUserId)
      .then(({ data }) => {
        setAlarmList(data);
      })
      .catch((e: any) => {});
  }

  function goProfile(id: Number) {
    router.push(`/profile/${id}`);
  }

  return (
    <>
      <div>
        <Button
          content={
            open ? (
              <>
                <Icon name="bell"></Icon>알림닫기
              </>
            ) : (
              <>
                <Icon name="bell"></Icon>알림보기
              </>
            )
          }
          positive={true}
          onClick={handleClick}
        />
        <TransitionablePortal onClose={handleClose} open={open}>
          <Segment
            style={{ right: "7%", position: "fixed", top: "5%", zIndex: 1000 }}
            className={styles.alarm_content}>
            <Header>알림</Header>
            <List className={styles.alarm_ybar}>
              {alarmList.slice(0, 10).map((alram: any) => {
                return (
                  <List.Item key={alram.id}>
                    <List.Content>
                      <List.Header as="a">
                        <Image src={alram.user.image} size="mini" inline />
                        &nbsp;&nbsp;&nbsp;{alram.message}&nbsp;&nbsp;&nbsp;
                      </List.Header>
                      <List.Description as="a">
                        Updated {alram.local_date_time.slice(0, 10)}{" "}
                        {alram.local_date_time.slice(11, 16)}
                      </List.Description>
                    </List.Content>
                  </List.Item>
                );
              })}
            </List>
          </Segment>
        </TransitionablePortal>
      </div>
    </>
  );
}
