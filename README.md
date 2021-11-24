# AlgoAssignment

# Introduction:
Consider a data communication network that must route data packets (email, MP3 files, or video files, for example). Such a network consists of routers connected by physical cables or links. A router can act as a source, a destination, or a forwarder of data packets. We can model a network as a graph with each router corresponding to a vertex and the link or physical connection between two routers corresponding to a pair of directed edges between the vertices. A network that follows the OSPF (Open Shortest Path First) protocol routes packets using Dijkstra’s shortest path algorithm. The criteria used to compute the weight corresponding to a link can include the time taken for data transmission, reliability of the link, transmission cost, and available bandwidth. Typically each router has a complete representation of the network graph and associated information available to it. For the purposes of this project, each link has associated with it the transmission time taken for data to get from the vertex at one end to the vertex at the other end. You will compute the best path using the criterion of minimizing the total time taken for data to reach the destination. The shortest time path minimizes the sum of the transmission times of the links along the path. The network topology can change dynamically based on the state of the links and the routers. For example, a link may go down when the corresponding cable is cut, and a vertex may go down when the corresponding router crashes. In addition to these transient changes, changes to a network occur when a link is added or removed.
Dijkstra’s shortest path algorithm:
Dijkstra’s algorithm is a graph traversal algorithm that finds the shortest path between graph vertices. This algorithm finds a path between the source node to every other node in a graph. Network routing protocols, artificial intelligence are common applications of Dijkstra’s algorithm.

# Program description:
- Programming Language: Java JDK(11) IDE: Eclipse
- Total number of Files: 4

# Program design:

This project contains four files: 
1) Main.java
2) Graph.java 
3) Vetex.java 
4) Edge.java
5) Heap.java

# Main.java: 
Main.java is a file from where project implementation starts. Main.java implements the main method. Main.java contains method generateGraph which takes file name from command prompt and generates the graph. It also contains method performGraphOperation which perform all graph operations which are mentioned for project implementation like addedge, deleteedge, edgedown, edgeup, vertexdown, vertexup, path, print, reachable.

# Graph.java: 
This file implements graph operations. This also contains a Dijkstra’s Algorithm implementation. This class implements all graph operation methods. Graph operation methods are as follows:
1) addEdge(): This method is used to add a new edge in a graph. This method takes three parameters: source vertex, destination vertex, edge cost. If a vertex is not available in a graph then it adds a new vertex and then connects two vertices.
2) deleteEdge(): This method is used to delete an edge from a graph. This method takes two parameters: source vertex, destination vertex.
3) markEdgeDown(): This method is used to mark edge down. This method takes two parameters: source vertex, destination vertex.
4) markEdgeUp(): This method is used to mark edge down and is available for use. This method takes two parameters: source vertex, destination vertex.
5) markVertexDown(): This method is used to mark vertex down and is unavailable for use. This method takes one parameter: vertex to mark as down.
6) markVertexUp(): This method is used to mark vertex up and available for use. This method takes one parameter: vertex to mark as up

7) printShortestPath(): This method finds the shortest path between source and destination. This method takes two parameters source and destination vertex.
8) displayGraph(): This method print graph generated from the input file.
9) findReachableVertices(): This method finds reachable vertices.

# Vertex.java: 
This file contains information about vertex such as the name of vertex and details about vertex to which it is connected with an edge. It also contains getter and setter methods.

# Edge.java: 
This file contains information about an edge such as edge weight and isEdge up or not. It also contains getter and setter methods.

# Heap.java: 
This file contains implementation of binary heap which is used for Dijkstra’s algorithm.

# Data Structures Used:
1) List: List is used to store edge details of a vertex. 
2) TreeMap: Treemap is used to store vertex details.

# Time Complexity:
Priority queue is implemented using binary heaps for implementing Dijkstra’s Algorithm.
Running time of this algorithm is O((|V|+|E|)logV). 

# Summary of what works and fails:
1) The input file is expected to be in a specific format. Each line will consist of three words, first vertex name, second vertex name and path cost.
2) Vertex name are case sensitive. If same name is provided with different cases it creates new vertex.
3) Generating graph, printing graph, marking edge up/down, marking vertex up/down, finding the reachable vertex, and finding the minimum path between two vertices works well.
