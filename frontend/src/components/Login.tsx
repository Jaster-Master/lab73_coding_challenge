import {useEffect, useState} from 'react'
import './Login.css'
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

    function onButtonClick() {
        if (!userName || !password) {
            setErrorLabel("UserName and/or password is empty!");
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
                setErrorLabel("This credentials are wrong or do not exist!");
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
                    setErrorLabel("This credentials are wrong or do not exist!");
                }
            });
    }

    return (
        <>
            <div className="login">
                <h1>Login</h1>
                <input type="text" value={userName} placeholder="UserName"
                       onChange={(e) => setUserName(e.target.value)}/>
                <input type="password" value={password} placeholder={"Password"}
                       onChange={(e) => setPassword(e.target.value)}/>
                <div className="text-red-600">{errorLabel}</div>
                <button onClick={onButtonClick}>Login</button>
            </div>
        </>
    )
}

export default Login
