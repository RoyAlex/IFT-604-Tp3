package com.tp1.Connection;

import java.io.IOException;
import java.net.Socket;

import com.tp1.DAO.ParisDAO;
import com.tp1.library.Match;
import com.tp1.library.Paris;

public class ParisThread implements Runnable {

	private Match match;
	private String[] info;
	private Socket handler;

	public ParisThread(Match match, String[] info, Socket handler) {
		this.match = match;
		this.info = info;
		this.handler = handler;
	}

	public void run() {
		
		ParisDAO parisDAO = new ParisDAO();
		Paris paris = parisDAO.getParisFromUserAndMatch(info[1], match);
		

		int periode = 0;
		try {
			periode = Integer.parseInt(match.getPeriodeCourante());
		} catch (NumberFormatException nfe) {
		}

		if (paris== null && periode < 3) {
			
			// doSomething enregistrer dans la BD le paris
			paris = new Paris();
			paris.setIdUtilisateur(info[1]);
			paris.setMatch(match);
			paris.setMontant(Float.parseFloat(info[4]));

			if (Integer.parseInt(info[3]) == match.getEquipeLocal()
					.getIdEquipe()) {
				paris.setEquipe(match.getEquipeLocal());
			} else {
				paris.setEquipe(match.getEquipeVisiteur());
			}

			paris.setEstEnregistrer(true);

			parisDAO.addParis(paris);
			
			try {		
				ServerUtils.SerializeAndSendData(paris.getEstEnregistrer(), handler);
				handler.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			try {		
				ServerUtils.SerializeAndSendData(false, handler);
				handler.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
