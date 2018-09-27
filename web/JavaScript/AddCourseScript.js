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
                patronymic:"patronymic",
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
