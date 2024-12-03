import { Container, Table } from "react-bootstrap"
import PHeader from "../components/PHeader";
import PFooter from "../components/PFooter";


const Providers = () => {
    return (
        <>
            <PHeader/>
            <Container>
                <Table>
                    <thead>
                        <tr>
                            <th></th>
                        </tr>
                    </thead>
                </Table>
            </Container>
            <PFooter/>
        </>
        
    )
}

export default Providers;   