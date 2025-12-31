import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
const Register = () => {
  const [data, setData] = useState({
    name: "",
    email: "",
    password: "",
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleRegister = async () => {
    const { name, email, password } = data;
    if (!name || !password || !email) {
      toast.error("All fields are required");
      return;
    }

    try {
      const response = await fetch(
        "http://localhost:8080/api/v1/users/register",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(data),
        }
      );
      console.log("succesfully fetched");

      if (!response.ok) {
        throw new Error(msg || "Resgitration falied");
      }
      toast.success("Registration successful");
      navigate("/login");
    } catch (err) {
      toast.error(err.message || "Something went wrong");
    }
  };
  return (
    <div className="register min-h-screen flex items-center justify-center">
      <fieldset className="fieldset bg-base-200 border-base-300 rounded-box w-sm border p-8 text-center">
        <h2 className="text-xl font-semibold mb-6">Create your account</h2>

        <label className="label font-bold text-sm">Name</label>
        <input
          type="text"
          className="input input-bordered input-md w-full mb-3"
          placeholder="Full name"
          name="name"
          value={data.name}
          onChange={handleChange}
        />

        <label className="label font-bold text-sm">Email</label>
        <input
          type="email"
          className="input input-bordered input-md w-full mb-3"
          placeholder="Email"
          name="email"
          value={data.email}
          onChange={handleChange}
        />

        <label className="label font-bold text-sm">Password</label>
        <input
          type="password"
          className="input input-bordered input-md w-full mb-4"
          placeholder="Password"
          name="password"
          value={data.password}
          onChange={handleChange}
        />

        <button
          className="btn btn-neutral btn-md w-full"
          onClick={handleRegister}
        >
          Register
        </button>

        <Link to={"/login"}>
          <p className="text-sm mt-4 text-base-content/70">
            Already have an account?{" "}
            <span className="link link-primary cursor-pointer">Login</span>
          </p>
        </Link>
      </fieldset>
    </div>
  );
};

export default Register;
