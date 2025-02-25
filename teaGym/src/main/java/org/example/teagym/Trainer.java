package org.example.teagym;
public class Trainer extends Staff{
    String certification;
    public Trainer(String name, int age, String phoneNo, String gender, String password,
                   String staffType, double salary, String[] weeklyWorkSchedule, String certification)
    {
        super(name, age, phoneNo, gender, password, staffType, salary, weeklyWorkSchedule);
        this.certification = certification;
    }
}
