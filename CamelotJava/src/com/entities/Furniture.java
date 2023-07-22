package com.entities;

public class Furniture implements IEntity{

	private String name;
	
	Furniture(String name){
		this.name=name;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
