package newpost.controller;

import newpost.model.Address;
import newpost.model.Client;
import newpost.model.Product;
import sun.security.krb5.internal.Ticket;

/**
 * Created by sasha on 09.07.2016.
 */
public class Validator implements IValidator {
    @Override
    public boolean validation(Address address) {
        return false;
    }

    @Override
    public boolean validation(Client client) {
        return false;
    }

    @Override
    public boolean validation(Product product) {
        return false;
    }

    @Override
    public boolean validation(Ticket ticket) {
        return false;
    }

    private boolean notNull (String str) {
        return !(str.length()==0);
    }

    private boolean notEmpty (String str){
        return str.isEmpty();
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
