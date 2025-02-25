package org.example.teagym;
public class WorkSchedule {
    private String[] workSchedules = new String[7];

    public WorkSchedule(String[] works)
    {
        workSchedules = works;
    }
    public String[] getWorkSchedules()
    {
        return workSchedules;
    }

    public void addWork(int dayNo, String workSchedule)
    {
        workSchedules[dayNo] = workSchedule;
    }
    public String printString()
    {
        String worksString = "";
        for(String workSchedule: workSchedules)
            worksString += workSchedule + "\n--------------------\n";
        return worksString;
    }

    @Override
    public String toString()
    {
        if(workSchedules == null)
            return ",,,,,,";
        String str = "";
        for(String workSchedule: workSchedules)
            str += (workSchedule.equals("")?" " : workSchedule) + ",";
        return str.substring(0, str.length()-1);
    }
}

