package onlineretailsystem.domain;


import jakarta.persistence.*;

import javax.xml.crypto.Data;


@Entity
public class Review {

    @Id
    @GeneratedValue
    private long id;


    @ManyToOne
    private Item item;


    private String title;

    private String description;

    private int numberOfStars;

    //private Data date;

    @ManyToOne(fetch=FetchType.EAGER, optional=true, cascade=CascadeType.ALL)
    @JoinColumn(name="customerId")
    private Customer customer;

    public Review(){

    }

    public Review(long id, String title, String description, int numberOfStars, Customer customer){
        this.id = id;
        this.title = title;
        this.description = description;
        this.numberOfStars = numberOfStars;
        this.customer= customer;
      //  this.date = date;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

  //  public Data getDate() {
  //      return date;
  //  }

  //  public void setDate(Data date) {
  //      this.date = date;
  //  }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title=" + title +
                ", description=" + description +
                ", number Of Stars=" + numberOfStars +

                '}';
    }

    public void parse(Review updatedReview) {
    }
}
