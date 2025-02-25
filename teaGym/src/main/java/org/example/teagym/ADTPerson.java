package org.example.teagym;
import java.util.ArrayList;
public abstract class ADTPerson {
    private String name;
    private int age;
    private String phoneNo;
    private String gender;
    private String password;
    private ArrayList<Measurement> measurements = new ArrayList<>();
    protected ADTPerson(String name, int age, String phoneNo, String gender, String password)
    {
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.password = password;
    }
    String getName(){return name;};
    void setName(String name){this.name = name;}
    public int getAge() {return age;}

    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    void getLast3Measurements(){
        System.out.println("Last 3 measuments :\n");
        for (int i = measurements.size() - 3; i < measurements.size(); i++) {
            if(i > -1)
                System.out.println(measurements.get(i).toString());
            else
                System.out.println("-------");
        }
    }

    public ArrayList<Measurement> getMeasurements()
    {
        return measurements;
    }

    void addMeasurement(Measurement measurement){
        measurements.add(measurement);
    }
//    String getDayTraining(int dayNo){
//
//    }
}

