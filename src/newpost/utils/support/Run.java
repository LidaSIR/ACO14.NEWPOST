package newpost.utils.support;

import newpost.db.AppDataContainer;
import newpost.db.InitDB;
import newpost.view.frame.LoginFrame;

/**
 * Created by Vladislav on 20.08.2016.
 */
public class Run {
    public static void main(String[] args) {
        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);
        new LoginFrame(appDataContainer);
    }
}
