
// Show all matches
function fillMatchsTable(data)
{
    for (var i = 0; i < 10; i++)
    {      
        $('#matchsTable').append('<tr class="rowMatch"><td>' + data[i].match_id + 
            '</td><td>' + data[i].match_nomEquipeVisiteur + " VS " + data[i].match_nomEquipeLocal + '</td></tr>');       
    }
}

// Show a single match
function fillMatch(data)
{
    $("#idMatch").html(data.match_id);
    $("#periodeMatch").html("Periode : " + data.match_periode);
    $("#timeMatch").html(cleanTime(data.match_timeMin, data.match_timeSec));
    
    $("#nomEquipeVisiteur").html(data.match_nomEquipeVisiteur);
    $("#butEquipeVisiteur").html(data.match_periodeVisiteur);
    $("#nomEquipeLocal").html(data.match_nomEquipeLocal);
    $("#butEquipeLocal").html(data.match_pointageLocal);
    
    $("#option1").val(data.match_idEquipeVisiteur);
    $("#option2").val(data.match_idEquipeLocal);
    
    if (data.match_periode == "End")
        $("#btnGetPari").prop("disabled", false);
    else
        $("#btnGetPari").prop("disabled", true);    
}

function updateTime(data)
{
    $("#periodeMatch").html("Periode : " + data.matchTime_periode);
    $("#timeMatch").html(cleanTime(data.matchTime_timeMin, data.matchTime_timeSec));
}

function fillPenalites(data)
{  
    var size = Object.keys(data).length;

    for (var i = 0; i < size; i++)
    {
        $('#matchPenalites').append('<tr class="rowPenalite"><td>' + data[i].penalite_nomJoueur + " (" +
            data[i].penalite_nomEquipe + ")</td><td>" + data[i].penalite_duree + "</td><td>" +
            cleanTime(data[i].penalite_timeMin, data[i].penalite_timeSec) + "</td><td>" +
            data[i].penalite_periode + '</tr>');
    }
}

function fillCompteurs(data)
{ 
    var size = Object.keys(data).length;

    for (var i = 0; i < size; i++)
    {
        $('#matchCompteurs').append('<tr class="rowCompteur"><td>' + data[i].compteur_nomJoueur + " (" +
            data[i].compteur_nomEquipe + ")</td><td>" + 
            cleanTime(data[i].compteur_timeMin, data[i].compteur_timeSec) + "</td><td>" +
            data[i].compteur_periode + '</tr>'); 
    }        
}

function showPari(data)
{
    var price = data.matchPari_montant;
    var text;
    
    if (price > 0)
        text = "Congratulations, you make " + price + " $ !";
    else if (price == 0)
        text = "Nobody wins: You get back your " + price + " $";
    else
        text = "Sorry, you have lost your money : " + price + " $";
        
    $().toastmessage(TOAST_PARI, text);
}