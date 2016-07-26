package newpost;

import newpost.controller.interfaces.IManagerController;
import newpost.controller.interfaces.IPostController;
import newpost.controller.proxy.LoggingClientControllerProxy;
import newpost.controller.proxy.LoggingManagerControllerProxy;
import newpost.db.AppDataContainer;
import newpost.controller.*;
import newpost.db.InitDB;
import newpost.exceptions.ControllerException;
import newpost.exceptions.InputDataException;
import newpost.exceptions.LogException;
import newpost.exceptions.ValidationException;
import newpost.validator.ValidationClientControllerProxy;
import newpost.validator.ValidationManagerControllerProxy;
import newpost.validator.Validator;
import newpost.view.Menu;

/**
 * Created by Serhii Fursenko on 08.07.2016.
 * fyrsenko@gmail.com
 */
public class RunApp {

    public static void main(String[] args) throws ControllerException, InputDataException, ValidationException, LogException {
        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);
        DirectorController directorController = new DirectorController(appDataContainer);

        IPostController postOfficeController = new PostController(appDataContainer);

        IManagerController managerController = new ValidationManagerControllerProxy(
                                                    new LoggingManagerControllerProxy(
                                                            new ManagerController(appDataContainer)), new Validator());

        Menu menu = new Menu();
        menu.start(
                new ValidationClientControllerProxy(
                        new LoggingClientControllerProxy(
                            new ClientController(appDataContainer)), new Validator()), managerController, postOfficeController);
    }
}
