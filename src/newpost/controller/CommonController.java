package newpost.controller;

import newpost.controller.interfaces.IClientController;
import newpost.controller.interfaces.IManagerController;
import newpost.db.AppDataContainer;
import newpost.exceptions.ValidationException;
import newpost.model.common.Address;
import newpost.model.common.MyDate;
import newpost.model.common.Product;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Lida on 24.07.2016.
 */
abstract class CommonController implements IClientController, IManagerController {

    public static final int DAYS_IN_ROAD = 2;

    protected AppDataContainer appDataContainer;
    public CommonController (AppDataContainer appDataContainer){
        this.appDataContainer = appDataContainer;
    }

    @Override
    public PostTicket makeOrder(Client client, Address sendToAddress, Product product) throws ValidationException {
        Address addressFrom = DataInitFactory.createAddress();
        Calendar calendar = GregorianCalendar.getInstance();
        MyDate currentTime = new MyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        MyDate estimationArrivalDate = currentTime;
        estimationArrivalDate.setDay(currentTime.getDay()+ DAYS_IN_ROAD);

        Product sendProduct = new Product(product.getName(), product.getSize(), product.getPrice(), client);
        Product[] sendProductArr = {sendProduct};

        PostTicket postTicket = new PostTicket(client, sendProductArr, addressFrom, sendToAddress,
                currentTime, estimationArrivalDate);

        appDataContainer.getTickets().add(postTicket);


        return null;
    }


    @Override
    public PostTicket showTicketById(String ticketId) throws ValidationException {
        return null;
    }
}
