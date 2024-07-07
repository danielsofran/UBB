import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        <lista_tranzitii> ::= <lista_tranzitii> | <tranzitii> <lista_tranzitii>
        <tranzitii> ::= <stare> <stare> <label> | <stare> <stare> <labels>
        <labels> ::= <label> | <labels> <label>
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
        File file = new File(inputTXT);
        InputStream inputFile = new FileInputStream(file);
        Scanner myReader = new Scanner(inputFile);

        String alphabet = myReader.nextLine();
        List<String> charactersAlphabet = Arrays.stream(alphabet.split(" ")).collect(Collectors.toList());

        String states = myReader.nextLine();
        List<String> elemStates = Arrays.stream(states.split(" ")).collect(Collectors.toList());

        String initialState = myReader.nextLine();

        String finalStates = myReader.nextLine();
        List<String> elemFinalStates = Arrays.stream(finalStates.split(" ")).collect(Collectors.toList());

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
                transitions.add(new Transition(source, destination, inputAlphabet));
                index++;
            }
        }

        AF af = new AF(initialState, elemFinalStates, elemStates, transitions, charactersAlphabet);
        return af;
    }

    private static AF readAFFromKeyboard(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the input alphabet: ");
        String alphabet = scanner.nextLine();
        List<String> charactersAlphabet = Arrays.stream(alphabet.split(" ")).collect(Collectors.toList());

        System.out.print("Enter the states: ");
        String states = scanner.nextLine();
        List<String> elemStates = Arrays.stream(states.split(" ")).collect(Collectors.toList());

        System.out.print("Enter the initial state: ");
        String initialState = scanner.nextLine();

        System.out.print("Enter the final states: ");
        String finalStates = scanner.nextLine();
        List<String> elemFinalStates = Arrays.stream(finalStates.split(" ")).collect(Collectors.toList());

        System.out.println("Enter the set of transitions: ");
        List<Transition> transitions = new ArrayList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.matches("^[ ]*$"))
                break;
            String[] words = line.split(" ");
            String source = words[0], destination = words[1];
            Integer index = 2;
            while(index < words.length){
                String inputAlphabet = words[index];
                transitions.add(new Transition(source, destination, inputAlphabet));
                index++;
            }
        }

        AF af = new AF(initialState, elemFinalStates, elemStates, transitions, charactersAlphabet);
        return af;
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("File/Keyboard: ");
        String inputWay = scanner.nextLine();

        AF af = null;
        if(inputWay.equals("file")) {
            System.out.print("File Name: ");
            String inputTxt = scanner.nextLine();
            af = readAFFromFile(inputTxt);
        }
        else
            af = readAFFromKeyboard();

        while(true){
            displayMenu();
            System.out.print("Introduce the command: ");
            Integer command = scanner.nextInt();
            if(command == 0)
                break;
            menu(command, af);
            System.in.read();
        }

        Boolean isDeterminism = af.isDeterminism();
        System.out.println("Is the state machine determinist: " + isDeterminism);
        if(isDeterminism == Boolean.FALSE)
            return;

        scanner.nextLine();
        while(true) {
            System.out.print("accepted/prefix: ");
            String seqWay = scanner.nextLine();
            System.out.println();
            System.out.print("Introduce the sequence: ");
            String sequence = scanner.nextLine();

            if (seqWay.equals("accepted")) {
                System.out.println("Output: " + af.checkSequence(sequence));
            } else if (seqWay.equals("prefix")) {
                System.out.println("The longest prefix is: " + af.findTheLongestPrefix(sequence));
            } else {
                break;
            }

            System.in.read();
        }
    }
}
