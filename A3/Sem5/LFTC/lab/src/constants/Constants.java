package constants;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final List<String> Keywords = Arrays.asList(
            "#include",
            "using", "namespace", "std",
            "main()",
            "int", "double",
            "cin", "cout",
            "return",
            "if", "else",
            "while"
    );
    public static final List<String> Delimiters = Arrays.asList(";", "{", "}", ",", "(", ")", "[", "]");
    public static final List<String> ArithmeticOperators = Arrays.asList("+", "-", "*", "/", "%", "<<", ">>");
    public static final List<String> RelationOperators = Arrays.asList("<", ">", "==", "!=");
}
