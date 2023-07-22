package com.entities;

import com.entities.Things.ThingNames;

public class Character implements IEntity, IThing<com.entities.Character.BodyTypes>{
	
	private String name;
	
	private BodyTypes bodytype;
	public enum BodyTypes{ A, B, C, D, E, F, G, H}
	
	private Clothing clothing;
	public enum Clothing{ Bandit, Beggar, LightArmour, HeavyArmour, Merchant, Noble, Peasant, Priest}
	public Clothing getClothing() {
		return clothing;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public Character(ThingNames name) {
		this(name, BodyTypes.A);
	}
	
	public Character(ThingNames name, BodyTypes bodytype) {
		this(name, bodytype, Clothing.Peasant);
	}
	
	public Character(ThingNames name, BodyTypes bodytype, Clothing clothing) {
		this.name=name.toString();
		this.bodytype=bodytype;
		this.clothing=clothing;
		
		Things.add(ThingNames.Tom, this);
	}

	@Override
	public BodyTypes getTemplate() {
		return bodytype;
	}

}
