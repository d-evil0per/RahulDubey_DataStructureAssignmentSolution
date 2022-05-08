/**
 * 
 */
package com.gl.question1;
import java.util.*;


/**
 * @author Rahul dubey
 * @question: 1
 * 	Your friend is a chief architect, who is working on building a skyscraper, in Mumbai. The
 *	construction is in such a way that the floors will be constructed in other factories and they will be
 *	assembled. All the sizes will be distinct.
 *	The skyscraper needs to be constructed in N days with the following conditions :
 *		a) Every day a floor is constructed in a separate factory of distinct size.
 *		b) The floor with the larger size must be placed at the bottom of the building.
 *		c) The floor with the smaller size must be placed at the top of the building.
 *	Note: A floor cannot be assembled in the building until all floors larger in size are placed.
 *	As a friend he wants you to build a small program that will help him analyze the construction
 *	process, to avoid manual work and errors.
 *	Input
 *		First Line: contains the total floors N in the building
 *		Second Line onwards: Contains N inputs in which the ith integer denotes the size of the floor
 *		that will be given to architect by factories on the ith day.
 *	Output
 *		You are required to print N lines. Print the size of the floor in descending order, which can be
 *		assembled.
 *	If no floor can be assembled on the ith day, leave the line empty.

 *
 */
public class Skyscrapper {

	/**
	 * Floor_size is a queue to store the user input of all the floor sizes
	 * sorted_floor_size is a priority queue to store the descending order values of
	 * floor_size floor_stack is a stack to hold the floor order
	 */
	public static Queue<Integer> floor_size = new LinkedList<>();
	public static PriorityQueue<Integer> sorted_floor_size = new PriorityQueue<Integer>(Collections.reverseOrder());
	public static Stack<Integer> floor_stack = new Stack<Integer>();

	// Sort a stack using temporary stack
	public static Stack<Integer> sortStack(Stack<Integer> stack) {
		Stack<Integer> tempStack = new Stack<Integer>();
		// iterating through each element from the stack until stack is empty
		while (!stack.isEmpty()) {
			// Storing the top element of stack in a local variable
			int currentData = stack.pop();
			// checking if temporary stack is not empty and top element is greater than
			// currently popped element of the main stack
			while (!tempStack.isEmpty() && tempStack.peek() > currentData) {
				// pushing the larger element back to main stack by popping it out from the
				// tempstack
				stack.push(tempStack.pop());
			}
			// pushing the currently popped element of main stack to the tempstack
			tempStack.push(currentData);
		}
		return tempStack;
	}

	// removing element from the Stack
	public static void popFromStack() {
		// popping the top element from the stack and storing it to the variable
		int stack_item = floor_stack.pop();
		// removing the popped element from the sorted priority queue
		sorted_floor_size.remove(stack_item);
		// Printing the popped element
		System.out.print(stack_item);
	}

	// main Method for the analyzing the construction process
	public static void analyzeConstructionProcess() {
		int i = 1;
		// iterating through queue until queue is empty
		while (!floor_size.isEmpty()) {
			System.out.println("Day: " + i);
			// popping the front element from the queue and storing it to the element
			int element = floor_size.poll();
			// pushing it to the stack
			floor_stack.push(element);
			// copying the stack to temporary stack for sorting it to descending order
			Stack<Integer> tempStack = new Stack<Integer>();
			tempStack.addAll(floor_stack);
			floor_stack = sortStack(tempStack);
			// checking the popped element of the queue is equal to top element of the
			// priority queue
			if (element == sorted_floor_size.peek()) {
				// looping until the queue doesn't contains the top element of the priority
				// queue and if the priority queue is not empty
				while (!floor_size.contains(sorted_floor_size.peek()) && !sorted_floor_size.isEmpty()) {
					// if all the element from the queue has been popped out
					if (floor_size.isEmpty()) {
						// if stack still contains the element then popping the element from the stack
						if (!floor_stack.empty()) {
							popFromStack();
						}

					}
					// popping from stack if the top element of the priority queue is equal to the
					// top element of the stack
					else if (sorted_floor_size.peek() == floor_stack.peek()) {
						popFromStack();
					}

				}
				System.out.println();

			}
			// If no floor can be assembled on the ith day, printing the line empty.
			else {
				System.out.println();
			}
			i++;
		}
	}

	public static void main(String[] args) {
		// Main Method to drive the process
		System.out.println("enter the total no of floors in the building");
		try (Scanner sc = new Scanner(System.in)) {
			int no_of_floors = sc.nextInt();
			for (int i = 0; i < no_of_floors; i++) {
				// taking the input from the user for the floor size of the nth day
				System.out.println("enter the floor size given on day : " + (i + 1));
				int element = sc.nextInt();
				// checking for the distinct input for each day
				if (floor_size.contains(element)) {
					System.out.println("Floor size should be distinct for each day!! Please enter it again!!");
					// if the element is duplicate then decreasing the index by 1
					i -= 1;
				} else {
					// pushing the element to the queue and the priority queue
					floor_size.add(element);
					sorted_floor_size.add(element);
				}

			}
			// calling the method for the analysis
			analyzeConstructionProcess();

		}

	}

}
