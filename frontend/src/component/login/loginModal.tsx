import { useState } from "react";
import { Button, Header, Image, Modal } from "semantic-ui-react";
import styles from "styles/Login.module.css";

export default function LoginModal() {
  const [open, setOpen] = useState(false);
  const REDIRECT_URI = "http://i6c210.p.ssafy.io";

  return (
    <>
      <Modal
        closeIcon
        onClose={() => setOpen(false)}
        onOpen={() => setOpen(true)}
        open={open}
        trigger={<Button>로그인</Button>}>
        <Modal.Header>
          <div>🌞Social Login🌞</div>
        </Modal.Header>
        <Modal.Content image>
          <Image
            size="large"
            src="/images/로고.png"
            className={styles.sunin_display}
          />
          <Modal.Description>
            <div className={styles.logintop}></div>
            <Image
              src="/images/btnG_완성형.png"
              width="500px"
              href={`http://i6c210.p.ssafy.io:8080/oauth2/authorization/naver?redirect_uri=${REDIRECT_URI}/oauth/redirect`}
              alt="네이버로그인"
              className={styles.social_login_button}
            />{" "}
            <br />
            <br />
            <Image
              src="/images/btn_google_signin_light_normal_web@2x.png"
              // size="medium"
              width="500px"
              href={`http://i6c210.p.ssafy.io:8080/oauth2/authorization/google?redirect_uri=${REDIRECT_URI}/oauth/redirect`}
              alt="구글로그인"
              className={styles.social_login_button}
            />{" "}
            <br />
            <br />
            <Image
              src="/images/kakao_login_large_narrow.png"
              // size="medium"
              width="500px"
              href={`http://i6c210.p.ssafy.io:8080/oauth2/authorization/kakao?redirect_uri=${REDIRECT_URI}/oauth/redirect`}
              alt="카카오로그인"
              className={styles.social_login_button}
            />
          </Modal.Description>
        </Modal.Content>
        <Modal.Actions></Modal.Actions>
      </Modal>
    </>
  );
}
