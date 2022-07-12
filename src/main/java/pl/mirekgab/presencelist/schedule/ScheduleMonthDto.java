


package pl.mirekgab.presencelist.schedule;

import java.time.LocalTime;


public class ScheduleMonthDto {
    private Long id;
    private int day;
    private String dayOfWeek;
    private int startOfWork;
    private int endOfWork;
    private int timeOfWork;

    public ScheduleMonthDto() {
    }

    public ScheduleMonthDto(Long id, int day, String dayOfWeek, int startOfWork, int endOfWork, int timeOfWork) {
        this.id = id;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.startOfWork = startOfWork;
        this.endOfWork = endOfWork;
        this.timeOfWork = timeOfWork;
    }
    
    

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getStartOfWork() {
        return startOfWork;
    }

    public void setStartOfWork(int startOfWork) {
        this.startOfWork = startOfWork;
    }

    public int getEndOfWork() {
        return endOfWork;
    }

    public void setEndOfWork(int endOfWork) {
        this.endOfWork = endOfWork;
    }

    public int getTimeOfWork() {
        return timeOfWork;
    }

    public void setTimeOfWork(int timeOfWork) {
        this.timeOfWork = timeOfWork;
    }
    
    public String stringStartOfWork() {
        return LocalTime.ofSecondOfDay(startOfWork).toString();
    }
    
    public String stringEndOfWork() {
        return LocalTime.ofSecondOfDay(endOfWork).toString();
    }
    public String stringTimeOfWork() {
        return LocalTime.ofSecondOfDay(timeOfWork).toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
