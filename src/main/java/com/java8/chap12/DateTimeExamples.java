package com.java8.chap12;

import com.sun.media.sound.SoftTuning;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Calendar;
import java.util.Date;

public class DateTimeExamples {

    private static final ThreadLocal<DateFormat> formatters = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {
//        userOldDate();
//        userLocalDate();
        userTemporalAdjuster();
        useDateFormatter();
    }

    private static void userOldDate() {
        Date date = new Date(117, 10, 22);
        System.out.println(date);
        System.out.println(formatters.get().format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.NOVEMBER, 22);
        System.out.println(calendar.getTime());
    }

    private static void userLocalDate() {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        System.out.println(date);

        int y = date.get(ChronoField.YEAR);
        int m = date.get(ChronoField.MONTH_OF_YEAR);
        int d = date.get(ChronoField.DAY_OF_MONTH);

        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println(time);

        LocalDateTime dt1 = LocalDateTime.of(2017, Month.NOVEMBER, 22, 21, 13, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(time);
        LocalDateTime dt4 = time.atDate(date);
        System.out.println(dt1);

        LocalDate date1 = dt1.toLocalDate();
        System.out.println(date1);
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(time1);

        Instant instant = Instant.ofEpochSecond(44 * 365 * 86400);
        Instant now = Instant.now();

        Duration d1 = Duration.between(LocalTime.of(13, 45, 10), time);
        Duration d2 = Duration.between(instant, now);
        System.out.println(d1.getSeconds());
        System.out.println(d2.getSeconds());

        Duration threeMinutes = Duration.ofMinutes(3);
        System.out.println(threeMinutes);
    }

    private static void userTemporalAdjuster(){
        LocalDate date = LocalDate.of(2017, 11, 22);
        date = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date);
        date = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(date);

        date = date.with(new NextWorkingDay());
        System.out.println(date);
        date = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        date = date.with(new NextWorkingDay());
        System.out.println(date);

        date = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        date = date.with((Temporal temporal)->{
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayOfAdd = 1;
            if(dow == DayOfWeek.FRIDAY) dayOfAdd = 3;
            else if(dow == DayOfWeek.SATURDAY) dayOfAdd = 2;
            return temporal.plus(dayOfAdd, ChronoUnit.DAYS);
        });
        System.out.println(date);
    }

    private static class NextWorkingDay implements TemporalAdjuster{

        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if(dow==DayOfWeek.FRIDAY) dayToAdd = 3;
            else if(dow ==DayOfWeek.SATURDAY) dayToAdd=2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }

    private static void useDateFormatter(){
        LocalDate date = LocalDate.of(2017, 11, 22);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(date.format(formatter));
        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

}
