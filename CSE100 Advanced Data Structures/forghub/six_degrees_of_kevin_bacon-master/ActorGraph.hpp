/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Header file representing an ActorGraph.
 */
#ifndef ACTORGRAPH_HPP
#define ACTORGRAPH_HPP

#include <iostream>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include "ActorNode.hpp"
#include "Movie.hpp"

using namespace std;

/** Defines a Graph for our actors and their relationships by movie.
 * Defined by a lists of unique actors, movies, and their relationships.
 */
class ActorGraph {

private:

    const short CURR_YEAR = 2019;

public:

    unordered_map<string, ActorNode*> actors; // Unique actors
    unordered_map<string, Movie*> movies; // Unique movies
    vector<ActorEdge> edges; // Relationships of actors

    /** Constructor of the Actor Graph. */
    ActorGraph();

    /** Destructor of the Actor Graph. */
    ~ActorGraph();

    /**
    * Load the graph from a tab-delimited file of actor->movie relationships.
    * @param in_filename - input filename
    * @param use_weighted_edges - if true, compute edge weights as
    *  1 + (2019 - movie_year), otherwise all edge weights will be 1
    * Return true if file was loaded successfully, false otherwise
    */
    bool loadFromFile(const char* in_filename, bool use_weighted_edges);

};


#endif
