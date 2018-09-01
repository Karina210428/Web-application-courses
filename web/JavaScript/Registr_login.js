function getRegistrForms(form, servletUrl){
    var flag=false;
    if(form.lastname.value=="")
        flag="Вы не ввели свою фамилию";
    else if(form.firstname.value=="")
        flag="Вы не ввели своё имя";
    else if(form.patronymic.value=="")
        flag="Вы не ввели отчество";
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
        patronymic: form.patronymic.value,
        occupation: form.occupation.value,
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
    xhr.open('Post', servletUrl, true);
    xhr.setRequestHeader('Content-Type', 'application/json; ');
    //var json  = JSON.parse(xhr.responseText);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
            console.log(json.flag);

            if(json.flag == true && json.occupation=="teacher"){
                window.location.href = "Main.html" ;
            }else if(json.flag == true && json.occupation=="student"){
               window.location.href = "Main.html" ;
           }else if(json.flag== false){
               document.getElementById("help-block").style.display = "block";
           }
            // Обновляем страницу с новым контентом
            //document.getElementById('table').innerHTML = table;
        }
    }

    xhr.send(jsonSend);
}