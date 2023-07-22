package com.storygraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Optional;

import com.playerInput.IPlayerChoice;

public class Node implements INode{
	private Hashtable<IPlayerChoice, INode> children;
	private String label;
	public Node(String label) {
		this.label=label;
		children=new Hashtable<>();
	}
	
	public void addChild(IPlayerChoice edge, INode child) {
		children.put(edge, child);
	}

	@Override
	public
	 INode getNextNode(Optional<IPlayerChoice> edge) {
		if(edge.isPresent())
			return children.get(edge.get());
		return null;
	}
	
	@Override
	public
	 Optional<ArrayList<IPlayerChoice>> getOutgoingEdges() {
		return Optional.ofNullable(Collections.list(children.keys()));
	}

	@Override
	public
	 String getLabel() {
		return label;
	}
	
}
