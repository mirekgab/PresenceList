package pl.mirekgab.presencelist.schedule;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ScheduleMapper {

    public static ScheduleDto map(Schedule schedule) {
        ScheduleDto dto = new ScheduleDto();
        dto.setId(schedule.getId());
        dto.setEmployeeId(schedule.getEmployee().getId());
        dto.setEmployeeName(schedule.getEmployee().getName() + " " + schedule.getEmployee().getSurname());
        dto.setYear(schedule.getYear());
        dto.setMonth(schedule.getMonth());
        dto.setDay(schedule.getDay());
        dto.setStartOfWork(schedule.stringStartOfWork());
        dto.setEndOfWork(schedule.stringEndOfWork());
        dto.setTimeOfWork(schedule.stringTimeOfWork());
        dto.setWorkingDay((schedule.getWorkingDay() != 0));
        return dto;
    }

    public static ScheduleMonthDto mapToScheduleMonthDto(Schedule schedule) {
        ScheduleMonthDto scheduleDto = new ScheduleMonthDto();
        scheduleDto.setId(schedule.getId());
        scheduleDto.setDay(schedule.getDay());
        scheduleDto.setDayOfWeek(LocalDate.of(schedule.getYear(), schedule.getMonth(), schedule.getDay()).getDayOfWeek().getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()));
        scheduleDto.setStartOfWork(schedule.getStartOfWork());
        scheduleDto.setEndOfWork(schedule.getEndOfWork());
        scheduleDto.setTimeOfWork(schedule.getTimeOfWork());
        scheduleDto.setWorkingDay(schedule.getWorkingDay()!=0);
        return scheduleDto;
    }
}
