import { Button, Container } from 'react-bootstrap'
import PHeader from '../components/PHeader';
import PFooter from '../components/PFooter';

import { Link } from 'react-router-dom';

const MainPage = () => {
    return (
        <> 
            <PHeader/>
            <Container style={{marginTop: '75px', display: 'grid', gap: '12px'}} >
                <h1 style={{textAlign:'center'}}>Главное меню</h1>
                <Button variant='dark' as={Link} to='/neworder'>Оформление заказа</Button>
                <Button variant='dark' as={Link} to="/providers">Поставщики</Button>
                <Button variant='dark' as={Link} to="/productchange">Товары</Button>
                <Button variant='dark' as={Link} to="/productadd">Добавить товары</Button>
                <Button variant='dark' as={Link} to="/reportmaterials">Остатки и обороты материалов</Button>
                <Button variant='dark' as={Link} to="/reportmoney">Движения денежных средств</Button>
                <Button variant='dark' as={Link} to="/allmaterials">Прайс лист на материалы</Button>
                <Button variant='dark' as={Link} to="/allorder">Выполненные заказы</Button>
                <Button variant='dark' as={Link} to="/materialsbyorder">Расход материалов по заказам</Button>
                <Button variant='dark' as={Link} to="/allproduct">Прайс лист на продукцию</Button>
            </Container>
            <PFooter/>
        </>
    )
}

export default MainPage;