import { ReactComponent as BellIcon } from "./icons/bell.svg";
import { ReactComponent as BoltIcon } from "./icons/bolt.svg";
import { ReactComponent as CaretIcon } from "./icons/caret.svg";
import { ReactComponent as PlusIcon } from "./icons/plus.svg";
import { ReactComponent as TerminalIcon } from "./icons/terminal.svg";

import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import { Navbar, NavItem, DropdownMenu } from "./Navbar";
import { Home } from "./pages/Home";
import { About } from "./pages/About";
import { Terminal } from "./pages/Terminal";
import { Test } from "./pages/TestGet";
import { Error404 } from "./pages/Error404";

function App() {
  return (
    <BrowserRouter>
      <Navbar>
        <NavItem icon={<PlusIcon />} />
        <NavItem icon={<BellIcon />} />
        <NavItem icon={<TerminalIcon />} href="/terminal" />
        <NavItem icon={<CaretIcon />}>
          <DropdownMenu></DropdownMenu>
        </NavItem>
      </Navbar>

      <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/about" exact component={About} />
        <Route path="/terminal" exact component={Terminal} />
        <Route path="/test" exact component={Test} />
        <Route path="/" component={Error404} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
