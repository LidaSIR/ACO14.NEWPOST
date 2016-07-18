package newpost.controller;

import newpost.model.*;

/**
 * Created by Lida on 18.07.2016.
 */
public interface IReport {
    Report finaleReport (int income, Transaction [] transaction, Employee []employees, PostTicket []postTickets);

}
