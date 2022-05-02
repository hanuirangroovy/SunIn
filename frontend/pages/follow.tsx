import type { NextPage } from "next";
// import Head from 'next/head'
// import Image from 'next/image'
import Menuvar from "../src/component/Menubar";
import Navbar from "../src/component/Navbar";
// import styles from '../styles/Home.module.css'
import { Grid, Header } from "semantic-ui-react";
import Axios from "axios";
import { useEffect, useState } from "react";
import SampleFeedList from "../src/component/SampleFeedList";
import allAxios from "src/lib/allAxios";
// import Searchbar from '../src/component/Searchbar'

const Home: NextPage = () => {
  const [list, setList]: any = useState([]);

  // const API_URL = "http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline";

  // function getData() {
  //   Axios.get(API_URL)
  //   .then(res => {
  //     setList(res.data)
  //   })
  // }

  // useEffect(()=>{
  //   getData();
  // }, []);

  useEffect(() => {
    loadFeed();
  }, []);

  function loadFeed() {
    allAxios
      .get(`/feed/latest`, {
        params: {
          size: 3,
          page: 1,
        },
      })
      .then(({ data }) => {
        setList([]);
        setList([...list, ...data]);
      });
  }

  return (
    <>
      <Navbar />
      {/* <Searchbar /> */}
      <Grid columns={4} padded stackable>
        <Grid.Column>
          <Menuvar />
        </Grid.Column>
        <Grid.Column textAlign="center">
          <Header as="h1">Flower</Header>
          <SampleFeedList list={list} />
        </Grid.Column>
        <Grid.Column textAlign="center">
          <Header as="h1">Flowing</Header>
          <SampleFeedList list={list} />
        </Grid.Column>
        <Grid.Column textAlign="center">
          <Header as="h1">Personal</Header>
          <SampleFeedList list={list} />
        </Grid.Column>
      </Grid>
    </>
  );
};

export default Home;
