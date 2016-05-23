package ua.nure.revuk.SummaryTask4.db.util;

import java.util.regex.Pattern;

/**
 * Epam Final Project
 * Revuk Group Java 2-1
 *
 * @author Revuk Oleksandr
 * @version 1.0
 * @since 09.01.2016
 */
public class ValidUtils {

    public static boolean isCharacteristicTruckDoubleNumber(String number) {
        boolean result = false;

        if (number != null) {
            result = Pattern.compile("^\\d{0,2}\\.?\\d{0,2}$").matcher(number).matches();
        }

        return result;
    }

    public static boolean isNumber(String number) {
        boolean result = false;

        if (number != null) {
            result = Pattern.compile("^\\d+$").matcher(number).matches();
        }

        return result;
    }

    public static boolean isBoolean(String string) {
        boolean result = false;

        if (string != null && (string.equals("true") || string.equals("false"))) {
            result = true;
        }

        return result;
    }

    public static boolean isBoolean(String lorryWithSides, String refrigerator, String serviceable) {

        return ValidUtils.isBoolean(lorryWithSides) && ValidUtils.isBoolean(refrigerator) &&
                ValidUtils.isBoolean(serviceable);
    }

    public static boolean isCorrectDate(String date) {
        boolean result = false;

        if (date != null) {
            result = Pattern.compile(
                    "^(20)(1[6-9]|[2-9]\\d)-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)$").
                    matcher(date).matches();
        }

        return result;
    }

    public static String validUserInput(String login, String password, String firstName, String lastName, String mail,
                                        String photoLink, String roleId) {

        if (!validLoginOrPassword(login)) {
            return "No validation login";
        }

        if (!validLoginOrPassword(password)) {
            return "No validation password";
        }

        if (!isOnlyRussianAndEnglishWords(firstName)) {
            return "No validation first name";
        }

        if (!isOnlyRussianAndEnglishWords(lastName)) {
            return "No validation first name";
        }

        if (mail != null && !mail.equals("") && !Pattern.compile(
                "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$").matcher(mail).matches()) {
            return "No validation e-mail";
        }

        if (!validPhotoLinkNameFile(photoLink)) {
            return "No validation name of file photo";
        }

        if (roleId != null && !(roleId.equals("1") || roleId.equals("2") || roleId.equals("3"))) {
            return "No validation role";
        }

        return "ok";
    }

    public static boolean isOnlyRussianAndEnglishWords(String str) {
        boolean result = false;

        if (str != null && !str.equals("") && str.length() < 26) {
            result = Pattern.compile("^[a-zA-Z\\u0430-\\u044F\\u0410-\\u042F]{1,25}$").matcher(str).matches();
        }

        return result;
    }

    public static boolean validLoginOrPassword(String str) {
        boolean result = false;

        if (str != null && !str.equals("") && str.length() < 11) {
            result = Pattern.compile("^[\\w\\u0430-\\u044F\\u0410-\\u042F]{1,10}$").matcher(str).matches();
        }

        return result;
    }

    public static boolean validTruckName(String str) {
        boolean result = false;

        if (str != null && !str.equals("") && str.length() < 26) {
            result = Pattern.compile("^[\\u0430-\\u044F\\u0410-\\u042F|\\w| ]{1,25}$").matcher(str).matches();
        }

        return result;
    }

    public static boolean validPhotoLinkNameFile(String str) {
        boolean result = false;

        if (str != null && !str.equals("") && str.length() < 26) {
            result = Pattern.compile("^\\w{0,20}\\.\\w{2,4}$").matcher(str).matches();
        }

        return result;
    }

    public static String numbersInputFormat(String number) {
        String result = null;

        if (number != null) {
            result = number.replaceAll(" ", "");
            result = result.replaceAll("-", "");
            result = result.replaceAll("\\+", "");
            result = result.replaceAll("\\(", "");
            result = result.replaceAll("\\)", "");
        }
        return result;
    }

    public static String nameInputFormat(String name) {
        String result = name;

        if (result != null) {
            result = result.substring(0, 1).toUpperCase()
                    + result.substring(1, result.length()).toLowerCase();
        }
        return result;
    }

    public static String inputValidCharacteristicTruck(String carrying, String capacity, String length) {
        if (carrying != null && !carrying.equals("") && !isCharacteristicTruckDoubleNumber(carrying)) {
            return "No validation carrying";
        }

        if (carrying != null && !carrying.equals("") && !isCharacteristicTruckDoubleNumber(capacity)) {
            return "No validation capacity";
        }

        if (carrying != null && !carrying.equals("") && !isCharacteristicTruckDoubleNumber(length)) {
            return "No validation length";
        }

        return "ok";
    }

    public static String inputValidFlightCreate(String flightNumber, String dateDeparture, String destination,
                                                String distance) {

        if (flightNumber == null || flightNumber.equals("") || !isNumber(flightNumber)) {
            return "No validation flightNumber";
        }

        if (dateDeparture == null || dateDeparture.equals("")) {
            return "No validation date of departure";
        } else {
            java.sql.Date sqlStartDate = new java.sql.Date(new java.util.Date().getTime());

            Integer isCorrectDateFromToday = Integer.valueOf(numbersInputFormat(String.valueOf(dateDeparture))) -
                    Integer.valueOf(numbersInputFormat(String.valueOf(sqlStartDate)));

            if (!isCorrectDate(dateDeparture) || (isCorrectDateFromToday < 0)) {
                return "No validation date of departure";
            }
        }

        if (destination == null || destination.equals("") || (destination.length() > 35)) {
            return "No validation destination";
        }

        if (distance == null || distance.equals("") || !isCharacteristicTruckDoubleNumber(distance) ||
                Double.valueOf(distance) > 10000.0) {
            return "No validation distance";
        }

        return "ok";
    }
}