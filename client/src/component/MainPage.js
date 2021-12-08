import React from "react";
import {ConversationsPage} from "./ConversationsPage";
import {FriendsPage} from "./FriendsPage";

export class MainPage extends React.Component {
    serverUrl = "http://localhost:8080/conversations"

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            page: null
        }

    }

    render() {
        if (this.state.page != null) {
            return <this.state.page/>
        }
        return (
            <div className='register'>
                <div>
                    <input type="button" value="Беседы" onClick={() => this.setState(() => ({page: ConversationsPage}))}/>
                    <input type="button" value="Друзья" onClick={() => this.setState(() => ({page: FriendsPage}))}/>
                </div>
            </div>
        )
    }
}