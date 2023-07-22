package com.sequences;

import com.actions.*;
import com.entities.Character;
import com.communication.ActionSequence;

public class CharacterCreation extends ActionSequence{
	public CharacterCreation(Character character) {
		add(new Create<Character>(character));
		add(new SetClothing(character));
	}
}
