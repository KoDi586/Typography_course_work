import { Container, Table } from "react-bootstrap"
import PHeader from "../components/PHeader";
import PFooter from "../components/PFooter";
import { useEffect, useState } from "react";
import axios from "axios";

import ip from "../ApiConfig";

const Providers = () => {

    const [ providers, setProviders ] = useState([])

    useEffect(
        () => {
            axios.get("http://"+ ip +":8089/api/provider/all").then(response => {
                setProviders(response.data.children)
            }).catch(error => {
                console.log(error);
            });
        }, []
    )

    // const prov = [
    //     {
    //       "id": 1,
    //       "name": "Поставщик 1",
    //       "material": "Материал 1",
    //       "contactInfo": "Контактная информация 1"
    //     },
    //     {
    //       "id": 2,
    //       "name": "Поставщик 2",
    //       "material": "Материал 2",
    //       "contactInfo": "Контактная информация 2"
    //     }
    //   ]

    return (
        <>
            <PHeader/>
            <Container style={{marginTop: '75px'}}>
                <h1 style={{textAlign: 'center'}}>Поставщики</h1>
                <Table>
                    <thead>
                        <tr style={{textAlign: 'center'}}>
                            <th>№</th>
                            <th>Поставщик</th>
                            <th>Материал</th>
                            <th>Контактная информация</th>
                        </tr>
                        
                    </thead>
                    <tbody>
                        {/* Поменять на providers - prov */}
                        {providers.map((provider) => (
                            <tr style={{textAlign: 'center'}}>
                                <td>{provider.id}</td>
                                <td>{provider.name}</td>
                                <td>{provider.material}</td>
                                <td>{provider.contactInfo}</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </Container>
            <PFooter/>
        </>
        
    )
}

export default Providers;   