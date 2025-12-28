import { Link } from "react-router";

const Register = () => {
  return (
    <div className="register min-h-screen flex items-center justify-center">
      <fieldset className="fieldset bg-base-200 border-base-300 rounded-box w-sm border p-8 text-center">
        <h2 className="text-xl font-semibold mb-6">Create your account</h2>

        <label className="label font-bold text-sm">Name</label>
        <input
          type="text"
          className="input input-bordered input-md w-full mb-3"
          placeholder="Full name"
        />

        <label className="label font-bold text-sm">Email</label>
        <input
          type="email"
          className="input input-bordered input-md w-full mb-3"
          placeholder="Email"
        />

        <label className="label font-bold text-sm">Password</label>
        <input
          type="password"
          className="input input-bordered input-md w-full mb-4"
          placeholder="Password"
        />

        <button className="btn btn-neutral btn-md w-full">Register</button>

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
