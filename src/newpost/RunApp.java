package newpost;

import newpost.controller.interfaces.IClientController;
import newpost.controller.interfaces.ILoginController;
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
import newpost.utils.AppConstants;
import newpost.utils.factory.ControllerFactory;
import newpost.validator.ValidationClientControllerProxy;
import newpost.validator.ValidationManagerControllerProxy;
import newpost.validator.Validator;
import newpost.view.console.Menu;
import newpost.view.frame.LoginFrame;

public class RunApp {

    public static void main(String[] args) throws ControllerException, InputDataException, ValidationException, LogException {


        try {


            IManagerController managerController = ControllerFactory.getManagerController();

            IClientController clientController = ControllerFactory.getClientController();

            ILoginController loginController = ControllerFactory.getLoginController();

            LoginFrame loginPassFrame = new LoginFrame(
                    loginController,managerController,clientController);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
