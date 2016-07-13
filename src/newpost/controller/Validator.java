package newpost.controller;

import newpost.model.*;
import sun.security.krb5.internal.Ticket;

import java.util.Objects;

/**
 * Created by sasha on 09.07.2016.
 */
public class Validator implements IValidator {

    public static final int MIN_PASSPORT_NUMBER_LENGTH = 8;
    public static final int MIN_PRODUCT_NAME_LENGTH = 2;
    public static final int MAX_PRODUCT_NAME_LENGTH = 30;
    public static final int MIN_PRODUCT_PRICE = 0;
    public static final int MIN_PRODUCT_SIZE = 0;
    public static final int MIN_PHONE_LENGTH = 9;
    public static final int MAX_PHONE_LENGTH = 12;

    ResultValidator resultValidator = new ResultValidator();


    @Override
    public ResultValidator validation(Address address) {

        resultValidator.setErr(isTrueStringIsAlfabetic(address.getCity())
            && isTrueStringIsAlfabetic(address.getStreet()) && isTrueStringIsNum(address.getHouseNum()));

        resultValidator.setTextErr(String.format("Address: %b \n \t City - %b,\n \t Street - %b,\n \t HouseNum - %b.\n",
                resultValidator.getErr(), isTrueStringIsAlfabetic(address.getCity()),
                isTrueStringIsAlfabetic(address.getStreet()), isTrueStringIsNum(address.getHouseNum())));

        return resultValidator;
    }

    @Override
    public ResultValidator validation(Client client) {

        resultValidator.setErr(isPhone(client.getPhone()) && isPassport(client.getPassport()));

        resultValidator.setTextErr(String.format("Client: %b\n \t phone - %b,\n \t passport: \n \t\t " +
                        "fullName - %b,\n \t\t passportNumber - %b\n", resultValidator.getErr(),
                isPhone(client.getPhone()), isTrueStringIsAlfabetic(client.getPassport().getFullname()),
                isPassportNumber(client.getPassport().getNumber())));

        return resultValidator;
    }

    @Override
    public ResultValidator validation(Product product) {

        resultValidator.setErr(isNameLength(MIN_PRODUCT_NAME_LENGTH, MAX_PRODUCT_NAME_LENGTH, product.getName())
                && product.getPrice() >= MIN_PRODUCT_PRICE && isSize(product.getSize()));

        resultValidator.setTextErr(String.format("Product: %b \n \t name - %b, \n \t prise - %b, \n \t size - %b",
                resultValidator.getErr(), isNameLength(MIN_PRODUCT_NAME_LENGTH, MAX_PRODUCT_NAME_LENGTH, product.getName()),
                product.getPrice() >= MIN_PRODUCT_PRICE, isSize(product.getSize())));

        return resultValidator;
    }

    @Override
    public ResultValidator validation(PostTicket postTicket) {

        return resultValidator;
    }

    private boolean isNameLength(int min, int max, String name){
        return name.length() >= min && name.length() <= max;
    }

    private boolean isSize(Size size){
        return size.getHeight() > MIN_PRODUCT_SIZE && size.getLength() > MIN_PRODUCT_SIZE
                && size.getWeight() > MIN_PRODUCT_SIZE && size.getWidth() > MIN_PRODUCT_SIZE;
    }

    private boolean isPassport (Passport passport){
        return isTrueStringIsAlfabetic(passport.getFullname()) && isPassportNumber(passport.getNumber());
    }

    private boolean isPassportNumber (String numPassport){
        return numPassport.length() >= MIN_PASSPORT_NUMBER_LENGTH
                && isAlfabet(numPassport.substring(0,2)) && isNum(numPassport.substring(2));
    }

    private boolean isPhone(String phone){
        return phone.length() >= MIN_PHONE_LENGTH && phone.length()<= MAX_PHONE_LENGTH && isTrueStringIsNum(phone);
    }

    private boolean isTrueStringIsAlfabetic(String str){
        return isNotEmpty(str) && isAlfabet(str);
    }

    private boolean isTrueStringIsNum (String str){
        return isNotEmpty(str) && isNum(str);
    }



    private boolean isNotEmpty(String str) {
        return !str.isEmpty();
    }

    private boolean isNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isAlfabet(String str) {
        String[] wordArray = str.split(" ");
        for (int i = 0; i < wordArray.length; i++) {
            for (int j = 0; j < wordArray[i].length(); j++ ) {
                if (!Character.isAlphabetic(wordArray[i].charAt(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
