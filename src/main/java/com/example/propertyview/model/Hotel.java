package com.example.propertyview.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String brand;

    @Embedded
    private Address address;

    @Embedded
    private Contacts contacts;

    @Embedded
    private ArrivalTime arrivalTime;

    @ElementCollection
    private List<String> amenities = new ArrayList<>();

    public List<String> getAmenities() {
    return amenities;
    }

    public void setAmenities(List<String> amenities) {
    this.amenities = amenities;
    }

    public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getName() { return name; }
public void setName(String name) { this.name = name; }

public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }

public String getBrand() { return brand; }
public void setBrand(String brand) { this.brand = brand; }

public Address getAddress() { return address; }
public void setAddress(Address address) { this.address = address; }

public Contacts getContacts() { return contacts; }
public void setContacts(Contacts contacts) { this.contacts = contacts; }

public ArrivalTime getArrivalTime() { return arrivalTime; }
public void setArrivalTime(ArrivalTime arrivalTime) { this.arrivalTime = arrivalTime; }
}