import {
  Button,
  Container,
  Grid,
  GridColumn,
  Header,
  Icon,
  Image,
  Progress,
} from "semantic-ui-react";
import Navbar from "../src/component/Navbar";
import Menubar from "../src/component/Menubar";
import styles from "../styles/rank.module.css";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import allAxios from "src/lib/allAxios";

export default function Rank() {
  const router = useRouter();
  const [userInfo, setUserInfo] = useState([]);

  const month = new Date().getMonth() + 1;

  function goProfile(data: any) {
    router.push(`/profile/${data}`);
  }

  useEffect(() => {
    loadFeed();
  }, []);

  function loadFeed() {
    allAxios.get(`/api/v1/users/rank`).then(({ data }) => {
      setUserInfo(data);
    });
  }

  return (
    <>
      <Navbar />
      <Container>
        <Grid>
          <Grid.Row>
            <Grid.Column width={3}>
              <br/>
              <Menubar />
            </Grid.Column>
            <Grid.Column width={1}></Grid.Column>
            <Grid.Column width={9}>
              <br />
              <Header
                as="h1"
                content={`${month}월 Sun in MVP`}
                textAlign="center"
                color="yellow"
              />
              <br />
              <div>
                <Image src="/images/1등.png" size="tiny" inline />
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <Header
                  as="h2"
                  content={userInfo[0] ? userInfo[0]["nick_name"] : "1등"}
                  textAlign="center"
                  className={styles.name}
                />
                &nbsp;&nbsp;&nbsp;
                <Icon name="trophy" />
                <Button
                  onClick={() => {
                    if (userInfo[0]["id"]) {
                      router.push(`/profile/${userInfo[0]["id"]}`);
                    }
                  }}>
                  프로필
                </Button>
                <Progress
                  progress="value"
                  value={userInfo[0] ? userInfo[0]["sunin_days"] : "30"}
                  total={userInfo[0] ? userInfo[0]["sunin_days"] : "50"}
                  success
                />
              </div>
              <br />
              <div>
                <Image src="/images/2등.png" size="tiny" inline />
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <Header
                  as="h2"
                  content={userInfo[1] ? userInfo[1]["nick_name"] : "2등"}
                  textAlign="center"
                  className={styles.name}
                />
                &nbsp;&nbsp;&nbsp;
                <Icon name="trophy" />
                <Button
                  onClick={() => {
                    if (userInfo[1]["id"]) {
                      router.push(`/profile/${userInfo[1]["id"]}`);
                    }
                  }}>
                  프로필
                </Button>
                <Progress
                  progress="value"
                  value={userInfo[1] ? userInfo[1]["sunin_days"] : 20}
                  total={userInfo[0] ? userInfo[0]["sunin_days"] : "50"}
                  success
                />
              </div>
              <br />
              <div>
                <Image src="/images/3등.png" size="tiny" inline />
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <Header
                  as="h2"
                  content={userInfo[2] ? userInfo[2]["nick_name"] : "3등"}
                  textAlign="center"
                  className={styles.name}
                />
                &nbsp;&nbsp;&nbsp;
                <Icon name="trophy" />
                <Button
                  onClick={() => {
                    if (userInfo[2]["id"]) {
                      router.push(`/profile/${userInfo[2]["id"]}`);
                    }
                  }}>
                  프로필
                </Button>
                <Progress
                  progress="value"
                  value={userInfo[2] ? userInfo[2]["sunin_days"] : 10}
                  total={userInfo[0] ? userInfo[0]["sunin_days"] : "50"}
                  success
                />
              </div>
            </Grid.Column>
            <GridColumn width={3}>
              <br />
              <h2>안내</h2>
              <p>* 매달 랭킹순위가 변경됩니다</p>
            </GridColumn>
          </Grid.Row>
        </Grid>
      </Container>
    </>
  );
}
