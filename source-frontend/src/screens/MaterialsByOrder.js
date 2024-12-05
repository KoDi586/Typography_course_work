import { useEffect, useState } from "react";
import ip from "../ApiConfig";
import PHeader from "../components/PHeader";
import axios from "axios";
import { Container, Table } from "react-bootstrap";


const MaterialsByOrder = () => { 

    const [materialsbyorder, setMaterialsByOrder] = useState([])

    useEffect(
        () => {
            axios.get("http://"+ ip +":8089/api/report/materials_by_order").then(response => {
                setMaterialsByOrder(response.data.children)
            }).catch(error => {
                console.log(error);
            });
        }, []
    )

    return (
        <>
            <PHeader/>
            <Container  style={{marginTop: '75px'}}>
                <h1 style={{textAlign: 'center'}}>Расход материалов по заказам</h1>
                <Table>
                    <thead style={{textAlign: 'center'}}>
                        <tr>
                            <th>№</th>
                            <th>Имя</th>
                        </tr>
                    </thead>
                    <tbody>
                        {materialsbyorder.map((order) => (
                                <tr style={{textAlign: 'center'}}> 
                                    <td>{order.orderId}</td>
                                    <td>{order.materialNames}</td>
                                </tr>
                        ))}
                    </tbody>
                </Table>
            </Container>
        </>
    )
};

export default MaterialsByOrder;