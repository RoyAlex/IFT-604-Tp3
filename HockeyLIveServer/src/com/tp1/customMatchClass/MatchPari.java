package com.tp1.customMatchClass;

import com.tp1.library.Match;
import com.tp1.library.Paris;

public class MatchPari {

    private Match match;
    private String montant;
    private boolean estEnregistrer;
    
    public MatchPari(Match match, String montant)
    {
        this.setMatch(match);
        this.setMontant(montant);
    }
    
    public MatchPari(Paris paris)
    {
        this.setMatch(paris.getMatch());
        this.setMontant(String.valueOf(paris.getMontant()));
        this.estEnregistrer = paris.getEstEnregistrer();
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public boolean isEstEnregistrer() {
        return estEnregistrer;
    }

    public void setEstEnregistrer(boolean estEnregistrer) {
        this.estEnregistrer = estEnregistrer;
    }
}
