import {useEffect, useState} from 'react'
import './Home.css'
import {TrafficSignObservationDto} from "../model/traffic_sign_observation_dto.ts";
import {useNavigate} from "react-router-dom";

function Home() {
    const navigate = useNavigate();
    const [observations, setObservations] = useState([]);

    useEffect(() => {
        const token = sessionStorage.getItem("token");
        if (token == null) {
            navigate("/");
            return;
        }
        const observationsRequest = async () => {
            const response = await fetch('http://localhost:8080/api/traffic-sign-observations', {
                headers: {"Authorization": "Bearer " + token}
            });
            if (response.status.toString().startsWith('4')) {
                sessionStorage.removeItem('token');
                navigate('/');
                return Promise.reject();
            }

            return Promise.resolve(response);
        };
        observationsRequest()
            .then(response => response.json())
            .then(data => setObservations(data));
    }, [navigate]);

    return (
        <>
            <div className="App">
                <h1>Traffic Sign Observations</h1>
                <ul>
                    {observations.map((observation: TrafficSignObservationDto) => (
                        <li key={observation.id}>
                            {observation.type} at ({observation.latitude}, {observation.longitude}) with
                            heading: {observation.heading} {observation.value != null ? 'and value: ' + observation.value : ''}
                            <a target="_blank" href={'https://www.google.com/maps/search/?api=1&query=' + observation.latitude + ',' + observation.longitude}>Click
                                here</a>
                        </li>
                    ))}
                </ul>
            </div>
        </>
    )
}

export default Home
