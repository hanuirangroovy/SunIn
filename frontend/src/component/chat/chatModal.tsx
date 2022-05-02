import { useState } from "react";
import { Button, Header, Segment, TransitionablePortal } from "semantic-ui-react";
import Chatting from "./chatting";

export default function ChatModal(){

  const [open, setOpen] = useState(false)

  function handleOpen() {
    setOpen(true)
  }

  function handleClose() {
    setOpen(false)
  }

  return (
    <>
      <TransitionablePortal
        closeOnTriggerClick
        onOpen={handleOpen}
        onClose={handleClose}
        openOnTriggerClick
        trigger={
          <Button
            content={open ? '채팅창 닫기' : '채팅창 열기'}
            // negative={open}
            positive={true}
            fluid
          />
        }
      >
        <Segment
          style={{ left: '0%', position: 'fixed', bottom: '0%', zIndex: 1000 }}
        >
          {/* <Header>여기에 채팅장 넣을 예정...</Header> */}
          <Chatting />
        </Segment>
      </TransitionablePortal>
    </>
  );
}