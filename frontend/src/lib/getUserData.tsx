// import axios from "axios"

// export default function GetUserData(){
//   var userDatas: any = null

//   if (typeof window !== "undefined") {
//     const token = localStorage.getItem('token')

   
//       axios.get(`http://i6c210.p.ssafy.io:8080/api/v1/users`, {
//         headers: { Authorization: `Bearer ${token}` },
//       })
//       .then((res: any) => {
//         // console.log(res.data.body.user);
//         userDatas = res.data.body.user
//         console.log(userDatas)
//       });
    
//     console.log('111',userDatas)

  
//   return userDatas
 
//   }
// }

import axios from "axios";

export default function GetUserData() {
  var userDatas: any = null;

  if (typeof window !== "undefined") {
    const token = localStorage.getItem("token");
    axios.get(`http://i6c210.p.ssafy.io:8080/api/v1/users`, {
            headers: { Authorization: `Bearer ${token}` }
    }).then((data) => {
        return data;
    }).catch(() => {
        return -1;
    })
  }
}
