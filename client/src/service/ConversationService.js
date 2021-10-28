import CredentialsStorage from "../storage/CredentialsStorage";

class ConversationService {

    serverUrl = "http://localhost:8080/conversations";

    getConversations() {
        var xhr = new XMLHttpRequest();

        xhr.open('GET', this.serverUrl, false);
        xhr.setRequestHeader("Authorization", CredentialsStorage.getAuthorizationHeaderValue());

        xhr.send();

        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText); // пример вывода: 404: Not Found
        } else {
            return JSON.parse(xhr.responseText);
        }

        return [];
    }
}

export default new ConversationService();