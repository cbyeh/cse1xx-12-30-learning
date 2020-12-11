/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Header file representing a Movie.
 */
#ifndef MOVIE_HPP
#define MOVIE_HPP

#include <iostream>
#include <vector>
class ActorNode;

using namespace std;

/** Defines a Movie.
 * Defined by its release date, title, and actors that appear in it */
class Movie {

public:

    string movieName;
    int year;
    vector<ActorNode*> actorsInMovie; // Actors that star in the movie

    /** Constructor. Initialize a Movie with a movie title and its year */
    Movie(string& movieName, unsigned short year);

};

#endif