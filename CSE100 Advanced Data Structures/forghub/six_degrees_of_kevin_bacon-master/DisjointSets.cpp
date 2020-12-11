/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Implementation of DisjointSets.
 */
#include "DisjointSets.hpp"

/** Constructor of the Disjoint Sets. */
DisjointSets::DisjointSets(int numActors, ActorGraph* actorGraph) {
    this->actorGraph = actorGraph;
    for (auto& actor : actorGraph->actors) {
        actor.second->parent = nullptr;
    }
}

/** Finds the set a node is in, by root of its UpTree */
ActorNode* DisjointSets::find(ActorNode* toFind) const {
    ActorNode* root = toFind;
    while (root->parent != nullptr) {
        root = root->parent;
    }
    return root;
}

/** Unions two sets */
void DisjointSets::unify(ActorNode* node1, ActorNode* node2) {
    ActorNode* root1 = find(node1);
    ActorNode* root2 = find(node2);
    if (root1 == root2) {
        return;
    }
    if (root1->actorName < root2->actorName) {
        root1->parent = root2;
    } else {
        root2->parent = root1;
    }
}