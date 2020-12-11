/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Header file representing a HCTree.
 * Methods for numerous encode and decodes for a naive approach, also
 * space efficient implementations, as well as building the tree.
 */
#ifndef HCTREE_HPP
#define HCTREE_HPP

#include <queue>
#include <vector>
#include <fstream>
#include <unordered_map>
#include <unordered_set>
#include "HCNode.hpp"

#include "BitInputStream.hpp"
#include "BitOutputStream.hpp"

using namespace std;

/** A 'function class' for use as the Compare class in a
 *  priority_queue<HCNode*>.
 *  For this to work, operator< must be defined to
 *  do the right thing on HCNodes.
 */
class HCNodePtrComp {
public:
    bool operator()(HCNode*& lhs, HCNode*& rhs) const {
        return *lhs < *rhs;
    }
};

/** A Huffman Code Tree class.
 *  Not very generic: Use only if alphabet consists
 *  of unsigned chars.
 *  @root the root of the trie.
 *  @leaves nodes where we have a symbol represented.
 *  @codes what the leaf would traverse to from root.
 */
class HCTree {
private:
    HCNode* root;
    vector<HCNode*> leaves;
    unordered_map<byte, string> codes;
    const int CHAR_TO_INT = 48;

    void deleteAll(HCNode* start);

public:
    const static int TABLE_SIZE = 256;

    explicit HCTree() : root(nullptr) {
        leaves = vector<HCNode*>(256, (HCNode*) nullptr);
    }

    /** Destructor */
    ~HCTree();

    /** Use the Huffman algorithm to build a Huffman coding trie.
     * PRECONDITION: freqs is a vector of ints, such that freqs[i] is
     * the frequency of occurrence of byte i in the message.
     * POSTCONDITION: root points to the root of the trie,
     * and leaves[i] points to the leaf node containing byte i.
     * @param freqs vector of ascii values and their frequency.
     */
    void build(const vector<int>& freqs);

    /** Use our encoding to build a Huffman coding trie.
     * PRECONDITION: a file was properly encoded.
     * @param in our input stream for bits.
     */
    void buildFromEncoding(BitInputStream& in);

    /** Encode this tree with pre-order traversal.
     * PRECONDITION: build() has been called, to create the coding
     * tree, and initialize root pointer and leaves vector.
     * @param out our input stream for bits.
     * @param numCharacters how many total characters there are.
     * @param numUniqueChars how many different ascii values there are.
     */
    void writeHeader(BitOutputStream& out, unsigned int numCharacters,
            unsigned int numUniqueChars) const;

    /** Helper for writeHeader.
     * @param out our input stream for bits.
     * @param parent root to be handed for recursion.
     */
    void writeHeaderHelper(BitOutputStream& out, HCNode* parent) const;

    /** Write to the given BitOutputStream.
     *  the sequence of bits coding the given symbol.
     *  PRECONDITION: build() has been called, to create the coding
     *  tree, and initialize root pointer and leaves vector.
     *  @param symbol 8 bits to be encoded.
     *  @param out our output stream.
     */
    void encode(byte symbol, BitOutputStream& out) const;

    /** Make sure we get the last byte in.
     * @param out our input stream for bits.
     */
    void pad(BitOutputStream& out) const;

    /** Return symbol coded in the next sequence of bits from the stream.
     *  PRECONDITION: build() has been called, to create the coding
     *  tree, and initialize root pointer and leaves vector.
     *  @param in our input stream for bits.
     *  @return symbol of the 8 bits read.
     */
    int decode(BitInputStream& in) const;

};

#endif // HCTREE_H
