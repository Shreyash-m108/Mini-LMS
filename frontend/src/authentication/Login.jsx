import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    if (!email || !password) {
      toast.error("Email and password required");
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/api/v1/users/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
      });

      if (!response.ok) {
        throw new Error("incorrect email or password");
      }

      const user = await response.json();
      console.log("Logged in:", user);
      toast.success("Logged in");
      localStorage.setItem("user", JSON.stringify(user));
      navigate("/");
    } catch (error) {
      toast.error(error.message || "Something went wrong");
    }
  };

  return (
    <div className="login min-h-screen flex items-center justify-center">
      <fieldset className="fieldset bg-base-200 border-base-300 rounded-box w-sm border p-8 text-center">
        <h2 className="text-xl font-semibold mb-6">Login</h2>

        <label className="label font-bold text-sm">Email</label>
        <input
          type="email"
          className="input input-bordered input-md w-full mb-3"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <label className="label font-bold text-sm">Password</label>
        <input
          type="password"
          className="input input-bordered input-md w-full mb-4"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <button className="btn btn-neutral btn-md w-full" onClick={handleLogin}>
          Login
        </button>

        <Link to="/register">
          <p className="text-sm mt-4 text-base-content/70">
            Don't have an account?{" "}
            <span className="link link-primary cursor-pointer">Register</span>
          </p>
        </Link>
      </fieldset>
    </div>
  );
};

export default Login;
