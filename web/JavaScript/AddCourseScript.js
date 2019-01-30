function getAddCourseForms(form, servletUrl){
    var flag=false;
    if(form.nameCourse.value=="")
        flag="Вы не ввели название курса";
    else if(form.startDate.value=="")
        flag="Вы не ввели дату начала курсов";
    else if(form.finishDate.value=="")
        flag="Вы не ввели дату окончания курса";
    else if(form.about.value=="")
        flag="Вы не ввели описание курса";
    if(flag) alert(flag);
    if(flag==false) {

        var addCourse = {
            name:form.nameCourse.value,
            startDate:form.startDate.value,
            finishDate:form.finishDate.value,
            lecturer:{
                id:1,
                surname:"surname",
                name:"name"
            },
            aboutCourse:form.about.value
        };

        var jsonSend = JSON.stringify(addCourse);

        var xhr = new XMLHttpRequest();
        xhr.open('Post', servletUrl, true);
        xhr.setRequestHeader('Content-Type', 'application/json; ');
        xhr.send(jsonSend);
    }
}

function getEditCourseForms(form, servletUrl){
    var flag=false;
    if(form.nameCourse.value=="")
        flag="Вы не ввели название курса";
    else if(form.startDate.value=="")
        flag="Вы не ввели дату начала курсов";
    else if(form.finishDate.value=="")
        flag="Вы не ввели дату окончания курса";
    else if(form.about.value=="")
        flag="Вы не ввели описание курса";
    if(flag) alert(flag);
    if(flag==false) {

        var addCourse = {
            name:form.nameCourse.value,
            startDate:form.startDate.value,
            finishDate:form.finishDate.value,
            lecturer:{
                id:1,
                surname:"surname",
                name:"name"
            },
            aboutCourse:form.about.value
        };

        var jsonSend = JSON.stringify(addCourse);
        function reqReadyStateChange() {
            if (xhr.readyState == 4 && xhr.status== 200) {
                document.location = "NewPageListCourse.html"
            }
        }
        var xhr = new XMLHttpRequest();
        xhr.open('Post', servletUrl, true);
        xhr.setRequestHeader('Content-Type', 'application/json; ');
        xhr.readystatechange = reqReadyStateChange();
        xhr.send(jsonSend);
    }
}

// запись id в сессиию
function recordingIdCourseToSession(id, page){
    document.location = page;
    var xhr = new XMLHttpRequest();
    var params ={
        id:1
    };
    params.id=id-1;
    var jsonSend = JSON.stringify(params);
    xhr.open('Post',"/RecordingIdCourseToSession", true, "root","root");
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(jsonSend);
}

function startEditPage(servletUrl){
    var xhr = new XMLHttpRequest();
    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
            var input = document.getElementById('nameCourse');
            input.value = json.name;
            var input = document.getElementById('startDate');
            input.value = json.startDate;
            var input = document.getElementById('finishDate');
            input.value = json.finishDate;
            var input1 = document.getElementById('about');
            input1.value = json.aboutCourse;
        }
    }
    var course = {
        id:1,
        name: "name",
        lecturer:{
            id:1,
            surname:"surname",
            name:"name"
        },
        startDate:"startDate",
        finishDate: "finishDate",
        aboutCourse: "aboutCourse"

    };
    var jsonSend = JSON.stringify(course);
    xhr.open('Post',servletUrl, true, "root","root");
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.onreadystatechange = reqReadyStateChange;
    xhr.send(jsonSend);
    console.log(jsonSend);
}
