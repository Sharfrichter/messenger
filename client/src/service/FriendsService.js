import CredentialsStorage from "../storage/CredentialsStorage";

class FriendsService {

    serverUrl = "http://localhost:8080/user";


    getFriends() {
        let request = new XMLHttpRequest();

        request.open('GET', this.serverUrl+"/"+CredentialsStorage.getUsername(), false);
        request.setRequestHeader("Authorization", CredentialsStorage.getAuthorizationHeaderValue());

        request.send();

        if (request.status !== 200) {
            alert(request.status + ': ' + request.statusText);
        } else {
            return JSON.parse(request.responseText);
        }
    }

    addFriend(username) {

    }
}

export default new FriendsService();