//https://stackoverflow.com/questions/7935858/the-split-method-in-java-does-not-work-on-a-dot
//https://stackabuse.com/regex-splitting-by-character-unless-in-quotes/

import java.util.regex.Pattern;

public class TrendDate extends Date {

    public TrendDate(String date) {
        String[] values = date.split(Pattern.quote("."));
        year = Integer.parseInt(values[0]);
        day = Integer.parseInt(values[1]);
        month = Integer.parseInt(values[2]);
        makeFullDate();
    }
}
