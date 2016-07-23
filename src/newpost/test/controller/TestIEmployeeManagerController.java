package newpost.test.controller;

import newpost.controller.DirectorController;
import newpost.db.AppDataContainer;
import newpost.model.office.Employee;

/**
 * Created by Lida on 18.07.2016.
 */
public class TestIEmployeeManagerController {
    public static void main(String[] args) {

        DirectorController directorController = new DirectorController(new AppDataContainer());
        testAddStaff(directorController);
    }
//    Employee addStaff(String jobTitle, String fullName, String phone, int salary);
//    Employee removeStaff(String fullName);
//    Employee findStaffByName(String fullName);
//    Employee[] filterStaffByPosition(String jobTitle);
//    Employee[] showStaffInfo();
   // test will be false, since method each time generates new password for every new employee
    public static void testAddStaff (DirectorController directorController){
        String jobTitle = "Admin";
        String fullName = "Ann Smitt";
        String phone = "+380935689878";
        int salary = 8000;
        Employee expected = new Employee("Admin","Ann Smitt","+380935689878",8000);
        Employee actual = directorController.addStaff(jobTitle,fullName,phone,salary);
        System.out.printf("%s, test add staff \nexpected %s,\nactual %s\n",
                actual.toString().equals(expected.toString()),  expected, actual);
        System.out.println("passwords are different ");
        System.out.println();

    }
}

