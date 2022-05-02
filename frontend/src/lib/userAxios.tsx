import Axios from "axios";

const LOCAL_API_URL = "http://localhost:8080";
const SERVER_API_URL = "http://i6c210.p.ssafy.io:8080";
let TOKEN_VALUE: any = null;

if (typeof window !== "undefined") TOKEN_VALUE = localStorage.getItem("token");

const userAxios = Axios.create({
  baseURL: `${SERVER_API_URL}`,
  headers: { Authorization: `Bearer ${TOKEN_VALUE}` },
});

export default userAxios;
