import {Outlet} from 'react-router-dom'
import Navbar from "./components/navbar/Navbar";
import {Container} from "react-bootstrap";

export default function Layout() {
  return (
    <>
      <Navbar/>
      <Container className="mt-3 pb-5">
        <Outlet/>
      </Container>
    </>
  )
}