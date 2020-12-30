window.addEventListener("load", initPage, false);

function initPage() {
    document.addEventListener("blur", checkField, true);
    document.addEventListener("submit", endValidation, false);
}

function endValidation(event) {
    let fields = event.target.elements;
    let error, hasErrors;

    for(let i = 0; i < fields.length; i++) {
        error = hasError(fields[i]);
        if (error) {
            showError(fields[i], error);
            if (!hasErrors) {
                hasErrors = fields[i];
            }
        }
    }
    if (hasErrors) {
        event.preventDefault();
        hasErrors.focus();
    }
}

function checkField(event) {
    let error = hasError(event.target);
    if(error) {
        showError(event.target, error);
    } else {
        removeError(event.target);
    }
}

function hasError(field) {
    if (field.disabled || field.type === "file" || field.type === "submit") {
        return;
    }
    let validity = field.validity;
    if (validity === null || validity.valid) {
        return;
    }
    if (validity.valueMissing) {
        return "Please fill in the field";
    }
    if (validity.typeMismatch) {
        return "Please fill in a correct input";
    }
    if (validity.patternMismatch) {
        if (field.type === "email") {
            return "Please fill in a correct email";
        }
    }
    return "Please complete form correctly"
}

function showError(field, error) {
    field.classList.add("error");
    let id = field.id;
    if (!id) {
        return;
    }
    let message = document.getElementById("error-For-" + id);
    if (!message) {
        message = document.createElement("span");
        message.className = "error";
        message.id = "error-For-" + id;
        field.parentNode.parentNode.insertBefore(message, field.nextSibling);
    }

    //error message tonen
    message.innerHTML = error;
}

function removeError(field) {
    if (field.classList !== null && field.classList.length > 0) {
        field.classList.remove("error");
        let id = field.id;
        let message = document.getElementById("error-For-" + id);
        if (message !== null) {
            message.parentNode.removeChild(message);
        }
    }

}