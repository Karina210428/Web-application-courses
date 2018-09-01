
function start(servletUrl){
    var xhr = new XMLHttpRequest();

    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
           // console.log(json.student +"  "+json.grade);
            var table="<tr><th>Student</th><th>Course</th><th>Lecturer</th><th>Grade</th><th>Comment</th><th>Action</th></tr>";
            for (var i = 0; i < json.length; i++) { // Перебираем объекты
                table += "<tr><td>" + json[i].student.firstName + " "+ json[i].student.lastName +"</td>";
                table +="<td>" + json[i].course.name + "</td>";
                table +="<td>" + json[i].course.lecturer.surname + " " + json[i].course.lecturer.name + " " +json[i].course.lecturer.patronymic +"</td>";
                table +="<td>" + json[i].grade + "</td>";
                table +="<td>" +json[i].comment +"</td>";
                table += "<td><a href='Update.html'  onclick='f(this.parentNode.parentNode.rowIndex)'>Updaye</a>" +
                    "<input type='button' onclick=getServletXMLDel('/HTML/deleteAjaxJson',this) value='Delete'></td></tr>";
            }
            // Обновляем страницу с новым контентом
            document.getElementById('table').innerHTML = table;
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
                patronymic:"patronymic",
                name:"name"
            }
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


function getServletXMLDel(servletUrl,r){
    var i = r.parentNode.parentNode.rowIndex;

    var xhr = new XMLHttpRequest();

    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
            //console.log(json.student +"  "+json.grade);
            var table="<tr><th>Student</th><th>Course</th><th>Lecturer</th><th>Grade</th><th>Comment</th><th>Action</th></tr>";
            for (var i = 0; i < json.length; i++) { // Перебираем объекты
                table += "<tr><td>" + json[i].student.firstName + " "+ json[i].student.lastName +"</td>";
                table +="<td>" + json[i].course.name + "</td>";
                table +="<td>" + json[i].course.lecturer.surname + " " + json[i].course.lecturer.name + " " +json[i].course.lecturer.patronymic +"</td>";
                table +="<td>" + json[i].grade + "</td>";
                table +="<td>" +json[i].comment +"</td>";
                table += "<td><a href='Update.html'  onclick=getID(this) >Updaye</a> " +
                    "<input type='button' onclick=getServletXMLDel('/HTML/deleteAjaxJson','Delete',this) value='Delete'></td></tr>";
            }
            // Обновляем страницу с новым контентом
            document.getElementById('table').innerHTML = table;
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
                patronymic:"patronymic",
                name:"name"
            }
        },
        grade:1,
        comment:"comment"
    };

    participant.id = i-1;

    var jsonSend = JSON.stringify(participant);
    xhr.open('Post',servletUrl, true, "root","root");
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.onreadystatechange = reqReadyStateChange;

    xhr.send(jsonSend);
    console.log(jsonSend);
}


function SetForms(servletUrl){

    var form = document.getElementById('form');
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
                patronymic:"patronymic",
                name:"name"
            }
        },
        grade:1,
        comment:"comment"
    };

    participant.id = i-1;

  /*  participant.grade = form.grade.value;
    participant.comment = form.comment.value;
    participant.student.firstName = form.firstNameStudent.value;
    participant.student.lastName = form.lastNameStudent.value;
    participant.course.name = form.courseName.value;
    participant.course.lecturer.name = form.firstNameLecture.value;
    participant.course.lecturer.patronymic=form.patronymicLecture.value;
    participant.course.lecturer.surname=form.surnameLecture.value;*/

    var jsonSend = JSON.stringify(participant);

    var xhr = new XMLHttpRequest();
    xhr.open('Post',servletUrl, false);
    xhr.setRequestHeader('Content-Type','application/json; ');
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            var json  = JSON.parse(xhr.responseText);
             form.grade = json.grade;
            alert('OK');
        }else {
            alert(xhr.status + " "+ xhr.readyState);
        }
    }

    xhr.send(jsonSend);
}

function getServletXML(form, servletUrl){
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
                patronymic:"patronymic",
                name:"name"
            }
        },
        grade:1,
        comment:"comment"
    };

        participant.grade = form.grade.value;
        participant.comment = form.comment.value;
        participant.student.firstName = form.firstNameStudent.value;
        participant.student.lastName = form.lastNameStudent.value;
        participant.course.name = form.courseName.value;
        participant.course.lecturer.name = form.firstNameLecture.value;
        participant.course.lecturer.patronymic=form.patronymicLecture.value;
        participant.course.lecturer.surname=form.surnameLecture.value;

        var jsonSend = JSON.stringify(participant);

        var xhr = new XMLHttpRequest();
        xhr.open('Post',servletUrl, true);
        xhr.setRequestHeader('Content-Type','application/json; ');
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4 && xhr.status == 200){
                //document.location.href = "Main.html";
                alert('OK');
            }else {
                alert(xhr.status + " "+ xhr.readyState);
            }
        }

        xhr.send(jsonSend);
    }

