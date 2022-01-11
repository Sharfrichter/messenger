import {Base64EncoderService} from "../service/Base64EncoderService";
import CredentialsStorage from "../storage/CredentialsStorage";
import {MainPage} from "./MainPage";
import {InputWithLabel} from "./InputWithLabel";
import React from "react";
import {AuthorizationPage} from "./AuthorizationPage";
import UserService from "../service/UserService";
import {User} from "../model/User"

export class RegistrationPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {registered: false, page: null}

        this.register = this.register.bind(this);
    }

    register() {
        let firstName = document.getElementsByName('firstName')[0].value;
        let lastName = document.getElementsByName('lastName')[0].value;
        let username = document.getElementsByName('username')[0].value;
        let password = document.getElementsByName('password')[0].value;
        let repeatPassword = document.getElementsByName('repeatPassword')[0].value;

        if (password !== repeatPassword) {
            alert("Пароли не совпадают");
        } else {
            let user = new User(firstName, lastName, username, password);
            UserService.register(user);
        }
        this.setState(() =>({registered:true}))

    }

    render() {
        if (this.state.page !== null) {
            return <this.state.page/>
        }
        if (this.state.registered) {
            return <AuthorizationPage/>
        }
        return (
            <div className='register'>
                <div className="txt">
                    <input name="firstName" id="firstName" type="text" placeholder="Имя"/>
                </div>
                <div className="txt">
                    <input id="lastName" name="lastName" type="text" placeholder="Фамилия"/>
                </div>
                <div className="txt">
                    <input id="username" name="username" type="text" placeholder="Пользователь"/>
                </div>
                <div className="txt">
                    <input id="password" name="password" type="password" placeholder="Пароль"/>
                </div>
                <div className="txt">
                    <input id="repeatPassword" name="repeatPassword" type="password" placeholder="Повторите пароль"/>
                </div>
                <div className="buttons">
                    <input type="button" value="Зарегистрироваться" onClick={this.register}/>
                    <span>
      <a href="javascript:void(0)" className="user-add register" onClick={() => this.setState(() => ({registered:true}))}>Назад</a>
                    </span>
                </div>
            </div>
        );
    }
}