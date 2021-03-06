package com.il.sod.db.model.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the Address database table.
 *
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAddress;

	private String address;

	private String address2;

	private String city;

	private String country;

	private String state;
	
	private String zipcode;
	
	private boolean prefered;
	
	private boolean factura;
	
	private BigDecimal lat;
	
	private BigDecimal lng;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="idClient")
	@JsonBackReference
	private Client client;
	

	public Address() {
	}

	public int getIdAddress() {
		return this.idAddress;
	}

	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	@Override
	public Integer getId() {
		return this.idAddress;
	}

	@Override
	public Address setId(Integer id) {
		this.idAddress = id;
		return this;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	

	public boolean isPrefered() {
		return prefered;
	}
	public void setPrefered(boolean prefered) {
		this.prefered = prefered;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public boolean isFactura() {
		return factura;
	}

	public void setFactura(boolean factura) {
		this.factura = factura;
	}

	@Override
	public String toString() {
		return "Address{" +
				"idAddress=" + idAddress +
				", address='" + address + '\'' +
				", address2='" + address2 + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", state='" + state + '\'' +
				", zipcode='" + zipcode + '\'' +
				", prefered=" + prefered +
				", factura=" + factura +
				", lat=" + lat +
				", lng=" + lng +
				'}';
	}
}
