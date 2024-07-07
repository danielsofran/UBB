import automat.finit.AutomateFinite;
import model.MatchedAtom;
import service.AnalizatorLexical;
import service.ParseError;
import service.RecognizeFunctions;
import service.TablesCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ParseError {
        List<String> lines = FileUtils.read(2);
        AnalizatorLexical analizatorLexical = new AnalizatorLexical(lines);
        List<MatchedAtom> matches = analizatorLexical.analize();
        System.out.println(analizatorLexical);
        TablesCreator tablesCreator = new TablesCreator(analizatorLexical.sort(matches));
        FileUtils.write("codes", tablesCreator.getCodes());
        FileUtils.write("FIP", tablesCreator.getPIF());
        FileUtils.write("ST", tablesCreator.getST());

        System.out.println(RecognizeFunctions.applyAF("4e+23", AutomateFinite.Number).getExpression());
    }
}