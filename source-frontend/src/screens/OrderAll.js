import { Card, CardBody, CardHeader, Container, Table } from "react-bootstrap";
import PHeader from "../components/PHeader";
import ip from "../ApiConfig";
import { useEffect, useState } from "react";
import axios from "axios";


const OrderAll = () => { 

    const [order, setOrder] = useState([])

    useEffect(
        () => {
            axios.get("http://"+ ip +":8089/api/order/all").then(response => {
                setOrder(response.data.children)
            }).catch(error => {
                console.log(error);
            });
        }, []
    )

    return (
        <>
            <PHeader/>
            <Container  style={{marginTop: '75px'}}>
                <h1 style={{textAlign: 'center'}}>Выполненные заказы</h1>
                <Table>
                    <thead>
                        <tr>
                            <th style={{textAlign: 'center'}}>№</th>
                            <th style={{textAlign: 'center'}}>Имя</th>
                            <th style={{textAlign: 'center'}}>Фамилия</th>
                            <th style={{textAlign: 'center'}}>Почта</th>
                            <th style={{textAlign: 'center'}}>Номер телефона</th>
                            <th style={{textAlign: 'center'}}>Номер карты</th>
                            <th style={{textAlign: 'center'}}>Товары</th>
                            <th style={{textAlign: 'center'}}>Финальная цена</th>
                        </tr>
                    </thead>
                    <tbody>
                        {order.map((orders) => (
                                <tr> 
                                    <td style={{textAlign: 'center'}}>{orders.orderId}</td>
                                    <td style={{textAlign: 'center'}}>{orders.client.name}</td>
                                    <td style={{textAlign: 'center'}}>{orders.client.secondName}</td>
                                    <td style={{textAlign: 'center'}}>{orders.client.email}</td>
                                    <td style={{textAlign: 'center'}}>{orders.client.phone}</td>
                                    <td style={{textAlign: 'center'}}>{orders.client.card}</td>
                                    <td>
                                        {orders.orderItems.map((orderItem) => (
                                            <Card>
                                                <CardHeader>{orderItem.productName}</CardHeader>
                                                <CardBody style={{textAlign: 'center'}}>{orderItem.count} шт.</CardBody>
                                            </Card>
                                        ))}
                                    </td>
                                    <td  style={{textAlign: 'center'}}>{orders.totalPrice} р.</td>
                                </tr>
                        ))}
                    </tbody>
                </Table>
            </Container>
        </>
    )
};

export default OrderAll;