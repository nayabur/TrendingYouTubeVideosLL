// https://howtodoinjava.com/java/string/get-first-4-characters/
//https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html

public class PublishDate extends Date {

    public PublishDate(String date) {

        String[] values = date.split("-");
        year = Integer.parseInt(values[0]);
        month = Integer.parseInt(values[1]);

        values[2] = values[2].substring(0,2);
        day = Integer.parseInt(values[2]);
        makeFullDate();

    }
    public PublishDate(){
    }
}
