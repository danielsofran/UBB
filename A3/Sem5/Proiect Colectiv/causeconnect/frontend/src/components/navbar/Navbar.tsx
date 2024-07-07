import {Container, Nav, Navbar as RBNavbar, NavDropdown} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
  faCirclePlus,
  faHome,
  faOtter,
  faRightFromBracket,
  faRightToBracket,
  faUser,
  faUserCheck,
  faHandHoldingHeart
} from "@fortawesome/free-solid-svg-icons";
import {useContext} from "react";
import {AuthContext} from "../util/AuthProvider.tsx";
import {useNavigate} from "react-router-dom";


export default function Navbar() {
  const authState = useContext(AuthContext);
  const navigate = useNavigate();

  const logout = async () => {
    authState.setAccount(undefined);
    navigate("/home");
  }

  return (
    <RBNavbar expand="lg" className="bg-body-tertiary" sticky="top">
      <Container>
        <RBNavbar.Brand href="/home">
          <FontAwesomeIcon icon={faOtter} className="pe-2"/>
          Cause Connect
        </RBNavbar.Brand>
        <RBNavbar.Toggle aria-controls="basic-navbar-nav" />
        <RBNavbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/home">
              <FontAwesomeIcon icon={faHome} /> Home
            </Nav.Link>
            {authState.account &&
              <>
                <Nav.Link href="/cause/add">
                    <FontAwesomeIcon icon={faCirclePlus} /> Create
                </Nav.Link>
                <Nav.Link href="/profile/my-causes">
                  <FontAwesomeIcon icon={faHandHoldingHeart} /> My Causes
                </Nav.Link>
              </>
            }
          </Nav>
          <Nav>
            {authState.account ?
              <NavDropdown title="Account">
                <NavDropdown.Item href="/profile">
                  <FontAwesomeIcon icon={faUser} className="pe-1" />
                  Profile
                </NavDropdown.Item>
                <NavDropdown.Item onClick={logout}>
                  <FontAwesomeIcon icon={faRightFromBracket} className="pe-1"/>
                  Logout
                </NavDropdown.Item>
              </NavDropdown> :
              <>
                <Nav.Link href="/login">
                  <FontAwesomeIcon icon={faRightToBracket} className="pe-1" />
                  Login
                </Nav.Link>
                <Nav.Link href="/register">
                  <FontAwesomeIcon icon={faUserCheck} className="pe-1"/>
                  Register
                </Nav.Link>
              </>
            }
          </Nav>
        </RBNavbar.Collapse>
      </Container>
    </RBNavbar>
  )
}