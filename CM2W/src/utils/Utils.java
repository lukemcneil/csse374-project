package utils;

import domain.MachineType;
import domain.Size;

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

    public static Size getSize(String s) {
        switch (s.toUpperCase()) {
            case "S":
            case "SMALL":
                return Size.SMALL;
            case "M":
            case "MEDIUM":
                return Size.MEDIUM;
            case "L":
            case "LARGE":
                return Size.LARGE;
            default:
                throw new Error("Invalid size \"" + s + "\"");
        }
    }

    public static MachineType getMachineType(String s) {
        switch (s.toUpperCase()) {
            case "SIMPLE": case "BASIC":
                return MachineType.SIMPLE;
            case "AUTOMATED":
                return MachineType.AUTOMATED;
            case "PROGRAMMABLE":
                return MachineType.PROGRAMMABLE;
            default:
                throw new Error("Invalid machine type \"" + s + "\"");
        }
    }
}
