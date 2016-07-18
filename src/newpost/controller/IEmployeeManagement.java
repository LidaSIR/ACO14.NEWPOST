package newpost.controller;

import newpost.model.Employee;

import java.util.ArrayList;

/**
 * Created by Lida on 17.07.2016.
 */
// add, remove, find, filter, show info
public interface IEmployeeManagement {
     Employee addStaff(String jobTitle, String fullName, String phone, int salary);
     Employee removeStaff(String fullName);
     Employee findStaffByName(String fullName);
     Employee[] filterStaffByPosition(String jobTitle);
     ArrayList<Employee> showStaffInfo();
}
