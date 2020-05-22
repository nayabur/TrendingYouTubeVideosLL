import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Date {
    public int month;
    public int day;
    public int year;
    public String fullDate;

    private static int[] daysInMonth = {31,-99,31,30,31,30,31,31,30,31,30,31};

    public Date() {
    }

    //0 if equal, 1 if greater, -1 if less than
    public int compareTo(Date comparing) {
        if (this.year > comparing.year) {
            return 1;
        } else if (this.year < comparing.year) {
            return -1;
        } else if (this.month > comparing.month) {
            return 1;
        } else if (this.month < comparing.month) {
            return -1;
        } else if (this.day > comparing.day) {
            return 1;
        } else if (this.day < comparing.day) {
            return -1;
        }
        return 0;
    }

    public long subtract(Date comparing) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dateBefore = LocalDate.parse(this.fullDate, formatter);
        LocalDate dateAfter = LocalDate.parse(comparing.fullDate, formatter);
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);

        return Math.abs(noOfDaysBetween);
    }

    public void splitDate (String stringDate){
        fullDate=stringDate;
        String[] values = stringDate.split("/");
        month = Integer.parseInt(values[0]);
        day = Integer.parseInt(values[1]);
        year = Integer.parseInt(values[2]);
    }


    public void print(){

        System.out.print(fullDate);
    }

    public void makeFullDate (){

        String sDay = "" + day;
        if (sDay.length()==1){
            sDay = "0" + sDay;
        }
        String sMonth = "" + month;
        if (sMonth.length()==1){
            sMonth = "0" + sMonth;
        }
        String result = sMonth + "/" + sDay + "/" + year;
        fullDate = result;
    }


}
