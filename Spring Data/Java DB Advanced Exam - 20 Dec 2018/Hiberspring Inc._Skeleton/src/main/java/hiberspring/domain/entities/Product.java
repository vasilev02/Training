package hiberspring.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    private String name;
    private int clients;
    private Branch branch;

    public Product() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "clients")
    public int getClients() {
        return clients;
    }

    public void setClients(int clients) {
        this.clients = clients;
    }

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
