public class GeneratedTriangles {
    Long min, max;
    public GeneratedTriangles(Long min, Long max) {
        this.min = min;
        this.max = max;
    }

    public Long getSmallestNumber(){
        return min;
    }

    public Long getLargestNumber(){
        return max;
    }
}
