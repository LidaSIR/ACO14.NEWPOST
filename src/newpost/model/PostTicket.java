package newpost.model;

public class PostTicket {

    private static int nextId = 0;

    private final String id;

    private Client client;
    private Product[] products;

    private Address from;
    private Address to;

    private MyDate creationDate;
    private MyDate estimationArrivalDate;
    private MyDate actualArrivedDate;

    private TicketStatus status;

    public PostTicket(String id, Client client, Product[] products, Address from, Address to,
                      MyDate creationDate, MyDate estimationArrivalDate) {
        this.id = id;
        this.client = client;
        this.products = products;
        this.from = from;
        this.to = to;
        this.creationDate = creationDate;
        this.estimationArrivalDate = estimationArrivalDate;
    }

    public PostTicket(Client client, Product[] products, Address from, Address to, MyDate creationDate, MyDate estimationArrivalDate) {

        this.id = Integer.toString(nextId++);
        this.client = client;
        this.products = products;
        this.from = from;
        this.to = to;
        this.creationDate = creationDate;
        this.estimationArrivalDate = estimationArrivalDate;
        this.status = TicketStatus.NEW;
    }

    public String getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Address getFrom() {
        return from;
    }

    public void setFrom(Address from) {
        this.from = from;
    }

    public Address getTo() {
        return to;
    }

    public void setTo(Address to) {
        this.to = to;
    }

    public MyDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(MyDate creationDate) {
        this.creationDate = creationDate;
    }

    public MyDate getEstimationArrivalDate() {
        return estimationArrivalDate;
    }

    public void setEstimationArrivalDate(MyDate estimationArrivalDate) {
        this.estimationArrivalDate = estimationArrivalDate;
    }

    public MyDate getActualArrivedDate() {
        return actualArrivedDate;
    }

    public void setActualArrivedDate(MyDate actualArrivedDate) {
        this.actualArrivedDate = actualArrivedDate;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public String asString(){
        return "client's name: " + client.getPassport().getFullname() + "\n" + "passport: " + client.getPassport().getNumber() +
                "\n" +"client's phone" + client.getPhone()  + "\n" + "product: "  +  products[0].getName();
    }

}
