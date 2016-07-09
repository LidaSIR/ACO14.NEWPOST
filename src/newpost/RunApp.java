package newpost;

import newpost.db.AppDataContainer;
import newpost.controller.*;

/**
 * Created by Serhii Fursenko on 08.07.2016.
 * fyrsenko@gmail.com
 */
public class RunApp {

    public static void main(String[] args) {
        AppDataContainer appDataContainer = new AppDataContainer();

        DirectorController directorController = new DirectorController(appDataContainer);
        ManagerController managerController = new ManagerController(appDataContainer);
    }
}
