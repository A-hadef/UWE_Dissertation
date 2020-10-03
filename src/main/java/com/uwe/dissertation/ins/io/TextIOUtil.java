package com.uwe.dissertation.ins.io;

import org.beryx.textio.BooleanInputReader;
import org.beryx.textio.DoubleInputReader;
import org.beryx.textio.IntInputReader;
import org.beryx.textio.StringInputReader;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TextIOUtil {

    private static final TextIO textIO;
    private static final TextTerminal<?> textTerminal;
    private static final StringInputReader stringInputReader;
    private static final StringInputReader passwordReader;
    private static final IntInputReader intInputReader;
    private static final BooleanInputReader booleanInputReader;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DoubleInputReader doubleInputReader;

    private TextIOUtil() {
        throw new UnsupportedOperationException("TextIO utility class, do not initialise");
    }

    public static <T extends Enum<T>> T readOption(Class<T> enumType, String prompt) {
        return textIO.newEnumInputReader(enumType).read(prompt);
    }

    public static LocalDate readDate(String prompt) {
        LocalDate localDate;
        do {
            localDate = safeReadDate(prompt);
        } while (localDate == null);
        return localDate;
    }

    private static LocalDate safeReadDate(String prompt) {
        try {
            return LocalDate.parse(readString(prompt + " in the format 'DD/MM/YYYY'"), formatter);
        } catch (DateTimeParseException e) {
            textTerminal.println("Date not in proper format, please try again");
            return null;
        }
    }

    public static String readString(String prompt) {
        return stringInputReader.read(prompt);
    }

    public static String readPassword(String prompt) {
        return passwordReader.read(prompt);
    }

    public static void println() {
        println("");
    }

    public static void println(String prompt) {
        textTerminal.println(prompt);
    }

    public static void println(String prompt, Object... args) {
        println(String.format(prompt, args));
    }

    public static int readInt(String prompt) {
        return intInputReader.read(prompt);
    }

    public static boolean readBoolean(String prompt) {
        return booleanInputReader.read(prompt);
    }


    static {
        textIO = TextIoFactory.getTextIO();
        textTerminal = textIO.getTextTerminal();
        stringInputReader = textIO.newStringInputReader();
        passwordReader = textIO.newStringInputReader().withMinLength(6).withInputMasking(true);
        intInputReader = textIO.newIntInputReader();
        booleanInputReader = textIO.newBooleanInputReader().withDefaultValue(false);
        doubleInputReader = textIO.newDoubleInputReader();
    }

    public static List<String> readList(String prompt) {
        return Arrays.asList(readString(prompt + " separated by commas").split(","));
    }

    public static BigDecimal readBigDecimal(String prompt) {
        return BigDecimal.valueOf(doubleInputReader.read(prompt));
    }
}
