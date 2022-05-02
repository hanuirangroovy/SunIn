import Link from 'next/link';
import { Container, Grid } from 'semantic-ui-react'

export default function FeedList2 ({ list }: any) {
  return (
    <>
      <Grid columns={3} stackable>
        <Grid.Row>
          {list.map((item: any) => (
            <Grid.Column key={item.id}>
              <Link href={`/feed/company/${item.id}`}><a>
                <Container textAlign='center'>
                  <img src={item.image_link} alt={item.name} style={{ display:"inline-block" }}/>
                    UserName
                  <p>{ item.description }</p>
                </Container>
              </a></Link>
            </Grid.Column>
          ))}
          
        </Grid.Row>
      </Grid>
    </>
  );
}