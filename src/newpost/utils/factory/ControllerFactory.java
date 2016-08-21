package newpost.utils.factory;

import newpost.controller.ClientController;
import newpost.controller.LoginController;
import newpost.controller.ManagerController;
import newpost.controller.interfaces.IClientController;
import newpost.controller.interfaces.ILoginController;
import newpost.controller.interfaces.IManagerController;
import newpost.controller.proxy.LoggingClientControllerProxy;
import newpost.controller.proxy.LoggingManagerControllerProxy;
import newpost.db.AppDataContainer;
import newpost.db.InitDB;
import newpost.utils.AppConstants;
import newpost.validator.ValidationClientControllerProxy;
import newpost.validator.ValidationManagerControllerProxy;
import newpost.validator.Validator;

import java.io.IOException;

/**
 * Created by serhii on 21.08.16.
 */
public class ControllerFactory {

    private static AppDataContainer appDataContainer;
    private static Validator validator;

    static {
        String location = ControllerFactory.class.getResource(AppConstants.DB_JSON_PATH).getPath();
        try {
            appDataContainer = InitDB.loadDBAsJson(location);
            validator = new Validator();


            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    InitDB.saveDBToFileAsJson(appDataContainer);
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static IClientController getClientController() {
        return new ValidationClientControllerProxy(
                new LoggingClientControllerProxy(
                        new ClientController(appDataContainer)), validator);
    }

    public static IManagerController getManagerController() {
        return new ValidationManagerControllerProxy(
                new LoggingManagerControllerProxy(
                        new ManagerController(appDataContainer)), validator);
    }

    public static ILoginController getLoginController(){
        return new LoginController(appDataContainer);
    }
}
