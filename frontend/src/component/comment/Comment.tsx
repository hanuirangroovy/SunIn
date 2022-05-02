import Router from "next/router";
import { useState } from "react";
import { List, Image, Icon, Label, Input } from "semantic-ui-react";
import allAxios from "src/lib/allAxios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import ByteLength from "../ByteLength";

function Comment({ nickName, item, userSeq, feedId }: any) {
  const [comment, setComment] = useState("");
  const [editable, setEditable] = useState(false); // 테스트
  const [replyable, setReplyable] = useState(false); // 테스트
  const [length, setLength] = useState(0); // 글자수 바이트 카운트

  const writeDate = (date: any) => {
    return date.split("T")[0];
  };

  const handleComment = (e: any) => {
    setComment(e.target.value);
    setLength(ByteLength(e.target.value));
  };

  const likeComment = (e: any) => {
    const body = new FormData();
    body.append("commentId", item[0]);
    body.append("feedId", feedId);
    body.append("userId", userSeq);

    allAxios
      .put(`/comment/addLike`, body)
      .then(() => {
        Router.reload();
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
  };

  const modifyButton = () => {
    setEditable(!editable);
  };

  const modifyComment = () => {
    setEditable(false);

    const body = new FormData();
    body.append("commentId", item[0]);
    body.append("content", comment);
    body.append("feedId", feedId);
    body.append("writer", userSeq);

    allAxios
      .put(`/comment`, body)
      .then(() => {
        Router.reload();
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
    setComment("");
  };

  const deleteButton = (e: any) => {
    const body: any = new FormData();
    body.append("commentId", e.target.name);
    body.append("feedId", feedId);
    body.append("writer", userSeq);

    allAxios
      .delete("/comment", {
        params: {
          commentId: e.target.name,
          feedId: feedId,
          writer: userSeq,
        },
      })
      .then(() => {
        Router.reload();
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
  };

  const replyButton = (e: any) => {
    setReplyable(!replyable);
  };

  const replyComment = (e: any) => {
    let flag = false;
    if (comment == "" || comment == " ") flag = true;

    if (flag || length > 60) {
      alert("내용을 확인해주세요.");
      return;
    }

    const body = new FormData();
    body.append("commentId", item[0]);
    body.append("content", comment);
    body.append("feedId", feedId);
    body.append("writer", userSeq);

    // 보내는 사람
    const fromUserId = localStorage.getItem("userId");
    const messages = nickName + "님이 대댓글을 작성하였습니다";
    const socket = new SockJS("http://i6c210.p.ssafy.io:8080/stomp");
    const stompClient = Stomp.over(socket);

    allAxios
      .post(`/comment/reply`, body)
      .then(() => {
        setTimeout(() => {
          stompClient.send(
            `/send/` + fromUserId + `/` + item[1].writer + `/` + messages
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
      <List.Content floated="right">
        {userSeq == undefined && (
          <Label>
            <Icon name="like" />
            {item[1].likes}
          </Label>
        )}
        {userSeq != undefined && !item[1].deleted && (
          <>
            <Label as="a" onClick={likeComment}>
              <Icon name="like" />
              {item[1].likes}
            </Label>
            {item[1].depth == 0 ? (
              <Label as="a" onClick={replyButton}>
                <Icon name="reply" />
                Reply
              </Label>
            ) : (
              ""
            )}
          </>
        )}
        {userSeq == item[1].writer && !item[1].deleted && (
          <>
            <Label as="a" name={item[0]} onClick={modifyButton}>
              <Icon name="edit" />
              Edit
            </Label>

            <Label as="a" name={item[0]} onClick={deleteButton}>
              <Icon name="remove" /> Delete
            </Label>
          </>
        )}
      </List.Content>

      <Image
        avatar
        circular
        width={30}
        height={30}
        src={item[1].user.image}
        alt={item[1].user.image}
      />
      <List.Content>
        <List.Header as="">
          {item[1].depth > 0 && (
            <Icon name="level up alternate" rotated="clockwise" />
          )}
          <span>{item[1].user.nick_name}</span>
          <span> | {writeDate(item[1].write_date)}</span>
        </List.Header>
        <List.Description>
          {item[1].deleted ? (
            "※ 삭제된 댓글입니다."
          ) : editable ? (
            <>
              <Input
                placeholder="Comment"
                icon="comment"
                iconPosition="left"
                value={comment}
                onChange={handleComment}
                action={{
                  icon: "send",
                  onClick: modifyComment,
                }}
              />
              {length > 60 ? (
                <span style={{ color: "red" }}>{length}</span>
              ) : (
                <span>{length}</span>
              )}
              <span> / 60</span>
            </>
          ) : (
            item[1].content
          )}

          {!item[1].deleted && item[1].modified && !editable
            ? "(" + writeDate(item[1].modified_date) + " 수정됨)"
            : ""}
        </List.Description>
        <List.Description>
          {replyable && (
            <>
              <br />
              <Icon name="level up alternate" rotated="clockwise" />
              &nbsp;&nbsp;
              <Input
                placeholder="Comment"
                icon="comment"
                iconPosition="left"
                value={comment}
                onChange={handleComment}
                action={{
                  icon: "send",
                  onClick: replyComment,
                }}
              />
            </>
          )}
        </List.Description>
      </List.Content>
    </>
  );
}

export default Comment;
