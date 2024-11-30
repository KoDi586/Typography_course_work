import { Button, Card, Container, Form } from "react-bootstrap";
import PHeader from "../components/PHeader";
import { useEffect, useState } from "react";

import axios from 'axios'

const OrderForm = () => {

    const [product, setProduct] = useState([])

    useEffect(
        () => {
            axios.get("http://192.168.0.201:8089/api/product/all").then(response => {
                setProduct(response.data.children)
            }).catch(error => {
                console.log(error);
            });
        },[]
    )

    console.info(product)

    const [formData, setFormData] = useState({
        name: '',
        secondName: '',
        email: '',
        phone: '',
        card: '',
        orderItems: []
    });

    const [isSubmitting, setIsSubmitting] = useState(false);
    const [responseMessage, setResponseMessage] = useState('');

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleCheckboxChange = (e) => {
        const { value, checked } = e.target;

        // Обновляем orderItems в зависимости от того,
        // выбран ли продукт или снят с выбора
        setFormData((prev) => {
            const newOrderItems = [...prev.orderItems];
            if (checked) {
                // Если продукт выбран, добавляем его в orderItems
                newOrderItems.push({ product: value, count: 0 });
            } else {
                // Если продукт снят с выбора, удаляем его из orderItems
                return {
                    ...prev,
                    orderItems: newOrderItems.filter((item) => item.product !== value),
                };
            }
            return { ...prev, orderItems: newOrderItems };
        });
    };

    const handleQuantityChange = (e, productId) => {
        const { value } = e.target;
        const count = parseInt(value) || 0; // Если значение не число, выставим 0

        setFormData((prev) => {
            const newOrderItems = prev.orderItems.map((item) => 
                item.product === productId ? { ...item, count } : item
            );
            return { ...prev, orderItems: newOrderItems };
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setResponseMessage('');
        console.info({formData})

        const payload = {
            client: {
                name: formData.name,
                secondName: formData.secondName,
                email: formData.email,
                phone: formData.phone,
                card: formData.card,
            },
            orderItems: formData.orderItems,
        };

        try {
            const response = await axios.post('http://192.168.0.201:8089/api/product', payload);
            setResponseMessage(`Успех! Ответ: ${response.data.message}`);
        } catch (error) {
            if (error.response) {
                setResponseMessage(`Ошибка: ${error.response.data.message}`);
            } else {
                setResponseMessage(`Ошибка: ${error.message}`);
            }
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <>
            <PHeader/>
            <Container style={{marginTop: '25px', marginBottom: '25px'}} >
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label>Имя</Form.Label>
                        <Form.Control type="text" placeholder="Введите имя" name="name" onChange={handleInputChange}/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Фамилия</Form.Label>
                        <Form.Control type="text" placeholder="Введите фамилию" name="secondName" onChange={handleInputChange}/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Почта</Form.Label>
                        <Form.Control type="email" placeholder="Введите почту" name="email" onChange={handleInputChange}/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Телефон</Form.Label>
                        <Form.Control type="text" placeholder="Введите номер телефона" name="phone" onChange={handleInputChange}/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Номер карты</Form.Label>
                        <Form.Control type="text" placeholder="Введите номер банковской карты" name="card" onChange={handleInputChange}/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicCheckbox">
                        <Form.Label className="d-flex flex-wrap justify-content-center">Виды продуктов</Form.Label>
                        <Container style={{gap:"30px"}} className="d-flex flex-wrap justify-content-center">
                            {product.map((product) => (
                                <Card key={product.id} style={{ width: '15rem', padding: '10px' }}>
                                    <Form.Check
                                        type="checkbox"
                                        label={product.name}
                                        value={product.id}
                                        onChange={handleCheckboxChange}
                                    />
                                    <Form.Control
                                        type="text"
                                        placeholder="Введите количество"
                                        onChange={(e) => handleQuantityChange(e, product.id)}
                                        disabled={!formData.orderItems.some(item => item.product === product.id)}
                                        
                                    />
                                    <Form.Label>Цена за еденицу товара: {product.price}</Form.Label>
                                </Card>
                            ))}
                        </Container>
                    </Form.Group>
                    <Container  className="d-flex flex-wrap justify-content-center">
                    <Button variant="dark" type="submit">
                         Оформить заказ
                    </Button>
                    </Container>
                </Form>
            </Container>
        </>
    )
};

export default OrderForm;