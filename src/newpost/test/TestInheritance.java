package newpost.test;

import newpost.db.AppDataContainer;
import newpost.db.InitDB;
import newpost.model.common.Passport;
import newpost.model.office.Client;

/**
 * Created by Lida on 19.08.2016.
 */
public class TestInheritance {
    public static void main(String[] args) {

        AppDataContainer appDataContainer = new AppDataContainer();

        InitDB.initDB(appDataContainer);
      //  System.out.println(appDataContainer.getClients().toString());
        Client client = new Client("+380935612565", new Passport("Basdf dfsdf","GH856545"));
        System.out.println(client.toString());
    }
}
