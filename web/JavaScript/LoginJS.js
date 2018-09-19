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
    form.appendChild(createField("email", "login", "required"));
    form.innerHTML += locale["label.form.login.password"];
    form.appendChild(createField("password", "password", "required"));
    form.appendChild(createButton("submit", locale["label.button.accept"], null, null));
    form.appendChild(createButton("button", locale["label.button.cancel"], "click", function(){document.body.removeChild(document.getElementById('loginForm'));}));
    document.body.appendChild(form);
}