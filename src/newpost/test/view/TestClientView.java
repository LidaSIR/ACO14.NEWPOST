package newpost.test.view;

import newpost.db.AppDataContainer;
import newpost.db.InitDB;
import newpost.view.frame.ClientView;


/**
 * Created by pashc on 20.08.2016.
 */
public class TestClientView {
    public static void main(String[] args) {
        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);

        //ClientView clientView = new ClientView(appDataContainer);
    }
}
