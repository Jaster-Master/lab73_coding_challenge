import './Home.css'
import {useCallback, useEffect, useState} from 'react'
import {TrafficSignObservationDto} from "../model/traffic_sign_observation_dto.ts";
import {useNavigate} from "react-router-dom";
import LanguageDropdown from "./LanguageDropdown.tsx";
import {useTranslation} from "react-i18next";

function Home() {
    const {t} = useTranslation();
    const navigate = useNavigate();
    const [observations, setObservations] = useState([]);
    const [searchFilter, setSearchFilter] = useState("");
    const [filteredObservations, setFilteredObservations] = useState([]);

    const logout = useCallback(() => {
        sessionStorage.removeItem('token');
        navigate('/');
    }, [navigate]);

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
    }, [logout, navigate]);

    return (
        <>
            <div className="App">
                <div className="flex items-center justify-between">
                    <h1 className="mb-6 inline">{t('observations_header_label')}</h1>
                    <span>
                        <button className="me-4 mb-6 right-0" onClick={logout}>{t('logout_label')}</button>
                        <LanguageDropdown isAbsolute={false}></LanguageDropdown>
                    </span>
                </div>
                <div className="mb-6">
                    <label className="w-64 h-12">{t('filter_label')}</label>
                    <input type="text" className="w-64 h-12" value={searchFilter}
                           onChange={(e) => setSearchFilter(e.target.value.toUpperCase())}/>
                </div>
                <table className="table-auto w-full">
                    <thead>
                    <tr>
                        <th>{t('observation_type_label')}</th>
                        <th>{t('latitude_longitude_label')}</th>
                        <th>{t('heading_label')}</th>
                        <th>{t('value_label')}</th>
                        <th>{t('google_maps_label')}</th>
                    </tr>
                    </thead>
                    <tbody>
                    {filteredObservations.length > 0 ? (filteredObservations.map((observation: TrafficSignObservationDto) => (
                        <tr>
                            <td>{observation.type}</td>
                            <td>{observation.latitude} x {observation.longitude}</td>
                            <td>{observation.heading}</td>
                            <td>{observation.value}</td>
                            <td>
                                <a target="_blank"
                                   href={'https://www.google.com/maps/search/?api=1&query=' + observation.latitude + ',' + observation.longitude}>{t('open_label')}</a>
                            </td>
                        </tr>
                    ))) : (<div
                        className="absolute no-data-label-width h-2/3 text-center flex justify-center items-center">
                        {t('no_data_label')}
                    </div>)}
                    </tbody>
                </table>
            </div>
        </>
    )
}

export default Home
