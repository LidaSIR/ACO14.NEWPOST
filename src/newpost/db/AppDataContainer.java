package newpost.db;

import ua.artcode.week2.newpost.utils.MyDynamicArray;

/**
 * Created by Serhii Fursenko on 08.07.2016.
 * fyrsenko@gmail.com
 */
public class AppDataContainer {

    private MyDynamicArray clients;
    private MyDynamicArray tickets;
    private MyDynamicArray drivers;

    public AppDataContainer() {
        clients = new MyDynamicArray();
        tickets = new MyDynamicArray();
        drivers = new MyDynamicArray();
    }

    public MyDynamicArray getClients() {
        return clients;
    }

    public MyDynamicArray getTickets() {
        return tickets;
    }

    public MyDynamicArray getDrivers() {
        return drivers;
    }
}
