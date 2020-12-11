/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * This file, whose class is constructed, represents a Binary Search Array.
 * Created to compare its efficiency compared to the Binary Search Tree when
 * finding and inserting. It uses a Binary Search algorithm to find and insert
 * its elements.
 */

#ifndef BSA_HPP
#define BSA_HPP

#include <vector>

using namespace std;

template<typename Data>

/** This class implements a binary search array 
 * Its vector holds its data. */
class BSA {

private:
    vector<Data> v;

    /** Helper method used to either find an element if it exists
     * or else find where it should be inserted.
     */
    virtual size_t binarySearch(const Data &item) const {
        int left = 0;
        int right = v.size() - 1;
        int mid = 0;
        int const TWO = 2;
        while (left <= right) {
            mid = (left + right) / TWO;
            if (item < v[mid]) {
                right = mid - 1;
            } else if (v[mid] < item) {
                left = mid + 1;
            } else {
                return mid; // Match.
            }
        }
        return mid; // Return where it should be inserted.
    }

public:
    /** Return the position of item, otherwise v.cend() */
    virtual typename vector<Data>::const_iterator find(const Data &item) const {
        size_t index = binarySearch(item);
        if (index < v.size()) {
            if (!(v[index] < item) && !(item < v[index])) { // If they are equal:
                return (v.cbegin() + index);
            }
        }
        return v.cend(); // Return constant iterator pointing past last node.
    }

    /** Insert item into sorted position */
    virtual bool insert(const Data &item) {
        size_t index = binarySearch(item);
        v.insert(v.begin() + index, item);
        if (index + 1 < v.size()) {
            if (!(v[index] < v[index + 1]) && !(v[index + 1] < v[index])) {
                return false; // If item already existed.
            }
        }
        return true;
    }

    typename vector<Data>::iterator begin() const {
        return v.begin();
    }

    typename vector<Data>::iterator end() const {
        return v.end();
    }
};

#endif
