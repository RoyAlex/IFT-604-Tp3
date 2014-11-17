package com.tp1.Connection;

import java.io.IOException;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;



import com.tp1.DAO.CompteurDAO;
import com.tp1.DAO.MatchDAO;
import com.tp1.DAO.ParisDAO;
import com.tp1.DAO.PenaliteDAO;
import com.tp1.dto.CompteurDTO;
import com.tp1.dto.MatchDTO;
import com.tp1.dto.MessageType;
import com.tp1.dto.PenaliteDTO;
import com.tp1.library.Compteur;
import com.tp1.library.Match;
import com.tp1.library.Paris;
import com.tp1.library.Penalite;

public class DataManagement {
	private static MatchDAO matchDAO = new MatchDAO();
	private static ParisDAO parisDAO = new ParisDAO();
	private static CompteurDAO compteurDAO = new CompteurDAO();
	private static PenaliteDAO penaliteDAO = new PenaliteDAO();

	public DataManagement() {
	}

	public void manageData(Socket handler, String data) {
		// Get the first char that correspond to the message code
		/*MessageType msgType = MessageType.values()[Integer.valueOf(data
				.substring(0, 1))];*/
		String str = data.substring(0, 1);
		data = data.substring(1);

		// Print the received message (Used for logging)
		System.out.println("Message received : " + data);
		
		
		if(MessageType.GetMatchs.getValue().equals(str)){
            try {
                List<Match> matchs = matchDAO.listMatchs();
                
                List<MatchDTO> matchsDTO = new ArrayList<MatchDTO>(matchs != null ? matchs.size() : 0);
                if (matchs != null) {
                  for (Match match : matchs) {
                      matchsDTO.add(DozerBeanMapperSingletonWrapper.getInstance().map(match, MatchDTO.class));
                  }
                }
                
                ServerUtils.SerializeAndSendData(matchsDTO, handler);
            } catch (IOException e) {
                System.out.println("Serialize parsing error : " + e.toString());
            }
        }
		else if(MessageType.GetMatch.getValue().equals(str)){
            try {
                String strId = data.substring(data.indexOf("(") + 1,
                        data.indexOf(")"));
                
                MatchDTO matchDTO = new MatchDTO();
                Match match = matchDAO.getMatch(Integer.parseInt(strId));
                
                if(match != null)
                    matchDTO = DozerBeanMapperSingletonWrapper.getInstance().map(match, MatchDTO.class);
                else
                    matchDTO = null;
                
                ServerUtils.SerializeAndSendData(matchDTO, handler);
            } catch (IOException e) {
                System.out.println("Serialize parsing error : " + e.toString());
            }
        }
		else if(MessageType.GetAllCompteurs.getValue().equals(str)){
            try {
                List<Compteur> allCompteurs = compteurDAO.listCompteurs();
                
                List<CompteurDTO> compteursDTO = new ArrayList<CompteurDTO>(allCompteurs != null ? allCompteurs.size() : 0);
                if (allCompteurs != null) {
                  for (Compteur compteur : allCompteurs) {
                      compteur.setDejaAfficher(true);
                      compteurDAO.updateCompteur(compteur);
                      compteursDTO.add(DozerBeanMapperSingletonWrapper.getInstance().map(compteur, CompteurDTO.class));
                  }
                }
                
                ServerUtils.SerializeAndSendData(compteursDTO, handler);
            } catch (IOException e) {
                System.out.println("Serialize parsing error : " + e.toString());
            }
        }
		else if(MessageType.GetAllPenalites.getValue().equals(str)){
            try {
                List<Penalite> allPenalites = penaliteDAO.listPenalites();

                List<PenaliteDTO> penalitesDTO = new ArrayList<PenaliteDTO>(allPenalites != null ? allPenalites.size() : 0);
                if (allPenalites != null) {
                  for (Penalite penalite : allPenalites) {
                      penalite.setDejaAfficher(true);
                      penaliteDAO.updatePenalite(penalite);
                      penalitesDTO.add(DozerBeanMapperSingletonWrapper.getInstance().map(penalite, PenaliteDTO.class));
                  }
                }
                
                ServerUtils.SerializeAndSendData(penalitesDTO, handler);
            } catch (IOException e) {
                System.out.println("Serialize parsing error : " + e.toString());
            }
        }
		else if(MessageType.SetPari.getValue().equals(str)){  
                String[] parts = data.split(";");
                // threading part
                Match match = matchDAO.getMatch(Integer.parseInt(parts[2]));

                ParisThread pt = new ParisThread(match, parts, handler);
                TamponManager.getInstance().GetTamponMatchAt(match.getIdMatch()).execute(pt);
    
        }
		else if(MessageType.GetCompteursForAMatch.getValue().equals(str)){  
            try {
                String strId = data.substring(data.indexOf("(") + 1,
                        data.indexOf(")"));
                List<Compteur> compteursList = compteurDAO
                        .getCompteursForAMatch(Integer.parseInt(strId));
                
                 List<CompteurDTO> compteursDTO = new ArrayList<CompteurDTO>(compteursList != null ? compteursList.size() : 0);
                    if (compteursList != null) {
                      for (Compteur compteur : compteursList) {
                          compteursDTO.add(DozerBeanMapperSingletonWrapper.getInstance().map(compteur, CompteurDTO.class));
                      }
                    }
                    
                ServerUtils.SerializeAndSendData(compteursDTO, handler);
            } catch (IOException e) {
                System.out.println("Serialize parsing error : " + e.toString());
            }
        }
		else if(MessageType.GetPenalitesForAMatch.getValue().equals(str)){  
            try {
                String strId = data.substring(data.indexOf("(") + 1,
                        data.indexOf(")"));
                List<Penalite> penalitesList = penaliteDAO
                        .getPenalitesForAMatch(Integer.parseInt(strId));
                
                List<PenaliteDTO> penalitesDTO = new ArrayList<PenaliteDTO>(penalitesList != null ? penalitesList.size() : 0);
                if (penalitesList != null) {
                  for (Penalite penalite : penalitesList) {
                      penalitesDTO.add(DozerBeanMapperSingletonWrapper.getInstance().map(penalite, PenaliteDTO.class));
                  }
                }
                
                ServerUtils.SerializeAndSendData(penalitesDTO, handler);
            } catch (IOException e) {
                System.out.println("Serialize parsing error : " + e.toString());
            }
        }
		else if(MessageType.GetTimeForAMatch.getValue().equals(str)){  
            try {
                String strId = data.substring(data.indexOf("(") + 1,
                        data.indexOf(")"));
                Match match = matchDAO.getMatch(Integer.parseInt(strId));
                MatchTime matchTime = new MatchTime(match.getMatchTime(), Integer.parseInt(match.getPeriodeCourante()));
                
                ServerUtils.SerializeAndSendData(matchTime, handler);
            } catch (IOException e) {
                System.out.println("Serialize parsing error : " + e.toString());
            }
        }
		else if(MessageType.GetPari.getValue().equals(str)){  
            try {
                String[] parts = data.split(";");
                String result = "NULL";
                float montant = 0;
                Match match = matchDAO.getMatch(Integer.parseInt(parts[2]));
                Paris paris = parisDAO.getParisFromUserAndMatch(parts[1], match);
                int equipeGagnante = Integer.parseInt(parts[3].trim()); // fix a problem
                
                if(paris!=null){
                    if(equipeGagnante == paris.getEquipe().getIdEquipe()){
                        //Gagnant
                        montant = (float) (paris.getMontant() * 1.75);
                    }
                    else if(equipeGagnante == 0){
                        //Null
                        montant = 0;
                    }
                    else{
                        //Perdant
                        montant -= paris.getMontant();
                    }
                    result = Float.toString(montant);
                }
               
                ServerUtils.SerializeAndSendData(result, handler);
            } catch (IOException e) {
                System.out.println("Serialize parsing error : " + e.toString());
            }
        }
    }
}
