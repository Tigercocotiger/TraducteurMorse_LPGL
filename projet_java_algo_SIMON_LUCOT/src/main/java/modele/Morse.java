package modele;

public class Morse {
    char carac;
    String code_morse;


/*
   _____                _                   _
  / ____|              | |                 | |
 | |     ___  _ __  ___| |_ _ __ _   _  ___| |_ ___ _   _ _ __
 | |    / _ \| '_ \/ __| __| '__| | | |/ __| __/ _ \ | | | '__|
 | |___| (_) | | | \__ \ |_| |  | |_| | (__| ||  __/ |_| | |
  \_____\___/|_| |_|___/\__|_|   \__,_|\___|\__\___|\__,_|_|  */


    public Morse(char carac, String code_morse) {
        this.carac = carac;
        this.code_morse = code_morse;
    }

    public Morse(){

    }


    /*
   _____      _   _              _______      _   _
  / ____|    | | | |            / / ____|    | | | |
 | |  __  ___| |_| |_ ___ _ __ / / (___   ___| |_| |_ ___ _ __
 | | |_ |/ _ \ __| __/ _ \ '__/ / \___ \ / _ \ __| __/ _ \ '__|
 | |__| |  __/ |_| ||  __/ | / /  ____) |  __/ |_| ||  __/ |
  \_____|\___|\__|\__\___|_|/_/  |_____/ \___|\__|\__\___|_|

                                                               */
    public char getCarac() {
        return carac;
    }

    public void setCarac(char carac) {
        this.carac = carac;
    }

    public String getCode_morse() {
        return code_morse;
    }

    public void setCode_morse(String code_morse) {
        this.code_morse = code_morse;
    }

    @Override
    public String toString() {
        return "Morse{" +
                "carac=" + carac +
                ", code_morse='" + code_morse + '\'' +
                '}';
    }
}
