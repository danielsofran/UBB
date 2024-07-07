package model;

public class OutputMatch {
    private Integer startPosition;
    private Integer endPosition;
    private String expression;

    public static OutputMatch Empty = new OutputMatch();

    public OutputMatch(){
        this.startPosition = -1;
        this.endPosition = -1;
        this.expression = "";
    }

    public OutputMatch(Integer startPosition, Integer endPosition, String expression) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.expression = expression;
    }

    public Boolean isEmpty() {
        return this.startPosition == -1;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    public Integer getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Integer endPosition) {
        this.endPosition = endPosition;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutputMatch that)) return false;

        if (!startPosition.equals(that.startPosition)) return false;
        if (!endPosition.equals(that.endPosition)) return false;
        return expression.equals(that.expression);
    }

    @Override
    public int hashCode() {
        int result = startPosition.hashCode();
        result = 31 * result + endPosition.hashCode();
        result = 31 * result + expression.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return expression;
    }
}
