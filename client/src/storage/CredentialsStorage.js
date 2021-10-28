import {Base64EncoderService} from "../service/Base64EncoderService";

class CredentialsStorage {

    setCredentials(username, password) {
        sessionStorage.setItem("credentials", Base64EncoderService.utf8_to_b64(username + ':' + password))
    }

    getCredentials() {
        return sessionStorage.getItem("credentials");
    }

    getAuthorizationHeaderValue() {
        return 'Basic ' + this.getCredentials();
    }
}

export default new CredentialsStorage();
