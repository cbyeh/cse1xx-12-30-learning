/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Main runner to uncompress a file with a huffman trie.
 * Compile and run with proper arguments.
 */
#include "HCTree.hpp"

/**
 * Decodes our compressed file.
 * @param argc number of arguments
 * @param argv two arguments, compressed file name and output file name.
 * @return failure if wrong number of arguments. Success otherwise.
 */
int main(int argc, char** argv) {
// Check for appropriate arguments. Does not account for invalid files.
    const int NUM_ARGS = 3;
    if (argc != NUM_ARGS) {
        cout << "Invalid number of arguments" << endl <<
             "Usage: ./uncompress <infile filename> <outfile filename>." << endl;
        return EXIT_FAILURE;
    }
    // Error "checking" done. Proceed with program.
    const string INFILE = argv[1];
    const string OUTFILE = argv[2];
    ifstream input;
    ofstream output;
    input.open(INFILE, ios_base::binary);
    output.open(OUTFILE, ios_base::trunc);
    // If file is empty, don't write anything.
    input.seekg(0, ios::end);
    if (input.tellg() == 0) {
        return EXIT_SUCCESS;
    }
    input.clear();
    input.seekg(0);
    // Get the number of characters for out output.
    BitInputStream bitIn = BitInputStream(input);
    unsigned int numCharacters = bitIn.readInt();
    unsigned int numUniqueChars = bitIn.readBit();
    unsigned char nextByte;
    // Single character cases.
    if (numUniqueChars == 1) {
        nextByte = (unsigned char) bitIn.readByte();
        for (int i = 0; i < numCharacters; i++) {
            output << nextByte;
        }
        return EXIT_SUCCESS;
    }
    // Build our tree from encoding.
    HCTree* ht = new HCTree();
    ht->buildFromEncoding(bitIn);
    // Output to our file. Deconstruct and return success.
    for (int i = 0; i < numCharacters; i++) {
        nextByte = (unsigned char) ht->decode(bitIn);
        output << nextByte;
    }
    delete ht;
    return EXIT_SUCCESS;
}