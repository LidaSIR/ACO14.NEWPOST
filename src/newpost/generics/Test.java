package newpost.generics;

import newpost.model.common.Passport;
import newpost.model.office.Client;
import newpost.model.office.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serhii on 21.08.16.
 */
public class Test {


    public static void main(String[] args) {
        List<Client> clients = new ArrayList<>();

        test(clients);

    }

    public static void test(List<? extends User> list){
        User client = (Client) list.get(0);
        //list.add(new User(null,null));
        //list.add(new Client("sdf",new Passport("sdf","sdf")));
    }

}


