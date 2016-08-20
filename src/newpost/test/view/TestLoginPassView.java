package newpost.test.view;

import newpost.db.AppDataContainer;
import newpost.db.InitDB;
import newpost.model.office.Employee;
import newpost.view.LoginFrame;

/**
 * Created by Anna on 19.08.2016.
 */
public class TestLoginPassView {

    public static void main(String[] args) {

        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);
        LoginFrame loginPassFrame = new LoginFrame(appDataContainer);
    }

}
