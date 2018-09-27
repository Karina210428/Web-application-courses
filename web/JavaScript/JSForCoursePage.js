function start(servletUrl){
    var xhr = new XMLHttpRequest();

    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
            // console.log(json.student +"  "+json.grade);
            var table = " ";
            for (var i = 0; i < json.length; i++) { // Перебираем объекты
                table += "<tr><td>"+parseFloat(i+1)+"<td><td>" + json[i].name +"</td>";
                table +="<td>" + json[i].lecturer.surname + " " + json[i].lecturer.name + " " +json[i].lecturer.patronymic +"</td>";
                table +="<td>" + json[i].startDate + "</td>";
                table +="<td>" +json[i].finishDate +"</td>";
                table +="<td>" +json[i].aboutCourse +"</td>";
                table += "<td> " +
                    "<input id ='buttonDelete' type='image' src='image/icons8-close-window-48.png' alt='Submit' width='48' height='48' onclick=getServletXMLDel('/DeleteCourse',this.parentNode.parentNode.rowIndex) ></td></tr>";
            }
            // Обновляем страницу с новым контентом
            document.getElementById('information').innerHTML = table;
        }
    }

    var course = {
            id:1,
            name: "name",
            lecturer:{
                id:1,
                surname:"surname",
                patronymic:"patronymic",
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


function getServletXMLDel(servletUrl,r){
    var i = r;

    var xhr = new XMLHttpRequest();

    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
            //console.log(json.student +"  "+json.grade);
            var table="";
            for (var i = 0; i < json.length; i++) { // Перебираем объекты
                table += "<tr><td>"+parseFloat(i+1)+"<td><td>" + json[i].name +"</td>";
                table +="<td>" + json[i].lecturer.surname + " " + json[i].lecturer.name + " " +json[i].lecturer.patronymic +"</td>";
                table +="<td>" + json[i].startDate + "</td>";
                table +="<td>" +json[i].finishDate +"</td>";
                table +="<td>" +json[i].aboutCourse +"</td>";
                table += "<td>" +
                    "<input id ='buttonDelete' type='image' src='image/icons8-close-window-48.png' alt='Submit' width='48' height='48' onclick=getServletXMLDel('/DeleteCourse',this.parentNode.parentNode.rowIndex) ></td></tr>";
            }
            // Обновляем страницу с новым контентом
            document.getElementById('information').innerHTML = table;
        }
    }

    var course = {
        id:1,
        name: "name",
        lecturer:{
            id:1,
            surname:"surname",
            patronymic:"patronymic",
            name:"name"
        },
        startDate:"startDate",
        finishDate: "finishDate",
        aboutCourse: "aboutCourse"

    };

    course.id = i-1;

    var jsonSend = JSON.stringify(course);
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
    participant.course.id = form.course.value;

    var jsonSend = JSON.stringify(participant);

    var xhr = new XMLHttpRequest();
    xhr.open('Post',servletUrl, true);
    xhr.setRequestHeader('Content-Type','application/json; ');
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            document.location.href = "Main.html";
        }
    }

    xhr.send(jsonSend);
}


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
