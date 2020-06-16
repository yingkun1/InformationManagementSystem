package online.luffyk.util;

public class Utils {
    public String dateConversion(String date){
        String[] split = date.split("/");
        String month = split[0];
        String day = split[1];
        String year = split[2];
        StringBuilder stringBuilder = new StringBuilder(year);
        stringBuilder.append("-");
        stringBuilder.append(month);
        stringBuilder.append("-");
        stringBuilder.append(day);
        return stringBuilder.toString();
    }
}
