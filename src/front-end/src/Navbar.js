import { ReactComponent as ArrowIcon } from "./icons/arrow.svg";
import { ReactComponent as ChevronIcon } from "./icons/chevron.svg";
import { ReactComponent as CogIcon } from "./icons/cog.svg";
import React, { useState } from "react";
import { CSSTransition } from "react-transition-group";

export function Navbar(props) {
  return (
    <nav className="navbar">
      <ul className="navbar-nav">{props.children}</ul>
    </nav>
  );
}
export function NavItem(props) {
  const [open, setOpen] = useState(false);

  return (
    <li className="nav-item">
      <a
        href={props.href}
        className="icon-button"
        onClick={() => setOpen(!open)}
      >
        {props.icon}
      </a>

      {open && props.children}
    </li>
  );
}

export function DropdownMenu() {
  const [activeMenu, setActiveMenu] = useState("main");

  function DropdownItem(props) {
    return (
      <a
        href="#"
        className="menu-item"
        onClick={() => props.goToMenu && setActiveMenu(props.goToMenu)}
      >
        <span className="icon-button">{props.leftIcon}</span>
        {props.children}
        <span className="icon-right">{props.rightIcon}</span>
      </a>
    );
  }

  return (
    <div className="dropdown">
      <CSSTransition
        in={activeMenu === "main"}
        unmountOnExit
        timeout={500}
        classNames="menu-primary"
      >
        <div className="menu">
          <DropdownItem>Profile</DropdownItem>
          <DropdownItem
            leftIcon={<CogIcon />}
            rightIcon={<ChevronIcon />}
            goToMenu="settings"
          >
            Settings
          </DropdownItem>
        </div>
      </CSSTransition>

      <CSSTransition
        in={activeMenu === "settings"}
        unmountOnExit
        timeout={500}
        classNames="menu-secondary"
      >
        <div className="menu">
          <DropdownItem leftIcon={<ArrowIcon />} goToMenu="main" />
          <DropdownItem>Profile</DropdownItem>
        </div>
      </CSSTransition>
    </div>
  );
}
