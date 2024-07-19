import {useEffect, useState} from 'react'
import {TrafficSignObservationDto} from "../model/traffic_sign_observation_dto.ts";
import {useNavigate} from "react-router-dom";

function Home() {
    const navigate = useNavigate();
    const [observations, setObservations] = useState([]);
    const [searchFilter, setSearchFilter] = useState("");
    const [filteredObservations, setFilteredObservations] = useState([]);

    function logout() {
        sessionStorage.removeItem('token');
        navigate('/');
    }

    useEffect(() => {
        const newObservations = observations.filter(
            (x: TrafficSignObservationDto) => !searchFilter
                || x.type.toString().includes(searchFilter)
                || x.latitude.toString().includes(searchFilter)
                || x.longitude.toString().includes(searchFilter));
        setFilteredObservations(newObservations);
    }, [observations, searchFilter]);

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
                logout();
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
                <div className="flex items-center justify-between">
                    <h1 className="mb-6 inline">Traffic Sign Observations</h1>
                    <button className="mb-6 right-0" onClick={logout}>Logout</button>
                </div>
                <div className="mb-6">
                    <label className="w-64 h-12">Filter:</label>
                    <input type="text" className="w-64 h-12" value={searchFilter}
                           onChange={(e) => setSearchFilter(e.target.value.toUpperCase())}/>
                </div>
                <table className="table-auto w-full">
                    <thead>
                    <tr>
                        <th>Observation-Type</th>
                        <th>Latitude x Longitude</th>
                        <th>Heading</th>
                        <th>Value</th>
                        <th>Google Maps</th>
                    </tr>
                    </thead>
                    <tbody>
                    {filteredObservations.map((observation: TrafficSignObservationDto) => (
                        <tr>
                            <td>{observation.type}</td>
                            <td>{observation.latitude} x {observation.longitude}</td>
                            <td>{observation.heading}</td>
                            <td>{observation.value}</td>
                            <td>
                                <a target="_blank"
                                   href={'https://www.google.com/maps/search/?api=1&query=' + observation.latitude + ',' + observation.longitude}>Open</a>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </>
    )
}

export default Home
