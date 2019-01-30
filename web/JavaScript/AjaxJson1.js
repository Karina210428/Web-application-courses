
function start(servletUrl){
    var xhr = new XMLHttpRequest();

    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
           // document.getElementById("aStudentCourse").remove();
            var table = "";
            for (var i = 0; i < json.length; i++) { // Перебираем объекты
                table += "<tr><td>" + parseFloat(i+1) + "</td>";
                table += "<td>" + json[i].student.firstName + " "+ json[i].student.lastName +"</td>";
                table +="<td>" + json[i].course.name + "</td>";
                table +="<td>" + json[i].course.lecturer.surname + " " + json[i].course.lecturer.name  +"</td>";
                table +="<td>" + json[i].grade + "</td>";
                table +="<td>" +json[i].comment +"</td>";
                table += "<td><input src='images/edit.png'  type='image' id='StudentUpdate' alt='Submit' width='48' height='48' onclick=recordingIdToSession(this.parentNode.parentNode.rowIndex,'NewEditPage.html') > " +
                    "<input id ='buttonDelete' type='image' src='images/cancel.png' alt='Submit' width='48' height='48' onclick=getServletXMLDel('/HTML/deleteAjaxJson',this.parentNode.parentNode.rowIndex) ></td></tr>";
            }
            // Обновляем страницу с новым контентом
            document.getElementById('information').innerHTML += table;
        }
    }

    var participant = {
        id : 1,
        student:{
            id:1,
            firstName:"firstName",
            lastName:"lastName"
        },
        course:{
            id:1,
            name: "name",
            lecturer:{
                id:1,
                surname:"surname",
                name:"name"
            },
            startDate: "startDate",
            finishDate:"finishDate",
            aboutCourse:"aboutCourse"
        },
        grade:1,
        comment:"comment"
    };

    var jsonSend = JSON.stringify(participant);
    xhr.open('Post',servletUrl, true, "root","root");
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.onreadystatechange = reqReadyStateChange;

    xhr.send(jsonSend);
    console.log(jsonSend);
}

function getListCompletedCourse(servletUrl){
    var xhr = new XMLHttpRequest();

    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
            //document.getElementById("aStudentCourse").remove();
            var table = "";
            for (var i = 0; i < json.length; i++) { // Перебираем объекты
                table += "<tr><td>" + parseFloat(i+1) + "</td>";
                table += "<td>" + json[i].student.firstName + " "+ json[i].student.lastName +"</td>";
                table +="<td>" + json[i].course.name + "</td>";
                table +="<td>" + json[i].course.lecturer.surname + " " + json[i].course.lecturer.name  +"</td>";
                table +="<td>" + json[i].grade + "</td>";
                table +="<td>" +json[i].comment +"</td>";
                table += "<td><input src='images/edit.png'  type='image' id='StudentUpdate' alt='Submit' width='48' height='48' onclick=recordingIdToSession(this.parentNode.parentNode.rowIndex,'NewEditPage.html') > " +
                    "<input id ='buttonDelete' type='image' src='images/cancel.png' alt='Submit' width='48' height='48' onclick=getServletXMLDel('/HTML/deleteAjaxJson',this.parentNode.parentNode.rowIndex) ></td></tr>";
            }
            // Обновляем страницу с новым контентом
            document.getElementById('information').innerHTML = table;
        }
    }

    var participant = {
        id : 1,
        student:{
            id:1,
            firstName:"firstName",
            lastName:"lastName"
        },
        course:{
            id:1,
            name: "name",
            lecturer:{
                id:1,
                surname:"surname",
                name:"name"
            },
            startDate: "startDate",
            finishDate:"finishDate",
            aboutCourse:"aboutCourse"
        },
        grade:1,
        comment:"comment"
    };

    var jsonSend = JSON.stringify(participant);
    xhr.open('Post',servletUrl, true, "root","root");
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.onreadystatechange = reqReadyStateChange;

    xhr.send(jsonSend);
    console.log(jsonSend);
}

// запись id в сессиию
function recordingIdToSession(id, page){
    document.location = page;
    var xhr = new XMLHttpRequest();
    var params ={
        id:1
    };
    params.id=id-1;

    var jsonSend = JSON.stringify(params);
    xhr.open('Post',"/RecordingIdToSession", true, "root","root");
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(jsonSend);
}

