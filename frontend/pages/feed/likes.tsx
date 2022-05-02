import { Container, Grid } from "semantic-ui-react";
import { useEffect, useState } from "react";

import InfiniteScroll from "react-infinite-scroll-component";

import FeedList from "src/component/feed/FeedList";
import Menubar from "src/component/Menubar";
import Navbar from "src/component/Navbar";
import allAxios from "src/lib/allAxios";
import styles from "styles/feed.module.css";

function FeedLikes() {
  const [list, setList]: any = useState([]);
  const [page, setPage] = useState(0);

  useEffect(() => {
    setPage(0);
    loadList();
  }, []);

  const loadList = async () => {
    await allAxios
      .get(`/feed/like`, {
        params: {
          size: 5,
          page: page,
        },
      })
      .then(({ data }) => {
        setList([...list, ...data]);
      });
    setPage(page + 1);
  };

  return (
    <>
      <Navbar />
      <Container className={styles.con}>
        <Grid padded stackable>
          <Grid.Column width={4}>
            <Menubar />
          </Grid.Column>
          <Grid.Column width={10}>
            <InfiniteScroll
              style={{ overflow: "hidden" }}
              dataLength={list.length}
              next={loadList}
              hasMore={true}
              loader={undefined}>
              <FeedList list={list} />
            </InfiniteScroll>
          </Grid.Column>
          <Grid.Column width={2} />
        </Grid>
      </Container>
    </>
  );
}

export default FeedLikes;
