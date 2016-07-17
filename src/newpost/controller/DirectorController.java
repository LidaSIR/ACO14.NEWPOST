package newpost.controller;

import newpost.db.AppDataContainer;
import newpost.model.Employee;


public class DirectorController implements IDirectorController, IEmployeeManagement{

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

    @Override

    public Employee[] filterStaffByPosition(String jobTitle) {
//        Object[] staff = appDataContainer.getEmployees().toArray();
//        for(int i = 0; i < staff.length; i++){
//            Employee iterStaff = (Employee)staff[i];
//            if(iterStaff.getJobTitle().equals(jobTitle)){
//                Employee[] staffByTitle = new Employee[staff.length];
//                staffByTitle[i] = iterStaff;
//                return staffByTitle[];
//            }
        //  }
         return new Employee[0];
    }

    @Override

    public Employee[] showStaffInfo() {
        Object[] staff = appDataContainer.getEmployees().toArray();
        for(int i = 0; i < staff.length; i++){
            Employee iterStaff = (Employee)staff[i];
            System.out.println(iterStaff);
        }

        return new Employee[0];
    }

    public void setAppDataContainer(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;

    }
}
