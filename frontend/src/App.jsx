import { Outlet } from "react-router";
import Header from "./components/Header";

const AppLayout = () => {
  return (
    <div className="mainapp">
      <Header />
      <Outlet />
    </div>
  );
};

export default AppLayout;
