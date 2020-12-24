// Remove alert messages on click
const alertDangerDiv = document.getElementById("alert-danger")
const alertSuccessDiv = document.getElementById("alert-success")

if (alertDangerDiv !== null) {
    alertDangerDiv.addEventListener("click", e => {
        alertDangerDiv.remove()
    })
}

if (alertSuccessDiv !== null) {
    alertSuccessDiv.addEventListener("click", e => {
        alertSuccessDiv.remove()
    })
}
