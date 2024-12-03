import { Container, Form, Button, Table, Card } from "react-bootstrap"
import PHeader from "../components/PHeader"
import PFooter from "../components/PFooter"

import { useEffect, useState } from "react";

import axios from 'axios'

const ProductAdd = () => {

    const [product, setProduct] = useState([])
    const [materials, setMaterials] = useState([])

    useEffect(
        () => {
            axios.get("http://192.168.12.78:8089/api/product/all").then(response => {
                setProduct(response.data.children)
            }).catch(error => {
                console.log(error);
            });
        }, []
    )

    useEffect(
        () => {
            axios.get("http://192.168.12.78:8089/api/material/all").then(response => {
                setMaterials(response.data.children)
            }).catch(error => {
                console.log(error);
            });
        }, []
    )

    const [formData, setFormData] = useState(
        {
            title: '',
            materials: [],
            price_with_materials: ''
        })

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({...formData, [name]: value})
    }

    const handleCheckboxChange = (e) => {
        const { id, checked } = e.target;

        // Обновляем состояние materials в formData
        setFormData(prev => {
            const newMaterials = checked 
                ? [...prev.materials, id] // Добавляем материал, если чекбокс отмечен
                : prev.materials.filter(material => material !== id); // Удаляем, если чекбокс снят

            return {
                ...prev,
                materials: newMaterials // Обновляем массив materials
            };
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        
            const response = await axios.post('http://192.168.12.78:8089/api/product', formData);

            window.location.reload();
    };
    

    return (
        <>
            <PHeader/>
            <Container style={{marginTop: '75px', marginBottom: '25px'}} >
                <h1 style={{textAlign: 'center'}}>Добавление товара</h1>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label>Введите название нового товара</Form.Label>
                        <Form.Control type="text" placeholder="Введите название новго товара" name="title" onChange={handleInputChange}/>
                    </Form.Group>
                    <Container style={{gap:"20px", marginTop:'25px'}} className="d-flex flex-wrap justify-content-center">
                        {materials.map((material) => (
                            <Card style={{ width: '10rem', padding: '10px' }}>
                                <Form.Check type="checkbox" id={material.name} label={material.name} onChange={handleCheckboxChange}/>
                                <Form.Label>Цена за еденицу материала: {material.price}</Form.Label>
                            </Card>
                        ))}
                    </Container>
                    <Form.Group>
                        <Form.Label>Введите общую сумму</Form.Label>
                        <Form.Control type="text" placeholder="Введите общую сумму" name="price_with_materials" onChange={handleInputChange}/>
                    </Form.Group>
                    <Container  className="d-flex flex-wrap justify-content-center" style={{marginTop: '25px'}}>
                        <Button variant="dark" type="submit">
                            Оформить заказ
                        </Button>
                    </Container>
                </Form>
                <Table style={{marginTop: '15px'}}>
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
            <PFooter/>
        </>
    )
}

export default ProductAdd;