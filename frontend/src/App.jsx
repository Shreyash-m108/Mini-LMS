import Header from "./components/Header";
import { createBrowserRouter } from "react-router";

const AppLayout = () => {
  return (
    <div className="mainapp">
      <Header></Header>
    </div>
  );
};

const router = createBrowserRouter([
  {
    path: "/",
    element: <body></body>,
  },
]);

export default AppLayout;
