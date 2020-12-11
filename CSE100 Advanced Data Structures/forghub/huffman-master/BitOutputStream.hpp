/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Header file representing a BitInputStream.
 * It is instantiated with a node representing the empty string.
 * @buf One byte buffer of bits.
 * @nbits How many bits have been written to buf.
 * @nbytes How many bytes have been written so far.
 * @out Reference to the output stream to use.
 */
#include "HCNode.hpp"
#include <climits>

class BitOutputStream {
private:
    byte buf;
    int nbits;
    int nbytes;
    ostream& out;

public:
    /** Constructor, clear buffer and bit counter. */
    BitOutputStream(ostream & os) : out(os), buf(0), nbits(0) {}

    /** Send the buffer to the output stream, and clear it */
    void flush();

    /** Write the least significant bit of the argument to
     * the bit buffer, and increment the bit buffer index.
     * But flush the buffer first, if it is full.
     * @param bit 1 or 0.
     */
    void writeBit(unsigned int bit);

    /** Write a (4) byte int in bits
     * @param num int to write.
     */
    void writeInt(unsigned int num);

    /** Write a (8) bit symbol in bits
     * @param symbol character or ascii value to write.
     */
    void writeByte(byte symbol);

    /** Make sure we get the last byte in
     * @return number of bits before padding.
     */
    int pad();

    /** Get our bytes written.
     * @return number of bytes written.
     */
    int getBytes();
};