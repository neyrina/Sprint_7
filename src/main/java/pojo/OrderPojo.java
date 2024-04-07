package pojo;

public class OrderPojo {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private Number rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;


    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }


    public void setLastName(String lastName) {

        this.lastName = lastName;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setRentTime(Number rentTime) {
        this.rentTime = rentTime;
    }


    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }


    public void setColor(String[] color) {
        this.color = color;
    }
}
