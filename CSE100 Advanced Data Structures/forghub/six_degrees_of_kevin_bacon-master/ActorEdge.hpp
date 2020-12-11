/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Header file representing an ActorEdge.
 */
#ifndef ACTOREDGE_HPP
#define ACTOREDGE_HPP

#include <iostream>
#include "Movie.hpp"
class ActorNode;

using namespace std;

/** Defines an edge for an ActorEdge.
 * Defined by a common movie, co-star, and its weight relative to year released */
class ActorEdge {

public:

    ActorNode* coStar1;
    ActorNode* coStar2;
    Movie* movie;
    int weight;

    /** Constructor. Initialize an ActorEdge with a Movie and co-star */
    explicit ActorEdge(Movie* movie, ActorNode* coStar1, ActorNode* coStar2);

    /** Constructor. Initialize an ActorEdge with a Movie, co-star, and weight */
    explicit ActorEdge(Movie* movie, ActorNode* coStar1, ActorNode* coStar2,
            int weight);

    /** Comparator for the sorting by release year in movietraveler */
    struct CompareWeight {
        bool operator() (const ActorEdge& n1, const ActorEdge& n2) {
            if (n1.weight == n2.weight) {
                return n1.movie->movieName < n2.movie->movieName;
            } else {
                return n1.weight < n2.weight;
            }
        }
    };
};

#endif