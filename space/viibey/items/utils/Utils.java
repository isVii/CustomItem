package space.viibey.items.utils;

/*
    @Author: ImViibey
    @Date 27/09/2021
*/
public class Utils {

    public static boolean isInt(String integer) {
        try {
            Integer.parseInt(integer);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
