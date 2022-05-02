import Link from "next/link";
import { Grid } from "semantic-ui-react";
import { Feed, Icon, Card, Image } from "semantic-ui-react";

export default function SampleFeedList({ list }: any) {
  function count(list: any) {
    if (!list || list.length == 0) return 0;
    return list.length;
  }

  return (
    <>
      <Grid columns={1}>
        <Grid.Row>
          {list.map((item: any) => (
            <Grid.Column key={item.id}>
              {/* <Feed>
                <Feed.Event>
                  <Feed.Label image='/images/디폴트프로필사진.png' />
                  <Feed.Content>
                    <Feed.Summary>
                      <a>User name: </a> { item.description }
                      <Feed.Date>4 days ago</Feed.Date>
                    </Feed.Summary>
                    <Feed.Extra images>
                      <a>
                        <img src='/images/로고만.png' />
                      </a>
                    </Feed.Extra>
                    <Feed.Meta>
                      <Feed.Like>
                        <Icon name='like' />1 Like
                      </Feed.Like>
                      &nbsp;&nbsp;&nbsp;#tagname
                    </Feed.Meta>
                  </Feed.Content>
                </Feed.Event>
              </Feed> */}
              <Link href={`/feed/${item.id}`}>
                <Card>
                  <Image
                    src={
                      item.file_path[0] ? item.file_path[0] : "/images/로고.png"
                    }
                    alt={item.file_path[0]}
                    height="300px"
                  />
                  <Card.Content>
                    <Image
                      floated="left"
                      size="mini"
                      src={item.user.image}
                      alt={item.user.image}
                      circular
                    />
                    <Card.Header>{item.user.nickName}</Card.Header>
                    <Card.Meta>
                      <span>
                        {item.modified_date.slice(0, 10)}{" "}
                        {item.modified_date.slice(11, 16)}
                      </span>
                    </Card.Meta>
                    <Card.Description>
                      {item.content.slice(0, 30)}
                    </Card.Description>
                  </Card.Content>
                  <Card.Content extra textAlign="center">
                    <span>
                      {item.likes}&nbsp;&nbsp;
                      <Icon name="like" color="red" />
                    </span>
                    <span> </span>
                    <span>
                      {count(item.comments)}&nbsp;&nbsp;
                      <Icon name="comments" color="blue" />
                    </span>
                  </Card.Content>
                </Card>
              </Link>
              <br />
              <br />
            </Grid.Column>
          ))}
        </Grid.Row>
      </Grid>
    </>
  );
}
