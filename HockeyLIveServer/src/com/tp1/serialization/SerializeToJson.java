package com.tp1.serialization;

import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tp1.customMatchClass.MatchPari;
import com.tp1.customMatchClass.MatchTime;
import com.tp1.dto.CompteurDTO;
import com.tp1.dto.MatchDTO;
import com.tp1.dto.PenaliteDTO;

public class SerializeToJson {
    
    private Object data;
    
    public SerializeToJson(Object data)
    {
        this.data = data;
    }
    
    public String toJson() {  
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = new Gson();
        
        if (data instanceof MatchDTO)
        {
            gson = gsonBuilder.registerTypeAdapter(MatchDTO.class, new SerializeToJson.MatchDTOAdapter()).create();
        }
        else if (data instanceof MatchTime)
        {
            gson = gsonBuilder.registerTypeAdapter(MatchTime.class, new SerializeToJson.MatchTimeAdapter()).create();
        }
        else if (data instanceof MatchPari)
        {
            gson = gsonBuilder.registerTypeAdapter(MatchPari.class, new SerializeToJson.MatchPariAdapter()).create();
        }
        else if (data instanceof List<?>)
        {
            if (((List<?>)data).size() > 0)
            {
                // List of MatchDTO
                if(((List<?>)data).get(0) instanceof MatchDTO)
                {
                    gson = gsonBuilder.registerTypeAdapter(MatchDTO.class, new SerializeToJson.MatchDTOAdapter()).create();
                }
                // List of CompteurDTO
                else if(((List<?>)data).get(0) instanceof CompteurDTO)
                {
                    gson = gsonBuilder.registerTypeAdapter(CompteurDTO.class, new SerializeToJson.CompteurDTOAdapter()).create();
                }
                // List of PenaliteDTO
                else if(((List<?>)data).get(0) instanceof PenaliteDTO)
                {
                    gson = gsonBuilder.registerTypeAdapter(PenaliteDTO.class, new SerializeToJson.PenaliteDTOAdapter()).create();
                }
            }
            else
                return null;
        }
        
        return gson.toJson(data);
    }
    
    private static class MatchPariAdapter implements JsonSerializer<MatchPari> {
        public JsonElement serialize(MatchPari matchPari, Type type, JsonSerializationContext jsc) {
          JsonObject jsonObject = new JsonObject();
          jsonObject.addProperty("matchPari_matchId", matchPari.getMatch().getIdMatch());
          jsonObject.addProperty("matchPari_montant", matchPari.getMontant());
          jsonObject.addProperty("matchPari_estEnregistrer", matchPari.isEstEnregistrer());
          return jsonObject;      
        }
    }
    
    private static class MatchTimeAdapter implements JsonSerializer<MatchTime> {
        public JsonElement serialize(MatchTime matchTime, Type type, JsonSerializationContext jsc) {
          JsonObject jsonObject = new JsonObject();
          jsonObject.addProperty("matchTime_timeMin", matchTime.getTime().getMinutes());
          jsonObject.addProperty("matchTime_timeSec", matchTime.getTime().getSeconds());
          jsonObject.addProperty("matchTime_periode", matchTime.getPeriode());
          return jsonObject;      
        }
    }

    private static class MatchDTOAdapter implements JsonSerializer<MatchDTO> {
        public JsonElement serialize(MatchDTO match, Type type, JsonSerializationContext jsc) {
          JsonObject jsonObject = new JsonObject();
          jsonObject.addProperty("match_id", match.getIdMatch());
          jsonObject.addProperty("match_periode", match.getPeriodeCourante());
          jsonObject.addProperty("match_nbrPenalite", match.getNbrPenalite());
          jsonObject.addProperty("match_pointageLocal", match.getPointageEquipeLocal());
          jsonObject.addProperty("match_periodeVisiteur", match.getPointageEquipeVisiteur());
          jsonObject.addProperty("match_nomEquipeLocal", match.getEquipeLocal().getNomEquipe());
          jsonObject.addProperty("match_nomEquipeVisiteur", match.getEquipeVisiteur().getNomEquipe());
          jsonObject.addProperty("match_timeMin", match.getMatchTime().getMinutes());
          jsonObject.addProperty("match_timeSec", match.getMatchTime().getSeconds());
          jsonObject.addProperty("match_idEquipeVisiteur", match.getEquipeVisiteur().getIdEquipe());
          jsonObject.addProperty("match_idEquipeLocal", match.getEquipeLocal().getIdEquipe());
          return jsonObject;      
        }
    }
    
    private static class PenaliteDTOAdapter implements JsonSerializer<PenaliteDTO> {
        public JsonElement serialize(PenaliteDTO penalite, Type type, JsonSerializationContext jsc) {
          JsonObject jsonObject = new JsonObject();
          jsonObject.addProperty("penalite_id", penalite.getIdPenalite());
          jsonObject.addProperty("penalite_duree", penalite.getDuree());
          jsonObject.addProperty("penalite_periode", penalite.getPeriode());
          jsonObject.addProperty("penalite_nomJoueur", penalite.getJoueur().getNomJoueur());
          jsonObject.addProperty("penalite_nomEquipe", penalite.getEquipe().getNomEquipe());
          jsonObject.addProperty("penalite_timeMin", penalite.getTimeOfPenalite().getMinutes());
          jsonObject.addProperty("penalite_timeSec", penalite.getTimeOfPenalite().getSeconds());
          return jsonObject;      
        }
    }
    
    private static class CompteurDTOAdapter implements JsonSerializer<CompteurDTO> {
        public JsonElement serialize(CompteurDTO compteur, Type type, JsonSerializationContext jsc) {
          JsonObject jsonObject = new JsonObject();
          jsonObject.addProperty("compteur_id", compteur.getIdCompteur());
          jsonObject.addProperty("compteur_periode", compteur.getPeriode());
          jsonObject.addProperty("compteur_nomJoueur", compteur.getJoueur().getNomJoueur());
          jsonObject.addProperty("compteur_nomEquipe", compteur.getEquipe().getNomEquipe());
          jsonObject.addProperty("compteur_timeMin", compteur.getTimeOfGoal().getMinutes());
          jsonObject.addProperty("compteur_timeSec", compteur.getTimeOfGoal().getSeconds());
          return jsonObject;      
        }
    }
}
