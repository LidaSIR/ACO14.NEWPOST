package newpost.test;

import newpost.controller.ManagerController;
import newpost.db.AppDataContainer;
import newpost.model.Client;
import newpost.model.Passport;

/**
 * Created by home on 08.07.2016.
 */
public class TestManagerController {

    public static void main(String[] args) {

        testGetClient();

        testAddClient();

    }

    private static void testGetClient() {
        ManagerController managerController = new ManagerController(new AppDataContainer());

        managerController.addClient(new Passport("ER546454", "Bob Jason"),"0935551666");

        String in = "0935551666";
        Client expected = new Client("0935551666", new Passport("ER546454", "Bob Jason"));
        Client actual = managerController.getClient(in);
        System.out.printf("%s, test get client in %s,\nexpected %s,\nactual %s\n",
                actual.toString().equals(expected.toString()), in,  expected, actual);
    }

    public static void testAddClient() {

        ManagerController managerController = new ManagerController(new AppDataContainer());
        Passport in1 = new Passport("ER546454", "Bob Jason");
        String in2 = "0935551666";
        Client expected = new Client("0935551666", new Passport("ER546454", "Bob Jason"));

        Client actual = managerController.addClient(in1, in2);

        System.out.printf("%s, test add client in1 %s,\nexpected %s,\nactual %s\n",
                actual.toString().equals(expected.toString()), in1,  expected, actual);
    }
}
