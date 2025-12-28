import React, { useEffect } from "react";
import logo from "../utils/learnflow-logo.png";
import { Link } from "react-router";

function Header() {
  return (
    <div className="navbar bg-yellow-50 shadow">
      <div className="flex items-center flex-1 gap-1">
        <img
          src={logo}
          alt="LeanFlow Logo"
          className="h-8 w-auto cursor-pointer"
        ></img>
      </div>

      <div className="dropdown dropdown-end">
        <div tabIndex={0} role="button" className="avatar cursor-pointer">
          <div className="w-10 h-10 rounded-full bg-neutral text-neutral-content flex items-center justify-center">
            <span className="text-sm font-semibold uppercase">S</span>
          </div>
        </div>

        <ul
          tabIndex={0}
          className="menu menu-lg dropdown-content mt-3 z-1 p-2 shadow bg-base-100 rounded-box w-40"
        >
          <li>
            <Link to={"/login"}>Login</Link>
          </li>
          <li>
            <Link to={"/register"}>Register</Link>
          </li>
        </ul>
      </div>
    </div>
  );
}

export default Header;
