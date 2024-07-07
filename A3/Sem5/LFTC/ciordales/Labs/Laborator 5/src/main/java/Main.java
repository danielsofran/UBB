import java.io.*;
import java.util.*;

public class Main {

    /*
    Continut fisier:
        <alfabet_intrare>
        <alfabet_stari>
        <stare_initiala>
        <lista_stari_finale>
        <lista_tranzitii>

        <alfabet_intrare> ::= <label> | <alfabet_intrare> <label>
        <alfabet_stari> ::= <stare> | <alfabet_stari> <stare>
        <stare_initiala> ::= <stare>
        <lista_stari_finale> ::= <stare> | <lista_stari_finale> <stare>
        <lista_tranzitii> ::= <lista_tranzitii> | <tranzitie> <lista_tranzitii>
        <tranzitii> ::= <stare> <stare> <label>
        <stare> ::= <letter> | <ID> ( <letter> | <digit> )
        <label> ::= 0 | 1 | ... | 9 | a | b | ... | z | A | .. | Z | + | -
        <digit> ::= 0 | 1 | 2 | … | 9
        <letter> ::= a | b | c | ... | y

     */

    private static void menu(Integer command, AF stateMachine){
        switch (command){
            case 1: {
                System.out.print("The set of states: ");
                for(String state: stateMachine.getNodes())
                    System.out.print(state + " ");
                System.out.println();
                break;
            }
            case 2: {
                System.out.print("The input alphabet: ");
                for(String element: stateMachine.getInputAlphabet())
                    System.out.print(element + " ");
                System.out.println();
                break;
            }
            case 3: {
                System.out.println("Transitions: ");
                for(Transition transition: stateMachine.getEdges())
                    System.out.println(transition);
                System.out.println();
                break;
            }
            case 4: {
                System.out.print("The final states: ");
                for(String finalState: stateMachine.getFinalStates())
                    System.out.print(finalState + " ");
                System.out.println();
                break;
            }
            case 5: {
                System.out.println("The initial state: " + stateMachine.getInitialState());
                break;
            }
        }
    }

    private static void displayMenu(){
        System.out.println("0 - Exit!");
        System.out.println("1 - Display the set of states!");
        System.out.println("2 - Display the input alphabet!");
        System.out.println("3 - Display the transitions!");
        System.out.println("4 - Display the set of final states!");
        System.out.println("5 - Display the initial state!");
        System.out.println("-------------------------------------------------------------------------");
    }

    private static AF readAFFromFile(String inputTXT) throws FileNotFoundException {
        inputTXT = "E:\\UBB-Didactic\\An 3\\LFTC\\Laborator 5\\src\\main\\resources\\" + inputTXT;
        InputStream inputFile = new FileInputStream(inputTXT);
        Scanner myReader = new Scanner(inputFile);

        String alphabet = myReader.nextLine();
        List<String> charactersAlphabet = Arrays.stream(alphabet.split(" ")).toList();

        String states = myReader.nextLine();
        List<String> elemStates = Arrays.stream(states.split(" ")).toList();

        String initialState = myReader.nextLine();

        String finalStates = myReader.nextLine();
        List<String> elemFinalStates = Arrays.stream(finalStates.split(" ")).toList();

        List<Transition> transitions = new ArrayList<>();
        while(myReader.hasNextLine()){
            String line = myReader.nextLine();
            if(line.matches("^[ ]*$"))
                break;
            String[] words = line.split(" ");
            String source = words[0], destination = words[1];
            Integer index = 2;
            while(index < words.length){
                String inputAlphabet = words[index];
                if(inputAlphabet.equals("space"))
                    inputAlphabet = " ";
                transitions.add(new Transition(source, destination, inputAlphabet));
//                if(inputTXT.equals("D:\\UBB-Didactic\\An 3\\LFTC\\Laborator 5\\src\\main\\resources\\AF_literal.txt"))
//                    System.out.println(source + " " + destination + " " + inputAlphabet);
                index++;
            }
        }

        AF af = new AF(initialState, elemFinalStates, elemStates, transitions, charactersAlphabet);
        return af;
    }

    public static void main(String[] args) throws Exception {
        AF identifierStateMachine = readAFFromFile("AF_identifier.txt");
        AF literalStateMachineList = readAFFromFile("AF_literal.txt");
        AF relationalOperatorStateMachine = readAFFromFile("AF_relation.txt");
        AF arithmeticOperatorStateMachine = readAFFromFile("AF_arithmetic.txt");
        AF separatorsStateMachine = readAFFromFile("AF_delimitator.txt");

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(identifierStateMachine, literalStateMachineList,
                relationalOperatorStateMachine, arithmeticOperatorStateMachine, separatorsStateMachine);
        lexicalAnalyzer.analyze(new File("E:\\UBB-Didactic\\An 3\\LFTC\\Laborator 5\\src\\main\\resources\\input.txt"));
    }
}
