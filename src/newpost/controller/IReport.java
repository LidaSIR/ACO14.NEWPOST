package newpost.controller;

import newpost.model.money.Report;
import newpost.model.money.Transaction;
import newpost.model.office.Employee;
import newpost.model.office.PostTicket;

/**
 * Created by Lida on 18.07.2016.
 */
public interface IReport {
    Report finaleReport (int income, Transaction[] transaction, Employee[]employees, PostTicket[]postTickets);

}
