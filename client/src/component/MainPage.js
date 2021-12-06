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
            <div>
                <div>
                    <button onClick={() => this.setState(() => ({page: ConversationsPage}))}>Беседы</button>
                </div>
                <button onClick={() => this.setState(() => ({page: FriendsPage}))}>Друзья</button>
            </div>

    )
    }
}