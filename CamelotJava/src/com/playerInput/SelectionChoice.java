package com.playerInput;

public class SelectionChoice implements IPlayerChoice{

	private String key;
	public SelectionChoice(String key) {
		this.key=key;
	}
	@Override
	public String getInputMsg() {
		return String.format("input Selected %s", key);
	}

}
