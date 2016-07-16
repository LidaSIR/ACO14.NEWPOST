package newpost.filter;

import newpost.db.AppDataContainer;
import newpost.model.Address;
import newpost.model.Client;
import newpost.model.PostTicket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhii Fursenko on 16.07.2016.
 * fyrsenko@gmail.com
 */
public abstract class Finder {

    public List<PostTicket> findByPrice(AppDataContainer appDataContainer, int price) {
        List<PostTicket> list = new ArrayList<>();
        for(PostTicket postTicket: appDataContainer.getTickets()) {
            if(postTicket.getPrice()==price) {
                list.add(postTicket);
            }
        }
        return  list;
    }

    public List<PostTicket> findByAddress(AppDataContainer appDataContainer, Address address) {
        List<PostTicket> list = new ArrayList<>();
        for(PostTicket postTicket: appDataContainer.getTickets()) {
            if(postTicket.getFrom().getCity().equals(address.getCity()) &&
                    postTicket.getFrom().getStreet().equals(address.getStreet()) &&
                    postTicket.getFrom().getHouseNum().equals(address.getHouseNum())) {
                list.add(postTicket);
            }
        }
        return  list;
    }

    public List<PostTicket> findByCity(AppDataContainer appDataContainer, String city) {
        List<PostTicket> list = new ArrayList<>();
        for(PostTicket postTicket: appDataContainer.getTickets()) {
            if(postTicket.getFrom().getCity().equals(city)) {
                list.add(postTicket);
            }
        }
        return  list;
    }

    public List<PostTicket> findByOwnerName(AppDataContainer appDataContainer, String name) {
        List<PostTicket> list = new ArrayList<>();
        for(PostTicket postTicket: appDataContainer.getTickets()) {
            if(postTicket.getClient().getPassport().getFullname().equals(name)) {
                list.add(postTicket);
            }
        }
        return  list;
    }

    public PostTicket findById(AppDataContainer appDataContainer, String id) {
        for(PostTicket postTicket: appDataContainer.getTickets()) {
            if(postTicket.getId().equals(id)) {
                return postTicket;
            }
        }
        return null;
    }


}
