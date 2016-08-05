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
public class TestDirectorController {

    public static void main(String[] args) {
        int stuffNumber = 50;
        System.out.println("Stuff = " + stuffNumber + " Persons");
        AppDataContainer appDataContainer = new AppDataContainer();

      /*  List<Employee> employees = appDataContainer.getEmployees();
        for (int i = 0; i < 10; i++) {
            String fullName = DataInitFactory.createFullName();
            String jobTitle = DataInitFactory.createJobTitle();
            String phone = DataInitFactory.createPnoneNumber();
            int salary = DataInitFactory.createSalary();

            Employee e = new Employee(jobTitle, fullName, phone, salary);
            employees.add(e);
        }*/

        DirectorController directorController = new DirectorController(appDataContainer);


        // testing Staff Adding Time (in mills)

        TimeCounter selectTime = new TimeCounter();

        long addStaffTime = selectTime.count(new Action() {
            @Override
            public void run() {
                /*AppDataContainer appDataContainer = new AppDataContainer();

                List<Employee> employees = appDataContainer.getEmployees();*/
                for (int i = 0; i < stuffNumber; i++) {
                    String fullName = DataInitFactory.createFullName();
                    String jobTitle = DataInitFactory.createJobTitle();
                    String phone = DataInitFactory.createPnoneNumber();
                    int salary = DataInitFactory.createSalary();

                    directorController.addStaff(jobTitle, fullName, phone, salary);

                }
            }
        });

        System.out.println("AddStaffTime - " + addStaffTime + " mills" + "\n");

        // testing Staff Removing Time (in mills)


        TimeCounter selectTime2 = new TimeCounter();

        long removeStaffTime = selectTime2.count(new Action() {
            @Override
            public void run() {
                directorController.removeStaff("Ivan Ivanov");
            }
        });

        System.out.println("RemoveStaffTime - " + removeStaffTime + " mills" + "\n");


        // testing Staff Finding Time by name (in mills)

        TimeCounter selectTime3 = new TimeCounter();

        long findStaffByNameTime = selectTime.count(new Action() {
            @Override
            public void run() {
                directorController.findStaffByName("Nikola Tesla");
            }
        });

        System.out.println("findStaffByNameTime - " + findStaffByNameTime + " mills" + "\n");

        Employee find = directorController.findStaffByName("Nikola Tesla");
        System.out.println(find.toString() + "\n");



        // testing Staff Filtering Time by Job Title (in mills)

        TimeCounter selectTime4 = new TimeCounter();

        long filterStaffByPositionTime = selectTime.count(new Action() {
            @Override
            public void run() {
                directorController.filterStaffByPosition("Manager");
            }
        });

        System.out.println("filterStaffByPositionTime - " + filterStaffByPositionTime + " mills" + "\n");

        List filt = directorController.filterStaffByPosition("Manager");
        System.out.println(filt.toString() + "\n");
        System.out.println();


        // testing Showing Staff Info Time  (in mills)

        System.out.println("Showing staff " + stuffNumber  + " persons : ");
        TimeCounter selectTime5 = new TimeCounter();

        long showStaffInfoTime = selectTime.count(new Action() {
            @Override
            public void run() {
                directorController.showStaffInfo();
            }
        });

        System.out.println("showStaffInfoTime - " + showStaffInfoTime + " mills" + "\n");







        testAddStaff(directorController);

    }

    private static void testAddStaff(DirectorController directorController) {

        System.out.println("AddStaff test:");
        String fullName = DataInitFactory.createFullName();
        String jobTitle = DataInitFactory.createJobTitle();
        String phone = DataInitFactory.createPnoneNumber();
        int salary = DataInitFactory.createSalary();

        Employee employee = directorController.addStaff(jobTitle, fullName, phone, salary);

        if (employee != null) {
            System.out.println("test passed " + "\n" + employee.toString() + "\n");
        } else System.out.println("failed");

    }


}
