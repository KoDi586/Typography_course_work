import { Container, Table } from "react-bootstrap"
import PHeader from "../components/PHeader"
import { useEffect, useState } from "react"

import ip from "../ApiConfig";
import axios from "axios";

const ReportMoney = () => {

    const [ reportmoney, setReportMoney ] = useState([])

    useEffect(
        () => {
            axios.get("http://"+ ip +":8089/api/report/money").then(response => {
                setReportMoney(response.data.children)
            }).catch(error => {
                console.log(error);
            });
        }, []
    )
    
    return (
        <>
            <PHeader/>
            <Container style={{marginTop: '75px'}}>
                <h1 style={{textAlign: 'center'}}>Движения денежных средств</h1>
                <Table>
                    <thead>
                        <tr style={{textAlign: 'center'}}>
                            <th>Наименование</th>
                            <th>Поставщики или клиенты</th>
                            <th>Цена</th>
                            <th>Тип</th>
                        </tr>
                        
                    </thead>
                    <tbody>
                        {reportmoney.map((repmon) => (
                            <tr style={{textAlign: 'justify'}}>
                                <td>{repmon.thing}</td>
                                <td style={{textAlign: 'center'}}>{repmon.people}</td>
                                <td style={{textAlign: 'center'}}>{repmon.price} р.</td>
                                <td style={{textAlign: 'center'}}>{repmon.type}</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </Container>
        </>
    )
}

export default ReportMoney;