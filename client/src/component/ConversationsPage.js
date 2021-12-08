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
            <div className='loginpanel'>
                {conversations.map(conversation =>
                    <div>
                        <input type="button" value={conversation.name} onClick={() => this.openConversation(conversation.id)}/>
                    </div>
                )}
                <div className="buttons">
                    <span>
      <a href="javascript:void(0)" className="user-add register" onClick={() => this.setState(() => ({page: MainPage}))}>Назад</a>
                    </span>
                </div>

            </div>
        );
    }
}