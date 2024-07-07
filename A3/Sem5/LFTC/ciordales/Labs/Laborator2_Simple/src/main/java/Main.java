import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Main {

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

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\UBB-Didactic\\An 3\\LFTC\\Laborator2_Simple\\src\\main\\resources\\input.txt");
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            List<String> parts = Arrays.stream(line.split(" ")).toList();
            for(String part: parts){
                if(part.matches("^[ ]*$"))
                    continue;
                part = part.strip();

                if(part.equals("main()"))
                    keyWordsMatch.put("main()", true);

                if(part.charAt(part.length() - 1) == ';'){
                    part = part.substring(0, part.length() - 1);
                    delimiters.put(";", true);
                }

                if(part.charAt(part.length() - 1) == ','){
                    part = part.substring(0, part.length() - 1);
                    delimiters.put(",", true);
                }

                if(part.charAt(part.length() - 1) == ')'){
                    part = part.substring(0, part.length() - 1);
                    delimiters.put(")", true);
                }

                if(part.charAt(0) == '('){
                    part = part.substring(1);
                    delimiters.put("(", true);
                }

                if(identifyKeyWords(part))
                    keyWordsMatch.put(part, true);
                else
                if(identifyID(part)) {
                    identifiers.put(part, true);
                }
                else
                if(identifyLiteral(part))
                    literals.put(part, true);
                else
                if(identifyArithmeticOperator(part))
                    arithmeticOperators.put(part, true);
                else
                if(identifyRelationOperator(part) || identifyAssigmentOperator(part))
                    relationOperators.put(part, true);
                else
                if(identifyDelimiters(part))
                    delimiters.put(part, true);
            }
        }

        System.out.print("Identifiers: ");
        displayMap(identifiers);

        System.out.print("Literals: ");
        displayMap(literals);

        System.out.print("Arithmetic Operators: ");
        displayMap(arithmeticOperators);

        System.out.print("Relation Operators: ");
        displayMap(relationOperators);

        System.out.print("KeyWords: ");
        displayMap(keyWordsMatch);

        System.out.print("Delimiters: ");
        displayMap(delimiters);

    }
}
