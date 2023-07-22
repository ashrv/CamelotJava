package com.communication;

import java.util.ArrayList;

import com.actions.IAction;

public class ActionSequence {
	private ArrayList<IAction> actions;
	public ActionSequence() {
		actions=new ArrayList<>();
	}
	
	public void add(IAction action) {
		actions.add(action);
	}
	
	public void execute() throws InterruptedException {
		for(var action:actions) {
			Messenger.getInstance().Send(action);
		}
	}
	
	public ActionSequence combineWith(ActionSequence other) {
		for(var action:other.actions) {
			add(action);
		}
		return this;
	}
	
}
