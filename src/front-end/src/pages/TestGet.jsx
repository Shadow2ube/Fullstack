import React, { useState } from "react";
import axios from "axios";

import { Link } from "react-router-dom";

export const Test = () => {
  const [userData, setUserData] = useState();

  const url = "http://localhost:8080/users/";

  const fetchData = async () => {
    const response = await axios.get(url);
    setUserData(response.data);
    console.log(response.data);
  };

  return (
    <div>
      <h1>Info</h1>
      <button onClick={fetchData}>fetch data</button>
      <div id="info">{/* <getData userData={userData} /> */}</div>
    </div>
  );
};

// function getData(props) {
//   return (
//     <div>
//       <div>{props.id}</div>
//       <div>{props.name}</div>
//       <div>{props.info}</div>
//     </div>
//   );
// }
