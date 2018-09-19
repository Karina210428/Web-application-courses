function loadStartPage(){
    var request = new XMLHttpRequest();
    var params = "command=loadlocale";
    request.open("post", "/LoginServlet", true);
    request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    request.onreadystatechange = function(){
        if(request.readyState == 4 && request.status == 200){
            var data = JSON.parse(request.responseText);
            startForm.style.display = "none";
            createLoginForm(data);
        }
    }
    request.send(params);
}

function createLoginForm(data){
    var login = document.createElement("input");
    login.type = "button";
    login.value = data["label.button.login"];
    login.addEventListener("click",function(){loadLoginForm(data)});
    document.body.appendChild(login);
    var reg = document.createElement("input");
    reg.type = "button";
    reg.value = data["label.button.registration"];
    reg.addEventListener("click",function(){loadRegisterForm(data)});
    document.body.appendChild(reg);
}

function loadLoginForm(locale) {
    var form = document.createElement("form");
    form.method = "post";
    form.name = "loginForm";
    form.id = "loginForm";
    form.addEventListener("submit", function() {
        var request = new XMLHttpRequest();
        var params = "command=login&login=" + loginForm.login.value + "&password=" + loginForm.password.value;
        request.open("post", "/LoginServlet", true);
        request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        request.onreadystatechange = function () {
            if (request.readyState == 4) {
                if(request.status == 200) {
                    data = JSON.parse(request.responseText);
                    if(data.url == null) {
                        var div = document.createElement("div");
                        div.id = "result";
                        div.innerHTML += locale["message.login.error"];
                        div.appendChild(createButton("button", locale["label.button.ok"], "click", function() {
                            loginForm.removeChild(document.getElementById("result"));}));
                        loginForm.appendChild(div);
                    } else {
                        window.location = data.url;
                    }
                } else {
                    alert(locale["message.server.error"]);
                }
            }

        }
        request.send(params);
        event.preventDefault();
    });
    form.innerHTML = locale["label.form.login.email"];
    form.appendChild(createFormForLogin());
    form.innerHTML += locale["label.form.login.password"];
    //form.appendChild(createField("password", "password", "required"));
    //form.appendChild(createButton("submit", locale["label.button.accept"], null, null));
    //form.appendChild(createButton("button", locale["label.button.cancel"], "click", function(){document.body.removeChild(document.getElementById('loginForm'));}));
    document.body.appendChild(form);
}

function createField(){
    return "<form method='POST'  id='formSign'>" +
        "    <h1 class='sign-up-title'>Вход</h1>" +
        "" +
        "    <input class='sign-up-input' type='text' id='loginIN' name='loginIN' placeholder='Введите логин..'>" +
        "" +
        "    <input class='sign-up-input' type='password' id='passwordIN' name='passwordIN' placeholder='Введите пароль..'>" +
        "" +
        "    <input type='submit' class='sign-up-button' value='Войти на сайт' onclick=sign_up(document.getElementById('formSign'),'/LoginServlet')>" +
        "    </form>";
}

function createFormForLogin() {
    var form = document.createElement("form");
    form.method = "post";
    form.name = "loginForm";
    form.id = "loginForm";
    var inputLogin = document.createElement("input");
    input.class = 'sign-up-input';
    input.type = 'text';
    input.name = 'loginIN';
    input.placeholder = "xv";
    form.appendChild(inputLogin);
    var inputPass = document.createElement("input");
    input.class = 'sign-up-input';
    input.type = 'text';
    input.name = 'loginIN';
    input.placeholder = "xv";
    form.appendChild(inputPass);
    return form;
}