package newpost;

import newpost.db.AppDataContainer;
import newpost.controller.*;
import newpost.view.Menu;

/**
 * Created by Serhii Fursenko on 08.07.2016.
 * fyrsenko@gmail.com
 */
public class RunApp {

    public static void main(String[] args) {
        AppDataContainer appDataContainer = new AppDataContainer();

        DirectorController directorController = new DirectorController(appDataContainer);
        ManagerController managerController = new ManagerController(appDataContainer);

        Menu menu = new Menu();
        menu.start(
                new ValidationControllerProxy(
                        new LoggingClientControllerProxy(
                                new ClientController(appDataContainer)),new Validator()));
    }
}
