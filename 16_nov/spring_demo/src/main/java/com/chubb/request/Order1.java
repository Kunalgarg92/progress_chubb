package com.chubb.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Set;

import com.chubb.request.Address;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity // should be treated as data readable 
public class Order1 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@NotBlank
	private String item;
	@Min(value =1)
	private float price;
	private int quantity;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Address> addresses;
	
	  @ManyToMany(cascade = {CascadeType.MERGE})
	    @JoinTable(
	        name = "order_shared_address",
	        joinColumns = @JoinColumn(name = "order_id"),
	        inverseJoinColumns = @JoinColumn(name = "address_id")
	    )
	    private Set<Address> sharedAddresses;

//	private Address address;
//	public int getQuantity() {
//		return quantity;
//	}
//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
