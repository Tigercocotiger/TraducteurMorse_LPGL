package modele;

import java.io.FileNotFoundException;

public class Arbre {
    private Morse val;
    private Arbre fDroit;
    private Arbre fGauche;
    private Liste liste=new Liste();

    private String phrase = "";

    public Arbre() throws FileNotFoundException {
        //this.RemplirArbre();
        this.ConstruireArbre();

    }


    public Arbre(Morse v) throws FileNotFoundException {
        this.fGauche = null;
        this.fDroit = null;
        this.val = v;
    }

    public Arbre(Morse v, Arbre fd, Arbre fg) throws FileNotFoundException {
        this.val = v;
        this.fDroit = fd;
        this.fGauche = fg;
    }


    public Morse getVal() {
        return val;
    }

    public void setVal(Morse val) {
        this.val = val;
    }

    public Arbre getfDroit() {
        return fDroit;
    }

    public void setfDroit(Arbre fDroit) {
        this.fDroit = fDroit;
    }

    public Arbre getfGauche() {
        return fGauche;
    }

    public void setfGauche(Arbre fGauche) {
        this.fGauche = fGauche;
    }

    public boolean estVide() {
        return (this == null);
    }

    @Override
    public String toString() {
        return "Arbre{" +
                "val=" + val +
                ", fDroit=" + fDroit +
                ", fGauche=" + fGauche +
                '}';
    }

    /*
        ______               _   _
        |  ____|             | | (_)
        | |__ ___  _ __   ___| |_ _  ___  _ __  ___
        |  __/ _ \| '_ \ / __| __| |/ _ \| '_ \/ __|
        | | | (_) | | | | (__| |_| | (_) | | | \__ \
        |_|  \___/|_| |_|\___|\__|_|\___/|_| |_|___/

                                                  */
    //On cherche un caractère à partir d'une string morse
    public char ChercherCaracAbre(String s) {
        if (s.length() == 0) { // On regarde si le string est vide = on est au bon endroit
            return this.val.carac; // On retourne donc la valeur du caractère
        } else {
            char c = s.charAt(0); // On prends le premier caractère de la string s ( Valeur = - ou . )
            s.substring(1); // On enlève ce même caractère de s

            if (c == '.') {
                if (this.fGauche != null) { // On regarde si le fils gauche est pas null (Cas où la string n'existe pas)
                    return this.fGauche.ChercherCaracAbre(s.substring(1)); // On relance la fonction à partir du fils gauche
                }
            } else {
                if (this.fDroit != null) { // On regarde si le fils droit est pas null (Cas où la string n'existe pas)
                    return this.fDroit.ChercherCaracAbre(s.substring(1)); // On relance la fonction à partir du fils gauche
                }
            }
        }
        return 0;
    }

    // Fonction pour construire l'arbre de base de traduction
    public void ConstruireArbre() throws FileNotFoundException {
        if (this.getVal() == null) { // Cas où on est dans le premier cas
            Arbre fg = new Arbre(new Morse('E', "."), null, null); // On affecte nous même les valeurs E
            Arbre fd = new Arbre(new Morse('T', "-"), null, null); // Et T

            this.setfGauche(fg); // On affecte le fils gauche à cet arbre
            fg.ConstruireArbre(); // On relance la fonction à partir du fils gauche
            this.setfDroit(fd); // On affecte le fils droit à cet arbre
            fd.ConstruireArbre(); // On relance la fonction à partir du fils droit

        } else {
            if (val.code_morse.length() < 5) { // On regarde si la valeur du code_morse est inférieur à 5 car aucune traduction n'a plus de 4 caractères
                this.val.setCarac(liste.getCharByMorse(this.val.code_morse)); // On affecte le caractère en fonction de la liste de traduction
                Arbre fg = new Arbre(new Morse('0', val.code_morse + "."), null, null); // On crée le fils gauche
                Arbre fd = new Arbre(new Morse('0', val.code_morse + "-"), null, null); // On crée le fils droit
                this.setfGauche(fg);
                fg.ConstruireArbre();
                this.setfDroit(fd);
                fd.ConstruireArbre();
            }
        }
    }

    //Fonction qui permet de faire en sorte de traduire un code morse, vers une phrase texte
    public String MorseToPhrase(String s){
        String[] sanssaut=s.split("\n");//on separe la chaine en fonction des sauts de lignes
        for(int it=0;it<sanssaut.length;it++){
            String[] sansespace = sanssaut[it].split("/");// On enlève les espaces entre les mots en morse
            for(int i = 0; i< sansespace.length; i++) {
                String[] result = sansespace[i].split(" ");
                for(int j = 0; j< result.length; j++){
                    phrase = phrase + this.ChercherCaracAbre(result[j]);// On cherche le caractère à partir du code morse
                }
                phrase = phrase + " ";//on change de mot
            }
            phrase =  phrase+"\n";//on remet les sauts de ligne
        }
        return phrase;
    }
}
