import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class Main {

    private static Integer LENGTH_WORD = 8;

    private static LexicalAtomTable lexicalAtomTable = new LexicalAtomTable();
    private static FIP fip = new FIP();
    private static SymbolTable identifiersSymbolTable = new SymbolTable();
    private static SymbolTable constantsSymbolTable = new SymbolTable();

    private static Map<String, Boolean> identifiers = new HashMap<String, Boolean>();
    private static Map<String, Boolean> literals = new HashMap<String, Boolean>();
    private static Map<String, Boolean> arithmeticOperators = new HashMap<String, Boolean>();
    private static Map<String, Boolean> relationOperators = new HashMap<String, Boolean>();
    private static Map<String, Boolean> delimiters = new HashMap<String, Boolean>();
    private static Map<String, Boolean> keyWordsMatch = new HashMap<String, Boolean>();

    private static List<String> keyWords = Arrays.asList("#include", "int", "double", "vector<int>", "if", "else",
            "for", "while", "cin", "cout", "main()", "using", "namespace", "std", "return");
    private static List<String> delimitersMark = Arrays.asList(";", "{", "}", ",", "(", ")");

    private static Boolean identifyDelimiters(String text){
        text = text.strip();
        for(String el: delimitersMark)
            if(text.equals(el))
                return true;
        return false;
    }

    private static Boolean identifyKeyWords(String text){
        text = text.strip();
        if(text.matches("(<[^>]+>)")) // name of header
            return true;
        for(String keyWord: keyWords)
            if(text.equals(keyWord))
                return true;
        return false;
    }

    private static Boolean identifyID(String text){
        text = text.trim();
        if(text.matches("^[a-z][a-z0-9]*$"))
            return true;
        return false;
    }

    private static Boolean identifyLiteral(String text){
        text = text.trim();
        if(text.matches("^[0-9]+(\\.[0-9]+)?$") || text.matches("^\"[A-Za-z:]*\"$"))
            return true;
        return false;
    }

    private static Boolean identifyAssigmentOperator(String text){
        text = text.trim();
        if(text.matches("="))
            return true;
        return false;
    }

    private static Boolean identifyRelationOperator(String text){
        text = text.trim();
        if(text.equals("<") || text.equals(">") || text.equals("==") || text.equals("!="))
            return true;
        return false;
    }

    private static Boolean identifyArithmeticOperator(String text){
        text = text.trim();
        if(text.equals("+") || text.equals("-") || text.equals("*") ||
                text.equals("%") || text.equals("<<") || text.equals(">>"))
            return true;
        return false;
    }

    private static void displayMap(Map<String, Boolean> map){
        for(Map.Entry<String, Boolean> entry: map.entrySet()) System.out.print(entry.getKey() + " ");
        System.out.println();
    }

    private static void checkAtom(String part, Integer line, Integer column) throws Exception {
        column = column + 1;
        if(identifyKeyWords(part)) {
            keyWordsMatch.put(part, true);
            lexicalAtomTable.addLexicalAtom(part);
            fip.addEntry(lexicalAtomTable.getCodeAtom(part), 0);
        }
        else
        if(identifyID(part)) {
            if(part.length() > LENGTH_WORD)
                throw new Exception("The length of word is greater than 8 characters");
            identifiers.put(part, true);
            if(identifiersSymbolTable.getCodeFromSymbolTable(part) == 0)
                identifiersSymbolTable.addIdentifierToSymbolTable(part);
            fip.addEntry(lexicalAtomTable.getIdentifierAtomCode(), identifiersSymbolTable.getCodeFromSymbolTable(part));
        }
        else
        if(identifyLiteral(part)) {
            literals.put(part, true);
            if(constantsSymbolTable.getCodeFromSymbolTable(part) == 0)
                constantsSymbolTable.addIdentifierToSymbolTable(part);
            fip.addEntry(lexicalAtomTable.getLiteralAtomCode(), constantsSymbolTable.getCodeFromSymbolTable(part));
        }
        else
        if(identifyArithmeticOperator(part)) {
            arithmeticOperators.put(part, true);
            lexicalAtomTable.addLexicalAtom(part);
            fip.addEntry(lexicalAtomTable.getCodeAtom(part), 0);
        }
        else
        if(identifyRelationOperator(part) || identifyAssigmentOperator(part)) {
            relationOperators.put(part, true);
            lexicalAtomTable.addLexicalAtom(part);
            fip.addEntry(lexicalAtomTable.getCodeAtom(part), 0);
        }
        else
        if(identifyDelimiters(part)) {
            delimiters.put(part, true);
            lexicalAtomTable.addLexicalAtom(part);
            fip.addEntry(lexicalAtomTable.getCodeAtom(part), 0);
        }
        else{
            throw new Exception("Lexical error on (line, column): ( " + line + " , " + column + " )" + " for the next token: " + part);
        }
    }

    public static void main(String[] args) throws Exception {
        URL url = Main.class.getClassLoader().getResource("input.txt");
        if(url == null) throw new IllegalArgumentException("file not found!");
        Scanner sc = new Scanner(url.getPath());

        Integer indexLine = 0;
        while(sc.hasNextLine()){
            String line = sc.nextLine();

            indexLine = indexLine + 1;
            Integer indexColumn = 0;

            Integer i = 0;
            while( i < line.length()){
                Integer j = i;
                indexColumn = i;
                while(j < line.length() && line.charAt(j) != ' ') j++;
                String part = line.substring(i, j);
                i = j + 1;

                if(part.matches("^[ ]*$"))
                    continue;
                part = part.strip();

                if(part.equals("main()")) {
                    keyWordsMatch.put("main()", true);
                    lexicalAtomTable.addLexicalAtom(part);
                    fip.addEntry(lexicalAtomTable.getCodeAtom(part), 0);
                    continue;
                }

                if(part.charAt(part.length() - 1) == ';'){
                    part = part.substring(0, part.length() - 1);
                    checkAtom(part, indexLine, indexColumn);
                    delimiters.put(";", true);
                    lexicalAtomTable.addLexicalAtom(";");
                    fip.addEntry(lexicalAtomTable.getCodeAtom(";"), 0);
                    continue;
                }

                if(part.charAt(part.length() - 1) == ','){
                    part = part.substring(0, part.length() - 1);
                    checkAtom(part, indexLine, indexColumn);
                    delimiters.put(",", true);
                    lexicalAtomTable.addLexicalAtom(",");
                    fip.addEntry(lexicalAtomTable.getCodeAtom(","), 0);
                    continue;
                }

                if(part.charAt(part.length() - 1) == ')'){
                    part = part.substring(0, part.length() - 1);
                    checkAtom(part, indexLine, indexColumn);
                    delimiters.put(")", true);
                    lexicalAtomTable.addLexicalAtom(")");
                    fip.addEntry(lexicalAtomTable.getCodeAtom(")"), 0);
                    continue;
                }

                if(part.charAt(0) == '('){
                    part = part.substring(1);
                    delimiters.put("(", true);
                    lexicalAtomTable.addLexicalAtom("(");
                    fip.addEntry(lexicalAtomTable.getCodeAtom("("), 0);
                    checkAtom(part, indexLine, indexColumn);
                    continue;
                }

                checkAtom(part, indexLine, indexColumn);

            }
        }

        FileWriter outputName = new FileWriter("src/main/resources/atomLexicalTable.txt");
        PrintWriter fileWriter = new PrintWriter(outputName);
        fileWriter.println(lexicalAtomTable);
        fileWriter.close();

        outputName = new FileWriter("src/main/resources/fip.txt");
        fileWriter = new PrintWriter(outputName);
        fileWriter.println(fip);
        fileWriter.close();

        outputName = new FileWriter("src/main/resources/identifiersSymbolTable.txt");
        fileWriter = new PrintWriter(outputName);
        fileWriter.println(identifiersSymbolTable);
        fileWriter.close();

        outputName = new FileWriter("src/main/resources/constantsSymbolTable.txt");
        fileWriter = new PrintWriter(outputName);
        fileWriter.println(constantsSymbolTable);
        fileWriter.close();

        displayMap(relationOperators);

    }
}
