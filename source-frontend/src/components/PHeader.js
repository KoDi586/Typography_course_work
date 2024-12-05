import { NavDropdown } from "react-bootstrap";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom';

const PHeader = () => {
    return (
        <>
            <Navbar bg="dark" data-bs-theme="dark" expand="lg" className="bg-body-tertiary" style={{fontWeight: '900'}} fixed="top">
                <Container>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link as={Link} to="/">Главная</Nav.Link>
                            <Nav.Link as={Link} to="/neworder">Оформление заказа</Nav.Link>
                            <Nav.Link as={Link} to="/providers">Поставщики</Nav.Link>
                            <NavDropdown title='Товары'>
                                <NavDropdown.Item as={Link} to="/productadd">Добавить товар</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="/productchange">Все товары  </NavDropdown.Item>
                            </NavDropdown>
                            <NavDropdown title='Отчеты' >
                                <NavDropdown.Item as={Link} to="/reportmaterials">Остатки и обороты материалов</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="/reportmoney">Движения денежных средств</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="/allmaterials">Прайс лист на материалы</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="/allproduct">Прайс лист на продукцию</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="/allorder">Выполненные заказы</NavDropdown.Item>
                                <NavDropdown.Item as={Link} to="/materialsbyorder">Расход материалов по заказам</NavDropdown.Item>
                            </NavDropdown>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>
    )
};

export default PHeader;