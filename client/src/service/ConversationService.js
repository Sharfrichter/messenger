import CredentialsStorage from "../storage/CredentialsStorage";

class ConversationService {

    serverUrl = "http://localhost:8080/conversations";

    async getConversations() {
        return fetch(this.serverUrl, {
            headers: {
                Authorization: CredentialsStorage.getCredentials()
            }
        }).then(response => response.json());

    }
}

export default new ConversationService();