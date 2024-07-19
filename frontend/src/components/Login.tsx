import {FormEvent, useEffect, useState} from 'react'
import {AuthDto} from "../model/auth_dto.ts";
import {useNavigate} from "react-router-dom";

function Login() {
    const navigate = useNavigate();
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const [errorLabel, setErrorLabel] = useState("");

    useEffect(() => {
        if (sessionStorage.getItem("token") != null) {
            navigate("/home");
        }
    }, [navigate]);

    function onLogin(event: FormEvent) {
        event.preventDefault();
        if (!userName || !password) {
            setErrorLabel("Username and/or password is empty!");
            return;
        }
        const authDto: AuthDto = {
            userName: userName,
            password: password
        };
        const loginRequest = async () => {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(authDto)
            });
            if (response.status.toString().startsWith("4")) {
                setErrorLabel("These credentials are wrong or do not exist!");
                return Promise.reject();
            }
            if (response.status.toString().startsWith("5")) {
                setErrorLabel("An error occurred. Please try again later!");
                return Promise.reject();
            }

            return Promise.resolve(response);
        };
        loginRequest()
            .then(response => response.text())
            .then(data => {
                if (data) {
                    sessionStorage.setItem("token", data);
                    navigate("/home");
                } else {
                    setErrorLabel("These credentials are wrong or do not exist!");
                }
            });
    }

    return (
        <>
            <form className="text-center" onSubmit={onLogin}>
                <h1 className="mb-6">Login</h1>
                <div className="mb-6">
                    <input type="text" className="w-64 h-12" value={userName} placeholder="Username"
                           onChange={(e) => setUserName(e.target.value)}/>
                </div>
                <div className="mb-6">
                    <input type="password" className="w-64 h-12" value={password} placeholder={"Password"}
                           onChange={(e) => setPassword(e.target.value)}/>
                </div>
                <div className={'text-red-600 ' + (errorLabel !== '' ? 'mb-6' : '')}>
                    <span className="inline-block w-64">{errorLabel}</span>
                </div>
                <button type="submit" className="w-64 h-12">Login</button>
            </form>
        </>
    )
}

export default Login
