import {FormEvent, useEffect, useState} from 'react'
import {AuthDto} from "../model/auth_dto.ts";
import {useNavigate} from "react-router-dom";
import LanguageDropdown from "./LanguageDropdown.tsx";
import {useTranslation} from "react-i18next";

function Login() {
    const {t} = useTranslation();
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
            setErrorLabel(t('empty_password_error'));
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
                setErrorLabel(t('wrong_credentials_error'));
                return Promise.reject();
            }
            if (response.status.toString().startsWith("5")) {
                setErrorLabel(t('unknown_error'));
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
                    setErrorLabel(t('wrong_credentials_error'));
                }
            });
    }

    return (
        <>
            <form className="text-center" onSubmit={onLogin}>
                <h1 className="mb-6">{t('login_header_label')}</h1>
                <LanguageDropdown isAbsolute={true}></LanguageDropdown>
                <div className="mb-6">
                    <input type="text" className="w-64 h-12" value={userName} placeholder={t('user_name_label')}
                           onChange={(e) => setUserName(e.target.value)}/>
                </div>
                <div className="mb-6">
                    <input type="password" className="w-64 h-12" value={password} placeholder={t('password_label')}
                           onChange={(e) => setPassword(e.target.value)}/>
                </div>
                <div className={'text-red-600 ' + (errorLabel !== '' ? 'mb-6' : '')}>
                    <span className="inline-block w-64">{errorLabel}</span>
                </div>
                <button type="submit" className="w-64 h-12">{t('login_label')}</button>
            </form>
        </>
    )
}

export default Login
