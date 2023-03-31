package Business;

import Model.Merce.IProdotti;
import Model.Merce.Prodotti;
import Model.Merce.ProdottoComposito;
import Model.Merce.Servizi;
import Model.Utenti.Utente;
import View.MenuAmministratore;
import View.MenuPrincipale;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.PDPage;
import org.pdfbox.pdmodel.edit.PDPageContentStream;
import org.pdfbox.pdmodel.font.PDType0Font;
import org.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.*;
import java.util.List;

public class CreazionePDF {

    private MenuPrincipale M;

    public CreazionePDF(MenuPrincipale m){
        M=m;
    }

    //Metodo utilizzato per creare un PDF una volta che abbiamo premuto su acquista tutto
    public void CreaPDF() throws Exception{

        try{
            PDDocument doc = new PDDocument(); //documento
            PDPage page = new PDPage(); //pagina
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc,page);
            contentStream.setFont(PDType1Font.COURIER,12);

            contentStream.beginText(); //inizio a scrivere
            contentStream.moveTextPositionByAmount(20,750);
            contentStream.drawString("Prodotti: ");
            contentStream.endText(); // finisco di scrivere

            //Prendo i prodotti ed i servizi una volta che voglio acquistare
            Utente u = M.getFD().getListaUtenti().get(M.getFD().getSessione().getNumUtente());
            List<IProdotti> prodotti = u.getCarrelloProdotti();
            List<Servizi> servizi = u.getCarrelloServizi();

            int y = 730;

            double totProd=0;
            double totServ=0;

            for (int i=0; i<prodotti.size();i++){
                contentStream.beginText();
                contentStream.moveTextPositionByAmount(20,y - ((i+1)*10));
                y = y - ((i+1)*10); // calcoli sui pixel del foglio
                //Se è un prodotto composito allora prelevo tutte le sue in formazioni e tutti i suoi sottoprodotti e
                //gli scrivo tutti quanti nel PDF
                if (prodotti.get(i).getPC()){
                    ProdottoComposito prodotto = (ProdottoComposito) prodotti.get(i);
                    contentStream.drawString("Prodotto composito: " + prodotto.getnome() + " " + M.getFD().getPuntivendita().get(prodotto.getNumPuntoVendita()).getNome());
                    contentStream.endText();
                    for (int j=0; j<prodotto.getSottoprodotti().size();j++){
                        contentStream.beginText();
                        contentStream.moveTextPositionByAmount(20,y - ((i+1)*10));
                        y = y - ((i+1)*10);
                        contentStream.drawString("     " + prodotto.getSottoprodotti().get(j).getnome() + " " + prodotto.getSottoprodotti().get(j).getprezzo());
                        contentStream.endText();
                        totProd = totProd + prodotto.getSottoprodotti().get(j).getprezzo();
                    }

                }else {
                    //Se non è un prodotto composito allora scrivo tutte le informazioni dei prodotti che voglio acquistare
                    y = y - ((i+1)*10);
                    Prodotti prodotto = (Prodotti) prodotti.get(i);
                    contentStream.drawString(prodotto.getnome() + " " + prodotto.getprezzo() + " " + M.getFD().getPuntivendita().get(prodotto.getNumPuntoVendita()).getNome());
                    totProd = totProd + prodotto.getPrezzo();
                    contentStream.endText();
                }

            }


            //Inzio la scrittura sulla parte dei servizi
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20,y - 20);
            contentStream.drawString("Servizi: ");
            contentStream.endText();

            y=y-30;

            int y1=650;// calcoli sui pixel

