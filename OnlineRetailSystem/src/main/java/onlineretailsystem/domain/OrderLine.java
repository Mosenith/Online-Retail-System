package onlineretailsystem.domain;

import jakarta.persistence.*;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Long id;
    private Integer qualtity;
    private Double discount;
    @ManyToOne
    private Item item;

    public OrderLine() {

    }

    public OrderLine(Integer qualtity, Double discount) {
        this.qualtity = qualtity;
        this.discount = discount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getQualtity() {
        return qualtity;
    }

    public void setQualtity(Integer quatity) {
        this.qualtity = quatity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void clone(OrderLine other) {
        this.qualtity = other.qualtity;
        this.discount = other.discount;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", quatity=" + qualtity +
                ", discount=" + discount +
                '}';
    }
}
