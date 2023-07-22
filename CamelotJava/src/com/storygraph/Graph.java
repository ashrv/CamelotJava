package com.storygraph;

import java.util.ArrayList;
import java.util.Optional;
import com.communication.InputManager;
import com.playerInput.IPlayerChoice;
import com.sequences.DisablePlayerOptionsSequence;
import com.sequences.EnablePlayerOptionsSequence;
import com.utility.DelayExecution;
import com.utility.Event;

public class Graph extends DelayExecution<IPlayerChoice>{
	private INode current;
	private ArrayList<IPlayerChoice> waitingFor;
	private ArrayList<IPlayerChoice> enabledChoices;
	public ActionMap map;
	public Graph(INode root, ActionMap map) throws InterruptedException {
		this.current=root;
		this.map=map;
		enabledChoices=new ArrayList<>();
		
		var sequence = map.get(root.getLabel());
		if(sequence!=null)
			sequence.execute();
	}
	
	public void Traverse() throws Exception {
		var possibleOptions = current.getOutgoingEdges();
		INode nextNode = null;
		
		if(possibleOptions.isPresent()) {
			var options = possibleOptions.get();
			DisablePreviousChoice(options);
			EnableNewChoices(options);
			waitingFor = options;
			var input = Wait();
			nextNode = current.getNextNode(Optional.of(input));
			
		}else {
			nextNode = current.getNextNode(null);
		}
		
		if(nextNode!=null) {
			current=nextNode;
			var label = current.getLabel();
			var sequence = map.get(label);
			if(sequence!=null)
				sequence.execute();
			else
				throw new Exception("map must contain the label "+label);
			Traverse();
		}
	}
	
	private void EnableNewChoices(ArrayList<IPlayerChoice> choices) throws InterruptedException {
		enabledChoices=choices;
		var enableSequence = new EnablePlayerOptionsSequence(enabledChoices);
		enableSequence.execute();
	}

	private void DisablePreviousChoice(ArrayList<IPlayerChoice> choices) throws InterruptedException {
		var disableSequence=new DisablePlayerOptionsSequence(enabledChoices);
		disableSequence.execute();
		
	}

	@Override
	protected Event<String> getEvent() {
		return InputManager.getInstance().subscribers;
	}

	@Override
	protected Optional<IPlayerChoice> CheckFormat(String msg) {
		return waitingFor.stream().filter(t->t.getInputMsg().equals(msg)).findFirst();
	}
	
}
