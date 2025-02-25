package org.example.teagym;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Member extends ADTPerson {
    private Subscription subscription = new Subscription(0);
    private int cabinetNo = 0;
    private Training training = new Training(new String[]{"","","","","","",""});

    public Member(String name, int age, String phoneNo, String gender, String password,
                  Subscription subscription, int cabinetNo, Training training)
    {
        super(name, age, phoneNo, gender, password);
        this.subscription = subscription;
        this.cabinetNo = cabinetNo;
        this.training = training;
    }
    public Member(String name, int age, String phoneNo, String gender, String password,
                  Subscription subscription)
    {
        super(name, age, phoneNo, gender, password);
        this.subscription = subscription;
    }
    public Member(String name, int age, String phoneNo, String gender, String password, int cabinetNo)
    {
        super(name, age, phoneNo, gender, password);
        this.cabinetNo = cabinetNo;
    }

    public void addTraining(Training training)
    {
        this.training = training;
    }

    public Training getTraining()
    {
        return training;
    }

    public Subscription getSubscription()
    {
        return subscription;
    }

    public int getCabinetNo()
    {
        return cabinetNo;
    }

    public void setCabinetNo(int cabinetNo)
    {
        this.cabinetNo = cabinetNo;
    }

    public void addSubscription(LocalDateTime startDate, int duration)
    {
        subscription = new Subscription(startDate, duration);
    }
    public void addSubscription(Subscription subscription)
    {
        this.subscription = subscription;
    }


    @Override
    public boolean equals(Object obj)
    {
        Member m = (Member) obj;
        return getPhoneNo().equals(m.getPhoneNo());
    }

    @Override
    public String toString()
    {
         String measurementsStr = getMeasurements().toString().replaceAll("^\\[|\\]$", "");

         String str = getSubscription() != null ? getSubscription().startDate.toString() : "";
        int index = str.indexOf('T');
        if (index != -1) {
            str = str.substring(0, index);
        }
         return "m, " + getName() + "," + getAge() + "," + getPhoneNo() + "," + (getGender() == null?"" : getGender())+ "," + getPassword()
            + "," + (cabinetNo == 0 ? "": cabinetNo)+ "," + str + "," + (getSubscription() == null ? "" : getSubscription().duration) + ","+ (training == null ? ",,,,,,,": training) + measurementsStr;
    }
}

