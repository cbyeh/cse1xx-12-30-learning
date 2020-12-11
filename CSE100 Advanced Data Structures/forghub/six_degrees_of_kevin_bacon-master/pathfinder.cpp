/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * A main driver to test run a pathfinder.
 * The pathfinder will find multiple paths from a given input file,
 * depending on how many pairs of actors there are.
 * It will output the paths, showing how the paths were found
 * by the actors and movies along that path.
 */
#include <iostream>
#include <fstream>
#include <climits>
#include <queue>
#include "ActorGraph.hpp"

using namespace std;

/**
 * @param argc only implemented for four arguments.
 * @param argv
 *      1: tsv file with actors and movies starred in
 *      2: u/w flag for unweighted/weighted paths respectfully
 *      3: tsv file for pairs of actors to find shortest path
 *      4: output file to write in.
 * @return 0 if success, 1 if invalid arguments.
 */
int main(int argc, char** argv) {
    // Check for appropriate arguments. Does not account for invalid files.
    const string INSTRUCTS = "Usage: ./pathfinder movie_cast_tsv_file "
                             "u/w pairs_tsv_file /output_paths_tsv_file";
    const int NUM_ARGS = 5;
    if (argc != NUM_ARGS) {
        cout << INSTRUCTS << endl;
        return EXIT_FAILURE;
    }
    const string IMDB_FILE = argv[1];
    const string IS_WEIGHTED = argv[2];
    const string PAIRS_FILE = argv[3];
    const string OUTPUT_FILE = argv[4];
    // Error "checking" done. Proceed with program
    bool isWeighted;
    if (IS_WEIGHTED == "u") {
        isWeighted = false;
    } else if (IS_WEIGHTED == "w"){
        isWeighted = true;
    } else {
        cout << INSTRUCTS << endl;
        return EXIT_FAILURE;
    }
    // Generate the graph
    const char* IMDB = IMDB_FILE.c_str();
    ActorGraph* actorGraph = new ActorGraph();
    actorGraph->loadFromFile(IMDB, isWeighted);
    ifstream infile(PAIRS_FILE);
    ofstream outfile(OUTPUT_FILE);
    // Add pairs to queue, write formatting header
    bool have_header = false;
    outfile << "(actor)--[movie#@year]-->(actor)--..." << endl;
    // Keep reading lines until the end of file is reached
    while (infile) {
        string s;
        // Get the next line
        if (!getline(infile, s)) break;
        if (!have_header) {
            // Skip the header
            have_header = true;
            continue;
        }
        istringstream ss(s);
        vector<string> record;
        while (ss) {
            string next;
            // Get the next string delimited by tab and put it in 'next'
            if (!getline(ss, next, '\t')) break;
            record.push_back(next);
        }
        if (record.size() != 2) {
            // We should have exactly 2 columns
            continue;
        }
        string actorOneStr(record[0]);
        string actorTwoStr(record[1]);
        // Setup for BFS, initialize pair and set distances of all to INF
        cout << "Computing path for (" << actorOneStr << ") -> ("
            << actorTwoStr << ")" << endl;
        if (actorGraph->actors.find(actorOneStr) == actorGraph->actors.end()) {
            outfile << "Actor(s) do not exist in file" << endl;
            continue;
        }
        if (actorGraph->actors.find(actorTwoStr) == actorGraph->actors.end()) {
            outfile << "Actor(s) do not exist in file" << endl;
            continue;
        }
        ActorNode* actorOne = actorGraph->actors[actorOneStr];
        ActorNode* actorTwo = actorGraph->actors[actorTwoStr];
        for (auto& actor : actorGraph->actors) {
            if (actor.second != nullptr) {
                actor.second->distance = INT_MAX;
                actor.second->isDone = false;
            }
        }
        // Begin BFS
        priority_queue<ActorNode*, vector<ActorNode*>,
                ActorNode::CompareDistance> toExplore;
        actorOne->distance = 0;
        toExplore.push(actorOne);
        bool foundPath = false;
        ActorNode* coStar;
        while (!toExplore.empty()) {
            ActorNode* curr = toExplore.top();
            toExplore.pop();
            if (curr == actorTwo) {
                foundPath = true;
                break;
            }
            if (!curr->isDone) {
                curr->isDone = true;
                for (ActorEdge edge : curr->relationships) {
                    unsigned int distFrom = curr->distance + edge.weight;
                    // Find other co-star
                    if (edge.coStar1 == curr) {
                        coStar = edge.coStar2;
                    } else {
                        coStar = edge.coStar1;
                    }

                    if (distFrom < coStar->distance) {
                        coStar->distance = distFrom;
                        coStar->prevActor = curr;
                        coStar->prevMovie = edge.movie;
                        toExplore.push(coStar);
                    }
                }
            }
        }
        // Print our shortest path and print it to outfile.
        if (!foundPath) {
            outfile << "No path found" << endl;
        } else {
            ActorNode* curr = actorTwo;
            vector<string> toPrint;
            while (curr != actorOne) {
                toPrint.push_back("]-->(" + curr->actorName + ")");
                toPrint.push_back("#@" + to_string(curr->prevMovie->year));
                toPrint.push_back("--[" + curr->prevMovie->movieName);
                curr = curr->prevActor;
            }
            toPrint.push_back("(" + actorOneStr + ")");
            for (auto it = toPrint.rbegin(); it != toPrint.rend(); it++) {
                outfile << *it;
            }
            outfile << endl;
        }
    }
    infile.close();
    outfile.close();
    delete actorGraph;
}
