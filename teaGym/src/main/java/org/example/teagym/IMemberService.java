package org.example.teagym;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IMemberService {
    void addSubscription(String phoneNo, LocalDateTime startDate, int duration);
    void addCabinetNo(String phoneNo, int cabinetNo);
    void setDailyTraining(String phoneNo, int dayNo, String training);
    void setPassword(String phoneNo, String password);
    void addNewMeasurement(String phoneNo, Measurement measurement);
    String getMemberPhone();
}
