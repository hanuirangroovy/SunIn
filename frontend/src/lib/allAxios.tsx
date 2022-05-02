import Axios from "axios";

const LOCAL_API_URL = "http://localhost:8080";
const SERVER_API_URL = "http://i6c210.p.ssafy.io:8080";

const allAxios = Axios.create({
  baseURL: `${SERVER_API_URL}`,
});

export default allAxios;
