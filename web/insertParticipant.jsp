<%@ page import="controllers.entity.Participant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//RU" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Update</title>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="#">CRUD Application</a>
</nav>
<div class="container">
    <form action="Servlet" method="post" name="formAddUser">
        <fieldset>
            <% Participant participant =(Participant)request.getAttribute("participant");%>

            <div class="form-group  mb-2">
                <input required name="nameStudent" type="text" value="" placeholder="Введите имя студента" class="form-control" class="span3" >
            </div>

            <div class="form-group mb-2">
                <input required name="lastNameStudent" type="text" value="" placeholder="Введите фамилию студента"  class="form-control" class="span3">
            </div>

            <div class="form-group  mb-2">
                <input required name="grade" type="text" value=""  placeholder="Введите оценку студента"  class="form-control" class="span3">
            </div>

            <div class="form-group  mb-2">
                <input required name="nameLecture" type="text" value=""  class="form-control" placeholder="Введите имя лектора">
            </div>

            <div class="form-group  mb-2">
                <input  required name="patronymicLecture" type="text" value=""  class="form-control" placeholder="Введите отчество лектора">
            </div>

            <div class="form-group mb-2">
                <input required name="surnameLecture" type="text" value=""  class="form-control" placeholder="Введите фамилию лектора">
            </div>

            <div class="form-group mb-2 ">
                <input required name="nameCourse" type="text" value=""   class="form-control" placeholder="Введите название курса">
            </div>

            <div class="form-group mb-2">
                <textarea required name="comment" type="text" value=""  class="form-control" placeholder="Оставьте комментарий студенту..."></textarea>
            </div>

            <div class="form-group">
                <input type="submit" value="Добавить" class="btn btn-primary" >
                <input type="button" onclick="history.back();" value="Отменить" class="btn"/>
            </div>
        </fieldset>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>
