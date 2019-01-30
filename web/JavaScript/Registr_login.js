function getRegisterForms(form, servletUrl){
    var flag=false;
    if(form.lastname.value=="")
        flag="Вы не ввели свою фамилию";
    else if(form.firstname.value=="")
        flag="Вы не ввели своё имя";
    else if(form.login.value=="")
        flag="Вы не ввели логин";
    else if(form.password1.value=="")
        flag="Вы не ввели пароль";
    else if(form.password1.value != form.password2.value)
        flag="Пароли не совпадают";
    if(flag) alert(flag);
if(flag==false) {

    var regUser = {
        login: form.login.value,
        lastName: form.lastname.value,
        firstName: form.firstname.value,
        password: form.password1.value,
    };

    var jsonSend = JSON.stringify(regUser);

    var xhr = new XMLHttpRequest();
    xhr.open('Post', servletUrl, true);
    xhr.setRequestHeader('Content-Type', 'application/json; ');
    xhr.send(jsonSend);
}
}

function sign_up(form,servletUrl){

    var signUpUser = {
        login: form.loginIN.value,
        password: form.passwordIN.value,
    };

    var jsonSend = JSON.stringify(signUpUser);

    var xhr = new XMLHttpRequest();
    var params = "command=login&login=" +form.loginIN.value + "&password=" + form.passwordIN.value;
    xhr.open('Post', servletUrl, true);
    xhr.setRequestHeader('Content-Type', 'application/json; ');
    //var json  = JSON.parse(xhr.responseText);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {

                var data = JSON.parse(xhr.responseText);
                console.log(json.flag);
                if(data.url = null){
                 var div = document.createElement("div");
                 div.id = "result";
                 console.log("Error");
                }else {
                    window.location = data.url;
                }
            }

        }
    }

    xhr.send(params);
}