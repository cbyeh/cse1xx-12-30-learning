/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Implementation of an ActorGraph.
 * loadFromFile() will load from a tsv.
 */
#include "ActorGraph.hpp"

/** Constructor of the Actor Graph. */
ActorGraph::ActorGraph() { }

/**
 * Load the graph from a tab-delimited file of actor->movie relationships.
 * @param in_filename - input filename
 * @param use_weighted_edges - if true, compute edge weights as
 *  1 + (2019 - movie_year), otherwise all edge weights will be 1
 * Return true if file was loaded successfully, false otherwise
 */
bool ActorGraph::loadFromFile(const char* in_filename,
        bool use_weighted_edges)
{
    // Initialize the file stream
    ifstream infile(in_filename);
    bool have_header = false;
    // Keep reading lines until the end of file is reached
    cout << "Reading " << in_filename << " ..." << endl;
    while (infile) {
        string s;
        // Get the next line
        if (!getline( infile, s )) break;
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
        if (record.size() != 3) {
            // We should have exactly 3 columns
            continue;
        }
        string actor_name(record[0]);
        string movie_title(record[1]);
        string movie_year(record[2]);
        short year = stoi(movie_year);
        // Create actor if not iterated yet
        if (actors.find(actor_name) == actors.end()) {
            auto newNode = new ActorNode(actor_name);
            actors.insert({actor_name, newNode});
        }
        // Create movie if not iterated yet
        string movieHash = movie_title + movie_year;
        if (movies.find(movieHash) == movies.end()) {
            Movie* movie = new Movie(movie_title, year);
            movies.insert({movieHash, movie});
        }
        // Create connections
        ActorNode* currActor = actors[actor_name];
        Movie* movie = movies[movieHash];
        vector<ActorNode*>& stars = movies[movieHash]->actorsInMovie;
        int weight;
        for (ActorNode* star : stars) {
            if (use_weighted_edges) {
                weight = CURR_YEAR - year + 1;
                ActorEdge newEdge = ActorEdge(movie, currActor, star, weight);
                edges.push_back(newEdge);
                star->relationships.push_back(newEdge);
                actors[actor_name]->relationships.push_back(newEdge);
            } else {
                // Weight is 1
                ActorEdge newEdge = ActorEdge(movie, currActor, star);
                edges.push_back(newEdge);
                star->relationships.push_back(newEdge);
                actors[actor_name]->relationships.push_back(newEdge);
            }
        }
        stars.push_back(currActor);
    }
    // Finish
    if (!infile.eof()) {
        cerr << "Failed to read " << in_filename << "!\n";
        return false;
    }
    cout << "done" << endl;
    infile.close();
    return true;
}

/** Destructor of the Actor Graph. */
ActorGraph::~ActorGraph() {
    for (auto& movie : movies) {
        delete movie.second; // Delete movie object
    }
    string hash;
    for (auto& actor : actors) {
        delete actor.second; // Delete actor object
    }
}