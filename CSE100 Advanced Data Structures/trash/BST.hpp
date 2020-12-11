/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * This file, whose class is constructed, represents a Binary Search Tree.
 * Using the root, we can traverse the data structure with an inorder traversal
 * to see the data within the nodes it contains.
 * We can see its size, the number of nodes it contains.
 */

#ifndef BST_HPP
#define BST_HPP

#include "BSTNode.hpp"
#include "BSTIterator.hpp"
#include <iostream>

using namespace std;

template<typename Data>

/** Defines a Binary Search Tree.
 * Defined by a pointer to its root, size and height. */
class BST {

   protected:

      /** Pointer to the root of this BST, or 0 if the BST is empty */
      BSTNode<Data>* root;

      /** Number of Data items stored in this BST. */
      unsigned int isize;

      /** Height of this BST. */
      unsigned int iheight;

   public:

      /** define iterator as an aliased typename for BSTIterator<Data>. */
      typedef BSTIterator<Data> iterator;

      /** Default constructor.
        * Initialize an empty BST.
        */
      BST() : root(0), isize(0), iheight(-1) {  }


      /** Default destructor.
        * Delete every node in this BST.
        */
      virtual ~BST() {
         deleteAll(root);
      }

      /** Given a reference to a Data item, insert a copy of it in this BST.
       *  Return  true if the item was added to this BST
       *  as a result of this call to insert,
       *  false if an item equal to this one was already in this BST.
       */
      virtual bool insert(const Data& item) {
         // Initialize on heap with passed item
         BSTNode<Data>* newNode = new BSTNode<Data>(item);
         unsigned int height = 0;
         // Check if our BST empty. If so, make our new Node the root.
         if (empty()) {
            root = newNode;
            iheight = height;
            isize++;
            return true;
         }
         // Iterate until we reach appropriate null child node.
         BSTNode<Data>* curr = root;
         while (curr != nullptr) {
            newNode->parent = curr;
            height++;
            if (curr->data < newNode->data) {
               curr = curr->right;
            } else {
               curr = curr->left;
            }
         }
         // Update our parent's child.
         if (newNode->parent->data < newNode->data) {
            newNode->parent->right = newNode;
         } else {
            newNode->parent->left = newNode;
         }
         // Update height.
         if (iheight < height) {
            iheight = height;
         }
         // Update size.
         isize++;
         // Item was equal to one already in BST. Return false.
         if (!(newNode->parent->data < newNode->data) && 
             !(newNode->data < newNode->parent->data))
         {
            return false;
         }
         return true;
      }


      /** Find a Data item in the BST.
       *  Return an iterator pointing to the item, or pointing past
       *  the last node in the BST if not found.
       *  Note: This function should use only the '<' operator when comparing
       *  Data items. (should not use ==, >, <=, >=).  For the reasoning
       *  behind this, see the assignment writeup.
       */
      virtual iterator find(const Data& item) const {
         BSTNode<Data>* curr = root;
         while (curr != nullptr) {
            // If they are equal
            if (!(curr->data < item) && !(item < curr->data)) {
               return BST::iterator(curr); // *%*
            }
            if (curr->data < item) {
               curr = curr->right;
            } else {
               curr = curr->left;
            }
         }
         // Our item is not in our BST. 
         // Return an iterator pointing past the last item in the BST.
         return typename BST<Data>::iterator(0);
      }


      /** Return the number of items currently in the BST.
       */
      unsigned int size() const {
         return isize;
      }

      /** Return the height of the BST.
       * The height of an empty tree is -1 and the height of a tree
       * with only one node is 0.
       */
      unsigned int height() const {
         return iheight;
      }


      /** Return true if the BST is empty, else false.
       */
      bool empty() const {
         return isize == 0;
      }

      /** Return an iterator pointing to the first item in the BST (not the root).
       */
      iterator begin() const {
         return BST::iterator(first(root));
      }

      /** Return an iterator pointing past the last item in the BST.
       */
      iterator end() const {
         return typename BST<Data>::iterator(0);
      }


      /** Inorder traverse BST, print out the data of each node in ascending order.
       * Implementing inorder and deleteAll base on the pseudo code is an easy way to get started.
       * Pseudo Code:
       * if current node is null: return;
       * recursively traverse left sub-tree
       * print current node data
       * recursively traverse right sub-tree
       */
      void inorder() const {
         inorder(root);
      }

      void inorder(BSTNode<Data>* curr) const {
         if (curr == nullptr) {
            return;
         }
         inorder(curr->left);
         cout << curr->data << endl;
         inorder(curr->right);
      }


   private:

      /** Find the first element of the BST
       */ 
      static BSTNode<Data>* first(BSTNode<Data>* root) {
         BSTNode<Data>* curr = root;
         if (root == nullptr) {
            return nullptr;
         }
         while (curr->left != nullptr) {
            curr = curr->left;
         }
         return curr;
      }

      /** do a postorder traversal, deleting nodes
       */
      static void deleteAll(BSTNode<Data>* n) {
         /* Pseudo Code:
            if current node is null: return;
            recursively delete left sub-tree
            recursively delete right sub-tree
            delete current node
            */
         BSTNode<Data>* curr = n;
         if (curr == nullptr) {
            return;
         }
         if (curr->left != nullptr) {
            deleteAll(curr->left);
         }
         if (curr->right != nullptr) {
            deleteAll(curr->right);
         }
         delete curr;
      }


};


#endif //BST_HPP
