package model;

import java.util.Objects;

public class NrComplex implements NumericOperations<NrComplex> {

    public static double PRECISION = 1e-6;
    private double r;
    private double i;

    public NrComplex(double _r, double _i) {
        r = _r;
        i = _i;
    }

    public double getReal() {
        return r;
    }

    public void setReal(double r) {
        this.r = r;
    }

    public double getImaginary() {
        return i;
    }

    public void setImaginary(double i) {
        this.i = i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, i);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof NrComplex)) return false;
        NrComplex nr = (NrComplex) obj;
        return Math.abs(nr.r - r) < PRECISION && Math.abs(nr.i - i) < PRECISION;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        NrComplex nr = new NrComplex(r, i);
        return nr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(r != 0)
        {
           if(r == (int)r)sb.append((int)r);
           else sb.append(r);
        }
        if(i==0) return sb.toString();
        if(r!=0 && i>0) sb.append("+");
        if(Math.abs(i) != 1){
            if(i==(int)i) sb.append((int)i);
            else sb.append(i);
            sb.append("*i");
        }
        else if (i==1) sb.append("i");
        else if (i==-1) sb.append("-i");
        return sb.toString();
    }

    public static NrComplex fromString (String s){
        NrComplex rez = new NrComplex(0, 0);
        s = s.trim();
        // partea reala
        int i=0;
        if("+-".indexOf(s.charAt(i))!=-1 && (
                s.length() > i+1 && s.charAt(i+1) != 'i'
            )) ++i;
        for ( ; i < s.length() && Character.isDigit(s.charAt(i)); ++i);
        double r = 0;
        try{ r = Double.parseDouble(s.substring(0, i));} catch (Exception ignored){}
        // r - nr real parsat
        if(i+1 < s.length() && s.charAt(i) == '*' && s.charAt(i+1) == 'i'){
            rez.setImaginary(r);
            return rez;
        }
        // partea imaginara
        if(i < s.length()) {
            int j = i;
            if ("+-".indexOf(s.charAt(j)) != -1) ++j;
            for (; Character.isDigit(s.charAt(j)); ++j) ;
            String si = s.substring(i, j) + ((j <= i + 1) ? "1" : "");
            double img = 0;
            try {
                img = Double.parseDouble(si);
            } catch (Exception ignored) {
            }
            rez.setImaginary(img);
        }
        return rez;
    }

    @Override
    public NrComplex Add(NrComplex other) {
        return new NrComplex(r + other.r, i + other.i);
    }

    @Override
    public NrComplex Substract(NrComplex other) {
        return new NrComplex(r - other.r, i - other.i);
    }

    @Override
    public NrComplex Multiply(NrComplex other) {
        return new NrComplex(r * other.r - i * other.i, r * other.i + i * other.r);
    }

    @Override
    public NrComplex Divide(NrComplex other) {
        return new NrComplex((r * other.r + i * other.i)/(other.r*other.r + other.i*other.i),  (i * other.r - r * other.i)/(other.r*other.r + other.i*other.i));
    }

    @Override
    public void parse(String s) {
        NrComplex aux = NrComplex.fromString(s);
        i = aux.i;
        r = aux.r;
    }

    public static boolean isComplexNumber(String s)
    {
        return s.equals(NrComplex.fromString(s).toString());
    }

    public NrComplex Conjugate() {
        return new NrComplex(r, -i);
    }
}
