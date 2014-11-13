
// Make a request to the server with ajax
$(document).ready(function() {
    $('#submit').click(function() {

        $.ajax({
            type: "POST",
            url: "http://127.0.0.1:9090",
            data: {
                request: "bc(1)" // Only for testing
            },
            dataType: "text",
            success: function(responseJson) {
                alert(responseJson);
                var obj = JSON.parse(responseJson);
                //$('#foo').val(obj.nbrDoors);

            }
        });

        return false;
    });
});