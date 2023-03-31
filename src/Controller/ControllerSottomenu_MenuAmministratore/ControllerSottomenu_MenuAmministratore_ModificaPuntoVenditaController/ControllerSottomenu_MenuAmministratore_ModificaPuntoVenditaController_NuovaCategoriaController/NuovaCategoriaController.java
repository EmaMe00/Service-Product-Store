package Controller.ControllerSottomenu_MenuAmministratore.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController.ControllerSottomenu_MenuAmministratore_ModificaPuntoVenditaController_NuovaCategoriaController;

import DAO.CategoriaDAO;
import Model.Merce.Categoria;
import Model.Merce.ICategoria;
import Model.Merce.PuntiVendita;
import Model.Merce.Sottocategoria;
import View.ControlloFinestre.ControlloFinestre;
import View.MenuAmministratore;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.MenuModifichePuntoVendita;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Categorie.EliminaCategoria;
import View.Sottomenu_MenuAmministratore.Sottomenu_MenuAmministratore_ModificaPuntoVendita.Sottomenu_MenuAmministratore_ModificaPuntoVendita_Categorie.NuovaCategoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class NuovaCategoriaController implements ActionListener {

    private NuovaCategoria N;
    private MenuAmministratore M;
    private MenuModifichePuntoVendita M1;
    private String S;
    private boolean managerIdentificator;

    public NuovaCategoriaController(NuovaCategoria n, MenuAmministratore m, MenuModifichePuntoVendita m1,String s,boolean a){
        N = n;
        M = m;
        M1 = m1;
        S = s;
        managerIdentificator = a;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Indietro".equals(e.getActionCommand())){
            if (managerIdentificator){
                ControlloFinestre.CloseFrame(N);
            }
            else {
                ControlloFinestre.SwitchFrameOn(M1);
                ControlloFinestre.CloseFrame(N);
            }
        }

        if("Nuova".equals(e.getActionCommand())){
          Categoria c = new Categoria(N.getCategoria());
          if(!N.getSottocategoria1().equals("") && !N.getSottocategoria2().equals("") && !N.getSottocategoria3().equals("")){

              ICategoria s1 = new Sottocategoria(N.getSottocategoria1());
              ICategoria s2 = new Sottocategoria(N.getSottocategoria2());
              ICategoria s3 = new Sottocategoria(N.getSottocategoria3());

              c.add(s1); c.add(s2); c.add(s3);

              ICategoria ctmp = (ICategoria) c;

              List<PuntiVendita> tmp = M.getPuntivendita();
              PuntiVendita ptmp = tmp.get(Integer.parseInt(S));

              ptmp.addCategoria(ctmp);
              tmp.set(Integer.parseInt(S),ptmp);
              M.getMenuPrincipale().getFD().setPuntiVendita(tmp);

              JOptionPane.showMessageDialog(N, "Categoria con sottocategorie aggiunte correttamente");
              if (managerIdentificator){
                  ControlloFinestre.CloseFrame(N);
              }
              else {
                  ControlloFinestre.SwitchFrameOn(M1);
                  ControlloFinestre.CloseFrame(N);
              }

              CategoriaDAO CDAO = new CategoriaDAO();
              try {
                  CDAO.UpdateCategoria(M.getMenuPrincipale().getFD(),M.getMenuPrincipale().getConnection());
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }

          }
          else{
              JOptionPane.showMessageDialog(N, "Inserisci dei nomi per le categorie !");
          }

        }

        if ("Elimina".equals(e.getActionCommand())){
            EliminaCategoria E = new EliminaCategoria();
            E.StartEliminaCategoria(M,N,S);
            ControlloFinestre.SwitchFrameOff(N);
        }

    }

}
