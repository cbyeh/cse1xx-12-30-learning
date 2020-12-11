/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Header file representing DisjointSets.
 */
#ifndef DISJOINTSET_HPP
#define DISJOINTSET_HPP

#include <iostream>
#include <set>
#include <unordered_map>
#include "ActorGraph.hpp"

using namespace std;

/** Defines a series of Disjoint Sets .
 * Defined by parent pointers of actors in an Actor Graph. */
class DisjointSets {

public:

    ActorGraph* actorGraph; // Graph to be split into sets

    /** Constructor of the Actor Graph. */
    DisjointSets(int numActors, ActorGraph* actorGraph);

    /** Finds the set a node is in, by root of its UpTree */
    ActorNode* find(ActorNode* toFind) const;

    /** Unions two sets */
    void unify(ActorNode* node1, ActorNode* node2);

};

#endif