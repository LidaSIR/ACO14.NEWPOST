package newpost.test.view;

import newpost.db.AppDataContainer;
import newpost.db.InitDB;
import newpost.view.frame.LoginFrame;

/**
 * Created by Anna on 19.08.2016.
 */
public class TestLoginPassView {

    public static void main(String[] args) {
        AppDataContainer appDataContainer = new AppDataContainer();

        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
               InitDB.saveDBToFileAsJson(appDataContainer);
            }
        });

        InitDB.initDB(appDataContainer);
        LoginFrame loginPassFrame = new LoginFrame(appDataContainer);
    }

}
