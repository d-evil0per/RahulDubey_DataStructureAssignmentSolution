/**
 * 
 */
package com.gl.question2;


/**
 * @author Rahul dubey
 * Question 2 
 *	You are working in an MNC, which manages the Transactions, where only BST is used as a
 *	Data Structure. The company stores all the data of transactions in BST such that the tree is
 *	always a complete BST.
 *	A new business requirement has arrived where the BST should not contain any left node.
 *	You are required to modify the existing BST and display the node values present in ascending
 *	order.
 *	Hint: ( Convert the Binary Search Tree into a Skewed Tree).
 *
 */

//Class of the node
class Node {
	int val;
	Node left, right;

	// Helper function that allocates a new node
	// with the given data and NULL left and right
	// pointers.
	Node(int item) {
		val = item;
		left = right = null;
	}
}

public class BSTToSkewedTree {

	public Node node;
	public Node prevNode = null;
	public Node headNode = null;

	// Function to to convert the binary
	// search tree into a skewed tree in
	// increasing order
	public void convertBSToSkewed(Node root) {

		// Base Case
		if (root == null) {
			return;
		}

		// Recurse to its left node if it exists, to get smaller value.
		convertBSToSkewed(root.left);
		Node rightNode = root.right;

		// After the complete traversal of its left node/subtree,
		// connect the previous node of the skewed tree to the root node.
		// Condition to check if the root Node
		// of the skewed tree is not defined
		if (this.headNode == null) {
			this.headNode = root;
			root.left = null;
			this.prevNode = root;
		} else {
			this.prevNode.right = root;
			root.left = null;
			this.prevNode = root;
		}

		// Similarly recurse for the right
		// subtree on the basis of the order required

		convertBSToSkewed(rightNode);

	}

	// Function to traverse the right
	// skewed tree using recursion
	public void traverseRightSkewed(Node root) {
		if (root == null) {
			return;
		}
		System.out.print(root.val + " ");
		traverseRightSkewed(root.right);
	}

	// Driver Code
	public static void main(String[] args) {

		BSTToSkewedTree tree = new BSTToSkewedTree();
		// adding the node to BST
		tree.node = new Node(50);
		tree.node.left = new Node(30);
		tree.node.right = new Node(60);
		tree.node.left.left = new Node(10);
		tree.node.right.left = new Node(55);
		// converting the BST to Skewed tree
		tree.convertBSToSkewed(tree.node);
		// Traversing the skewed tree
		tree.traverseRightSkewed(tree.headNode);
	}
}
