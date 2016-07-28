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
        AppDataContainer appDataContainer = new AppDataContainer();

        List<Employee> employees = appDataContainer.getEmployees();
        for (int i = 0; i < 100000; i++) {
            String fullName = DataInitFactory.createFullName();
            String jobTitle = DataInitFactory.createJobTitle();
            String phone = DataInitFactory.createPnoneNumber();
            int salary = DataInitFactory.createSalary();

            Employee e = new Employee(jobTitle, fullName, phone, salary);
            employees.add(e);
        }

        DirectorController directorController = new DirectorController(appDataContainer);

        // testing Staff Adding Time (in mills)

        TimeCounter selectTime = new TimeCounter();

        long addStaffTime = selectTime.count(new Action() {
            @Override
            public void run() {
                AppDataContainer appDataContainer = new AppDataContainer();

                List<Employee> employees = appDataContainer.getEmployees();
                for (int i = 0; i < 1000; i++) {
                    String fullName = DataInitFactory.createFullName();
                    String jobTitle = DataInitFactory.createJobTitle();
                    String phone = DataInitFactory.createPnoneNumber();
                    int salary = DataInitFactory.createSalary();

                    directorController.addStaff(jobTitle,fullName,phone,salary);

                    /*Employee e = new Employee(jobTitle, fullName, phone, salary);
                    employees.add(e);*/
                }
            }
        });

        System.out.println("AddStaffTime - " + addStaffTime);

        // testing Staff Removing Time (in mills)


        TimeCounter selectTime2 = new TimeCounter();

        long removeStaffTime = selectTime2.count(new Action() {
            @Override
            public void run() {
              /*  AppDataContainer appDataContainer = new AppDataContainer();

                List<Employee> employees = appDataContainer.getEmployees();
                for (int i = 0; i < 1000; i++) {
                    String fullName = DataInitFactory.createFullName();
                    String jobTitle = DataInitFactory.createJobTitle();
                    String phone = DataInitFactory.createPnoneNumber();
                    int salary = DataInitFactory.createSalary();

                    Employee e = new Employee(jobTitle, fullName, phone, salary);
                    employees.add(e);

                }*/

                employees.remove("Nikola Tesla");
            }
        });

        System.out.println("RemoveStaffTime - " + removeStaffTime);


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
