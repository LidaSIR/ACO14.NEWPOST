package newpost.controller;

import newpost.model.*;

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
    public static final int MAX_PHONE_LENGTH = 13; // I change from 12 to 13


    @Override
    public ResultValidator validation(Address address) {

        ResultValidator resultValidator = new ResultValidator();

        resultValidator.setErr(isTrueStringIsAlfabetic(address.getCity())
                && isTrueStringIsAlfabetic(address.getStreet()) && isTrueStringIsNum(address.getHouseNum()));

        resultValidator.setTextErr(String.format("Address: %b \n \t City - %b,\n \t Street - %b,\n \t HouseNum - %b.\n",
                resultValidator.getErr(), isTrueStringIsAlfabetic(address.getCity()),
                isTrueStringIsAlfabetic(address.getStreet()), isTrueStringIsNum(address.getHouseNum())));

        return resultValidator;
    }

    @Override
    public ResultValidator validation(Client client) {

        ResultValidator resultValidator = new ResultValidator();

        resultValidator.setErr(isPhone(client.getPhone().substring(1)) && isPassport(client.getPassport()));

        resultValidator.setTextErr(String.format("Client: %b\n \t phone - %b,\n \t passport: \n \t\t " +
                        "fullName - %b,\n \t\t passportNumber - %b\n", resultValidator.getErr(),
                isPhone(client.getPhone()), isTrueStringIsAlfabetic(client.getPassport().getFullname()),
                isPassportNumber(client.getPassport().getNumber())));

        return resultValidator;
    }

    @Override
    public ResultValidator validation(Product product) {

        ResultValidator resultValidator = new ResultValidator();

        resultValidator.setErr(isNameLength(MIN_PRODUCT_NAME_LENGTH, MAX_PRODUCT_NAME_LENGTH, product.getName())
                && product.getPrice() >= MIN_PRODUCT_PRICE && isSize(product.getSize()));

        resultValidator.setTextErr(String.format("Product: %b \n \t name - %b, \n \t prise - %b, \n \t size - %b \n",
                resultValidator.getErr(), isNameLength(MIN_PRODUCT_NAME_LENGTH, MAX_PRODUCT_NAME_LENGTH, product.getName()),
                product.getPrice() >= MIN_PRODUCT_PRICE, isSize(product.getSize())));

        return resultValidator;
    }

    @Override
    public ResultValidator validation(PostTicket postTicket) {

        ResultValidator resultValidator = new ResultValidator();

        resultValidator.setErr(validation(postTicket.getClient()).getErr() && validation(postTicket.getFrom()).getErr()
                && validation(postTicket.getTo()).getErr() && isValidProductArr(postTicket.getProducts()).getErr());

        resultValidator.setTextErr(validation(postTicket.getClient()).getTextErr() + validation(postTicket.getFrom()).getTextErr()
                + validation(postTicket.getTo()).getTextErr() + isValidProductArr(postTicket.getProducts()).getTextErr());

        return resultValidator;
    }

    private  ResultValidator isMyDate(MyDate myDate){
        ResultValidator resultValidator = new ResultValidator();

        return resultValidator;
    }

    private ResultValidator isValidProductArr(Product[] products){

        ResultValidator resultValidator = new ResultValidator();
        resultValidator.setTextErr("");
        resultValidator.setErr(true);

        if(products.length == 0){
            resultValidator.setErr(false);
            resultValidator.setTextErr("product[] is empty \n");
        } else {
            for (int i = 0; i < products.length; i++){
                resultValidator.setErr(resultValidator.getErr() && validation(products[i]).getErr());
                resultValidator.setTextErr(resultValidator.getTextErr() + "product [" + i +"] \n"
                        + validation(products[i]).getTextErr());
            }
        }
        return resultValidator;
    }

    private boolean isNameLength(int min, int max, String name){
        return name.length() >= min && name.length() <= max;
    }

    private boolean isSize(Size size){
        return size.getHeight() > MIN_PRODUCT_SIZE && size.getLength() > MIN_PRODUCT_SIZE
                && size.getWeight() > MIN_PRODUCT_SIZE && size.getWidth() > MIN_PRODUCT_SIZE;
    }

    public boolean isPassport (Passport passport){
        return isTrueStringIsAlfabetic(passport.getFullname()) && isPassportNumber(passport.getNumber());
    }

    private boolean isPassportNumber (String numPassport){
        return numPassport.length() >= MIN_PASSPORT_NUMBER_LENGTH
                && isAlfabet(numPassport.substring(0,2)) && isNum(numPassport.substring(2));
    }

    public boolean isPhone(String phone){
        return phone.length() >= MIN_PHONE_LENGTH && phone.length()<= MAX_PHONE_LENGTH && isTrueStringIsNum(phone.substring(1));
    }

    private boolean isTrueStringIsAlfabetic(String str){
        return isNotEmpty(str) && isAlfabet(str);
    }

    private boolean     isTrueStringIsNum (String str){
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