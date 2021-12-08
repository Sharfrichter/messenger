export function InputWithLabel(props) {
    let req= props.required === true;

    return (
        <div>
            <label>{props.label}</label>
            <input type={props.inputType} name={props.inputName} required={req}/>
        </div>
    );
}