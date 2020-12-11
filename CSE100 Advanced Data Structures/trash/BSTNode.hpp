/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * This file, whose class is constructed, represents a node
 * for the Binary Search Tree. Finding its successor is implemented in this
 * file rather than using a method involving the root.
 */

#ifndef BSTNODE_HPP
#define BSTNODE_HPP

#include <iostream>
#include <iomanip>

using namespace std;

template<typename Data>

/** Defines a node for a Binary Search Tree.
 * Defined by its data, and pointer to its children and parent. */
class BSTNode {

	public:

		BSTNode<Data>* left;
		BSTNode<Data>* right;
		BSTNode<Data>* parent;
		Data const data;   // the const Data in this node.

		/** Constructor.  Initialize a BSTNode with the given Data item,
		 *  no parent, and no children.
		 */
		BSTNode(const Data& d) : data(d) {
			left = nullptr;
			right = nullptr;
			parent = nullptr;
		}


		/** Return the successor of this BSTNode in a BST, or 0 if none.
		 *  PRECONDITION: this BSTNode is a node in a BST.
		 *  POSTCONDITION:  the BST is unchanged.
		 *  RETURNS: the BSTNode that is the successor of this BSTNode,
		 *  or 0 if there is none.
		 */
		BSTNode<Data>* successor() {
			// Case one, we have a right subtree.
			BSTNode<Data>* curr = this;
			if (curr->right != nullptr) {
				curr = curr->right; // Proceed to find min in right subtree.
				while (curr->left != nullptr) {
					curr = curr->left;
				}
				return curr;
			}
			// No right subtree.
			BSTNode<Data>* prev = this;
			curr = curr->parent; // Go up the tree.
			// Case two, we are our parent's left child. 
			if (curr != nullptr) {
				if (curr->left == prev) {
					return curr;
				}
			}
			// Case three, we are our parent's right child.
			while (curr != nullptr) {
				prev = curr;
				curr = curr->parent;
				if (curr != nullptr) {
					if (curr->left == prev) { // Prev was a left child. Found.
						return curr;
					}
				}
			}
			return 0; // Case four, this is only node or right-most.
		}

}; 

/** Overload operator<< to print a BSTNode's fields to an ostream. */
template <typename Data>
ostream & operator<<(ostream& stm, const BSTNode<Data> & n) {
	stm << '[';
	stm << setw(10) << &n;                 // address of the BSTNode
	stm << "; p:" << setw(10) << n.parent; // address of its parent
	stm << "; l:" << setw(10) << n.left;   // address of its left child
	stm << "; r:" << setw(10) << n.right;  // address of its right child
	stm << "; d:" << n.data;               // its data field
	stm << ']';
	return stm;
}

#endif // BSTNODE_HPP
