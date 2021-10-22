import React from "react";
import {AuthorizationPage} from './component/AuthorizationPage'
import {MainPage} from './component/MainPage'
import CredentialsStorage from "./storage/CredentialsStorage";

export class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            page: AuthorizationPage,
            credentials: CredentialsStorage.getCredentials()
        }
    }

    render() {
        if (this.state.credentials === null) {
            return <AuthorizationPage/>
        }
        else {
            return <MainPage/>
        }
    }
}

