import { Outlet } from "react-router";
import Header from "./components/Header";
import { ToastContainer } from "react-toastify";

const AppLayout = () => {
  return (
    <div className="mainapp">
      <Header />
      <Outlet />
      <ToastContainer />
    </div>
  );
};

export default AppLayout;
