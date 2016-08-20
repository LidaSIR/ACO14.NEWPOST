package newpost.test.view;

import newpost.db.AppDataContainer;
import newpost.db.InitDB;
import newpost.view.frame.LoginFrame;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Anna on 19.08.2016.
 */
public class TestLoginPassView {

    public static void main(String[] args) {

        try {
            AppDataContainer appDataContainer = InitDB.loadDBAsJson("src/resources/db.json");

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    InitDB.saveDBToFileAsJson(appDataContainer);
                }
            });

            System.out.println(appDataContainer.getClients());
            LoginFrame loginPassFrame = new LoginFrame(appDataContainer);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
