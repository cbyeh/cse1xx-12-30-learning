/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * This file, whose class is constructed, represents an iterator
 * for the Binary Search Tree. We can, for ex. use it to traverse the tree
 * by incrementing it to its successor.
 */

#ifndef BSTITERATOR_HPP
#define BSTITERATOR_HPP

#include "BSTNode.hpp"
#include <list>
#include <iterator>

using namespace std;

template<typename Data>

/** Defines a Binary Search Tree Iterator.
 * Defined by its current pointer to a BST Node. */
class BSTIterator : public iterator<input_iterator_tag, Data> {

   private:

      BSTNode<Data>* curr;

   public:

      /** Constructor.  Use the argument to initialize the current BSTNode
       *  in this BSTIterator.
       */
      BSTIterator(BSTNode<Data>* curr): curr(curr) { }

      /** Dereference operator. */
      Data operator*() const {
         return curr->data;
      }

      /** Pre-increment operator. */
      BSTIterator<Data>& operator++() {
         curr = curr->successor();
         return *this;
      }

      /** Post-increment operator. */
      BSTIterator<Data> operator++(int) {
         BSTIterator before = BSTIterator(curr);
         ++(*this);
         return before;
      }

      /** Equality test operator.
       * Notice that other is a reference and not a pointer, thus it
       * cannot be null. Return true if other is equal to the calling 
       * object. Two iterators are equal if they point to the same 
       * BSTNode in the same BST.
       */
      bool operator==(BSTIterator<Data> const & other) const {
         return (curr == other.curr); // Compare same pointer
      }

      /** Inequality test operator. */
      bool operator!=(BSTIterator<Data> const & other) const {
         return (curr != other.curr); // Compare same pointer
      }

};

#endif //BSTITERATOR_HPP
