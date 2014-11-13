
// To make more beautiful
$(document).ready(function($) {
    $(".rowMatch").click(function() {
        $('.rowMatch').removeClass("activeRowHockeyLive");
        $(this).addClass("activeRowHockeyLive");

        $(".match").fadeOut("slow");
        $(".tab_penalite_compteur").fadeOut("slow");

        $(".match").fadeIn("slow");
        $(".tab_penalite_compteur").fadeIn("slow");
    });

    $("#closeGame").click(function() {
        $(".match").fadeOut("slow");
        $(".tab_penalite_compteur").fadeOut("slow");
    });
});