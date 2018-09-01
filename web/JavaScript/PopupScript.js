

function div_show() {
    document.getElementById('abc').style.display = "block";
    listLecture();

}

function  div_close() {
    document.getElementById("abc").style.display = "none";
}

function listLecture(){
    var xhr = new XMLHttpRequest();

    function reqReadyStateChange() {
        if (xhr.readyState == 4 && xhr.status== 200) {
            var json  = JSON.parse(xhr.responseText);
            // console.log(json.student +"  "+json.grade);
            var select="<select name='lecture'>";
            for (var i = 0; i < json.length; i++) { // Перебираем объекты
                select += " <option value='"+json[i].id+"'>" +json[i].surname + " " + json[i].name + " " + json[i].patronymic + "</option>" +
                    "</select>";
            }
            // Обновляем страницу с новым контентом
            document.getElementById('CourseLecture').innerHTML = select;
        }
    }

    var lecturer={
        id:1,
        surname:"surname",
        patronymic:"patronymic",
        name:"name" };

    var jsonSend = JSON.stringify(lecturer);
    xhr.open('Post',"/ListLecture", true, "root","root");
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.onreadystatechange = reqReadyStateChange;
    xhr.send(jsonSend);
    console.log(jsonSend);
}