//package entities.paymentSystem;
//
//import entities.BaseEntity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "billing_details")
//@Inheritance(strategy = InheritanceType.JOINED)
//public class BillingDetail extends BaseEntity {
//
//    private String number;
//    private User owner;
//
//    public BillingDetail() {
//    }
//
//    @Column(name = "number")
//    public String getNumber() {
//        return number;
//    }
//
//    public void setNumber(String number) {
//        this.number = number;
//    }
//
//    @ManyToOne()
//    @JoinColumn(name = "owner_id", referencedColumnName = "id")
//    public User getOwner() {
//        return owner;
//    }
//
//    public void setOwner(User owner) {
//        this.owner = owner;
//    }
//}
