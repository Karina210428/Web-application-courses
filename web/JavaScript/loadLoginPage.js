function loadStartPage(){
    var request = new XMLHttpRequest();
    var params = {
        language:"en"
    };
    var jsonParam = JSON.stringify(params);
    request.open("post", "/SetLocale", true);
    request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    request.onreadystatechange = function(){
        if(request.readyState == 4 && request.status == 200){
            var data = JSON.parse(request.responseText);
            startForm.style.display = "none";
            createLoginForm(data);
        }
    }
    request.send(jsonParam);
}

function createLoginForm(data){
    var div = document.createElement("div");
    div.id = "div";
    div.className = "btn-group";
    var login = document.createElement("input");
    login.type = "button";
    login.value = data["label.button.login"];
    login.className = "btn btn-primary"
    login.addEventListener("click",function(){loadLoginForm(data)});
    div.appendChild(login);

    var reg = document.createElement("input");
    reg.type = "button";
    reg.className = "btn btn-primary";
    reg.value = data["label.button.registration"];
    reg.addEventListener("click",function(){loadRegisterForm(data)});
    div.appendChild(reg);
    document.body.appendChild(div);
    document.body.className = "text-center";
}

function loadLoginForm(locale) {
    var loginForm = document.getElementById("formSign");
        var request = new XMLHttpRequest();
        var params = "command=login&login=" + loginForm.loginIN.value + "&password=" + loginForm.passwordIN.value;
        request.open("post", "/LoginServlet", true);
        request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        request.onreadystatechange = function () {
            if (request.readyState == 4) {
                if(request.status == 200) {
                    data = JSON.parse(request.responseText);
                    if(data.url == undefined){
                        var div = document.createElement("div");
                        div.id = "result";
                        div.innerHTML += locale["message.login.error"];
                        loginForm.appendChild(div);
                    } else {
                        window.location = data.url;
                    }
                } else {
                    alert(locale["message.server.error"]);
                }
            }
        };
    request.send(params);
    event.preventDefault();
   // form.innerHTML = locale["label.form.login.email"];
    //form.appendChild(createLabel(""));


}

function createButton(buttonValue, fun){
    var div = document.createElement("div");
    div.id = "div";
    div.className = "col-sm-4";
    var button = document.createElement("input");
    button.class = 'sign-up-button';
    button.type = "submit";
    button.value = buttonValue;
    button.onclick = fun;
    button.className = "btn btn-success";
    // button.addEventListener("submit", sign_up(document.getElementById('loginForm'),'/LoginServlet'));
    div.appendChild(button);
    return div;
}

function createLabel(labelValue) {
    var label = document.createElement("label");
    label.class = "sr-only";

    return label;
}

function createInput(name,placeholder) {
    var div = document.createElement("div");
    div.id = "div";
    div.className = "col-sm-4";
    var nameInput = document.createElement("input");
    nameInput.class = 'sign-up-input';
    nameInput.type = 'text';
    nameInput.name = name;
    nameInput.className = "form-control";
    nameInput.placeholder = placeholder;
    div.appendChild(nameInput);
    return div;
}

