import "../styles/globals.css";
import "semantic-ui-css/semantic.min.css";

import type { AppProps } from "next/app";
import Head from "next/head";

import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <>
      <Head>
        <title>Sun-In</title>
        <link rel="shortcut icon" href="/images/로고만.png" />
      </Head>
      <ToastContainer />
      <Component {...pageProps} />
    </>
  );
}

export default MyApp;
