import logo from "../utils/learnflow-logo.png";
import { Link, useNavigate } from "react-router-dom";

function Header() {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user"));

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/");
  };

  return (
    <div className="navbar bg-yellow-50 shadow">
      <div className="flex items-center flex-1 gap-1">
        <Link to="/">
          <img
            src={logo}
            alt="LearnFlow Logo"
            className="h-8 w-auto cursor-pointer"
          />
        </Link>
      </div>

      <div className="dropdown dropdown-end">
        <div tabIndex={0} role="button" className="avatar cursor-pointer">
          <div className="w-10 h-10 rounded-full bg-neutral text-neutral-content flex items-center justify-center">
            <span className="text-sm font-semibold uppercase">
              {user ? user.name?.[0] : "P"}
            </span>
          </div>
        </div>

        {!user ? (
          /* BEFORE LOGIN */
          <ul
            tabIndex={0}
            className="menu dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-50"
          >
            <li>
              <Link to="/login">Login</Link>
            </li>
            <li>
              <Link to="/register">Register</Link>
            </li>
          </ul>
        ) : (
          /* AFTER LOGIN */
          <ul
            tabIndex={0}
            className="menu dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-50"
          >
            <li>
              <Link to="/profile">Profile</Link>
            </li>
            <li>
              <button onClick={handleLogout}>Logout</button>
            </li>
          </ul>
        )}
      </div>
    </div>
  );
}

export default Header;
