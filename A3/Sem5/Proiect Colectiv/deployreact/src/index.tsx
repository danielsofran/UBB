import React from 'react'
import ReactDOM from 'react-dom'
import 'bootstrap/dist/css/bootstrap.min.css';
import {Route, createBrowserRouter, RouterProvider, Navigate, createRoutesFromElements} from "react-router-dom";
import Layout from "./Layout";
import Cause from "./pages/Cause";
import Causes from "./pages/Causes";
import CauseEdit from "./pages/CauseEdit";
import CauseAdd from "./pages/CauseAdd";
import Login from "./pages/Login";
import Register from "./pages/Register";
import AuthProvider from "./components/util/AuthProvider";
import MyCauses from "./pages/MyCauses";
import Profile from "./pages/Profile";

const router = createBrowserRouter(
  createRoutesFromElements(
    [
      <Route index element={<Navigate to="/home" />} />,
      <Route path="/register" element={<Register />}/>,

      <Route path="/" element={<Layout />}>
        <Route path="/home" element={<Causes />} />
        <Route path="/cause/:id" element={<Cause />} />
        <Route path="/cause/:id/edit" element={<CauseEdit />} />
        <Route path="/cause/add" element={<CauseAdd />} />

        <Route path="/login" element={<Login />}/>,
        <Route path="/profile" element={<Profile />} />
        <Route path="/profile/my-causes" element={<MyCauses />} />
      </Route>
    ]
  ), {basename: process.env.PUBLIC_URL}
)

ReactDOM.render(
  <React.StrictMode>
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  </React.StrictMode>,
  document.getElementById('root')
);
