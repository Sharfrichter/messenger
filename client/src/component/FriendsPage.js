import {MainPage} from "./MainPage";
import React from "react";
import ConversationService from "../service/ConversationService";
import CredentialsStorage from "../storage/CredentialsStorage";
import FriendsService from "../service/FriendsService";

export class FriendsPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            friends: FriendsService.getFriends(),
        }
    }

    addFriend() {
        let username = document.getElementsByName('friendUsername')[0].value;
        FriendsService.addFriend(username);
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

                {this.state.friends.map(friend =>
                    <div>
                        <span>{friend.username}</span>
                        <button>Написать сообщение</button>
                    </div>
                )}
                <div><input type='text' id='friendUsername'/><button>Добавить друга</button></div>
            </div>
        );
    }
}