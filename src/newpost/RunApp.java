package newpost;

import newpost.controller.proxy.LoggingClientControllerProxy;
import newpost.controller.proxy.LoggingManagerControllerProxy;
import newpost.db.AppDataContainer;
import newpost.controller.*;
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

        DirectorController directorController = new DirectorController(appDataContainer);

        Validator validator = new Validator();

        ManagerController managerController1 = new ManagerController(appDataContainer);

        LoggingManagerControllerProxy originalController = new LoggingManagerControllerProxy(managerController1);

        IManagerController managerController = new ValidationManagerControllerProxy(originalController, validator);

        ClientController clientController = new ClientController(appDataContainer);

        LoggingClientControllerProxy controller1 = new LoggingClientControllerProxy(clientController);

        ValidationClientControllerProxy controller = new ValidationClientControllerProxy(controller1, validator);

        MoneyController moneyController = new MoneyController(appDataContainer);



        Menu menu = new Menu();
        menu.start(controller, managerController,moneyController,directorController);
    }
}
