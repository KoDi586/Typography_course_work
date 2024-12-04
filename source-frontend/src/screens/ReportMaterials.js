import { Container, Table } from "react-bootstrap";
import PHeader from "../components/PHeader";
import PFooter from "../components/PFooter";
import { useState, useEffect } from "react";

import axios from "axios";

import ip from "../ApiConfig";

const ReportMaterials = () => {

    const [ reportMaterials, setReportMaterials ] = useState([])

    useEffect(
        () => {
            axios.get("http://"+ ip +":8089/api/report/materials").then(response => {
                setReportMaterials(response.data.children)
            }).catch(error => {
                console.log(error);
            });
        }, []
    )

    return (
        <>
            <PHeader/>
            <Container style={{marginTop: '75px'}}>
                <h1 style={{textAlign: 'center'}}>Остатки и обороты материалов</h1>
                <Table style={{textAlign: 'center', marginTop: '15px'}}>
                    <thead>
                        <th>Название</th>
                        <th>Количество на складе</th>
                        <th>Количество потрачено</th>
                        <th>Количество купленно</th>
                        <th>Финальная цена</th>
                    </thead>
                    <tbody>
                        {reportMaterials.map((repMat) => (
                            <tr>
                                <td>{repMat.name}</td>
                                <td>{repMat.countInWarehouse} шт.</td>
                                <td>{repMat.countOfSpent} шт.</td>
                                <td>{repMat.countOfBought} шт.</td>
                                <td>{repMat.totalSpentMoney} р.</td>
                            </tr>
                        ))} 
                    </tbody>
                </Table>
                
            </Container>
        </>
    )
}

export default ReportMaterials;