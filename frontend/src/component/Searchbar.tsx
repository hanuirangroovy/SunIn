import Link from "next/link";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { Dropdown, Input, Search } from "semantic-ui-react";
import allAxios from "../lib/allAxios";

export default function Searchbar() {
  const [feedList, setFeedList]: any = useState([]);
  const router = useRouter();

  function getSearchValue(e: any) {
    // if (e.target.value[0] === '#'){
    allAxios
      .get(`/feed/search`, {
        params: {
          content: e.target.value,
        },
      })
      .then(({ data }) => {
        if (data.feed_dtos) {
          let newFeedList: any = [];
          data.feed_dtos.map((feed: any) => {
            newFeedList.push({
              key: feed.created_date,
              title: feed.content.slice(0, 20),
              id: feed.id,
            });
          });
          setFeedList(newFeedList);
        }
      });
    //   } else {
    //   allAxios
    //   .get(`/feed/latest`, {
    //     params: {
    //       size: 100,
    //     },
    //   })
    //   .then(({ data }) => {
    //     let newList: any = []
    //     data.map((feed: any) => {
    //       if (feed.content.indexOf(e.target.value) != '-1'){
    //         newList.push({key: feed.createdDate, title: feed.content.slice(0, 20), id: feed.id})
    //       }
    //     })
    //     setFeedList(newList)
    //   });
    // }
  }
  function goReloading() {
    router.reload();
  }

  function getSelectedValue(e: any) {
    if (e.type === "click") {
      feedList.filter((feed: any) => {
        if (feed.title === e.target.outerText) {
          router.push(`/feed/${feed.id}`);
          setTimeout(goReloading, 1500);
        }
      });
    } else {
      feedList.filter((feed: any) => {
        if (feed.title === e.target.value) {
          router.push(`/feed/${feed.id}`);
          setTimeout(goReloading, 1500);
        }
      });
    }
  }

  return (
    <>
      <Search
        onResultSelect={getSelectedValue}
        results={feedList}
        onKeyUp={getSearchValue}
        placeholder="#tag / 피드내용 입력"
        name="searchInput"
      />
    </>
  );
}
