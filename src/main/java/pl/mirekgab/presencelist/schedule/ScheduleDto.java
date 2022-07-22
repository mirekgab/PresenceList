
package pl.mirekgab.presencelist.schedule;


public class ScheduleDto {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private int year;
    private int month;
    private int day;
    private String startOfWork;
    private String endOfWork;
    private String timeOfWork;
    private boolean workingDay;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getStartOfWork() {
        return startOfWork;
    }

    public void setStartOfWork(String startOfWork) {
        this.startOfWork = startOfWork;
    }

    public String getEndOfWork() {
        return endOfWork;
    }

    public void setEndOfWork(String endOfWork) {
        this.endOfWork = endOfWork;
    }

    public String getTimeOfWork() {
        return timeOfWork;
    }

    public void setTimeOfWork(String timeOfWork) {
        this.timeOfWork = timeOfWork;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(boolean workingDay) {
        this.workingDay = workingDay;
    }
    
    
}
