
// Enum
var messageType = Object.freeze({
    ThreadPerObject: "a", 
    ThreadPerRequest: "b", 
    GetMatch: "c",
    GetMatchs: "d",
    GetPari: "e",
    SetPari: "f",
    GetCompteursForAMatch: "g",
    GetPenalitesForAMatch: "h",
    GetTimeForAMatch: "i",
    GetAllCompteurs: "j",
    GetAllPenalites: "k"
});

// Thread per request/object
var tpr = messageType.ThreadPerRequest;
var tpo = messageType.ThreadPerObject;

// Requests
function getMatch(id) { return tpr + messageType.GetMatch + "(" + id + ")"; }
function getMatchs() { return tpr + messageType.GetMatchs; }
function getPari() { return tpo + messageType.GetPari; }
function setPari() { return tpo + messageType.SetPari; }
function getCompteursForAMatch(id) { return tpr + messageType.GetCompteursForAMatch + "(" + id + ")"; }
function getPenalitesForAMatch(id) { return tpr + messageType.GetPenalitesForAMatch + "(" + id + ")"; }
function getTimeForAMatch(id) { return tpr + messageType.GetTimeForAMatch + "(" + id + ")"; }
function getAllCompteurs() { return tpr + messageType.GetAllCompteurs; }
function getAllPenalites() { return tpr + messageType.GetAllPenalites; }