//удаление записи
function getServletXMLDel(servletUrl,r){
    var j = r;
    var xhr = new XMLHttpRequest();

    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
            document.getElementById("aStudentCourse").remove();
            //console.log(json.student +"  "+json.grade);
            var table="";
            for (var i = 0; i < json.length; i++) { // Перебираем объекты
                table += "<tr><td>" + parseFloat(i+1) + "</td>";
                table += "<td>" + json[i].student.firstName + " "+ json[i].student.lastName +"</td>";
                table +="<td>" + json[i].course.name + "</td>";
                table +="<td>" + json[i].course.lecturer.surname + " " + json[i].course.lecturer.name  +"</td>";
                table +="<td>" + json[i].grade + "</td>";
                table +="<td>" +json[i].comment +"</td>";
                table += "<td><input src='images/edit.png'  type='image' id='StudentUpdate' alt='Submit' width='48' height='48' onclick=recordingIdToSession(this.parentNode.parentNode.rowIndex,'NewEditPage.html') > " +
                    "<input id ='buttonDelete' type='image' src='images/cancel.png' alt='Submit' width='48' height='48' onclick=getServletXMLDel('/HTML/deleteAjaxJson',this.parentNode.parentNode.rowIndex) ></td></tr>";
            }
            // Обновляем страницу с новым контентом
            document.getElementById('information').innerHTML = table;

        }
    }

    var participant = {
        id : 1,
        student:{
            id:1,
            firstName:"firstName",
            lastName:"lastName"
        },
        course:{
            id:1,
            name: "name",
            lecturer:{
                id:1,
                surname:"surname",
                name:"name"
            },
            startDate: "startDate",
            finishDate:"finishDate",
            aboutCourse:"aboutCourse"
        },
        grade:1,
        comment:"comment"
    };

    participant.id = j-1;

    var jsonSend = JSON.stringify(participant);
    xhr.open('Post',servletUrl, true, "root","root");
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.onreadystatechange = reqReadyStateChange;

    xhr.send(jsonSend);
    console.log(jsonSend);
}


function getServletXML(form, servletUrl, button){
    var participant = {
        id : 1,
        student:{
            id:1,
            firstName:"firstName",
            lastName:"lastName"
        },
        course:{
            id:1,
            name: "name",
            lecturer:{
                id:1,
                surname:"surname",
                name:"name"
            },
            startDate: "startDate",
            finishDate:"finishDate",
            aboutCourse:"aboutCourse"
        },
        grade:1,
        comment:"comment"
    };


        participant.grade = form.grade.value;
        participant.comment = form.comment.value;
        participant.student.firstName = form.firstNameStudent.value;
        participant.student.lastName = form.lastNameStudent.value;
        participant.course.id = form.course.value;

        var jsonSend = JSON.stringify(participant);

        var xhr = new XMLHttpRequest();
        function reqReadyStateChange() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                document.location = "NewMain.html";
            }
        }
        if(button="Add"){
            xhr.open('Post',servletUrl, true);
        }else if(button=="Update"){
            xhr.open('Post',servletUrl, true);
        }
        xhr.readystatechange = reqReadyStateChange();
        xhr.setRequestHeader('Content-Type','application/json; ');
        xhr.send(jsonSend);
    }

//вывод списка курсов в select
function listCourse(){
    var xhr = new XMLHttpRequest();

    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
            // console.log(json.student +"  "+json.grade);
            var select="<select name='course'>";
            for (var i = 0; i < json.length; i++) { // Перебираем объекты
                select += " <option value='"+json[i].id +"'>" +json[i].name +"</option>";
            }
            select+= "</select>"
            // Обновляем страницу с новым контентом
            document.getElementById('listCourse').innerHTML = select;
        }
    }

    var lecturer={
        id:1,
        nameCourse:"name",
        lecturer_id:1,
        startDate:"startDate",
        firstDate:"finishDate",
        aboutCourse: "about"
    };

    var jsonSend = JSON.stringify(lecturer);
    xhr.open('Post',"/ListCourse", true, "root","root");
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.onreadystatechange = reqReadyStateChange;
    xhr.send(jsonSend);
    console.log(jsonSend);
}

function  startUpdatePage(servletUrl) {
    var xhr = new XMLHttpRequest();
    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
            var input = document.getElementById('firstNameStudent');
            input.value = json.student.firstName;
            var input = document.getElementById('lastNameStudent');
            input.value = json.student.lastName;
            var input = document.getElementById('grade');
            input.value = json.grade;
            var input1 = document.getElementById('comment');
            input1.value = json.command;
        }
    }
    var participant = {
        id : 1,
        student:{
            id:1,
            firstName:"firstName",
            lastName:"lastName"
        },
        course:{
            id:1,
            name: "name",
            lecturer:{
                id:1,
                surname:"surname",
                name:"name"
            },
            startDate: "startDate",
            finishDate:"finishDate",
            aboutCourse:"aboutCourse"
        },
        grade:1,
        comment:"comment"
    };
    var jsonSend = JSON.stringify(participant);
    xhr.open('Post',servletUrl, true, "root","root");
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.onreadystatechange = reqReadyStateChange;
    xhr.send(jsonSend);
    console.log(jsonSend)
}
