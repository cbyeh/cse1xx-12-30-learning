/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * A main driver to test run the movietraveler.
 * Given a tsv file to create the graph, it works by creating
 * a minimum spanning tree of that graph, effectively connecting
 * all the actors by newest movies first.
 * It will output each actor visited, and the movie from that
 * actor to the another.
 */
#include <iostream>
#include <fstream>
#include <climits>
#include <queue>
#include <algorithm>
#include "ActorGraph.hpp"
#include "DisjointSets.hpp"

using namespace std;

/**
 * @param argc only implemented for two arguments.
 * @param argv
 *      1: tsv file with actors and movies starred in
 *      2: output file to write in.
 * @return 0 if success, 1 if invalid arguments.
 */
int main(int argc, char** argv) {
    // Check for appropriate arguments. Does not account for invalid files.
    const string INSTRUCTS = "Usage: ./movietraveler "
                             "movie_cast_tsv_file /output_paths_tsv_file";
    const int NUM_ARGS = 3;
    if (argc != NUM_ARGS) {
        cout << INSTRUCTS << endl;
        return EXIT_FAILURE;
    }
    const string IMDB_FILE = argv[1];
    const string OUTPUT_FILE = argv[2];
    // Generate the graph
    const char* IMDB = IMDB_FILE.c_str();
    ActorGraph* actorGraph = new ActorGraph();
    actorGraph->loadFromFile(IMDB, true);
    ifstream infile(IMDB_FILE);
    ofstream outfile(OUTPUT_FILE);
    int numActors = actorGraph->actors.size();
    // Add pairs to queue, write formatting header
    bool have_header = false;
    outfile << "(actor)<--[movie#@year]-->(actor)" << endl;
    // Keep reading lines until the end of file is reached
    vector<ActorEdge>& edges = actorGraph->edges;
    sort(edges.begin(), edges.end(), ActorEdge::CompareWeight());
    DisjointSets sets = DisjointSets(numActors, actorGraph);
    // Begin Kruskal's algorithm
    int edgesChosen = 0, edgeWeights = 0;
    for (ActorEdge& edge : edges) {
        // If no cycle, connect the actors.
        ActorNode* find1 = sets.find(edge.coStar1);
        ActorNode* find2 = sets.find(edge.coStar2);
        if (find1 != find2) {
            sets.unify(edge.coStar1, edge.coStar2);
            outfile << "(" << edge.coStar1->actorName << ")<--[" <<
            edge.movie->movieName << "#@" << edge.movie->year <<
            "]-->(" << edge.coStar2->actorName << ")" << endl;
            // if (find1 != edge.coStar1)
            edgesChosen++;
            edgeWeights += edge.weight;
        }
        if (edgesChosen == numActors - 1) {
            break;
        }
    }
    outfile << "#NODE CONNECTED: " << numActors << endl <<
    "#EDGE CHOSEN: " << edgesChosen << endl <<
    "TOTAL EDGE WEIGHTS: " << edgeWeights << endl;
    infile.close();
    outfile.close();
    delete actorGraph;
}
