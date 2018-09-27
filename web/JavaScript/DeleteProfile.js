function deleteProfile(){
    var xhr = new XMLHttpRequest();
    var params ={
        id:1
    };
    var jsonSend = JSON.stringify(params);
    xhr.open('Post',"/DeleteProfile", true, "root","root");
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            var json  = JSON.parse(xhr.responseText);
            alert(json.message);
        }else {
            alert(xhr.status + " "+ xhr.readyState);
        }
    }
    xhr.send(jsonSend);
}