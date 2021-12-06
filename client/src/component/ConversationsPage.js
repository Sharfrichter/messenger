import React from "react";
import {ConversationPage} from "./ConversationPage";
import ConversationService from "../service/ConversationService";
import {MainPage} from "./MainPage";

export class ConversationsPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            conversationId: null
        }
    }

    openConversation(id) {
        this.setState({
            conversationId: id,
            page: ConversationPage
        });
    }

    render() {
        let id = this.state.conversationId;
        if (this.state.page != null) {
            if (this.state.page === ConversationPage) {
                return (<ConversationPage id={id}/>)
            }
            return <MainPage/>
        }
        let conversations = ConversationService.getConversations();

        return (
            <div>
                <div>
                    <button onClick={() => this.setState(() => ({page: MainPage}))}>Назад</button>
                </div>

                {conversations.map(conversation =>
                    <div>
                        <button onClick={() => this.openConversation(conversation.id)}>{conversation.name}</button>
                    </div>
                )}
            </div>

        );
    }
}