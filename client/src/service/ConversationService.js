import CredentialsStorage from "../storage/CredentialsStorage";

class ConversationService {

    serverUrl = "http://localhost:8080/conversations";

    async getConversations() {
        let request = new XMLHttpRequest();
        request.open("GET", this.serverUrl);

        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                console.log(xhr.status);
                console.log(xhr.responseText);
            }};

        request.send();
        return request.responseText;

    }
}

export default new ConversationService();