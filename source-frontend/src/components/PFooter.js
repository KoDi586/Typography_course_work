import { Container } from "react-bootstrap"



const PFooter = () => {
    return (
        <Container className="navbar-light bg-dark" style={{border: '1px solid', width: '270px', position: "fixed", left: '0px', top: '55px', padding: '10px', borderBottomRightRadius: '15px'}}>
            <p style={{color:"white"}}>ООО "ПринтМастер"  </p>
            <p style={{color:"white"}}>ИНН: 1234567890  </p>
            <p style={{color:"white"}}>Телефон: +7 (495) 123-45-67  </p>
            <p style={{color:"white"}}>Мессенджеры:</p>
            <p style={{color:"white"}}>WhatsApp: +7 (495) 123-45-67  </p>
            <p style={{color:"white"}}>Telegram: @PrintMasterRU</p>
            <p style={{color:"white"}}>Адрес: г. Москва, ул. Типографская, д. 10, офис 5 </p>
        </Container>
    )
}

export default PFooter;