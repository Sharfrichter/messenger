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

    componentDidMount() {
        let id = this.state.id;
        this.timerID = setInterval(() => this.setState(() => ({messages: ConversationService.getMessages(id)})), 1000);
    }

    sendMessage(id) {
        let text = document.getElementsByName('message')[0].value;
        ConversationService.saveMessage(id, text);
        setTimeout(() => {
            this.setState(state => ({messages: ConversationService.getMessages(id)}));
        }, 100);
    }


    render() {
        if (this.state.page != null) {
            return <ConversationsPage/>;
        }
        return (
            <div className='loginpanel'>
                {this.state.messages.map(message =>
                    <div>
                        <span>{message.date}</span><span>{message.text}</span><span>{message.user.username}</span>
                    </div>
                )}
                <div>
                    <input name='message' type='text'/>
                    <input type="button" value="Отправить" onClick={() => this.sendMessage(this.state.id)}/>
                </div>
                <div className="buttons">
                    <span>
      <a href="javascript:void(0)" className="user-add register" onClick={() => this.setState(() => ({page: MainPage}))}>Назад</a>
                    </span>
                </div>
            </div>
        );
    }


}