            //Scrito servizio per servizio tutti quelli presenti nella mia lista di servizi che sto acquistando
            for (int i=0; i<servizi.size();i++){
                contentStream.beginText();
                contentStream.moveTextPositionByAmount(20,y - ((i+1)*10));
                y1 = y - ((i+1)*10);
                contentStream.drawString(servizi.get(i).getNome() + " " + servizi.get(i).getPrezzo() + " " + M.getFD().getPuntivendita().get(servizi.get(i).getNumPuntoVendita()).getNome());
                totServ = totServ + servizi.get(i).getPrezzo();
                contentStream.endText();
            }

            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20,y1 - 70);
            contentStream.drawString("Totale: " + (totProd+totServ));
            contentStream.endText();


            contentStream.close();
            doc.save("ListaProdottiServizi.pdf");
            doc.close();

            JOptionPane.showMessageDialog(M, "Generato un PDF con la tua lista !");
        }catch (Exception e){
            JOptionPane.showMessageDialog(M, "PDF NON GENERATO !");
        }


    }

    //Funziona come CreaPDF ma ha solo la parte dei prodototti e prodotti compositi
    public void CreaPDFProdotti(String prodottiDaAcquistare) throws Exception{

        try{

            PDDocument doc = new PDDocument(); //documento
            PDPage page = new PDPage(); //pagina
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc,page);
            contentStream.setFont(PDType1Font.COURIER,12);

            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20,750);
            contentStream.drawString("Prodotti: ");
            contentStream.endText();

            Utente u = M.getFD().getListaUtenti().get(M.getFD().getSessione().getNumUtente());
            List<IProdotti> prodotti = u.getCarrelloProdotti();
            List<Servizi> servizi = u.getCarrelloServizi();

            String[] prodDaAcquistare = prodottiDaAcquistare.split(" ");

            int y = 730;

            double totProd=0;

            for (int i=0; i<prodDaAcquistare.length;i++){
                contentStream.beginText();
                contentStream.moveTextPositionByAmount(20,y - ((i+1)*10));
                y = y - ((i+1)*10);
                if (prodotti.get(Integer.parseInt(prodDaAcquistare[i])).getPC()){
                    ProdottoComposito prodotto = (ProdottoComposito) prodotti.get(Integer.parseInt(prodDaAcquistare[i]));
                    contentStream.drawString("Prodotto composito: " + prodotto.getnome() + " " + M.getFD().getPuntivendita().get(prodotto.getNumPuntoVendita()).getNome());
                    contentStream.endText();
                    for (int j=0; j<prodotto.getSottoprodotti().size();j++){
                        contentStream.beginText();
                        contentStream.moveTextPositionByAmount(20,y - ((i+1)*10));
                        y = y - ((i+1)*10);
                        contentStream.drawString("     " + prodotto.getSottoprodotti().get(j).getnome() + " " + prodotto.getSottoprodotti().get(j).getprezzo());
                        contentStream.endText();
                        totProd = totProd + prodotto.getSottoprodotti().get(j).getprezzo();
                    }

                }else {
                    y = y - ((i+1)*10);
                    Prodotti prodotto = (Prodotti) prodotti.get(Integer.parseInt(prodDaAcquistare[i]));
                    contentStream.drawString(prodotto.getnome() + " " + prodotto.getprezzo() + " " + M.getFD().getPuntivendita().get(prodotto.getNumPuntoVendita()).getNome());
                    totProd = totProd + prodotto.getPrezzo();
                    contentStream.endText();
                }

            }

            int y1=650;

            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20,y1 - 400);
            contentStream.drawString("Totale: " + (totProd));
            contentStream.endText();


            contentStream.close();
            doc.save("ListaProdotti.pdf");
            doc.close();
            JOptionPane.showMessageDialog(M, "PDF Prodotti generato correttamente !");

        }catch(Exception E){
            JOptionPane.showMessageDialog(M, "PDF NON GENERATO !");
        }


    }


    //Funziona come CreaPDF ma ha solo la parte dei Servizi
    public void CreaPDFServizi(String serviziDaAcquistare) throws Exception{

        try{
            PDDocument doc = new PDDocument(); //documento
            PDPage page = new PDPage(); //pagina
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc,page);
            contentStream.setFont(PDType1Font.COURIER,12);

            Utente u = M.getFD().getListaUtenti().get(M.getFD().getSessione().getNumUtente());
            List<Servizi> servizi = u.getCarrelloServizi();

            int y = 730;

            double totServ=0;

            String[] servDaAcquistare = serviziDaAcquistare.split(" ");

            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20,750);
            contentStream.drawString("Servizi: ");
            contentStream.endText();

            y=y-30;

            int y1=650;
            for (int i=0; i<servDaAcquistare.length;i++){
                contentStream.beginText();
                contentStream.moveTextPositionByAmount(20,y - ((i+1)*10));
                y1 = y - ((i+1)*10);
                contentStream.drawString(servizi.get(Integer.parseInt(servDaAcquistare[i])).getNome() + " " + servizi.get(Integer.parseInt(servDaAcquistare[i])).getPrezzo() + " " + M.getFD().getPuntivendita().get(servizi.get(Integer.parseInt(servDaAcquistare[i])).getNumPuntoVendita()).getNome());
                totServ = totServ + servizi.get(Integer.parseInt(servDaAcquistare[i])).getPrezzo();
                contentStream.endText();
            }

            contentStream.beginText();
            contentStream.moveTextPositionByAmount(20,y1 - 400);
            contentStream.drawString("Totale: " + (totServ));
            contentStream.endText();


            contentStream.close();
            doc.save("ListaServizi.pdf");
            doc.close();
            JOptionPane.showMessageDialog(M, "PDF Servizi generato correttamente !");
        }catch(Exception E){
            JOptionPane.showMessageDialog(M, "PDF NON GENERATO !");
        }


    }



}
