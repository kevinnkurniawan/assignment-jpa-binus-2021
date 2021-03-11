package com.blibli.training.springframework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tommy.setiawan on 3/9/2021.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cart")
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "addToCartTimestamp")
  private Date addToCartTimestamp;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_fk", referencedColumnName = "id")
  @JsonBackReference
  private User user;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<CartItem> cartItems = new ArrayList<>();

}
