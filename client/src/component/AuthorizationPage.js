import React from "react";
import {Base64EncoderService} from '../service/Base64EncoderService'
import CredentialsStorage from "../storage/CredentialsStorage";
import {MainPage} from "./MainPage";
import {RegistrationPage} from "./RegistrationPage";
import styles from '../styles/authorization.css'

let serverUrl = "http://localhost:8080/conversations"

export class AuthorizationPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {authorized: false, page: null}

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
        this.setState(() => this.setState(() => ({page: RegistrationPage})))
    }

    render() {
        if (this.state.page !== null) {
            return <this.state.page/>
        }
        if (this.state.authorized) {
            return <MainPage/>
        }
        return (
            <div className='loginpanel'>
                <div className="txt">
                    <input name="username" id="username" type="text" placeholder="Пользователь"/>
                    <label htmlFor="username" className="entypo-user"></label>
                </div>
                <div className="txt">
                    <input id="password" name="password" type="password" placeholder="Пароль"/>
                    <label htmlFor="password" className="entypo-lock"></label>
                </div>
                <div className="buttons">
                    <input type="button" value="Войти" onClick={this.authorize}/>
                    <span>
      <a href="javascript:void(0)" className="entypo-user-add register" onClick={this.register}>Зарегистрироваться</a>
                    </span>
                </div>

            </div>
        );
    }
}