
package pl.mirekgab.presencelist.schedule;


public class ScheduleMapper {

    public static ScheduleDto map(Schedule schedule) {
        ScheduleDto dto = new ScheduleDto();
        dto.setEmployeeId(schedule.getEmployee().getId());
        dto.setEmployeeName(schedule.getEmployee().getName()+" "+schedule.getEmployee().getSurname());
        dto.setYear(schedule.getYear());
        dto.setMonth(schedule.getMonth());
        dto.setDay(schedule.getDay());
        dto.setStartOfWork(schedule.stringStartOfWork());
        dto.setEndOfWork(schedule.stringEndOfWork());
        dto.setTimeOfWork(schedule.stringTimeOfWork());
        return dto;
    }
    
    public static Schedule map(ScheduleDto dto) {
        Schedule s = new Schedule();
        
        return s;
    }
}
