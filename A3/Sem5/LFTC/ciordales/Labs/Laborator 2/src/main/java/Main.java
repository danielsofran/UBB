import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static List<String> identifiers = new ArrayList<>();
    private static List<String> literals = new ArrayList<>();
    private static List<String> operators = new ArrayList<>();
    private static List<String> keyWords = new ArrayList<>();

    private static OutputMatch identifyID(String text){
        text = text.trim();
        Pattern pattern = Pattern.compile("^[a-z][a-z0-9]+$");
        Matcher matcher = pattern.matcher(text);
        if(matcher.matches())
            return new OutputMatch(true, matcher.start(), matcher.end(), matcher.group());
        return new OutputMatch();
    }

    private static OutputMatch identifyLiteral(String text){
        text = text.trim();
        Pattern pattern = Pattern.compile("^[0-9]+(\\.[0-9]+)?$");
        Matcher matcher = pattern.matcher(text);
        if(matcher.matches())
            return new OutputMatch(true, matcher.start(), matcher.end(), matcher.group());
        return new OutputMatch();
    }

    private static OutputMatch identifyAssigmentOperator(String text){
        text = text.trim();
        for(int i=0;i<text.length(); i++){
            char c = text.charAt(i);
            if(c == '=')
                return new OutputMatch(true, i, i + 1, Character.toString(c));
        }
        return new OutputMatch();
    }

    private static OutputMatch identifyArithmeticOperator(String text){
        text = text.trim();
        for(int i=0;i<text.length(); i++){
            char c = text.charAt(i);
            if(c == '+' || c == '-' || c =='%' || c == '*')
                return new OutputMatch(true, i, i + 1, Character.toString(c));
        }
        return new OutputMatch();
    }

    private static Boolean identifyExpression(String text){
        text = text.trim();
        if(identifyID(text).getMatch()){
            identifiers.add(text);
            return true;
        }

        if(identifyLiteral(text).getMatch()){
            literals.add(text);
            return true;
        }

        OutputMatch outputMatchOperator = identifyArithmeticOperator(text);
        if(outputMatchOperator.getMatch()){
            String leftSide = text.substring(0, outputMatchOperator.getStartPosition());
            String rightSide = text.substring(outputMatchOperator.getEndPosition());
            Boolean isExpression = identifyExpression(leftSide) && identifyExpression(rightSide);
            if(isExpression){
                operators.add(outputMatchOperator.getExpression());
            }
            return isExpression;
        }

        return false;
    }

    private static Boolean identifyAssigment(String text){
        text = text.trim();
        OutputMatch outputMatchAssigment = identifyAssigmentOperator(text);

        if(outputMatchAssigment.getMatch()){
            String leftSide = text.substring(0, outputMatchAssigment.getStartPosition());
            String rightSide = text.substring(outputMatchAssigment.getEndPosition());
            OutputMatch outputMatchID = identifyID(leftSide);
            Boolean isAssigment = outputMatchID.getMatch() && identifyExpression(rightSide);
            if(isAssigment){
                operators.add(Character.toString(text.charAt(outputMatchAssigment.getStartPosition())));
                identifiers.add(outputMatchID.getExpression());
            }

            return isAssigment;
        }
        return false;
    }

    private

    private static Boolean identifyDeclarationExpression(String text){
        text = text.trim();


    }

    public static void main(String[] args) throws IOException {

        String str = Files.readString(Path.of("D:\\UBB-Didactic\\An 3\\LFTC\\Laborator 2\\src\\main\\java\\input.txt"));
        System.out.println(identifyAssigment("abc = ddsf + sas * ff + sf + sd9433sf + 99 + 9 + 82.09932"));

        System.out.print("Identifiers: ");
        for(String c: identifiers) System.out.print(c + " ");;
        System.out.println();

        System.out.print("Literals: ");
        for(String c: literals) System.out.print(c + " ");;
        System.out.println();

        System.out.print("Operators: ");
        for(String c: operators) System.out.print(c + " ");;
        System.out.println();

        System.out.print("KeyWords: ");
        for(String c: keyWords) System.out.print(c + " ");;
        System.out.println();

    }
}
