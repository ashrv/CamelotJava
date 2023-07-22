package com.storygraph;

import java.util.ArrayList;
import java.util.Optional;

import com.playerInput.IPlayerChoice;

public interface INode {
	 String getLabel();
	 INode getNextNode(Optional<IPlayerChoice> edge);
	 Optional<ArrayList<IPlayerChoice>> getOutgoingEdges();
}
