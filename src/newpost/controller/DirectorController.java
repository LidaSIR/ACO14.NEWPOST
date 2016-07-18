package newpost.controller;

import newpost.db.AppDataContainer;
import newpost.model.Employee;
import newpost.model.PostTicket;
import newpost.model.Report;
import newpost.model.Transaction;

import java.util.ArrayList;


public class DirectorController implements IEmployeeManagement,IReport{

    private AppDataContainer appDataContainer;

    public DirectorController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    public AppDataContainer getAppDataContainer() {
        return appDataContainer;
    }

    @Override
    public Employee addStaff(String jobTitle, String fullName, String phone, int salary) {
        Employee employee = new Employee(jobTitle,fullName,phone,salary);

        appDataContainer.getEmployees().add(employee);

        return employee;
    }

    @Override
    public Employee removeStaff(String fullName) {
        for(Employee employee:appDataContainer.getEmployees()){
           if(employee.getFullName().equals(fullName)) {
               appDataContainer.getEmployees().remove(employee);
           }
        }
        return null;
    }

    @Override
    public Employee findStaffByName(String fullName) {
        for(Employee employee: appDataContainer.getEmployees()) {
            if (employee.getFullName().equals(fullName)) {
                return employee;
            }
        }
        return null;
    }

   @Override     //in process

   public Employee[] filterStaffByPosition(String jobTitle) {
//        Object[] staff = appDataContainer.getEmployees().toArray();
//        for(int i = 0; i < staff.length; i++){
//            Employee iterStaff = (Employee)staff[i];
//            if(iterStaff.getJobTitle().equals(jobTitle)){
//                Employee[] staffByTitle = new Employee[staff.length];
//                staffByTitle[i] = iterStaff;
//                return staffByTitle[];
//            }
//          }
      return new Employee[0];
 }

    @Override // in process

    public ArrayList<Employee> showStaffInfo() {

        return null;
    }



    @Override
    public Report finaleReport(int income, Transaction[] transaction, Employee[] employees, PostTicket[] postTickets) {

        Report report = new Report(transaction,postTickets,employees, income);
        appDataContainer.getReports().add(report);

        return report;
    }
}
