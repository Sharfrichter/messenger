import React from "react";
import ConversationService from "../service/ConversationService";
import {MainPage} from "./MainPage";

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
        let message = ConversationService.saveMessage(id, text);
        this.setState(state =>
            ({
                id: id,
                page: null,
                messages: message
            }));
    }


    render() {
        if (this.state.page != null) {
            return <MainPage/>;
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