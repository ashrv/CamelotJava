package com.playerInput;

import com.entities.Character;
import com.entities.IEntity;

public class PositionChoice implements IPlayerChoice{

	private String position;
	private Character character;
	private Condition condition;
	
	public enum Condition{arrived, exited}
	public PositionChoice(Character character, IEntity entity, Condition condition) {
		this(character, entity.getName(), condition);
	}
	
	public PositionChoice(Character character, String position, Condition condition) {
		this.character=character;
		this.position=position;
		this.condition=condition;
	}
	
	@Override
	public String getInputMsg() {
		return String.format("input $ %s position %s", condition.toString(), character.getName(), position);
	}

}
