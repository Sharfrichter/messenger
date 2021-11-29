import React from "react";
import ConversationService from "../service/ConversationService";
import {ConversationPage} from "./ConversationPage";

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

    openConversation(id) {
        this.setState({page: id});
    }

    render() {
        let id = this.state.page;
        if (this.state.page != null) {
            return (<ConversationPage id={id}/>)
        }
        let conversations = ConversationService.getConversations();

        return (
            conversations.map(conversation =>
                <div>
                    <button onClick={() => this.openConversation(conversation.id)}>{conversation.name}</button>
                </div>
            )
        );
    }
}