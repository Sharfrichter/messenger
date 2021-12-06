import CredentialsStorage from "../storage/CredentialsStorage";

class ConversationService {

    serverUrl = "http://localhost:8080/conversations";

    getConversations() {
        return this.getData(this.serverUrl);
    }

    getMessages(conversationId) {
        return this.getData(this.serverUrl + '/' + conversationId);
    }

    saveMessage(conversationId, text) {
        let request = new XMLHttpRequest();
        request.open("POST", this.serverUrl+"/"+conversationId);

        request.setRequestHeader("Authorization", CredentialsStorage.getAuthorizationHeaderValue());
        request.setRequestHeader("Content-Type", "application/json");
        request.send(text);
    }

    getData(url) {
        let request = new XMLHttpRequest();

        request.open('GET', url, false);
        request.setRequestHeader("Authorization", CredentialsStorage.getAuthorizationHeaderValue());

        request.send();

        if (request.status !== 200) {
            alert(request.status + ': ' + request.statusText);
        } else {
            return JSON.parse(request.responseText);
        }
    }
}

export default new ConversationService();