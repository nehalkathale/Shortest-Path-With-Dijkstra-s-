/*Name: Nehal Kathale
 * Student ID: 801205316
 */
package com.main;

import java.util.LinkedList;
import java.util.List;


public class Vertex {
	public String vertexName;
	public List<Edge> adjacentEdgeDetails;
	public double cost; 
	public Vertex previous;
	public boolean isVertexUp;
	int position;
	public String color;

	public Vertex(String name, boolean availability) {
		vertexName = name;
		this.isVertexUp = availability;
		adjacentEdgeDetails = new LinkedList();
		reset();
	}
	
	public Vertex() {

	}
	
	public String getVertexName() {
		return vertexName;
	}

	public void setVertexName(String vertexName) {
		this.vertexName = vertexName;
	}

	public List<Edge> getAdjacentEdgeDetails() {
		return adjacentEdgeDetails;
	}

	public void setAdjacentEdgeDetails(List<Edge> adjacent) {
		this.adjacentEdgeDetails = adjacent;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double pathCost) {
		this.cost = pathCost;
	}

	public Vertex getPrevious() {
		return previous;
	}

	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}

	public boolean isVertexUp() {
		return isVertexUp;
	}

	public void setVertexUp(boolean isVertexUp) {
		this.isVertexUp = isVertexUp;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public void reset() {
		cost = Graph.INFINITY;
		previous = null;
		color = "White";
	}
	
	
}
