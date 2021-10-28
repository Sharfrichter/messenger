import React from "react";
import ConversationService from "../service/ConversationService";

export class MainPage extends React.Component {
    serverUrl = "http://localhost:8080/conversations"

    constructor(props) {
        super(props);
        this.state = {username: '', password: ''}

    }

    openConversation(id) {
        alert(id);
    }

    render() {
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