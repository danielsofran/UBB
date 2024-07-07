package service;

import constants.RecognizedAtoms;
import model.MatchedAtom;

import java.util.*;
import java.util.stream.Collectors;

public class TablesCreator {
    SortedMap<Integer, List<MatchedAtom>> matches;
    Map<String, Integer> codes;
    List<AbstractMap.Entry<Integer, Integer>> PIF; // Program Internal Form, Pair of <code, position in ST>
    String[] ST; // Symbol Table
    int n = 0;

    // same as Collections.binarySearch
    int binarySearch(String atom) {
        int l = 0, r = n-1;
        while(l <= r){
            int m = (l+r)/2;
            if(ST[m].equals(atom))
                return m;
            if(ST[m].compareTo(atom) < 0)
                l = m+1;
            else
                r = m-1;
        }
        return -l-1;
    }

    private int addToST(String atom) {
        // find lower bound of atom in ST
        int i = binarySearch(atom);
        if(i >= 0)
            return i;
        i = -i-1;
        // insert atom in ST
        // shift right all elements from i to n-1
        for(int j = n-1; j >= i; j--)
            ST[j+1] = ST[j];
        ST[i] = atom;
        n++;

        // replace old codes with new ones in PIF
        for(AbstractMap.Entry<Integer, Integer> entry : PIF){
            if(entry.getValue() >= i)
                entry.setValue(entry.getValue()+1);
        }
        return i;
    }

    public TablesCreator(SortedMap<Integer, List<MatchedAtom>> matches) {
        this.matches = matches;
        createCodes();
        createTables();
    }

    private void createCodes() {
        codes = new HashMap<>();
        codes.put("ID", 0);
        codes.put("CONST", 1);
        List<RecognizedAtoms> excluded = List.of(RecognizedAtoms.LITERAL, RecognizedAtoms.IDENTIFIER);
        matches.values().stream().flatMap(List::stream).forEach(match -> {
            String atom = match.getOutputMatch().getExpression();
            if(!codes.containsKey(atom) && !excluded.contains(match.getAtom()))
                codes.put(atom, codes.size());
        });
    }

    private void createTables() {
        this.PIF = new ArrayList<>();
        this.ST = new String[10000];
        for(Integer line : matches.keySet()){
            for(MatchedAtom match : matches.get(line)){
                String atom = match.getOutputMatch().getExpression();
                if(match.getAtom() == RecognizedAtoms.IDENTIFIER || match.getAtom() == RecognizedAtoms.LITERAL){
                    final int code = match.getAtom() == RecognizedAtoms.IDENTIFIER ? 0 : 1;
                    PIF.add(new AbstractMap.SimpleEntry<>(code, addToST(atom)));
                } else {
                    PIF.add(new AbstractMap.SimpleEntry<>(codes.get(atom), -1));
                }
            }
        }
    }

    private final String separator = " ";

    public String getCodes() {
        StringBuilder sb = new StringBuilder();
        codes.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry ->
            sb.append(entry.getKey()).append(separator).append(entry.getValue()).append("\n")
        );
        return sb.toString();
    }

    public String getPIF() {
        StringBuilder sb = new StringBuilder();
        PIF.forEach(entry -> sb.append(entry.getKey()).append(separator).append(entry.getValue()).append("\n"));
        return sb.toString();
    }

    public String getST() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++)
            sb.append(i).append(separator).append(ST[i]).append("\n");
        return sb.toString();
    }
}
