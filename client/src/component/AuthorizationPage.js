import {InputWithLabel} from './InputWithLabel'
import React from "react";
import {Base64EncoderService} from '../service/Base64EncoderService'
import CredentialsStorage from "../storage/CredentialsStorage";
import {MainPage} from "./MainPage";

let serverUrl = "http://localhost:8080/conversations"

export class AuthorizationPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {authorized: false}

        this.authorize = this.authorize.bind(this);
        this.register = this.register.bind(this);
    }

    async authorize() {
        let username = document.getElementsByName('username')[0].value;
        let password = document.getElementsByName('password')[0].value;
        let credentials = String.raw`Basic ${Base64EncoderService.utf8_to_b64(username + ':' + password)}`

        let response = await fetch(serverUrl, {
            headers: {
                Authorization: credentials
            }
        });

        if (!response.ok) {
            alert("Введенный пользователь или пароль неверны")
        } else {
            CredentialsStorage.setCredentials(username, password);
            this.setState(state => ({authorized: true}));
        }

    }

    register() {
        alert("Create account");
    }

    render() {
        if (this.state.authorized) {
            return <MainPage/>
        }
        return (
            <div>
                <InputWithLabel label='Пользователь' inputType='text' inputName='username'/>
                <InputWithLabel label='Пароль' inputType='password' inputName='password'/>
                <button onClick={this.authorize}>Войти</button>
                <button onClick={this.register}>Зарегистрироваться</button>
            </div>
        );
    }
}