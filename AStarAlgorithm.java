import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;


public class AStarAlgorithm {
	public static void main(String args[]){

		//Creating start node
		Node start = new Node();
		ArrayList<Integer> startState = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		for(int i=0;i<=x;i++) {
			 startState.add(sc.nextInt());
			}
		sc.close(); 
		start.state = startState;
		start.parent = null;
		start.move = null;
		start.priority = 0;
		start.distance = -1;
			
		//Creating goal node
		Node goal = new Node();
		ArrayList<Integer> goalState = new ArrayList<>();
		for(int j= 1 ; j <= x;j++) {
			goalState.add(j);
		}goalState.add(0);
		goal.state = goalState;
		goal.parent = null;
		goal.distance = -1;
		goal.move = null;
		
		
		//Creating states, goal etc.
		Node[] states = new Node[4];
		Node goalNodeFound = new Node();
		goalNodeFound = null;
		Node current = new Node();
		int count = 0;
		
		//Creating Priority Queue of Initial Capacity 100elements,
		// ordering them by Node.priority
		Comparator<Node> comparator = new NodeCompare();
		PriorityQueue<Node> pQ = new PriorityQueue<Node>(100,comparator);
		pQ.add(start);
				
		LinkedList<ArrayList<Integer>> visited = new LinkedList<ArrayList<Integer>>();
		visited.add(start.state);
		
		while(!pQ.isEmpty()){
			count++;
			current = pQ.remove();
			states = findStates(current);
			
			for(int i = 0; i<=3; i++){
				if(states[i] != null){
						if (states[i].state.equals(goal.state)){
							goalNodeFound = states[i];
							break;
						}
						else{
							if(!visited.contains(states[i].state)){
							states[i].distance = current.distance + 1;	
							visited.add(states[i].state);
							states[i].priority = costFunction(states[i],goal);
							pQ.add(states[i]);
							}
						}
				} 
			}
			if(goalNodeFound != null)
				break;	
		}
		
		// ----- Found Solution:
		System.out.println(count);
		
		Stack<String> stack = new Stack<String>();
		while (goalNodeFound.parent != null){
			if(goalNodeFound.move != null){
				stack.push(goalNodeFound.move);
			}
			goalNodeFound = goalNodeFound.parent;
		}
		
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
		
		
	}
	

	
	private static int costFunction(Node node, Node goal) {
		
		int priority;
		int count = 0;
		
		//Heuristic Function Calculation
		for(int i=0; i<9; i++){
			if(node.state.get(i) != goal.state.get(i)){
				count++;
			}
		}
		priority = node.distance + count; 
		return priority;
	}


	private static Node[] findStates(Node state) {
		
		Node state1,state2,state3,state4;
		
		state1 = moveUp(state);
		state2 = moveDown(state);
		state3 = moveLeft(state);
		state4 = moveRight(state);
		
		Node[] states = {state1, state2, state3, state4};
		
		return states;
	}
	


	private static Node moveLeft(Node node) {
		int space = node.state.indexOf(0);
		ArrayList<Integer> childState;
		int temp;
		Node childNode = new Node();
		
		if (space != 2 && space != 5 && space != 8) {
			childState = (ArrayList<Integer>) node.state.clone();
			temp = childState.get(space+1);
			childState.set(space+1,0);
			childState.set(space,temp);			
			childNode.state = childState;
			childNode.parent = node;
			childNode.distance = node.distance + 1;
			childNode.move = "left";
			return childNode;
		}
		else{
			return null;
		}
	}

	private static Node moveRight(Node node) {
		int space = node.state.indexOf(0);
		ArrayList<Integer> childState;
		int temp;
		Node childNode = new Node();
		
		if (space != 0 && space != 3 && space != 6) {
			childState = (ArrayList<Integer>) node.state.clone();
			temp = childState.get(space-1);
			childState.set(space-1,0);
			childState.set(space,temp);			
			childNode.state = childState;
			childNode.parent = node;
			childNode.distance = node.distance + 1;
			childNode.move = "right";
			return childNode;
		}
		else{
			return null;
		}
	}

	private static Node moveUp(Node node) {
		int space = node.state.indexOf(0);
		ArrayList<Integer> childState;
		int temp;
		Node childNode = new Node();
		
		if (space <= 5) {
			childState = (ArrayList<Integer>) node.state.clone();
			temp = childState.get(space+3);
			childState.set(space+3,0);
			childState.set(space,temp);			
			childNode.state = childState;
			childNode.parent = node;
			childNode.distance = node.distance + 1;
			childNode.move = "up";
			return childNode;
		}
		else{
			return null;
		}
	}

	private static Node moveDown(Node node) {
		int space = node.state.indexOf(0);
		ArrayList<Integer> childState;
		int temp;
		Node childNode = new Node();
		
		if (space > 2) {
			childState = (ArrayList<Integer>) node.state.clone();
			temp = childState.get(space-3);
			childState.set(space-3,0);
			childState.set(space,temp);			
			childNode.state = childState;
			childNode.parent = node;
			childNode.distance = node.distance + 1;
			childNode.move = "down";
			return childNode;
		}
		else{
			return null;
		}
	}
	
	
}
