package newpost.test.view;

import newpost.controller.DataInitFactory;
import newpost.db.AppDataContainer;
import newpost.db.InitDB;
import newpost.model.office.Client;
import newpost.utils.factory.ControllerFactory;
import newpost.view.frame.ClientView;


/**
 * Created by pashc on 20.08.2016.
 */
public class TestClientView {
    public static void main(String[] args) {
        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);
        Client client = DataInitFactory.clientCreator();
        ClientView clientView = new ClientView(ControllerFactory.getClientController(), client);
    }
}
