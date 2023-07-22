package com.storygraph;

import java.util.Hashtable;

import com.communication.ActionSequence;

public class ActionMap {
	private Hashtable<String, ActionSequence> map;
	
	public ActionMap() {
		map= new Hashtable<>();
	}
	
	public boolean add(String label, ActionSequence sequence) {
		if(map.containsKey(label))
			return false;
		map.put(label, sequence);
		return true;
	}
	
	public ActionSequence get(String label) {
		return map.get(label);
	}
	
}
