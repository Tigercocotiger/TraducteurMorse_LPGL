package com.example.projet_java_algo_simon_lucot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import modele.Arbre;
import modele.Liste;

import java.io.*;
import java.util.regex.Pattern;

public class TradControlleur {
    @FXML
    private Button btn_reverse;

    @FXML
    private Pane btn_trad;

    @FXML
    private Label lbl_langue_saisie;

    @FXML
    private Label lbl_langue_trad;

    @FXML
    private Label lbl_titre;

    @FXML
    private TextArea txt_saisie;

    @FXML
    private TextArea txt_trad;

    @FXML
    private Button btn_fichiers;

    //-------------------traduction de fichiers
    @FXML
    private Pane pnl_fichier;

    @FXML
    private RadioButton rbtn_txt;

    @FXML
    private RadioButton rbtn_morse;





    @FXML
    protected void onClickReverse() {//Inverse le langage de saisie et le langage traduit
        String old=lbl_langue_saisie.getText();
        lbl_langue_saisie.setText(lbl_langue_trad.getText());
        lbl_langue_trad.setText(old);
        txt_saisie.clear();
        txt_trad.clear();

    }

    // Fonction qui traduit le texte de la boite de gauche et l'affiche dans la boite de droite
    @FXML
    protected void onClickTrad() throws FileNotFoundException {

        txt_trad.clear(); // On clear pour ne pas avoir le texte d'avant
        String lignes[] = txt_saisie.getText().split("\\r?\\n"); // On fait un tableau, chaque élément = une ligne

        for (String ligne : lignes) { // On fait une action pour chaque élément de ce tableau ( une ligne )

            if (lbl_langue_saisie.getText().equals("Morse")) { // Test si nous sommes dans le cas morse -> texte
                Pattern p = Pattern.compile("[-. ]"); // Création du patten pour tester si nous avons bien que des caractères propres au code morse

                if (p.matcher(ligne).find()) { // On regarde si la ligne match le pattern
                    txt_saisie.setStyle("-fx-border-color: none;");
                    Arbre a = new Arbre(); // On crée un arbre de traduction

                    if (txt_trad.getText().isBlank()) { // On regarde si la boite de droite est vide ( cas où nous sommes dans la première ligne traité
                        txt_trad.setText(a.MorseToPhrase(ligne));
                    } else txt_trad.setText(txt_trad.getText() + "\n" + a.MorseToPhrase(ligne));

                } else { // Cas où on match pas le pattern regex
                    txt_saisie.setStyle("-fx-border-color: red;");
                    txt_trad.clear();
                }

            } else { // Cas on nous sommes morse -> texte
                Pattern p = Pattern.compile("^[a-zA-Z \r]+$"); // Pattern pour les lettres

                if (p.matcher(ligne).find()) { // On regarde si la ligne match
                    txt_saisie.setStyle("-fx-border-color: none;");
                    Liste l = new Liste(); // Création de la liste de traduction

                    if (txt_trad.getText().isBlank()) { // On regarde si la boite de droite est vide ( cas où nous sommes dans la première ligne traité
                        txt_trad.setText(l.PhraseToMorse(ligne));
                    } else txt_trad.setText(txt_trad.getText() + "\n" + l.PhraseToMorse(ligne));

                } else { // Cas où on match pas le pattern regex
                    txt_saisie.setStyle("-fx-border-color: red;");
                    txt_trad.clear();
                }
            }
        }
    }

    @FXML
    protected void onClickFichiers(){//affiche le panel de traduction de fichiers
        pnl_fichier.setVisible(!pnl_fichier.isVisible());
    }

    @FXML
    protected void onClickTradFichiers() throws IOException {//traduit un fichier
        if(rbtn_txt.isSelected()){//si on traduit un fichier de texte
            File file = new File("src/main/java/modele/texte_a_traduire.txt");
            // Créer l'objet File Reader
            StringBuffer sb=readFile(file);
            Liste l =new Liste();
            File file2 = new File("src/main/java/modele/morse_a_traduire.txt");
            FileWriter fw = new FileWriter(file2);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(l.PhraseToMorse(sb.toString()));
            bw.close();
        }else{//si on traduit un fichier de morse
            File file = new File("src/main/java/modele/morse_a_traduire.txt");
            // Créer l'objet File Reader
            StringBuffer sb=readFile(file);
            Arbre a= new Arbre();
            File file2 = new File("src/main/java/modele/texte_a_traduire.txt");
            FileWriter fw = new FileWriter(file2);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(a.MorseToPhrase(sb.toString()));
            bw.close();
        }
    }

    public StringBuffer readFile(File file) throws IOException {//lit un fichier et retourne son contenu
        FileReader fr = new FileReader(file);
        // Créer l'objet BufferedReader
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String line;
        while((line = br.readLine()) != null)
        {
            // ajoute la ligne au buffer
            sb.append(line);
            sb.append("\n");
        }
        fr.close();
        return sb;
    }

}