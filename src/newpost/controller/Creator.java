package newpost.controller;

import newpost.model.Address;

/**
 * Created by macaque on 10.07.2016.
 */
public class Creator {

    public static Address  addressCreator(){
        return new Address("Kiyv","Lesi","22");
    }
}
