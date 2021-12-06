import React from "react";
import ConversationService from "../service/ConversationService";
import {MainPage} from "./MainPage";
import {ConversationsPage} from "./ConversationsPage";

export class ConversationPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            id: props.id,
            messages: ConversationService.getMessages(props.id),
            page: null
        }
    }

    sendMessage(id) {
        let text = document.getElementsByName('message')[0].value;
        ConversationService.saveMessage(id, text);
        setTimeout(() => {  this.setState(state => ({messages: ConversationService.getMessages(id)})); }, 100);
    }


    render() {
        if (this.state.page != null) {
            return <ConversationsPage/>;
        }
        return (
            <div>
                <div>
                    <button onClick={() => this.setState(() => ({page: MainPage}))}>Назад</button>
                </div>

                {this.state.messages.map(message =>
                    <div>
                        <span>{message.text}</span>
                    </div>
                )}

                <input name='message' type='text'/>
                <button onClick={() => this.sendMessage(this.state.id)}>Отправить</button>
            </div>
        );
    }


}