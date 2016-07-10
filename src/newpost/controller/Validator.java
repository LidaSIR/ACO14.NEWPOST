package newpost.controller;

import newpost.model.Address;
import newpost.model.Client;
import newpost.model.Passport;
import newpost.model.Product;
import sun.security.krb5.internal.Ticket;

/**
 * Created by sasha on 09.07.2016.
 */
public class Validator implements IValidator {
    @Override
    public boolean validation(Address address) {
        return trueStringAlfab(address.getCity())&&trueStringAlfab(address.getStreet())&&trueStringNum(address.getHouseNum());
    }

    @Override
    public boolean validation(Client client) {
        return validPassport(client.getPassport())&&validPhone(client.getPhone());
    }

    @Override
    public boolean validation(Product product) {
        return false;
    }

    @Override
    public boolean validation(Ticket ticket) {
        return false;
    }

    private boolean validPassportNumber (String str){
        return (str.length()>=8) && isAlfabet(str.substring(0,2)) && isNum(str.substring(2));
    }

    private boolean validPassport (Passport passport){
        return trueStringAlfab(passport.getFullname())&&validPassportNumber(passport.getNumber());
    }

    private boolean validPhone(String str){
        return trueStringNum(str)&& (str.length()<=9) && (str.length()>=12);
    }

    private boolean trueStringNum(String str){

         if (!(notEmpty(str))){
            System.out.println("string empty!");
        } else if (!(isNum(str))){
            System.out.println("string is contains invalid characters!");
        }
        return  notEmpty(str)&&isNum(str);
    }

    private boolean trueStringAlfab(String str){

        if (!(notEmpty(str))){
            System.out.println("string empty!");
                    } else if (!(isAlfabet(str))){
            System.out.println("string is contains invalid characters!");
        }
        return notEmpty(str)&&isAlfabet(str);
    }


    private boolean notEmpty (String str){
        return !str.isEmpty();
    }

    private boolean isNum (String str){
        boolean num = true;
        for (int i=0; i<str.length() && num; i++){
            if (!(Character.isDigit(str.charAt(i)))){
                num = false;
            }
        }
        return num;
    }

    private boolean isAlfabet (String str){
        boolean num = true;
        for (int i=0; i<str.length() && num; i++){
            if (!(Character.isAlphabetic(str.charAt(i)))){
                num = false;
            }
        }
        return num;
    }
}
