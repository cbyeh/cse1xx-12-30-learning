/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Implementation of an ActorEdge.
 */
#include "ActorEdge.hpp"

/** Constructor. Initialize an ActorEdge with a Movie and co-star */
ActorEdge::ActorEdge(Movie* movie, ActorNode* coStar1, ActorNode* coStar2) :
    ActorEdge(movie, coStar1, coStar2, 1) { }

/** Constructor. Initialize an ActorEdge with a Movie, co-star, and weight */
ActorEdge::ActorEdge(Movie* movie, ActorNode* coStar1, ActorNode* coStar2,
        int weight) :
    movie(movie), coStar1(coStar1), coStar2(coStar2), weight(weight) { }
