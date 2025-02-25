package org.example.teagym;

public interface IAdminService {
    String getGymName();
    void setGymName(String name);
    int memberCount();
    String membershipFeeList();
    void changeMembershipFeeList(double[] fees);
    double hallCrowdRate();
    double manWomanRate();
    String getAddress();
    String getGymPhone();
    Double totalSalary();
    void addEquipment(Equipment equipment);
    String allEquipments();
}
