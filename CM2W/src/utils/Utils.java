package utils;

public class Utils {
    public static boolean isValidSize(String size) {
        switch (size.toLowerCase()) {
            case "s":
            case "m":
            case "l":
                return true;
            default:
                return false;
        }
    }
}
