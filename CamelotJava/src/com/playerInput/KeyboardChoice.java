package com.playerInput;

public class KeyboardChoice implements IPlayerChoice{

	private Keys key;
	public enum Keys{Pause, Inventory, Interact}
	public KeyboardChoice(Keys key) {
		this.key=key;
	}
	@Override
	public String getInputMsg() {
		return String.format("input Key %s", key.toString());
	}

}
