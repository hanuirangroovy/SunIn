import { Container, Grid } from "semantic-ui-react";
import { useEffect, useState } from "react";

import FeedList from "src/component/feed/FeedList";
import Menubar from "src/component/Menubar";
import Navbar from "src/component/Navbar";
import allAxios from "src/lib/allAxios";
import userAxios from "src/lib/userAxios";
import styles from "styles/feed.module.css";

function Personal() {
  const [list, setList]: any = useState([]);

  useEffect(() => {
    userAxios.get("/api/v1/users").then(({ data }) => {
      allAxios
        .get(`/feed/followerLatest/${data.body.user.user_seq}`)
        .then(({ data }) => {
          setList(data);
        });
    });
  }, []);

  return (
    <>
      <Navbar />
      <Container className={styles.con}>
        <Grid padded stackable>
          <Grid.Column width={4}>
            <Menubar />
          </Grid.Column>
          <Grid.Column width={10}>
            <FeedList list={list} />
          </Grid.Column>
          <Grid.Column width={2} />
        </Grid>
      </Container>
    </>
  );
}

export default Personal;
