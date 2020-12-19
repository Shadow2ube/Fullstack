import * as React from "react";

import { Link } from "react-router-dom";

export const Home = ({ history }) => {
  return (
    <div>
      <div>Home</div>
      <Link to="/about">About</Link>
      <button
        onClick={() => {
          history.push("/about");
        }}
      >
        Click-eth
      </button>
    </div>
  );
};
