import * as React from "react";

import { Link } from "react-router-dom";

export const Home = ({ history }) => {
  return (
    <div>
      <div>Home</div>
      <Link to="/test">Test</Link>
    </div>
  );
};
