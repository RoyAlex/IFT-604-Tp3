
$(document).ready(function($) {

    function matchFadeOut()
    {
        $(".match").fadeOut("slow");
        $(".tab_penalite_compteur").fadeOut("slow");
    }
    
    function matchFadeIn()
    {
        $(".match").fadeIn("slow");
        $(".tab_penalite_compteur").fadeIn("slow");
    }

    $("#matchsTable").on('click', 'tr', function(event) {
        $('.rowMatch').removeClass("activeRowHockeyLive");
        $(this).addClass("activeRowHockeyLive");

        matchFadeOut();
        matchFadeIn();
    });

    $("#closeGame").click(function() {
        matchFadeOut();
    });
    
    $("#refreshGame").click(function() {
        matchFadeOut();
        
        // Get the id of the row
        var id = $("#idMatch").html();
        showGameById(id);
        
        matchFadeIn();
    });
});