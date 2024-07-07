package automat.finit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class AF {

    class PairNode{
        private final String state;
        private final String label;

        public PairNode(String state, String label) {
            this.state = state;
            this.label = label;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PairNode pairNode = (PairNode) o;
            return Objects.equals(state, pairNode.state) && Objects.equals(label, pairNode.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(state, label);
        }
    }

    private List<String> inputAlphabet;
    private String initialState;
    private List<String> finalStates;
    private List<String> nodes; // the set of states
    private List<Transition> edges;

    public AF(String initialState, List<String> finalStates, List<String> nodes, List<Transition> edges, List<String> inputAlphabet) {
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.nodes = nodes;
        this.edges = edges;
        this.inputAlphabet = inputAlphabet;
    }

    public AF(String filename) {
        File file = new File(filename);
        InputStream inputFile;
        try {
            inputFile = new FileInputStream(file);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
            return;
        }
        Scanner myReader = new Scanner(inputFile);

        String alphabet = myReader.nextLine();
        List<String> charactersAlphabet = Arrays.stream(alphabet.split(" ")).collect(Collectors.toList());
        if(alphabet.contains("  "))
            charactersAlphabet.add(" ");

        String states = myReader.nextLine();
        List<String> elemStates = Arrays.stream(states.split(" ")).collect(Collectors.toList());

        String initialState = myReader.nextLine();

        String finalStates = myReader.nextLine();
        List<String> elemFinalStates = Arrays.stream(finalStates.split(" ")).collect(Collectors.toList());

        List<Transition> transitions = new ArrayList<>();
        while(myReader.hasNextLine()){
            String line = myReader.nextLine();
            boolean containsOnlySpaces = line.trim().isEmpty();
            if(containsOnlySpaces)
                break;
            String[] words = line.split(" ");
            String source = words[0], destination = words[1];
            Integer index = 2;
            while(index < words.length){
                String inputAlphabet = words[index];
                if(inputAlphabet.length() > 0)
                    transitions.add(new Transition(source, destination, inputAlphabet));
                index++;
            }
            if(line.contains("  "))
                transitions.add(new Transition(source, destination, " "));
        }

        this.inputAlphabet = charactersAlphabet.stream().distinct().collect(Collectors.toList());
        this.initialState = initialState;
        this.finalStates = elemFinalStates;
        this.nodes = elemStates;
        this.edges = transitions;
    }

    public List<String> getInputAlphabet() {
        return inputAlphabet;
    }

    public void setInputAlphabet(List<String> inputAlphabet) {
        this.inputAlphabet = inputAlphabet;
    }

    public String getInitialState() {
        return initialState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public List<Transition> getEdges() {
        return edges;
    }

    public Boolean isDeterminism(){
        Map< PairNode, Boolean> map = new HashMap<>();
        for(Transition transition: edges){
            PairNode pairNode = new PairNode(transition.getSource(), transition.getWeight());
            if(map.containsKey(pairNode))
                return Boolean.FALSE;
            map.put(pairNode, true);
        }

        return Boolean.TRUE;
    }

    public String findTheLongestPrefix(String sequence){
        Integer i = 0;
        String currentState = initialState;
        String prefix = "";
        while(i < sequence.length()){
            String character = sequence.substring(i, i + 1);
            Optional<Transition> transition = checkThereIsTransition(currentState, character);
            if(transition.isEmpty())
                return prefix;
            currentState = transition.get().getDestination();
            i = i + 1;
            if(checkFinalState(currentState))
                prefix = sequence.substring(0, i);
        }
        return prefix;
    }

    public Boolean checkSequence(String sequence){
        Integer i = 0;
        String currentState = initialState;
        while(i < sequence.length()){
            String character = sequence.substring(i, i + 1);
            Optional<Transition> transition = checkThereIsTransition(currentState, character);
            if(transition.isEmpty())
                return Boolean.FALSE;
            currentState = transition.get().getDestination();
            i = i + 1;
        }

        if(checkFinalState(currentState))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    private Boolean checkFinalState(String currentState){
        for(String finalState: finalStates)
            if(finalState.equals(currentState))
                return Boolean.TRUE;
        return Boolean.FALSE;
    }

    private Optional<Transition> checkThereIsTransition(String currentState, String character){
        for(Transition transition: edges){
            if(transition.getSource().equals(currentState) && transition.getWeight().equals(character))
                return Optional.of(transition);
        }
        return Optional.empty();
    }
}
