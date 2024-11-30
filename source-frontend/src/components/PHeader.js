import { NavDropdown } from "react-bootstrap";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom';

const PHeader = () => {
    return (
        <>
            <Navbar bg="dark" data-bs-theme="dark" expand="lg" className="bg-body-tertiary" style={{fontWeight: '900', }}>
                <Container>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link as={Link} to="/">Главная</Nav.Link>
                            <Nav.Link as={Link} to="/neworder">Оформление заказа</Nav.Link>
                            <Nav.Link as={Link} to="/">Поставщики</Nav.Link>
                            <NavDropdown title='Товары'>
                                <NavDropdown.Item as={Link} to="/">Добавить товар</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="/">Все товары  </NavDropdown.Item>
                            </NavDropdown>
                            <NavDropdown title='Отчеты' >
                                <NavDropdown.Item as={Link} to="/">Отчет 1</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="/">Отчет 2</NavDropdown.Item>
                            </NavDropdown>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>
    )
};

export default PHeader;