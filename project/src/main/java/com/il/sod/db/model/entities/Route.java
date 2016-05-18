package com.il.sod.db.model.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the Routes database table.
 * 
 */
@Entity
@Table(name="Routes")
@NamedQuery(name="Route.findAll", query="SELECT r FROM Route r")
public class Route implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idRoutes;

	private int category;

	private String description;

	private String name;

	//bi-directional many-to-one association to Stop
	@OneToMany(mappedBy="route")
	private Set<Stop> stops;

	public Route() {
	}

	public int getIdRoutes() {
		return this.idRoutes;
	}

	public void setIdRoutes(int idRoutes) {
		this.idRoutes = idRoutes;
	}

	public int getCategory() {
		return this.category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Stop> getStops() {
		return this.stops;
	}

	public void setStops(Set<Stop> stops) {
		this.stops = stops;
	}

	public Stop addStop(Stop stop) {
		getStops().add(stop);
		stop.setRoute(this);

		return stop;
	}

	public Stop removeStop(Stop stop) {
		getStops().remove(stop);
		stop.setRoute(null);

		return stop;
	}

	@Override
	public Integer getId() {
		return this.idRoutes;
	}

	@Override
	public Route setId(Integer id) {
		this.idRoutes = id;
		return this;
	}

}