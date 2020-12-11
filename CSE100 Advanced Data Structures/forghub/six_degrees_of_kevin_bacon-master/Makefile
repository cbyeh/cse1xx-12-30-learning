# A simple makefile

CC=g++
CXXFLAGS=-std=c++11
LDFLAGS=

# if passed "type=opt" at command-line, compile with "-O3" flag (otherwise use "-g" for debugging)

ifeq ($(type),opt)
    CPPFLAGS += -O3
    LDFLAGS += -O3
else
    CPPFLAGS += -g
    LDFLAGS += -g
endif

all: pathfinder movietraveler

pathfinder: Movie.o ActorGraph.o ActorNode.o ActorEdge.o

movietraveler: Movie.o ActorGraph.o ActorNode.o ActorEdge.o DisjointSets.o

DisjointSets.o: DisjointSets.hpp ActorGraph.hpp

ActorGraph.o: Movie.hpp ActorGraph.hpp ActorNode.hpp ActorEdge.hpp

ActorEdge.o: Movie.hpp ActorNode.hpp ActorEdge.hpp

ActorNode.o: Movie.hpp ActorNode.hpp ActorEdge.hpp

Movie.o: Movie.hpp

clean:
	rm -f pathfinder *.o core*

