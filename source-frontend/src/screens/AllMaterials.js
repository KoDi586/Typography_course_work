import { Container, Table } from "react-bootstrap"
import PHeader from "../components/PHeader"
import { useEffect, useState } from "react"
import axios from "axios"
import ip from "../ApiConfig"


const AllMaterials = () => { 

    const [materials, setMaterials] = useState([])

    useEffect(
        () => {
            axios.get("http://"+ ip +":8089/api/material/all").then(response => {
                setMaterials(response.data.children)
            }).catch(error => {
                console.log(error);
            });
        }, []
    )

    return (
        <>
            <PHeader/>
            <Container style={{marginTop: '75px'}}>
                <h1 style={{textAlign: 'center'}}>Прайс лист на материалы</h1>
                <Table>
                    <thead>
                        <tr style={{textAlign: 'center'}}>
                            <th>Название</th>
                            <th>Цена</th>
                        </tr>
                    </thead>
                    <tbody>
                        {materials.map((material) => (
                            <tr style={{textAlign: 'center'}}>
                                <td>{material.name}</td>
                                <td>{material.price} р.</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </Container>
        </>
    )
}

export default AllMaterials;