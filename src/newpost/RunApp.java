package newpost;

import newpost.db.AppDataContainer;
import newpost.controller.*;
import newpost.model.exceptions.ControllerException;
import newpost.model.exceptions.InputDataException;
import newpost.model.exceptions.LogException;
import newpost.model.exceptions.ValidationException;
import newpost.view.Menu;

/**
 * Created by Serhii Fursenko on 08.07.2016.
 * fyrsenko@gmail.com
 */
public class RunApp {

    public static void main(String[] args) throws ControllerException, InputDataException, ValidationException, LogException {
        AppDataContainer appDataContainer = new AppDataContainer();

        DirectorController directorController = new DirectorController(appDataContainer);

        IManagerController managerController = new ValidationManagerControllerProxy(
                                                    new LoggingManagerControllerProxy(
                                                            new ManagerController(appDataContainer)), new Validator());

        Menu menu = new Menu();
        menu.start(
                new ValidationClientControllerProxy(
                        new LoggingClientControllerProxy(
                            new ClientController(appDataContainer)), new Validator()));
    }
}
