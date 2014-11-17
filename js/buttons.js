
function getButtonsClick()
{
    // Retrieve information for a match
    $("#matchsTable").on('click', 'tr', function(event) {
        // Get the id of the row
        var id = $(this).find("td").eq(0).html();
        
        showGameById(id);
    });
    
    $("#btnPari").click(function() {
        var periodeCourante = $("#periodeMatch").html().substring(10);
        var idMatch = $("#idMatch").html();
        var montant = $("#pari_montant").val();
        var idEquipe;
        
        var isEquipeVisiteur = $("#option1").is(':checked');

        if (isEquipeVisiteur)
            idEquipe = $("#option1").val();
        else
            idEquipe = $("#option2").val();
        
        // periodeCourante < 3 is also verified on server side
        if (montant != "" && montant > 0 && idMatch != "" 
            && periodeCourante != "End" && periodeCourante < 3)
        {
            requestSetPari(idMatch, idEquipe, montant);
        }
        else
        {
            alert("You cannot bet, please check that you put a price and the period is not the third");
        }
    });
    
    $("#btnGetPari").click(function() {
        var idMatch = $("#idMatch").html();
        var idEquipe;
        
        var isEquipeVisiteur = $("#option1").is(':checked');

        if (isEquipeVisiteur)
            idEquipe = $("#option1").val();
        else
            idEquipe = $("#option2").val();

        if (idMatch != "")     
        {
            requestGetPari(idMatch, idEquipe);
        }
    });
}