import {useEffect, useState} from 'react'
import './App.css'
import {TrafficSignObservationDto} from "./model/traffic_sign_observation_dto.ts";

function App() {
    const [observations, setObservations] = useState([]);

    useEffect(() => {
        fetch('/api/observations')
            .then(response => response.json())
            .then(data => setObservations(data));
    }, []);

    return (
        <>
            <div className="App">
                <h1>Traffic Sign Observations</h1>
                <ul>
                    {observations.map((observation: TrafficSignObservationDto) => (
                        <li key={observation.id}>
                            {observation.type} at ({observation.latitude}, {observation.longitude}) with
                            value: {observation.value}
                        </li>
                    ))}
                </ul>
            </div>
        </>
    )
}

export default App
