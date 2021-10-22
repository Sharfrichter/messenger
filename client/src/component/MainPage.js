import React from "react";
import ConversationService from "../service/ConversationService";

export class MainPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {username: '', password: ''}

    }


    render() {
        let conversationsJSON = ConversationService.getConversations();
        alert(conversationsJSON);
        let conversations = JSON.parse(conversationsJSON);
        let menuItems = []
        conversations.forEach(conversation => {
            menuItems.push(conversation.name);
        })
        return (
            <div>{menuItems}</div>
        );
    }
}