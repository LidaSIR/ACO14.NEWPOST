package newpost.test.view;

import newpost.db.AppDataContainer;
import newpost.model.common.Passport;
import newpost.model.office.Client;
import newpost.view.frame.ClientView;

/**
 * Created by sasha on 20.08.2016.
 */
public class TestCllientView {

    public static void main(String[] args) {

        AppDataContainer app = new AppDataContainer();
        Client cl = new Client("380509999999" ,new Passport("alex","fg123456"), "1@2.mail.ru");

        ClientView mf = new ClientView(app, cl);
    }
}
