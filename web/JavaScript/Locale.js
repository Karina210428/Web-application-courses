function fun(languageString, page){
    var request = new XMLHttpRequest();
    var params = {
        language:""
    };

    params.language = languageString;
    var jsonSend = JSON.stringify(params);
    request.open("post", "/SetLocale", true);
    request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    request.onreadystatechange = function(){
        if(request.readyState == 4 && request.status == 200){
            var data = JSON.parse(request.responseText);
            console.log(data.locale);
            if(page == "SignUp") {
                updateSignUp(data);
            }else if(page=="Main"){
                updateMain(data);
            }else if(page == "CoursePage"){
                updateCoursePage(data);
            }else if(page == "Update"){
                updatePage(data,"Update");
            }else if(page=="Register"){
                updateRegisterPage(data);
            }else if(page == "Add"){
                updatePage(data,"Add");
            }else if(page=="Profile"){
                updateProfile(data);
            }
        }
    }
    request.send(jsonSend);
}

function updateMain(data){
    document.getElementById("aHome").innerText = data["main.a.home"];
    document.getElementById("aExit").innerText = data["main.a.Exit"];
    document.getElementById("aReg").innerText = data["main.a.Register"];
    document.getElementById("aCourseListPage").innerText = data["main.a.CourseListPage"];
    document.getElementById("addCourse").innerText = data["main.button.addCourse"];
    document.getElementById("addStudent").innerText = data["main.button.addStudent"];
    document.getElementById("titlePopup").innerText = data["main.popup.label.title"];
    document.getElementById("labelPopupName").innerText = data["main.popup.label.name"];
    document.getElementById("labelPopupStartDate").innerText = data["main.popup.label.startDate"];
    document.getElementById("labelPopupFinishDate").innerText = data["main.popup.label.finishDate"];
    document.getElementById("labelPopupAbout").innerText = data["main.popup.label.about"];
    document.getElementById("PopupAddCourse").innerText = data["main.popup.button.addCourse"];
    document.getElementById("tableThGrade").innerText = data["main.table.th.grade"];
    document.getElementById("tableThStudent").innerText = data["main.table.th.student"];
    document.getElementById("tableThCourse").innerText = data["main.table.th.course"];
    document.getElementById("tableThLecturer").innerText = data["main.table.th.lecturer"];
    document.getElementById("tableThComment").innerText = data["main.table.th.comment"];
    document.getElementById("buttonDelete").value = data["main.table.button.delete"];
    document.getElementById("StudentUpdate").innerText = data["main.table.a.update"];
    document.getElementById("selectLang").innerText = "";
    document.getElementById("selectLang").innerText = data["main.select.lang"];
}

function updateCoursePage(data){
    document.getElementById("aHome").innerText = data["main.a.home"];
    document.getElementById("aExit").innerText = data["main.a.Exit"];
    document.getElementById("aReg").innerText = data["main.a.Register"];
    document.getElementById("aCourseListPage").innerText = data["main.a.CourseListPage"];
    document.getElementById("addCourse").innerText = data["main.button.addCourse"];
    document.getElementById("tableThStartDate").innerText = data["page.course.table.th.startDate"];
    document.getElementById("tableThFinishDate").innerText = data["page.course.table.th.finishDate"];
    document.getElementById("tableThCourse").innerText = data["page.course.table.th.course"];
    document.getElementById("tableThLecturer").innerText = data["page.course.table.th.lecturer"];
    document.getElementById("tableThAbout").innerText = data["page.course.table.th.about"];
    document.getElementById("selectLang").innerText = "";
    document.getElementById("selectLang").innerText = data["main.select.lang"];
    document.getElementById("titlePopup").innerText = data["main.popup.label.title"];
    document.getElementById("labelPopupName").innerText = data["main.popup.label.name"];
    document.getElementById("labelPopupStartDate").innerText = data["main.popup.label.startDate"];
    document.getElementById("labelPopupFinishDate").innerText = data["main.popup.label.finishDate"];
    document.getElementById("labelPopupAbout").innerText = data["main.popup.label.about"];
    document.getElementById("PopupAddCourse").innerText = data["main.popup.button.addCourse"];
}

