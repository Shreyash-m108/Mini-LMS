import { createRoot } from "react-dom/client";
import "/src/index.css";
import AppLayout from "./App";
import { RouterProvider } from "react-router";
import { createBrowserRouter } from "react-router";
import Body from "./components/Body";
import About from "./components/About";
import Login from "./authentication/Login";
import Register from "./authentication/Register";

const router = createBrowserRouter([
  {
    path: "/",
    element: <AppLayout></AppLayout>,
    children: [
      {
        index: true,
        element: <Body />,
      },
      {
        path: "/about",
        element: <About />,
      },
      {
        path: "/login",
        element: <Login />,
      },
      {
        path: "/register",
        element: <Register />,
      },
    ],
  },
]);

createRoot(document.getElementById("root")).render(
  <RouterProvider router={router} />
);
