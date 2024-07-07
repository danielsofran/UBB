public class OutputMatch {
    private Boolean isMatch;
    private Integer startPosition;
    private Integer endPosition;
    private String expression;

    public OutputMatch(){
        this.isMatch = false;
        this.startPosition = -1;
        this.endPosition = -1;
        this.expression = "";
    }

    public OutputMatch(Boolean isMatch, Integer startPosition, Integer endPosition, String expression) {
        this.isMatch = isMatch;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Integer getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Integer endPosition) {
        this.endPosition = endPosition;
    }

    public Boolean getMatch() {
        return isMatch;
    }

    public void setMatch(Boolean match) {
        isMatch = match;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }
}

