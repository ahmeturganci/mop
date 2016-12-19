package Islemler;

public class Islem {
    private String toSummaryString;
    private String fMeasure;
    private String precision;
    private String  recall;
    private double crrRate;
    private static Islem islem;
    private Islem(){

    }
    public static Islem getIslem(){
        if (islem==null)
            islem=new Islem();

        return  islem;
    }

    public String getToSummaryString() {
        return toSummaryString;
    }

    public void setToSummaryString(String toSummaryString) {
        this.toSummaryString = toSummaryString;
    }

    public String getfMeasure() {
        return fMeasure;
    }

    public void setfMeasure(String fMeasure) {
        this.fMeasure = fMeasure;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getRecall() {
        return recall;
    }

    public void setRecall(String recall) {
        this.recall = recall;
    }

    public double getCrrRate() {
        return crrRate;
    }

    public void setCrrRate(double crrRate) {
        this.crrRate = crrRate;
    }
}
