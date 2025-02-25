package org.example.teagym;
public class Training {
    private String[] trainings = new String[7];
    public Training(String[] trainings)
    {
        this.trainings = trainings;
    }
    public void addTraining(int dayNo, String training)
    {
        trainings[dayNo] = training;
    }
    public String toString()
    {
        String trainingsString = "";
        for (int i =0; i < trainings.length; i++)
             trainingsString += trainings[i]+ ",";
        return trainingsString;
    }
    public String printTraining()
    {
        String trainingsString = "";
        for (int i =0; i < trainings.length; i++)
            trainingsString += String.format("Day %d :\n%s\n--------\n", i + 1, trainings[i]);
        return trainingsString;
    }
}

