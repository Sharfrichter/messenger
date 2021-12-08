import CredentialsStorage from "../storage/CredentialsStorage";

class UserService {

    serverUrl = "http://localhost:8080/user";

    register(user) {
        let request = new XMLHttpRequest();

        request.open('POST', this.serverUrl+"/create");
        request.setRequestHeader("Content-Type", "application/json");

        request.send(JSON.stringify(user));
    }

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
        let request = new XMLHttpRequest();

        request.open('GET', this.serverUrl+"/add/friend/" + username);
        request.setRequestHeader("Authorization", CredentialsStorage.getAuthorizationHeaderValue());
        request.send();
    }
}

export default new UserService();