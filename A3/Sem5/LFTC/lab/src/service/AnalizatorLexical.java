package service;

import constants.RecognizedAtoms;
import model.MatchedAtom;
import model.OutputMatch;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnalizatorLexical {
    Iterable<String> lines;

    public AnalizatorLexical(Iterable<String> lines) {
        this.lines = lines;
    }

    private void validate(MatchedAtom matchedAtom) throws ParseError {
        String atom = matchedAtom.getOutputMatch().getExpression();
        if(matchedAtom.getAtom() == RecognizedAtoms.IDENTIFIER && atom.length() > 8)
            throw new ParseError("Identifier '"+atom+"' has more than 8 characters!");
    }

    public List<MatchedAtom> analize() throws ParseError {
        List<MatchedAtom> result = new LinkedList<>();
        int index = 0;
        for(String line: lines){
            String current_line = line;
            if (current_line.length() > 0){
                List<MatchedAtom> lineMatches = new LinkedList<>();
                for(RecognizedAtoms atom : Arrays.stream(RecognizedAtoms.values()).sorted().toList()) {
                    Function<String, OutputMatch> function = RecognizeFunctions.MAP.get(atom);
                    OutputMatch outputMatch = function.apply(current_line);
                    while (!outputMatch.isEmpty()){
                        MatchedAtom matchedAtom = new MatchedAtom(outputMatch, index+1, line, atom);
                        validate(matchedAtom);
                        lineMatches.add(matchedAtom);
                        current_line = current_line.substring(0, outputMatch.getStartPosition()) +
                                " ".repeat(outputMatch.getEndPosition() - outputMatch.getStartPosition()) +
                                current_line.substring(outputMatch.getEndPosition());
                        outputMatch = function.apply(current_line);
                    }
                }
                current_line = current_line.strip();
                if(current_line.length() > 0){
                    String unknown = current_line.split(" ")[0];
                    throw new ParseError("Sequence on line "+(index+1)+" unknown: \""+unknown+"\"");
                }
                result.addAll(lineMatches);
            }
            index++;
        }
        return result;
    }

    public SortedMap<Integer, List<MatchedAtom>> sort(List<MatchedAtom> matches) {
        TreeMap<Integer, List<MatchedAtom>> result = matches.stream().collect(Collectors.groupingBy(MatchedAtom::getLineNumber, TreeMap::new, Collectors.toList()));
        for(Integer line : result.keySet()){
            List<MatchedAtom> list = result.get(line);
            list.sort(Comparator.comparingInt(match -> match.getOutputMatch().getStartPosition()));
        }
        return result;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        List<MatchedAtom> matches;
        try {
            matches = this.analize();
        } catch (ParseError e) {
            throw new RuntimeException(e);
        }

        this.sort(matches).forEach((line, list) -> {
            result.append(list.stream().map(MatchedAtom::toString).collect(Collectors.joining("\n"))).append("\n");
        });

        return result.toString();
    }
}
