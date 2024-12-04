import { useEffect, useState } from "react";
import PHeader from "../components/PHeader"
import axios from "axios";
import { Container, Table } from "react-bootstrap";
import ip from "../ApiConfig";


const ProductAll = () => {

    const [product, setProduct] = useState([])

    useEffect(
        () => {
            axios.get("http://"+ ip +":8089/api/product/all").then(response => {
                setProduct(response.data.children)
            }).catch(error => {
                console.log(error);
            });
        }, []
    )

    return (
        <>
            <PHeader/>
            <Container style={{marginTop: '75px'}}>
                <h1 style={{textAlign: 'center'}}>Прайс лист на продукцию</h1>
                <Table style={{textAlign: 'center', marginTop: '15px'}}>
                    <thead>
                        <tr style={{textAlign: 'center'}}>
                            <th>№</th>
                            <th>Название товара</th>
                            <th>Материалы</th>
                            <th>Стоимость</th>
                        </tr>
                    </thead>
                    <tbody>
                        {product.map((products) => (
                            <tr style={{textAlign: 'center'}}>
                                <td>{products.id}</td>
                                <td>{products.name}</td>
                                <td>{products.materials}</td>
                                <td>{products.price} р.</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
                
            </Container>
        </>
    )
}

export default ProductAll;