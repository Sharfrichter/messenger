import {MainPage} from "./MainPage";
import React from "react";
import ConversationService from "../service/ConversationService";
import CredentialsStorage from "../storage/CredentialsStorage";
import FriendsService from "../service/UserService";
import {ConversationPage} from "./ConversationPage";

export class FriendsPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            friends: FriendsService.getFriends(),
            conversationId: null
        }
    }

    addFriend() {
        let username = document.getElementsByName('friendUsername')[0].value;
        FriendsService.addFriend(username);
        setTimeout(() => {  this.setState(state => ({friends: FriendsService.getFriends()})); }, 100);
    }

    startConversation(username) {
        let that = this;
        ConversationService.startConversation(username).then(value => {
            that.setState(() => ({conversationId:value}))
        })
    }

    render() {
        if (this.state.conversationId != null) {
            return <ConversationPage id = {this.state.conversationId}/>
        }
        if (this.state.page != null) {
            return <MainPage/>;
        }
        return (
            <div className='loginpanel'>
                {this.state.friends.map(friend =>
                    <div className="inline">
                        <span type="username">{friend.username}</span>
                        <input type="button" value="Написать" onClick={() => this.startConversation(friend.username)}/>
                    </div>
                )}
                <input type='text' name='friendUsername'/>
                <div className="buttons">
                    <input type="button" value="Добавить друга" onClick={() => this.addFriend()}/>
                    <span>
      <a href="javascript:void(0)" onClick={() => this.setState(() => ({page:MainPage}))} className="user-add register">Назад</a>
                    </span>
                </div>

            </div>
        );
    }
}