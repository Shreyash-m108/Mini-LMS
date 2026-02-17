import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
const Register = () => {
  const [data, setData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setData({
      ...data,
      [e.target.name]: e.target.value,
    });
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    const { firstName, lastName, email, password } = data;
    if (!firstName || !lastName || !password || !email) {
      toast.error("All fields are required");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/users/register",
        data,
      );

      console.log("user details: ", response.data);
      toast.success("Registration successful");
      navigate("/login");
    } catch (err) {
      console.log("error:", err);
      toast.error(err.response?.data?.message || "something went wrong");
    }
  };
  return (
    <div className="register min-h-screen flex items-center justify-center">
      <form
        onSubmit={handleRegister}
        className="fieldset bg-base-200 border-base-300 rounded-box w-sm border p-8 text-center"
      >
        <h2 className="text-xl font-semibold mb-6">Create your account</h2>

        <label className="label font-bold text-sm">First Name</label>
        <input
          type="text"
          className="input input-bordered input-md w-full mb-3"
          placeholder="first name"
          name="firstName"
          value={data.firstName}
          onChange={handleChange}
        />

        <label className="label font-bold text-sm">Last Name</label>
        <input
          type="text"
          className="input input-bordered input-md w-full mb-3"
          placeholder="last name"
          name="lastName"
          value={data.lastName}
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

        <button type="submit" className="btn btn-neutral btn-md w-full">
          Register
        </button>

        <Link to={"/login"}>
          <p className="text-sm mt-4 text-base-content/70">
            Already have an account?{" "}
            <span className="link link-primary cursor-pointer">Login</span>
          </p>
        </Link>
      </form>
    </div>
  );
};

export default Register;
