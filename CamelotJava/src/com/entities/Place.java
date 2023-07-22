package com.entities;

import com.entities.Things.ThingNames;

public class Place implements IThing<com.entities.Place.Places>{
	
	private String name;
	public String getName() {
		return name;
	}
	
	private Places template;
	public enum Places{
		AlchemyShop,Blacksmith,Bridge,Camp,CastleBedroom,CastleCrossroads,City,Cottage,Courtyard,DiningRoom,Dungeon,Farm,ForestPath,GreatHall,Hallway,Library,Port,Ruins,SpookyPath,Storage,Tavern
	}
	
	public Places getTemplate() {
		return template;
	}
	
	public Place(ThingNames name, Places template) {
		this.name=name.toString();
		this.template=template;
		
		Things.add(name, this);
	}
	
	public Furniture getFurniture(String furnitureName) {
		return new Furniture(getName()+"."+furnitureName);
	}
}
