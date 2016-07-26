package newpost.test.controller;

import newpost.controller.DirectorController;
import newpost.db.AppDataContainer;
import newpost.model.office.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lida on 18.07.2016.
 */
public class TestIEmployeeManagerController {
    public static void main(String[] args) {

        DirectorController directorController = new DirectorController(new AppDataContainer());
        testAddStaff(directorController);
    }

//    Employee removeStaff(String fullName);
//    Employee findStaffByName(String fullName);
//    List<Employee> showStaffInfo();

    public static void testRemoveStaff(DirectorController directorController){
        Employee employee = new Employee("Admin","Ann Smitt","+380935689878",8000);

    }
    public static void testFilterStaffByPosition(DirectorController directorController){
        Employee employee = new Employee("Admin","Rita Smitt","+380935686878",8000);
        Employee employee1 = new Employee("Admin","Den Atryr","+380978989825",7000);
        List<Employee> expected = new ArrayList();
        expected.add(employee);
        expected.add(employee1);

        List<Employee>actual = directorController.filterStaffByPosition("Admin");
        System.out.printf("%s, Filter Staff by Position \nexpected %s,  %s\nactual %s\n",
                expected.toString().equals(actual.toString()), expected, actual);
        System.out.println();

    }


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

