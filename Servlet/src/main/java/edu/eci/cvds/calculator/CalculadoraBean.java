package edu.eci.cvds.calculator;

import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "calculadoraBean")
@SessionScoped

public class CalculadoraBean {
    private String text;
    private ArrayList<Double> values;
    private double mean;
    private double mode;
    private double variance;
    private double standardDeviation;
    private ArrayList<String> historial = new ArrayList<>();
    
    public CalculadoraBean(){
    }

    //Getter
    public String getText() {
        return text;
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public double getMean() {
        return mean;
    }
    
    public double getMode() {
        return mode;
    }

    public double getVariance() {
        return variance;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }

    //Setters
    public void setText(String text) {
        this.text = text;
    }

    public void setValores(String texto) {
        ArrayList<Double>values = new ArrayList<>();
        texto = texto.strip();
        String[]vals=texto.split(";");
        for(String value: vals){
            values.add(Double.valueOf(value));
        }
        this.values = values;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public void setMode(double mode) {
        this.mode = mode;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public void setHistorial(ArrayList<String> historial) {
        this.historial = historial;
    }

    public double calculateMean(ArrayList<Double> values){
        double result = 0;
        for(double value: values){
            result += value;
        }
        return result/values.size();
    }

    public double calculateStandardDeviation(ArrayList<Double> values){
        return Math.sqrt(calculateVariance(values));
    }
    
    public double calculateVariance(ArrayList<Double> values){
        double mean = calculateMean(values);
        double result = 0;
        for(double value: values){
            result += Math.pow(value-mean,2);
        }
        return(result/values.size());
    }
    
    public double calculateMode(ArrayList<Double> values){
        ArrayList<Double> check = new ArrayList<>();
        double mode = 0;
        int maxRep = 0;
        int reps;
        for(double value: values){
            if(!check.contains(value)){
                reps = 0;
                for(double compare: values){
                    if(value == compare){
                        reps += 1;
                    }
                }
                if(reps > maxRep){
                    mode = value;
                    maxRep = reps;
                }
                check.add(value);
            }
        }
        return mode;
    }

    public void restart(){
        text = "";
        mode = 0;
        mean = 0;
        variance = 0;
        standardDeviation = 0;
        historial.clear();
    }

    public void calculateXXX(){
        setValores((text=="")?"0":text);
        historial.add(text);
        mode = calculateMode(values);
        mean = calculateMean(values);
        variance = calculateVariance(values);
        standardDeviation = calculateStandardDeviation(values);
    }
}
