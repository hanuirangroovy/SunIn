import {
  Button,
  Form,
  Input,
  Grid,
  TextArea,
  Label,
  Container,
} from "semantic-ui-react";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";

import Navbar from "src/component/Navbar";
import userAxios from "src/lib/userAxios";
import SwiperMedia from "src/component/Swiper";

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/scrollbar";

import styles from "styles/CreateFeed.module.css";
import allAxios from "src/lib/allAxios";
import ByteLength from "src/component/ByteLength";

function Modifyfeed({ feedid }: any) {
  const router = useRouter();

  const [feed, setFeed]: any = useState({
    userSeq: 0,
    content: "",
    filePath: [],
    hashtags: [],
  });

  const [user, setUser]: any = useState({
    user_seq: -1,
    user_id: "",
    username: "",
    email: "",
    user_nickname: "",
    profile_image_url: "",
    provider_type: "",
    role_type: "",
    sunin_days: 0,
    created_at: "",
    modified_at: "",
    follower: [],
    address: "",
    introduction: "",
    phone_number: "",
  });

  const [media, setMedia]: any = useState([]);
  const [length, setLength] = useState(0); // 글자수 바이트 카운트

  useEffect(() => {
    allAxios.get(`feed/detail/${feedid}`).then(({ data }) => {
      setFeed({
        userSeq: data.user_seq,
        content: data.content,
        filePath: data.file_path,
        hashtags: data.hashtags,
      });

      setLength(ByteLength(data.content));
    });

    userAxios
      .get(`/api/v1/users`)
      .then(({ data }) => {
        setUser(data.body.user);
      })
      .catch(() => {
        alert("잘못된 접근입니다.");
        router.push("/feed");
      });
  }, []);

  const handleOnChange = (e: any) => {
    const { value, name } = e.target;

    setFeed({
      ...feed,
      [name]: value,
    });

    if (e.target.name == "content") {
      setLength(ByteLength(e.target.value));
    }
  };

  const handleKeyPress = (e: any) => {
    if (e.key === " ") {
      const value = e.target.value.split(" ");
      if (!feed.hashtags.includes(value[0]))
        setFeed({
          ...feed,
          hashtags: [...feed.hashtags, value[0]],
        });
      e.target.value = "";
    }
  };

  const onRemove = (e: any, data: any) => {
    const index = feed.hashtags.indexOf(data.content);
    feed.hashtags.splice(index, 1);
    const result = feed.hashtags.filter((word: any) => word != data.content);
    setFeed({ ...feed, hashtags: result });
  };

  const uploadFile = (e: any) => {
    setFeed({
      ...feed,
      filePath: Array.from(e.target.files),
    });

    const arr: any = [];
    Object.entries(e.target.files).map((item: any) => {
      const url = URL.createObjectURL(item[1]);
      arr.push(url);
    });
    setMedia(arr);
  };

  const handleSubmit = (e: any) => {
    e.preventDefault(); // 새로고침 방지

    let flag = false;
    if (feed.content == "") flag = true;
    if (feed.filePath.length == 0) flag = true;

    if (flag) {
      alert("내용을 입력해주세요.");
      return;
    }

    if (length > 200) {
      alert("내용을 확인해주세요.");
      return;
    }

    const body = new FormData();
    body.append("userId", user.user_seq);
    body.append("id", feedid);
    body.append("content", feed.content);

    if (feed.filePath != null) {
      feed.filePath.map((each: any) => {
        body.append("files", each);
      });
    }

    // feed.hashtags.map((each: any) => {
    //   body.append("hashtags", each);
    // });

    {
      feed.hashtags.map((data: any, key: any) => {
        return (
          <div key={key}>
            <Label>data</Label>
          </div>
        );
      });
    }

    allAxios
      .put("/feed", body)
      .then(() => {
        router.push("/feed");
      })
      .catch(() => {
        alert("잠시 후 다시 시도해주세요.");
      });
  };

  return (
    <Container>
      <Navbar />

      <Grid>
        <Grid.Row>
          <Grid.Column width={3} />
          <Grid.Column width={10}>
            <Form className={styles.form}>
              <Form.Field>
                <h3>피드 내용</h3>
                {length > 200 ? (
                  <span style={{ color: "red" }}>{length}</span>
                ) : (
                  <span>{length}</span>
                )}
                <span> / 200</span>
                <TextArea
                  name="content"
                  rows={10}
                  value={feed.content}
                  placeholder="내용을 작성하세요..."
                  onChange={handleOnChange}
                />
              </Form.Field>
              <Form.Field>
                <div>
                  <h3>이미지 선택</h3>
                  <Input
                    type="file"
                    accept="image/*, video/*"
                    onChange={uploadFile}
                    multiple
                  />
                  {media.length > 0 && <SwiperMedia media={media} />}
                </div>
              </Form.Field>
              <Form.Field>
                <Input
                  placeholder="해시태그를 입력하세요"
                  onKeyUp={handleKeyPress}
                />
                {feed.hashtags &&
                  feed.hashtags.map((tag: any, index: any) => {
                    return (
                      <Label
                        name="mail"
                        key={index}
                        content={tag}
                        removeIcon="delete"
                        onRemove={onRemove}
                      />
                    );
                  })}
              </Form.Field>
              <Form.Field>
                <Button control={Button} onClick={() => router.back()}>
                  뒤로가기
                </Button>
                <Button control={Button} onClick={handleSubmit}>
                  저장하기
                </Button>
              </Form.Field>
            </Form>
          </Grid.Column>
          <Grid.Column width={3} />
        </Grid.Row>
      </Grid>
    </Container>
  );
}

export async function getServerSideProps(context: any) {
  const feedid = context.params.feedid;
  return { props: { feedid } };
}

export default Modifyfeed;
