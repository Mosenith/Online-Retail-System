package onlineretailsystem.domain;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;

@Data
@Entity
@SequenceGenerator(name = "individual_item_seq", sequenceName = "individual_item_seq", allocationSize = 1)
public class IndividualItem extends Item {
  
//    @Lob 
//    protected byte[] image;
    

//
//    @OneToMany(mappedBy = "item")
//    protected List<Review> reviews;
}