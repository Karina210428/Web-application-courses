function start(servletUrl){
    var xhr = new XMLHttpRequest();

    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
            console.log(json.student +"  "+json.grade);
            var table="<tr><th>Student</th><th>Course</th><th>Lecturer</th><th>Grade</th><th>Comment</th><th>Action</th></tr>";
            for (var i = 0; i < json.length; i++) { // Перебираем объекты
                table += "<tr><td>" + json[i].student.firstName + " "+ json[i].student.lastName +"</td>";
                table +="<td>" + json[i].course.name + "</td>";
                table +="<td>" + json[i].course.lecturer.surname + " " + json[i].course.lecturer.name + " " +json[i].course.lecturer.patronymic +"</td>";
                table +="<td>" + json[i].grade + "</td>";
                table +="<td>" +json[i].comment +"</td>";
                table += "<td><a href='Update.html' id='btn1'>Update</a> <a href='#'>Delete</a></td></tr>"
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

function getServletXML(form, servletUrl, button){
    var xhr = new XMLHttpRequest();

    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
            console.log(json.student +"  "+json.grade);
            var table="<tr><th>Student</th><th>Course</th><th>Lecturer</th><th>Grade</th><th>Comment</th><th>Action</th></tr>";
            for (var i = 0; i < json.length; i++) { // Перебираем объекты
                table += "<tr><td>" + json[i].student.firstName + " "+ json[i].student.lastName +"</td>";
                table +="<td>" + json[i].course.name + "</td>";
                table +="<td>" + json[i].course.lecturer.surname + " " + json[i].course.lecturer.name + " " +json[i].course.lecturer.patronymic +"</td>";
                table +="<td>" + json[i].grade + "</td>";
                table +="<td>" +json[i].comment +"</td>";
                table += "<td><a href='Update.html' id='btn1'>Update</a> <a href='#'>Delete</a></td></tr>"
            }
            // Обновляем страницу с новым контентом
            document.getElementById('table').innerHTML = table;
        }
    }

    xhr.open('Post',servletUrl, true, "root","root");
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.onreadystatechange = reqReadyStateChange;
    if(button == "Add"){
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
        xhr.send(jsonSend);

    }
}