import { Button, Container, Form, Table } from "react-bootstrap"
import PHeader from "../components/PHeader"
import PFooter from "../components/PFooter"

import { useEffect, useState } from "react";
import axios from "axios";

import ip from "../ApiConfig";

const ProductChange = () => {

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


    // const product =  [
    //     {
    //         id: "1001",
    //         name: "Стол из дуба",
    //         price: 15000,
    //         materials: "Материал 1, Материал 2, Материал 3"
    //     },
    //     {
    //         id: "1002",
    //         name: "Шкаф-купе",
    //         price: 25000,
    //         materials: "Материал 4, Материал 5, Материал 6"
    //     }
    //   ]

    const [editMode, setEditMode] = useState(false);
    const [editedProduct, setEditedProduct] = useState(null);

    const handleEdit = (product) => {
        setEditMode(true);
        setEditedProduct(product);
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setEditedProduct(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async () => {
        if (!editedProduct) return;

        // Формируем данные для отправки в нужном формате
        const payload = {
            title: editedProduct.name,
            materials: editedProduct.materials.split(', '), // предполагаем, что materials уже массив
            price_with_materials: Number(editedProduct.price), // преобразуем в число
            id: Number(editedProduct.id)
        };

        try {
            const response = await axios.post("http://"+ ip +":8089/api/product", payload);

            if (response.status === 200) {
                // Обработка успешного ответа
                console.log('Product updated successfully');
                window.location.reload();
            }
        } catch (error) {
            console.error('Error updating product:', error);
        } finally {
            // Выход из режима редактирования
            setEditMode(false);
            setEditedProduct(null); // Сбрасываем редактируемый продукт
        }
    };
    
    
    
    return (
        <>
            <PHeader/>
            <Container style={{marginTop: '75px'}}>
                <h1 style={{textAlign: 'center'}}>Все товары</h1>
                <Table style={{marginTop: '15px'}}>
                    <thead>
                        <tr style={{textAlign: 'center'}}>
                            <th>№</th>
                            <th>Название товара</th>
                            <th>Материалы</th>
                            <th>Стоимость</th>
                            <th>Изменить</th>
                        </tr>
                    </thead>
                    <tbody>
                    {product.map((products) => (
                            <tr style={{textAlign: 'center'}} key={products.id}>
                                <td>{products.id}</td>
                                <td>
                                    {editMode && editedProduct.id === products.id ? (
                                        <input
                                            style={{textAlign: 'center'}}
                                            type="text"
                                            name="name"
                                            value={editedProduct.name}
                                            onChange={handleChange}
                                        />
                                    ) : (
                                        products.name
                                    )}
                                </td>
                                <td>
                                    {products.materials}
                                </td>
                                <td>{editMode && editedProduct.id === products.id ? (
                                        <input
                                            style={{textAlign: 'center'}}
                                            type="number"
                                            name="price"
                                            value={editedProduct.price}
                                            onChange={handleChange}
                                        />
                                    ) : (
                                        `${products.price} р.`
                                    )}
                                </td>
                                <td>
                                    {editMode && editedProduct.id === products.id ? (
                                        <Button variant="light" style={{borderColor: 'black'}} onClick={handleSubmit}>Сохранить</Button>
                                    ) : (
                                        <Button variant="dark" onClick={() => handleEdit(products)}>Изменить</Button>
                                    )}
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </Container>
        </>
    );
};

export default ProductChange;
                    