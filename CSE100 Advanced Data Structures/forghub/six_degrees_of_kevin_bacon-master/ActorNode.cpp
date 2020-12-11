/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Implementation of an ActorNode.
 * Initializes one with the actor's name.
 */
#include "ActorNode.hpp"

/** Constructor. Initialize an ActorNode with empty edges and actor name */
ActorNode::ActorNode(string& actorName) : actorName(actorName) {
    prevActor = nullptr;
    prevMovie = nullptr;
}