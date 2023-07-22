package com.communication;

import com.utility.Event;

import java.util.Optional;

import com.actions.IAction;
import com.utility.DelayExecution;

class Messenger extends DelayExecution<String>{
	private static Messenger instance;
	private String waitingFor;
	private Messenger() {}
	static Messenger getInstance() {
		if(instance==null)
			instance=new Messenger();
		return instance;
	}
	
	void Send(IAction action) throws InterruptedException {
		if(action.getShouldWait())
			SendAndWait(action);
		else
			System.out.println("start "+action.toString());
	}

	private String SendAndWait(IAction action) throws InterruptedException {
		waitingFor = action.toString();
		System.out.println("start "+waitingFor);
		return Wait();
	}

	@Override
	protected Optional<String> CheckFormat(String msg) {
		var match = "succeeded "+ waitingFor;
		if(msg.trim().startsWith(match))
			return Optional.of(match);
		match = "failed "+msg;
		if(msg.trim().startsWith(match))
			return Optional.of(match);
		match="error";
		if(msg.trim().startsWith(match))
				return Optional.of(match);
		return Optional.empty();
	}
	@Override
	protected Event<String> getEvent() {
		return Listener.get().listeners;
	}


}
