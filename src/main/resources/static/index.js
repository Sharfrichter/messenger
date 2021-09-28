let isAuthorized = false;

function Input(props) {
    return (
        <div>
            <label>{props.label}</label>
            <input type={props.inputType} name={props.inputName}/>
        </div>
    );
}

class AuthorizationPage extends React.Component {
    constructor(props) {
        super(props);
        this.authorize = this.authorize.bind(this);
        this.register = this.register.bind(this);
    }

    authorize() {
        alert("Authorization");
    }

    register() {
        alert("Create account");
    }

    render() {
        return (
            <div>
                <Input label='Почта' inputType='text' inputName='email'/>
                <Input label='Пароль' inputType='password' inputName='password'/>
                <button onClick={this.authorize}>Войти</button>
                <button onClick={this.register}>Зарегистрироваться</button>
            </div>
        );
    }
}

class RegistrationPage extends React.Component {
    constructor(props) {
        super(props);

        this.register = this.register.bind(this);
    }

    register() {
        var object = {};
        var formData = new FormData(document.forms.registrationForm);

        formData.forEach(function (value, key) {
            if (key === 'confirmPassword') {
                //todo password validation
            } else {
                object[key] = value;
            }
        });
        var json = JSON.stringify(object);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", 'http://localhost:8080/registration', true);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

        alert(json);
        xhr.send(json);
        alert(xhr.responseText);
    }

    render() {
        return (
            <form onSubmit={this.register} id='registrationForm' name='registrationForm' method='post'>
                <Input label='Имя' inputType='text' inputName='firstName'/>
                <Input label='Фамилия' inputType='text' inputName='lastName'/>
                <Input label='Почта' inputType='text' inputName='email'/>
                <Input label='Пароль' inputType='password' inputName='password'/>
                <Input label='Подтвердите пароль' inputType='password' inputName='confirmPassword'/>
                <input type='submit' value='Зарегистрироваться'/>
            </form>
        );
    }
}

class App extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        if (!isAuthorized) {
            return (
                <AuthorizationPage/>
            );
        }
        return <h1>Hello there</h1>
    }
}


ReactDOM.render(
    <RegistrationPage/>,
    document.getElementById('root')
);

