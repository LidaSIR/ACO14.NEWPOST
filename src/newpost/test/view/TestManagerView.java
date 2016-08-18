package newpost.test.view;

import newpost.controller.ManagerController;
import newpost.db.AppDataContainer;
import newpost.db.InitDB;
import newpost.exceptions.ValidationException;
import newpost.model.common.Address;
import newpost.model.common.Passport;
import newpost.model.common.Product;
import newpost.model.common.Size;
import newpost.model.office.Client;
import newpost.view.ManagerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhii Fursenko on 17.08.16.
 * mail to fyrsenko@gmail.com
 */
public class TestManagerView {

    public static void main(String[] args) {

        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);
        ManagerView managerView = new ManagerView(appDataContainer);

        managerView.showManagerView();
    }
}
