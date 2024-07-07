import {Button, Container, Form, Nav, Navbar, NavDropdown, Offcanvas} from "react-bootstrap";
import {Link, Outlet} from "react-router-dom";
import React from "react";
import {createBootstrapComponent} from "react-bootstrap/ThemeProvider";
import {handleLogout} from "../data/DjangoCsrfToken";

export const MyNavbar : React.FC<{sef:boolean}> = ({sef}) => {
    let expand = 'md';
    return (
        <>
            <Navbar sticky="top" key={expand} bg="light" expand={expand} className="mb-3">
                <Container fluid>
                    <Navbar.Brand href="#">Meniu</Navbar.Brand>
                    <Navbar.Toggle aria-controls={`offcanvasNavbar-expand-${expand}`} />
                    <Navbar.Offcanvas
                        id={`offcanvasNavbar-expand-${expand}`}
                        aria-labelledby={`offcanvasNavbarLabel-expand-${expand}`}
                        placement="start"
                    >
                        <Offcanvas.Header closeButton>
                            <Offcanvas.Title id={`offcanvasNavbarLabel-expand-${expand}`}>
                                Meniu
                            </Offcanvas.Title>
                        </Offcanvas.Header>
                        <Offcanvas.Body>
                            <Nav className="justify-content-start flex-grow-1 pe-3">
                                {!sef ? <Nav.Link href="/home_angajat">Home</Nav.Link> :
                                    <><Nav.Link href="/sef_angajati">Angajati</Nav.Link>
                                    <Nav.Link href="/sef_sarcini">Sarcini</Nav.Link></>
                                }
                                <Nav.Link onClick={() => handleLogout()}> Logout </Nav.Link>
                            </Nav>
                            {/*<Form className="d-flex">*/}
                            {/*    <Form.Control*/}
                            {/*        type="search"*/}
                            {/*        placeholder="Search"*/}
                            {/*        className="me-2"*/}
                            {/*        aria-label="Search"*/}
                            {/*    />*/}
                            {/*    <Button variant="outline-success">Search</Button>*/}
                            {/*</Form>*/}
                        </Offcanvas.Body>
                    </Navbar.Offcanvas>
                </Container>
            </Navbar>
        </>
    );
}