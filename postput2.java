let consoleFormEl = document.getElementById("consoleForm");
let requestUrlEl = document.getElementById("requestUrl");
let responseStatusEl = document.getElementById("responseStatus");
let requestUrlErrMsgEl = document.getElementById("requestUrlErrMsg");
let requestMethodEl = document.getElementById("requestMethod");
let requestBodyEl = document.getElementById("requestBody");
let responseBodyEl = document.getElementById("responseBody");

let formData = {
    requestUrl: "https://gorest.co.in/public-api/users",
    requestMethod: "POST",
    requestBody: "",
};

requestUrlEl.addEventListener("change", function(event) {
    formData.requestUrl = event.target.value;
});

requestMethodEl.addEventListener("change", function(event) {
    formData.requestMethod = event.target.value;
});

requestBodyEl.addEventListener("change", function(event) {
    formData.requestBody = event.target.value;
});

function validate(formData) {
    if (formData.requestUrl === "") {
        requestUrlErrMsgEl.textContent = "Required*";
    } else {
        requestUrlErrMsgEl.textContent = "";
    }
}

function okay(formData) {
    let options = {
        method: formData.requestMethod,
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
            Authorization: "Bearer 971f77c0135ddd286dec354f1ea2982929d326efa95e325b81338652e3c78f84",
        },
        body: formData.requestBody,
    };

    fetch(formData.requestUrl, options)
        .then(function(response) {
            return response.json();
        })
        .then(function(jsonText) {
            responseStatusEl.value = jsonText.code;
            responseBodyEl.value = JSON.stringify(jsonText);
        });
}

consoleFormEl.addEventListener("submit", function(event) {
    event.preventDefault();
    validate(formData);
    okay(formData);
});