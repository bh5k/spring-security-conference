$(document).ready(() => {
    $.ajax({
        url: "http://localhost:8080/conference/user"
    }).then(data => {
        $('.firstname').append(data.firstname);
        $('.lastname').append(data.lastname);
        $('.age').append(data.age);
    });
});