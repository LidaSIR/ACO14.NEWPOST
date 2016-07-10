package newpost.controller;

import newpost.model.*;
import sun.security.krb5.internal.Ticket;

/**
 * Created by sasha on 09.07.2016.
 */
public class Validator implements IValidator {

    public static final int MIN_PASSPORT_LENGTH = 8;
    public static final int MIN_PRODUCTNAME_LENGTH = 2;
    public static final int MAX_PRODUCTNAME_LENGTH = 20;
    public static final int MIN_PRODUCPRICE = 0;
    public static final int MIN_PHONE_LENGTH = 9;
    public static final int MAX_PHONE_LENGTH = 12;

    @Override
    public boolean validation(Address address) {
        return trueStringAlfab(address.getCity()) && trueStringAlfab(address.getStreet())
                && trueStringNum(address.getHouseNum());
    }

    @Override
    public boolean validation(Client client) {
        return validPassport(client.getPassport()) && validPhone(client.getPhone());
    }

    @Override
    public boolean validation(Product product) {
        return product.getName().length()>MIN_PRODUCTNAME_LENGTH
                && product.getName().length()<MAX_PRODUCTNAME_LENGTH
                            && validSize(product.getSize()) && product.getPrice()>=MIN_PRODUCPRICE;
    }

    private boolean validation(Product[] products) {

        for (int i = 0; i < products.length; i++){

            if (products[i].getName().length()>MIN_PRODUCTNAME_LENGTH
                    && products[i].getName().length()<MAX_PRODUCTNAME_LENGTH
                    && validSize(products[i].getSize()) && products[i].getPrice()>=MIN_PRODUCPRICE == false){
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean validation(PostTicket postTicket) {
        return validation(postTicket.getClient()) && validation(postTicket.getProducts());
    }



    private boolean validSize(Size size){
        return size.getHeight()>0 && size.getLength()>0 && size.getWeight()>0;
    }

    private boolean validPassportNumber(String str) {
        return (str.length() >= MIN_PASSPORT_LENGTH) && isAlfabet(str.substring(0, 2)) && isNum(str.substring(2));
    }

    private boolean validPassport(Passport passport) {
        return trueStringAlfab(passport.getFullname()) && validPassportNumber(passport.getNumber());
    }

    private boolean validPhone(String str) {
        return trueStringNum(str) && (str.length() <= MIN_PHONE_LENGTH)
                                        && (str.length() >= MAX_PHONE_LENGTH);
    }

    private boolean trueStringNum(String str) {

        if (!(notEmpty(str))) {
            System.out.println("string empty!");
        } else if (!(isNum(str))) {
            System.out.println("string is contains invalid characters!");
        }
        return notEmpty(str) && isNum(str);
    }

    private boolean trueStringAlfab(String str) {

        if (!(notEmpty(str))) {
            System.out.println("string empty!");
        } else if (!(isAlfabet(str))) {
            System.out.println("string is contains invalid characters!");
        }
        return notEmpty(str) && isAlfabet(str);
    }


    private boolean notEmpty(String str) {
        return !str.isEmpty();
    }

    private boolean isNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!(Character.isDigit(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    private boolean isAlfabet(String str) {
        boolean num = true;
        for (int i = 0; i < str.length() && num; i++) {
            if (!(Character.isAlphabetic(str.charAt(i)))) {
                num = false;
            }
        }
        return num;
    }
}
