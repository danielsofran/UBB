package automat.finit;

public class AutomateFinite {
    public static AF Header, Identifier, Number, String;

    static {
        Header = new AF("AF/header");
        Identifier = new AF("AF/identifier");
        Number = new AF("AF/number");
        String = new AF("AF/string");

        AF[] afs = {Header, Identifier, Number, String};
        for (AF af : afs) {
            if(!af.isDeterminism()){
                System.err.println("An automate is not deterministic!");
            }
        }
    }
}
