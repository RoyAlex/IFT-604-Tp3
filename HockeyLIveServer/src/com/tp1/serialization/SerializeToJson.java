package com.tp1.serialization;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
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
        else if (data instanceof List<?>)
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
        
        return gson.toJson(data);
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
          return jsonObject;      
        }
    }
    
    private static class PenaliteDTOAdapter implements JsonSerializer<PenaliteDTO> {
        public JsonElement serialize(PenaliteDTO penalite, Type type, JsonSerializationContext jsc) {
          JsonObject jsonObject = new JsonObject();
          jsonObject.addProperty("penalite_id", penalite.getIdPenalite());
          jsonObject.addProperty("penalite_duree", penalite.getDuree());
          return jsonObject;      
        }
    }
    
    private static class CompteurDTOAdapter implements JsonSerializer<CompteurDTO> {
        public JsonElement serialize(CompteurDTO compteur, Type type, JsonSerializationContext jsc) {
          JsonObject jsonObject = new JsonObject();
          jsonObject.addProperty("compteur_id", compteur.getIdCompteur());
          jsonObject.addProperty("compteur_nomEquipe", compteur.getEquipe().getNomEquipe());
          return jsonObject;      
        }
    }
}
