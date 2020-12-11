/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Header file representing a HCNode.
 * Comparator is implemented, no methods needed.
 * @byte an unsigned char.
 */
#ifndef HCNODE_HPP
#define HCNODE_HPP
#include <iostream>

typedef unsigned char byte;
using namespace std;

/**
 * A class, instances of which are nodes in an HCTree.
 * @count How frequent the symbol occurs.
 * @symbol Byte in the file we're keeping track of.
 * @c0 Pointer to '0' child.
 * @c1 Pointer to '0' child.
 * @p Pointer to parent.
 */
class HCNode {
    friend bool comp(HCNode* one, HCNode* other);

public:
    int count;
    byte symbol;
    HCNode* c0;
    HCNode* c1;
    HCNode* p;

    /** Constructor */
    HCNode(int count,
      byte symbol,
      HCNode* c0 = nullptr,
      HCNode* c1 = nullptr,
      HCNode* p = nullptr)
        : count(count), symbol(symbol), c0(c0), c1(c1), p(p) { }

     /**
      * Less-than comparison, so HCNodes will work in std::priority_queue
      * We want small counts to have high priority.
      * And we want to break ties deterministically.
      * @param other Node to be compared.
      */
     bool operator<(const HCNode& other) {
         // If counts are different, just compare counts.
         if (this->count != other.count) {
             return this->count > other.count;
         }
         // Counts are equal. use symbol value to break tie.
         return this->symbol < other.symbol;
     }
};

/** For printing an HCNode to an ostream
 *  Possibly useful for debugging.
 */
// ostream& operator<<(ostream&, const HCNode&) __attribute__((weak)); // shut the linker up
inline ostream& operator<<(ostream& stm, const HCNode& n) {
    stm << "[" << n.count << "," << (int) (n.symbol) << "]";
    return stm;
}

bool comp(HCNode* one, HCNode* other);

#endif // HCNODE_HPP
