package modele;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Liste {
    private Morse val;
    private Liste suiv;


    /*
   _____                _                   _
  / ____|              | |                 | |
 | |     ___  _ __  ___| |_ _ __ _   _  ___| |_ ___ _   _ _ __ ___
 | |    / _ \| '_ \/ __| __| '__| | | |/ __| __/ _ \ | | | '__/ __|
 | |___| (_) | | | \__ \ |_| |  | |_| | (__| ||  __/ |_| | |  \__ \
  \_____\___/|_| |_|___/\__|_|   \__,_|\___|\__\___|\__,_|_|  |___/

                                                                   */

    public Liste(Morse v, Liste s) {
        this.val = v;
        this.suiv = s;
    }

    public Liste() throws FileNotFoundException {
        this.txt_to_liste();
    }


    /*
             _                       _
            | |     ___             | |
   __ _  ___| |_   ( _ )    ___  ___| |_
  / _` |/ _ \ __|  / _ \/\ / __|/ _ \ __|
 | (_| |  __/ |_  | (_>  < \__ \  __/ |_
  \__, |\___|\__|  \___/\/ |___/\___|\__|
   __/ |
  |___/                                  */


    public Morse getVal() {
        return val;
    }

    public void setVal(Morse val) {
        this.val = val;
    }

    public Liste getSuiv() {
        return suiv;
    }

    public void setSuiv(Liste suiv) {
        this.suiv = suiv;
    }

    /*
  _______       _____ _        _
 |__   __|     / ____| |      (_)
    | | ___   | (___ | |_ _ __ _ _ __   __ _
    | |/ _ \   \___ \| __| '__| | '_ \ / _` |
    | | (_) |  ____) | |_| |  | | | | | (_| |
    |_|\___/  |_____/ \__|_|  |_|_| |_|\__, |
                                        __/ |
                                       |___/ */
    @Override
    public String toString() {
        return "Liste{" +
                "val=" + val +
                ", suiv=" + suiv +
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

    // ajouter une élément à la fin d'une liste
    public void add(Liste element) {
        if (this.getSuiv() == null) {
            setSuiv(element);
        } else {
            this.getSuiv().add(element);
        }
    }

    // On teste l'éxistance d'un élément dans une liste
    public boolean Exist(Liste element) {
        if (this.getSuiv() == null) {
            return false;
        } else {
            if (getSuiv() == element) {
                return true;
            } else {
                return this.getSuiv().Exist(element);
            }
        }
    }

    // On verifie si un char existe dans la liste
    public boolean Exist(char c) {
        if (this.getVal() == null) {
            return false;
        } else {
            if (this.getVal().getCarac() == c) {
                return true;
            } else {
                try {
                    return this.getSuiv().Exist(c);
                } catch (Exception e) {
                    return false;
                }

            }
        }
    }

    // On supprime un caratère de la liste
    public void Remove(char c) {
        if (this.Exist(c)) {
            if (this.getSuiv() != null) {
                if (getSuiv().getVal().getCarac() == c) {
                    setSuiv(getSuiv().getSuiv());
                } else {
                    getSuiv().Remove(c);
                }
            }
        }
    }

    // Traduction du fichier Morse.txt -> Liste de traduction
    public void txt_to_liste() throws FileNotFoundException {
        File fichier_morse = new File("src/main/java/modele/Morse"); // On cherche le fichier
        Scanner scanner = new Scanner(fichier_morse); // On fait un scanner

        while (scanner.hasNextLine()) { // Tant qu'on a une ligne on fait ce qui suit
            String data = scanner.nextLine(); // On affecte la ligne à une string

            if (data.charAt(0) == 'A') { // On regarde si le premier caractère est un A -> Différent du reste car première fois
                this.setVal(new Morse('A', data.substring(2))); // On ajoute une valeur à cet élément à partir du caractère 2
            } else {
                this.add(new Liste(new Morse(data.charAt(0), data.substring(2)), null)); // On ajoute un élément de la liste et une valeur à cet élément à partir du caractère 2
            }
        }
        scanner.close(); // on ferme le scanner
    }

    // Obenir un caractère à partir d'un code morse
    public char getCharByMorse(String s) {
        if (this.getVal() == null) {
            return '0';
        } else {
            if (this.getVal().getCode_morse().equals(s)) {
                return this.getVal().getCarac();
            } else {
                try {
                    return this.getSuiv().getCharByMorse(s);
                } catch (Exception e) {
                    return '0';
                }

            }
        }
    }

    // Obtenir un code morse à partir d'un caractère
    public String getMorseByChar(char c) {
        if (this.getVal() == null) {
            return null;
        } else {
            if (this.getVal().getCarac() == c) {
                return this.getVal().getCode_morse();
            } else {
                try {
                    return this.getSuiv().getMorseByChar(c);
                } catch (Exception e) {
                    return null;
                }

            }
        }

    }

    // on crée un string morse à partir d'une phrase
    public String PhraseToMorse(String s){
        s = s.toUpperCase();
        s=s.replace("'"," ");
        s=s.replace(".","");
        s=s.replace(",","");
        String str="";
        String[] sanssaut=s.split("\n");
        for(int it=0;it<sanssaut.length;it++){
            String ret = "";
            String[] sansespace = sanssaut[it].split(" ");
            for(int i = 0; i< sansespace.length; i++) {
                for(int j = 0; j< sansespace[i].length(); j++){
                    if (j == sansespace[i].length() - 1){

                        ret = ret + getMorseByChar(sansespace[i].charAt(j));
                    }
                    else {
                        ret = ret + getMorseByChar(sansespace[i].charAt(j)) + " ";
                    }
                }
                if (i != sansespace.length - 1) {
                    ret = ret + "/";
                }
            }
            str+=ret+"\n";
        }
        return str;
    }

}
