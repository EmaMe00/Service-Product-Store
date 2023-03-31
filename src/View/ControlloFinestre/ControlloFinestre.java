package View.ControlloFinestre;

//Classe che utilizzo per gestire tutte le finestra in modo da chiuderle o renderele visibili ed invisibili
//Utilizzo una Interfaccia per fare ciò in modo da applicare su tutte le finestre questi unici metodi
public class ControlloFinestre {

    public static void SwitchFrameOff(IFinestre I){
        I.getFinestra().setVisible(false);

    }

    public static void SwitchFrameOn(IFinestre I){
        I.getFinestra().setVisible(true);
    }

    public static void CloseFrame(IFinestre I){
        I.getFinestra().setVisible(false);
        I.getFinestra().dispose();
    }

}
