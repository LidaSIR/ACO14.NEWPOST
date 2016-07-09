package newpost;

import ua.artcode.week2.newpost.controller.DirectorController;
import ua.artcode.week2.newpost.controller.ManagerController;
import ua.artcode.week2.newpost.db.AppDataContainer;

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