function updatePage(data,page){
    document.getElementById("aHome").innerText = data["main.a.home"];
    document.getElementById("aExit").innerText = data["main.a.Exit"];
    document.getElementById("aReg").innerText = data["main.a.Register"];
    document.getElementById("aCourseListPage").innerText = data["main.a.CourseListPage"];
    document.getElementById("selectLang").innerText = "";
    document.getElementById("selectLang").innerText = data["main.select.lang"];

    var input = document.getElementById("firstNameStudent");
    input.placeholder = data["label.form.firstNameStudent.placeholder"];
    var input1 = document.getElementById("lastNameStudent");
    input1.placeholder = data["label.form.lastNameStudent.placeholder"];
    var textarea = document.getElementById("grade");
    textarea.placeholder = data["label.form.grade.placeholder"];
    var textarea1 = document.getElementById("comment");
    textarea1.placeholder = data["label.form.comment.placeholder"];

    document.getElementById("commentLabel").innerText = data["form.label.comment"];
    document.getElementById("gradeLabel").innerText = data["form.label.grade"];
    document.getElementById("listCourseLabel").innerText = data["form.label.listCourse"];
    document.getElementById("lastNameStudentLabel").innerText = data["form.label.lname"];
    document.getElementById("firstNameStudentLabel").innerText = data["form.label.fname"];
    if(page=="Update") {
        document.getElementById("buttonUpdate").value = data["label.page.update.button.update"];
    }else if(page=="Add") {
        document.getElementById("buttonAdd").value = data["label.page.add.button.add"];
    }
}

function updateSignUp(data){
    var inputLog = document.getElementById("loginIN");
    inputLog.placeholder = data["label.form.login.email.placeholder"];
    var inputPass = document.getElementById("passwordIN");
    inputPass.placeholder = data["label.form.login.password.placeholder"];
    var aToRegister = document.getElementById("ToRegister");
    aToRegister.innerText = data["label.button.registration"];
    var p = document.getElementById("question");
    p.innerText = data["label.question"];
    var h1 = document.getElementById("h1Sign");
    h1.innerText = data["label.h1.Sign"];

    var button = document.getElementById("buttonSignUp");
    button.type ="submit";
    button.value = data["label.button.login"];
    button.className = "sign-up-button";
    button.addEventListener("click",function(){loadLoginForm(data)});
    document.getElementById("formSign").appendChild(button);
}

function updateRegisterPage(data) {
    var inputLogin = document.getElementById("login");
    inputLogin.placeholder = data["label.form.register.email.placeholder"];
    var inputFN = document.getElementById("fname");
    inputFN.placeholder = data["label.form.register.fname.placeholder"];
    var inputPatronymic = document.getElementById("patronymic");
    inputPatronymic.placeholder = data["label.form.register.patronymic.placeholder"];
    var inputLN = document.getElementById("lname");
    inputLN.placeholder = data["label.form.register.lname.placeholder"];
    var inputPass = document.getElementById("password1");
    inputPass.placeholder = data["label.form.register.password1.placeholder"];
    var inputPass1 = document.getElementById("password2");
    inputPass1.placeholder = data["label.form.register.password2.placeholder"];
    var p = document.getElementById("question");
    p.innerText = data["label.question.register"];
    var h1 = document.getElementById("h1Register");
    h1.innerText = data["label.h1.Register"];
    document.getElementById("aSignUp").innerText = data["label.register.a.signIn"];
    document.getElementById("titleRegister").innerText=data["label.title.register"];

    document.getElementById("buttonRegister").value = data["label.button.register"];
}

function updateProfile(data) {
    document.getElementById("aHome").innerText = data["main.a.home"];
    document.getElementById("aExit").innerText = data["main.a.Exit"];
    document.getElementById("aReg").innerText = data["main.a.Register"];
    document.getElementById("aCourseListPage").innerText = data["main.a.CourseListPage"];
    document.getElementById("addCourse").innerText = data["main.button.addCourse"];
    document.getElementById("addStudent").innerText = data["main.button.addStudent"];
    document.getElementById("selectLang").innerText = "";
    document.getElementById("selectLang").innerText = data["main.select.lang"];
    document.getElementById("buttonDelete").value = data["page.button.delete"];
}