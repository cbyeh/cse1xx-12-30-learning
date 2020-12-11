/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Implementation of a Movie.
 */
#include "Movie.hpp"

/** Constructor. Initialize a Movie with a movie title and its year */
Movie::Movie(string& movieName, unsigned short year) :
    movieName(movieName), year(year) { }
