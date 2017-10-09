package ia2_trab1;

import viul.Home;

/**
 *
 * @author Bruno
 */
public class IA2_Trab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Investidor i = new Investidor();
        Home home = new Home(i);
        home.setVisible(true);
    }
}
