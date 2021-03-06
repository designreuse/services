package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the PromotionTypeDTO database table.
 * 
 */
@Entity
@NamedQuery(name="PromotionType.findAll", query="SELECT p FROM PromotionType p")
public class PromotionType extends SoftDeleteEntity implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPromotionType;

	private String description;

	private String name;

	//bi-directional many-to-one association to PromotionDTO
	@OneToMany(mappedBy="promotionType", fetch = FetchType.EAGER)
	private Set<Promotion> promotions;

	public PromotionType() {
	}

	public int getIdPromotionType() {
		return this.idPromotionType;
	}

	public void setIdPromotionType(int idPromotionType) {
		this.idPromotionType = idPromotionType;
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

	public Set<Promotion> getPromotions() {
		return this.promotions;
	}

	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}

	public Promotion addPromotion(Promotion promotion) {
		getPromotions().add(promotion);
		promotion.setPromotionType(this);

		return promotion;
	}

	public Promotion removePromotion(Promotion promotion) {
		getPromotions().remove(promotion);
		promotion.setPromotionType(null);

		return promotion;
	}

	@Override
	public Integer getId() {
		return this.idPromotionType;
	}

	@Override
	public PromotionType setId(Integer id) {
		this.idPromotionType = id;
		return this;
	}
}