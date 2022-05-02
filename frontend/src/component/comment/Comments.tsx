import Router from "next/router";
import { useState } from "react";
import { List, Image, Icon, Label, Input } from "semantic-ui-react";
import allAxios from "src/lib/allAxios";
import Comment from "./Comment";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import ByteLength from "../ByteLength";

function Comments({ feedWriter, nickName, list, userSeq, feedId }: any) {
  const [comment, setComment] = useState("");
  const [length, setLength] = useState(0); // 글자수 바이트 카운트

  const handleComment = (e: any) => {
    setComment(e.target.value);
    setLength(ByteLength(e.target.value));
  };

  const writeComment = () => {
    let flag = false;
    if (comment == "" || comment == " ") flag = true;

    if (flag || length > 60) {
      alert("내용을 확인해주세요.");
      return;
    }

    const body = new FormData();
    body.append("content", comment);
    body.append("feedId", feedId);
    body.append("writer", userSeq);

    // 보내는 사람
    const fromUserId = localStorage.getItem("userId");
    const messages = nickName + "님이 게시글에 댓글을 작성하였습니다";
    const socket = new SockJS("http://i6c210.p.ssafy.io:8080/stomp");
    const stompClient = Stomp.over(socket);

    allAxios
      .post(`/comment`, body)
      .then(() => {
        // 메시지 전달
        setTimeout(() => {
          stompClient.send(
            `/send/` + fromUserId + `/` + feedWriter + `/` + messages
          );
        }, 300);

        setTimeout(() => {
          Router.reload();
        }, 1000);
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
    setComment("");
  };

  return (
    <>
      {userSeq != undefined && (
        <>
          <Input
            fluid
            className="commentInput"
            placeholder="Comment"
            icon="comment"
            iconPosition="left"
            value={comment}
            onChange={handleComment}
            action={{
              icon: "send",
              onClick: writeComment,
            }}
          />
          {length > 60 ? (
            <span style={{ color: "red" }}>{length}</span>
          ) : (
            <span>{length}</span>
          )}
          <span> / 60</span>
        </>
      )}

      <List relaxed="very" divided verticalAlign="middle">
        {Object.entries(list).map((object: any) => {
          return (
            <List.Item key={object}>
              <Comment
                nickName={nickName}
                item={object}
                userSeq={userSeq}
                feedId={feedId}
              />
            </List.Item>
          );
        })}
      </List>
    </>
  );
}

export default Comments;
