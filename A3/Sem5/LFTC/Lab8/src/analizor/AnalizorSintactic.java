package analizor;

import java.util.*;
import java.util.function.Supplier;

public class AnalizorSintactic {
    Tabel tabel;
    Deque<String> stivaDeLucru; // alpha
    Stack<String> stivaDeIntrare; // beta
    Stack<Integer> bandaDeIesire; // pi

    public AnalizorSintactic(Gramatica gramatica){
        ColectieCanonica colectieCanonica = new ColectieCanonica(gramatica);
        this.tabel = new Tabel(colectieCanonica);
        stivaDeLucru = new ArrayDeque<>();
        stivaDeIntrare = new Stack<>();
        bandaDeIesire = new Stack<>();
        tabel.print();
    }

    public boolean isAccepted(String secventa){
        init(secventa);
        Supplier<Integer> peekStivaDeLucru = () -> {
            if(stivaDeLucru.isEmpty())
                return null;
            String last = stivaDeLucru.getLast();
            try {
                return Integer.parseInt(last);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Nu se poate converti " + last + " la int, stiva de lucru nu e in stare consistenta");
            }
        };
        while (true) {
            if(stivaDeLucru.isEmpty())
                return false;
            Integer varfStivaDeLucru = peekStivaDeLucru.get();
            String varfStivaDeIntrare = stivaDeIntrare.peek();
            ActiuneData actiuneData = tabel.get(varfStivaDeLucru, varfStivaDeIntrare);
            if(actiuneData == null)
                return false;
            switch (actiuneData.actiune) {
                case ACCEPTARE -> {
                    return true;
                }
                case REDUCERE -> {
                    int index = actiuneData.index;
                    Productie productie = tabel.colectieCanonica.gramatica.productii.get(index);
                    int nrDreapta = productie.dreapta.size();
                    if(stivaDeLucru.size() < nrDreapta * 2)
                        return false;
                    for (int i = 0; i < nrDreapta * 2; i++)
                        stivaDeLucru.removeLast();
                    int stare = peekStivaDeLucru.get();
                    Simbol stanga = productie.stanga;
                    stivaDeLucru.addLast(stanga.toString());
                    stivaDeLucru.addLast(String.valueOf(tabel.get(stare, stanga.toString()).index));
                    bandaDeIesire.add(index);
                }
                case SHIFTARE -> {
                    stivaDeLucru.add(varfStivaDeIntrare);
                    stivaDeIntrare.pop();
                    stivaDeLucru.add(String.valueOf(actiuneData.index));
                }
                case EROARE -> {
                    return false;
                }
            }
        }
    }

    private void init(String secventa){
        stivaDeLucru.clear();
        stivaDeIntrare.clear();
        bandaDeIesire.clear();
        stivaDeLucru.addLast(Simbol.DOLLAR.toString());
        stivaDeLucru.addLast("0");
        stivaDeIntrare.add(Simbol.DOLLAR.toString());
        List<Simbol> terminalList = getTerminalList(secventa);
        for (int i = terminalList.size() - 1; i >= 0; i--) {
            stivaDeIntrare.add(terminalList.get(i).toString());
        }
    }

    private List<Simbol> getTerminalList(String secventa){
        String secv = new String(secventa);
        List<Simbol> terminalList = new ArrayList<>();
        List<Simbol> terminali = tabel.colectieCanonica.gramatica.getTerminale();
        boolean found = true;
        while (found) {
            found = false;
            for (Simbol terminal : terminali) {
                if(secv.startsWith(terminal.toString())){
                    terminalList.add(terminal);
                    secv = secv.substring(terminal.toString().length());
                    found = true;
                }
            }
            if(!found && !secv.isEmpty()){
                throw new RuntimeException("Unexpected token at char " + secventa.indexOf(secv.charAt(0)) + ": " + secv);
            }
        }
        return terminalList;
    }

    public void printBandaDeIesire(){
        System.out.print("Banda de iesire: ");
        // reverse order
        for(int i = bandaDeIesire.size() - 1; i >= 0; i--){
            System.out.print(bandaDeIesire.get(i) + " ");
        }
    }
}
