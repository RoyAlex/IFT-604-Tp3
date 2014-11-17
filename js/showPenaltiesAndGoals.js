
// Show new penalties
function ShowPenalites(data)
{
    var size = Object.keys(data).length;

    for (var i = 0; i < size; i++)
    {     
        var text = "Penalty: " + data[i].penalite_nomEquipe + " - " + data[i].penalite_nomJoueur +
            " at " + cleanTime(data[i].penalite_timeMin, data[i].penalite_timeSec) + 
            " for " + data[i].penalite_duree + " minutes";
    
        // Show them
        $().toastmessage(TOAST_PENALTY, text);    
    }
}

// Show new penalties
function ShowGoals(data)
{
    var size = Object.keys(data).length;

    for (var i = 0; i < size; i++)
    {     
        var text = "Goal: " + data[i].compteur_nomEquipe + " - " + data[i].compteur_nomJoueur + 
            " scores at " + cleanTime(data[i].compteur_timeMin, data[i].compteur_timeSec);
    
        // Show them
        $().toastmessage(TOAST_GOAL, text);    
    }
}