/**
 * Christopher Yeh
 * cyeh@ucsd.edu
 * Q&A
 */
 
It was claimed that anyone involved in the Hollywood film industry can be 
linked through their film roles to Kevin Bacon within six steps. You are 
suspicious of such claim and decide to verify it using the given dataset. 
 
1. Before you start to verify the claim, you first notice that it is not 
possible for this claim to be true if the actor graph is not even connected.
We define a connected component of an undirected graph as a largest subgraph 
in which any two vertices are reachable to each other by a path.
Explain how you can verify a given undirected graph is connected 
(i.e all the vertices are in a same connected components). 
You should also include the runtime of your algorithm.

    We can do a BFS traversal on the graph from any arbitrary vertex,
    and as we visit nodes,add it to a set, where no duplicates are allowed.
    If we have visited every node, it is connected. This can be done by
    checking if our set has |V| elements, where |V| is the number of vertices.
    The time complexity is O(|V| + |E|), the complexity of BFS,
    where |V| is the number of vertices and |E| is the number of edges.

2. If the given undirected graph is not connected, then some vertices must
be in different connected components. Explain how you can find the number of
connected components in an undirected graph. You should also include the
runtime of your algorithm.

    For every vertex, if it is not visited, call a function BFS(vertex v), and
    increment a counter.
    In BFS(vertex v), we mark v as visited, and for every vertex connected to v,
    call BFS(vertex v) if it is not visited already.
    The counter is how many connected components there are.
    The time complexity is O(|V| + |E|), the complexity of BFS,
    where |V| is the number of vertices and |E| is the number of edges.

3. Now that you know how to verify that the actor graph defined by the given
dataset is connected (the actor graph is indeed connected) , explain how can
you further verify the claim of "Six Degrees of Kevin Bacon", that is: in the
actor graph, each actor/actress is connected to Kevin Bacon with a path that
contains no more than 6 edges. You should also include the runtime of your
algorithm.

    For every vertex in the graph, which represents an actor/actress, if it
    is not marked as done, run a BFS unweighted shortest path algorithm
    to every vertex not marked as done. If any path is greater than 6,
    then we return false. Mark that vertex as done.
    Return true after every vertex is marked as done and we finished running.
    The time complexity is O(|V| + |E|)², the complexity of BFS,
    where |V| is the number of vertices and |E| is the number of edges.
    It is squared because we performing a BFS on every vertex.