package model;

import constants.RecognizedAtoms;

public class MatchedAtom {
    private OutputMatch outputMatch;
    private Integer lineNumber;
    private String line;
    private RecognizedAtoms atom;

    public MatchedAtom() {
    }

    public MatchedAtom(OutputMatch outputMatch, Integer lineNumber, String line, RecognizedAtoms atoms) {
        this.outputMatch = outputMatch;
        this.lineNumber = lineNumber;
        this.line = line;
        this.atom = atoms;
    }

    public RecognizedAtoms getAtom() {
        return atom;
    }

    public void setAtom(RecognizedAtoms atom) {
        this.atom = atom;
    }

    public OutputMatch getOutputMatch() {
        return outputMatch;
    }

    public void setOutputMatch(OutputMatch outputMatch) {
        this.outputMatch = outputMatch;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "Line "+lineNumber+": '"+outputMatch.getExpression()+"', type="+atom.toString();
    }
}
