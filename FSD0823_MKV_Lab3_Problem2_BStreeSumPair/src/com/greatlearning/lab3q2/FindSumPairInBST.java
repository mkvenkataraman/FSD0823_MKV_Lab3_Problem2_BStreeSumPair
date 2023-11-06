//Solution Write up
//This code provides a complete program for creating a binary search tree (BST), 
//inserting nodes into it, and finding if there exists a pair of nodes that add up 
//to a given sum. The program prompts the user to enter a sum, and then it checks 
//if any two nodes in the BST add up to that sum, printing out the pair if it exists.

// Package declaration to organize the code
package com.greatlearning.lab3q2;

//Importing classes for using hash sets for unique collections, scanners for input, 
//and sets as a collection interface.

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// Class definition for a Node in the BST
class Node {
	int key; // Data to be stored in the node
	Node left, right; // References to the left and right child nodes

	// Constructor to initialize a node with a given key
	public Node(int key) {
		this.key = key;
		left = right = null;
	}
}

// Class that contains methods to find a pair of nodes in a BST that add up to a given sum
public class FindSumPairInBST {
	Node root; // Reference to the root of the BST

	// Main method which acts as the entry point of the Java program
	public static void main(String[] args) {
		FindSumPairInBST tree = new FindSumPairInBST(); // Creating an instance of the class

		// Inserting nodes into the BST
		tree.root = tree.insert(tree.root, 40);
		tree.insert(tree.root, 20);
		tree.insert(tree.root, 60);
		tree.insert(tree.root, 10);
		tree.insert(tree.root, 30);
		tree.insert(tree.root, 50);
		tree.insert(tree.root, 70);

		// Performing an in-order traversal to print the BST nodes in ascending order
		System.out.println("InOrder Traverse");
		tree.inOrderTraverse(tree.root);
		System.out.println();

		// Using Scanner to read input from the user
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the Sum to find BST Pair: ");
		int sum = scanner.nextInt(); // Storing the user-provided sum

		// Finding and printing a pair with the given sum
		tree.findPairWithGivenSum(tree.root, sum);

		scanner.close(); // Closing the scanner to prevent resource leaks
	}

	// Method to find a pair with the given sum
	private void findPairWithGivenSum(Node root, int sum) {
		Set<Integer> set = new HashSet<>(); // A set to store visited nodes' values
		if (!findPairUtil(root, sum, set)) {
			// If no pair is found, print a message indicating that
			System.out.print("Pairs don't exist\n");
		}
	}

	// Utility method to find a pair recursively. Returns true if a pair is found.
	private boolean findPairUtil(Node node, int sum, Set<Integer> set) {
		if (node == null) {
			// Base case: if the current node is null, return false
			return false;
		}
		// Recursive case: search the left subtree
		if (findPairUtil(node.left, sum, set))
			return true;
		int complement = sum - node.key; // Calculate the complement value
		if (set.contains(complement)) {
			// If the complement exists in the set, print the pair
			System.out.println("Given Pair (" + node.key + "," + complement + ")");
			return true;
		} else {
			// If the complement doesn't exist, add the current node's key to the set
			set.add(node.key);
		}
		// Continue to search in the right subtree
		return findPairUtil(node.right, sum, set);
	}

	// Method to perform an in-order traversal of the BST
	private void inOrderTraverse(Node node) {
		if (node == null)
			return;
		// Traverse the left subtree, visit the node, and then traverse the right
		// subtree
		inOrderTraverse(node.left);
		System.out.print(node.key + " ");
		inOrderTraverse(node.right);
	}

	// Utility function to insert a new node with a given key into the BST
	private Node insert(Node node, int key) {
		if (node == null) {
			// If the current node is null, a new node is created and returned
			node = new Node(key);
			return node;
		}
		// Recursive calls to insert the node in the correct position according to BST
		// rules
		if (key < node.key) {
			// If key is less than the current node, insert in the left subtree
			node.left = insert(node.left, key);
		} else if (key > node.key) {
			// If key is greater than the current node, insert in the right subtree
			node.right = insert(node.right, key);
		}
		// Return the (possibly updated) current node
		return node;
	}
}