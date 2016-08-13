package newpost.test.controller;

import newpost.controller.DataInitFactory;
import newpost.controller.DirectorController;
import newpost.db.AppDataContainer;
import newpost.model.office.Employee;
import newpost.test.time_counter.Action;
import newpost.test.time_counter.TimeCounter;

import java.util.List;

/**
 * Created by Артем on 23.07.2016.
 */
// todo use test data from generated db
// todo replace annon classes by lambda
public class TestDirectorController {

    private static int staffNumber = 150;

    public static void main(String[] args) {

        System.out.println("Staff = " + staffNumber + " Persons");
        AppDataContainer appDataContainer = new AppDataContainer();
        List<Employee> employees = appDataContainer.getEmployees();
        generateTestData(employees);

        DirectorController directorController = new DirectorController(appDataContainer);

        testFindStaff(directorController);
        testShowStaff(directorController);
        testRemoveStaff(directorController);
        testFilterStaff(directorController);
        testAddStaff(directorController);

        testAddStaffTime(directorController);
        testRemoveStaffTime(directorController);
        testFindStaffTime(directorController);
        testFilterStaffTime(directorController);
        testShowStaffTime(directorController);

    }

    private static void generateTestData(List<Employee> employees) {
        for (int i = 0; i < staffNumber; i++) {
            String fullName = DataInitFactory.createFullName();
            String jobTitle = DataInitFactory.createJobTitle();
            String phone = DataInitFactory.createPnoneNumber();
            int salary = DataInitFactory.createSalary();
            Employee e = new Employee(jobTitle, fullName, phone, salary);
            employees.add(e);
        }
    }

    private static void testFindStaff(DirectorController directorController) {
        if (directorController.findStaffByName("Anton Stepanov") != null) {
            System.out.println("FindStaff test passed " + "\n\t" + directorController.findStaffByName("Nikola Tesla").toString() + "\n");
        } else System.out.println("FindStaff failed");
    }

    private static void testShowStaff(DirectorController directorController) {
        if (directorController.showStaffInfo() != null) {
            System.out.println("ShowStaff test passed " + "\n" + directorController.showStaffInfo().toString() + "\n");
        } else System.out.println("Show staff failed");

    }

    private static void testRemoveStaff(DirectorController directorController) {
        if (directorController.removeStaff("Ivan Ivanov") != null) {
            System.out.println("RemoveStaff test passed " + "\n" + directorController.removeStaff("Ivan Ivanov").toString() + "\n");
        } else System.out.println("RemoveStaff failed");
    }

    private static void testFilterStaff(DirectorController directorController) {
        if (directorController.findStaffByName("Nikola Tesla") != null) {
            System.out.println("FilterStaff test passed " + "\n" + directorController.findStaffByName("Nikola Tesla").toString() + "\n");
        } else System.out.println("FilterStaff failed");
    }

    private static void testAddStaff(DirectorController directorController) {

        System.out.println("AddStaff test:");
        String fullName = DataInitFactory.createFullName();
        String jobTitle = DataInitFactory.createJobTitle();
        String phone = DataInitFactory.createPnoneNumber();
        int salary = DataInitFactory.createSalary();

        Employee employee = directorController.addStaff(jobTitle, fullName, phone, salary);

        if (employee != null) {
            System.out.println("AddStaff test passed " + "\n" + employee.toString() + "\n");
        } else System.out.println("AddStaff failed");

    }

    private static void testAddStaffTime(DirectorController directorController) {

        // testing Staff Adding Time (in mills)

        TimeCounter selectTime = new TimeCounter();

        long addStaffTime = selectTime.count(() -> {

            for (int i = 0; i < staffNumber; i++) {
                String fullName = DataInitFactory.createFullName();
                String jobTitle = DataInitFactory.createJobTitle();
                String phone = DataInitFactory.createPnoneNumber();
                int salary = DataInitFactory.createSalary();

                directorController.addStaff(jobTitle, fullName, phone, salary);

            }
        });

        System.out.println("AddStaffTime - " + addStaffTime + " mills" + "\n");
    }

    private static void testRemoveStaffTime(DirectorController directorController) {

        // testing Staff Removing Time (in mills)

        TimeCounter selectTime2 = new TimeCounter();

        long removeStaffTime = selectTime2.count(() -> directorController.removeStaff("Ivan Ivanov"));

        System.out.println("RemoveStaffTime - " + removeStaffTime + " mills" + "\n");
    }

    private static void testFindStaffTime(DirectorController directorController) {

        // testing Staff Finding Time by name (in mills)

        TimeCounter selectTime3 = new TimeCounter();

        long findStaffByNameTime = selectTime3.count(() -> directorController.findStaffByName("Nikola Tesla"));

        System.out.println("findStaffByNameTime - " + findStaffByNameTime + " mills" + "\n");

        Employee find = directorController.findStaffByName("Nikola Tesla");
        System.out.println(find.toString() + "\n");

    }

    private static void testFilterStaffTime(DirectorController directorController) {

        // testing Staff Filtering Time by Job Title (in mills)

        TimeCounter selectTime4 = new TimeCounter();

        long filterStaffByPositionTime = selectTime4.count(new Action() {
            @Override
            public void run() {
                directorController.filterStaffByPosition("Manager");
            }
        });

        System.out.println("filterStaffByPositionTime - " + filterStaffByPositionTime + " mills" + "\n");

        List filt = directorController.filterStaffByPosition("Manager");
        System.out.println(filt.toString() + "\n");
        System.out.println();
    }

    private static void testShowStaffTime(DirectorController directorController) {

        // testing Showing Staff Info Time  (in mills)

        System.out.println("Showing staff " + staffNumber + " persons : ");
        TimeCounter selectTime5 = new TimeCounter();

        long showStaffInfoTime = selectTime5.count(new Action() {
            @Override
            public void run() {
                directorController.showStaffInfo();
            }
        });

        System.out.println("showStaffInfoTime - " + showStaffInfoTime + " mills" + "\n");
    }


}
