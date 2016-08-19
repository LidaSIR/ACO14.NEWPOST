package newpost.test.view;

import newpost.db.AppDataContainer;
import newpost.db.InitDB;
import newpost.model.office.Employee;
import newpost.view.LoginFrame;

/**
 * Created by Anna on 19.08.2016.
 */
public class TestLoginPassView {

    public static void main(String[] args) {

        AppDataContainer appDataContainer = new AppDataContainer();
        InitDB.initDB(appDataContainer);
        Employee employee = new Employee("Manager", "Anna", "777", 10000);
        System.out.println("test login = "    + employee.getLogin());
        System.out.println("test password = " + employee.getPassword());
        appDataContainer.getEmployees().add(employee);
        appDataContainer.getUsers().put(employee.getLogin(), employee);
        LoginFrame loginPassFrame = new LoginFrame(appDataContainer);
    }

}
