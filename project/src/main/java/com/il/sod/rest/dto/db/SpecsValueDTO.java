package com.il.sod.rest.dto.db;

public class SpecsValueDTO {
	
	private int idSpecsValues;
	private int idProductType;
	private int type;
	private String value;
	private int idSpecs;
	private double serviceIncrement; 
	
	public SpecsValueDTO() {
	}

	public int getIdSpecsValues() {
		return this.idSpecsValues;
	}

	public void setIdSpecsValues(int idSpecsValues) {
		this.idSpecsValues = idSpecsValues;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getIdProductType() {
		return idProductType;
	}

	public void setIdProductType(int idProductType) {
		this.idProductType = idProductType;
	}

	public double getServiceIncrement() {
		return serviceIncrement;
	}

	public void setServiceIncrement(double serviceIncrement) {
		this.serviceIncrement = serviceIncrement;
	}

	public int getIdSpecs() {
		return idSpecs;
	}

	public void setIdSpecs(int idSpecs) {
		this.idSpecs = idSpecs;
	}

}