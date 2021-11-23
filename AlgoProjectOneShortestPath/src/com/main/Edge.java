/*Name: Nehal Kathale
 * Student ID: 801205316
 */
package com.main;


public class Edge {

	public Vertex sourceVertex, destinationVertex; 
	public double pathCost; 
	public boolean isEdgeUp;

	public Edge(Vertex source, Vertex destination, double cost, boolean isUp) {
		sourceVertex = source;
		destinationVertex = destination;
		pathCost = cost;
		this.isEdgeUp = isUp;
	}

	public Edge() {
	}	

	public Vertex getSourceVertex() {
		return sourceVertex;
	}

	public void setSourceVertex(Vertex sourceVertex) {
		this.sourceVertex = sourceVertex;
	}

	public Vertex getDestinationVertex() {
		return destinationVertex;
	}

	public void setDestinationVertex(Vertex destinationVertex) {
		this.destinationVertex = destinationVertex;
	}

	public double getPathCost() {
		return pathCost;
	}

	public void setPathCost(double pathCost) {
		this.pathCost = pathCost;
	}

	public boolean isEdgeUp() {
		return isEdgeUp;
	}

	public void setEdgeUp(boolean isEdgeUp) {
		this.isEdgeUp = isEdgeUp;
	}
	public String getSource() {
		return sourceVertex.vertexName;
	}

	public String getDestination() {
		return destinationVertex.vertexName;
	}
}
