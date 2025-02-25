package org.example.teagym;
public class Measurement {
    private double height;
    private double weight;
    private double fatRate;
    public Measurement(double height, double weight, double fatRate)
    {
        this.height = height;
        this.weight = weight;
        this.fatRate = fatRate;
    }
    public double getHeight() {
        return height;
    }
    public double getWeight() {
        return weight;
    }
    public double getFat_rate() {
        return fatRate;
    }
    public void printMeasurement()
    {
        System.out.printf("Height : %f\nWeight : %f\nBody Fate Rate : %f", height, weight, fatRate);
    }

    @Override
    public String toString()
    {
        return String.format("%f, %f, %f", height, weight, fatRate);
    }
    //no need to update an Measurement.
}
