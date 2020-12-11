/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Header file representing a BitInputStream.
 * It is instantiated with a node representing the empty string.
 * @buf One byte buffer of bits.
 * @nbits How many bits have been read from buf.
 * @in Reference to the input stream to use.
 */
#include "HCNode.hpp"
#include <climits>

class BitInputStream {
private:
    byte buf;
    int nbits;
    istream& in;

public:
    /** Constructor, clear buffer and initialize bit index */
    BitInputStream(istream & is) : in(is), buf(0), nbits(CHAR_BIT) {}

    /** Fill the buffer, read one byte from the input stream */
    void fill();

    /** Read the next bit from the bit buffer.
     * Fill the buffer from the input stream first if needed.
     * @return 1 if the bit read is 1, 0 if bit read is 0.
     */
    int readBit();

    /** Read the amount of characters or unique characters
     * from our bit buffer.
     * @return the int given (32) bits
     */
    unsigned int readInt();

    /** Read the next byte from the bit buffer.
     * @return the character given (8) bits.
     */
    byte readByte();
};