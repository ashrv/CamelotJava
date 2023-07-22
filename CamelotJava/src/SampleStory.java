import com.actions.*;
import com.communication.ActionSequence;
import com.storygraph.ActionMap;
import com.storygraph.Graph;
import com.entities.Character;
import com.entities.Item.Items;
import com.entities.*;
import com.entities.Place.Places;
import com.entities.Things.ThingNames;
import com.playerInput.*;
import com.playerInput.ActionChoice.Icons;
import com.sequences.CharacterCreation;
import com.storygraph.*;

public class SampleStory {
	private Item sword;
	private Character tom;
	private Place home;
	
	private enum ActionNames{
		Take, Exit
	}
	
	private enum NodeLabels{
		Start, Init, TakeSword, ExitHome
	}
	private Graph graph;
	public SampleStory() throws Exception {
		getThings();
		var map = getMap();
		graph = GenerateGraph(map);
		graph.Traverse();
	}
	
	private void getThings() {
		 tom=new Character(ThingNames.Tom);
		 home = new Place(ThingNames.Home, Places.Cottage);
		 sword = new Item(ThingNames.Sword, Items.Sword);
	}
	
	private ActionMap getMap() {
		var map=new ActionMap();
		map.add(NodeLabels.Init.toString(), getInitSequence());
		map.add(NodeLabels.Start.toString(), getStartSequence());
		map.add(NodeLabels.ExitHome.toString(), getExitHomeSequence());
		map.add(NodeLabels.TakeSword.toString(), getTakeSwordSequence());
		return map;
	}

	private Graph GenerateGraph(ActionMap map) throws Exception {
		
		var takeSwordNode = new Node(NodeLabels.TakeSword.toString());
		var exitHomeNode = new Node(NodeLabels.ExitHome.toString());
		
		var startNode = new Node(NodeLabels.Start.toString());
		startNode.addChild(
			new ActionChoice(
				ActionNames.Take.toString(), 
				sword, 
				Icons.sword, 
				"Take the sword", 
				true)
			, takeSwordNode);
		
		startNode.addChild(
			new ActionChoice(
					ActionNames.Exit.toString(),
					home.getFurniture("Door"),
					Icons.door,
					"Leave house",
					true),
			exitHomeNode);
		
		
		var root = new Node(NodeLabels.Init.toString());
		root.addChild(new SelectionChoice("Start"), startNode);
		
		return new Graph(root, map);
	}
	
	private ActionSequence getInitSequence() {
		var sequence = new ActionSequence();
		sequence.combineWith(new CharacterCreation(tom));
		sequence.add(new Create<Place>(home));
		sequence.add(new Position(tom, home));
		sequence.add(new Create<Item>(sword));
		sequence.add(new Position(sword, home, "Shelf"));
		sequence.add(new SetCameraFocus(tom));
		sequence.add(new ShowMenu(true));
		return sequence;
	}
	
	private ActionSequence getStartSequence() {
		var sequence=new ActionSequence();
		sequence.add(new ShowMenu(false));
		sequence.add(new EnableInput(true));
		return sequence;
	}
	
	private ActionSequence getTakeSwordSequence() {
		var sequence = new ActionSequence();
		sequence.add(new Take(tom, sword));
		return sequence;
	}

	private ActionSequence getExitHomeSequence() {
		var sequence = new ActionSequence();
		sequence.add(new Exit(tom, home.getFurniture("Door"), true));
		return sequence;
	}
}
