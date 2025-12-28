import { createRoot } from "react-dom/client";
import "/src/index.css";
import AppLayout from "./App";
import { BrowserRouter, Route } from "react-router";

createRoot(document.getElementById("root")).render(
  <BrowserRouter>
    <Route path="/" element={<AppLayout />} />
  </BrowserRouter>
);
