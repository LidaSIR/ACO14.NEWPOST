package newpost.model;

/**
 * Created by home on 08.07.2016.
 */
public class PostOffice {

    private String id;
    private Address address;

    public PostOffice(String id, Address address) {
        this.id = id;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
