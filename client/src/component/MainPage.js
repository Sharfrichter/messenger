import React from "react";
import ConversationService from "../service/ConversationService";

export class MainPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {username: '', password: ''}

    }


    render() {
        let conversations = null;
        alert(ConversationService.getConversations());
        let menuItems = []
        conversations.forEach(conversation => {
            menuItems.push(conversation.name);
        })
        return (
            <div>{menuItems}</div>
        );
    }
}