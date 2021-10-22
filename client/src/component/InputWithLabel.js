export function InputWithLabel(props) {
    return (
        <div>
            <label>{props.label}</label>
            <input type={props.inputType} name={props.inputName}/>
        </div>
    );
}