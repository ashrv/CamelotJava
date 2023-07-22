package com.entities;

import com.entities.Things.ThingNames;

public class Item implements IEntity, IThing<com.entities.Item.Items> {
	private String name;
	private Items template;
	public enum Items{
		Apple,
		Bag,
		BlueBook,
		BlueCloth,
		BlueKey,
		BluePotion,
		Bottle,
		Bread,
		ChickenLeg,
		Coin,
		Compass,
		Cup,
		EvilBook,
		GoldCup,
		GreenBook,
		GreenKey,
		GreenPotion,
		Hammer,
		Helmet,
		InkandQuill,
		JewelKey,
		LitTorch,
		Lock,
		MagnifyingGlass,
		OpenScroll,
		PurpleBook,
		PurpleCloth,
		PurpleKey,
		PurplePotion,
		Rags,
		RedBook,
		RedCloth,
		RedKey,
		RedPotion,
		Scroll,
		Skull,
		SpellBook,
		Sword,
		Torch
	}
	@Override
	public String getName() {
		return name;
	}
	
	public Item(ThingNames name, Items template) {
		this.name=name.toString();
		this.template=template;
		Things.add(name, this);
	}

	@Override
	public Items getTemplate() {
		return template;
	}

}
