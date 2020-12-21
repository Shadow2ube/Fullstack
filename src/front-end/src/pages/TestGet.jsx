import React, { useState } from "react";
import axios from "axios";

import { Link } from "react-router-dom";

export const Test = () => {
  const [userData, setUserData] = useState(null);

  const url = "http://localhost:8080/users/2";

  const fetchData = async () => {
    const response = await axios.get(url);
    setUserData(response.data);
    console.log(response.data);
  };

  const checkIfEmpty = () => {
    if (userData === null || userData === undefined) {
      return false;
    }
    return true;
  };

  return (
    <div>
      <h1>Info</h1>
      <button onClick={fetchData}>fetch data</button>
      <div>{userData && checkIfEmpty()}</div>
    </div>
  );
};
