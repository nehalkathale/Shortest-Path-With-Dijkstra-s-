/*Name: Nehal Kathale
 * Student ID: 801205316
 * 
 */
package com.main;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class Graph {

	public static final double INFINITY = Double.MAX_VALUE;
	private Map<String, Vertex> vertexDetailsMap = new TreeMap<String, Vertex>();

	public Vertex getVertex(String name, boolean status) {
		Vertex vertex = vertexDetailsMap.get(name);

		if (vertex == null) {
			vertex = new Vertex(name, status);
			vertexDetailsMap.put(name, vertex);
		}
		return vertex;
	}

	public void generateGraph(String sourceVertex, String destinationVertex, double cost, boolean isUp) {
		Vertex source = getVertex(sourceVertex, isUp);
		Vertex destination = getVertex(destinationVertex, isUp);
		source.adjacentEdgeDetails.add(new Edge(source, destination, cost, isUp));
		destination.adjacentEdgeDetails.add(new Edge(destination, source, cost, isUp));
	}

	public void addEdge(String firstVertex, String secondVertex, Double pathCost,boolean isEdgeUp) {
		Vertex source = getVertex(firstVertex, isEdgeUp);
		Vertex destination = getVertex(secondVertex, isEdgeUp);
		Iterator iterator = source.adjacentEdgeDetails.listIterator();
		while (iterator.hasNext()) {
			Edge edge = (Edge) iterator.next();
			if (secondVertex.equals(edge.getDestination())) {
				iterator.remove();
			}
		}
		source.adjacentEdgeDetails.add(new Edge(source, destination, pathCost, isEdgeUp));

	}

	public void displayGraph() {
		for (Vertex vertex : vertexDetailsMap.values()) {
			Comparator<Edge> comparator=new Comparator<Edge>(){
				public int compare(Edge edge1, Edge edge2) {
					int c = edge1.getDestination().compareTo(edge2.getDestination());
					return c;
				}
			};
			Collections.sort(vertex.adjacentEdgeDetails, comparator);
		}
		for (Vertex vertex : vertexDetailsMap.values()) {
			if (vertex.isVertexUp) {
				System.out.println(vertex.vertexName);
			} else {
				System.out.println(vertex.vertexName + " " + "DOWN");
			}
			Iterator iterator = vertex.adjacentEdgeDetails.listIterator();
			while (iterator.hasNext()) {
				Edge edge = new Edge();
				edge = (Edge) iterator.next();
				String destination = edge.getDestination();
				double pathCost = edge.getPathCost();
				if (edge.isEdgeUp) {
					System.out.println(" " + destination + " " + pathCost);
				} else {
					System.out.println(" " + destination + " " + pathCost + " " + "DOWN");
				}
			}
		}
	}

	public void deletEdge(String firstVertex, String secondVertex) {
		Vertex source = vertexDetailsMap.get(firstVertex);
		if(null == source) {
			throw new NoSuchElementException("Invalid vertices");
		}
		Iterator iterator = source.adjacentEdgeDetails.listIterator();
		while (iterator.hasNext()) {
			Edge edge = (Edge) iterator.next();
			if (secondVertex.equals(edge.getDestination())) {
				iterator.remove();
			}
		}
		
	}

	public void markEdgeUp(String firstVertex, String secondVertex) {
		Vertex vertex = vertexDetailsMap.get(firstVertex);
		if(null == vertex) {
			throw new NoSuchElementException("Invalid vertices");
		}
		Iterator iterator = vertex.adjacentEdgeDetails.listIterator();
		while (iterator.hasNext()) {
			Edge edge = new Edge();
			edge = (Edge) iterator.next();
			if (secondVertex.equals(edge.getDestination())) {
				edge.setEdgeUp(true);
			}
		}
		
	}

	public void markEdgeDown(String firstVertex, String secondVertex) {
		Vertex vertex = vertexDetailsMap.get(firstVertex);
		if(null == vertex) {
			throw new NoSuchElementException("Source or destination not found");
		}
		Iterator iterator = vertex.adjacentEdgeDetails.listIterator();
		while (iterator.hasNext()) {
			Edge edge = new Edge();
			edge = (Edge) iterator.next();
			if (secondVertex.equals(edge.getDestination())) {
				edge.setEdgeUp(false);
			}
		}
		
	}

	public void markVertexUp(String vertexName) {
		Vertex vertex = vertexDetailsMap.get(vertexName);
		if(null == vertex) {
			throw new NoSuchElementException("Invalid vertex");
		}else
			vertex.setVertexUp(true);
		
	}

	public void markVertexDown(String vertexName) {
		Vertex vertex = vertexDetailsMap.get(vertexName);
		if(null == vertex) {
			throw new NoSuchElementException("Invalid vertex");
		}else
			vertex.setVertexUp(false);
		
	}
	
	public void clearData() {
		for (Vertex vertex : vertexDetailsMap.values())
			vertex.reset();
	}

	public void findShortestPath(String firstVertex, String secondVertex) {
		if(!vertexDetailsMap.containsKey(firstVertex) || !vertexDetailsMap.containsKey(secondVertex)) {
			throw new NoSuchElementException( "Invalid source or destination" );
		}
		clearData();
		int activeVertexCount = 0;
		for (Vertex vertex : vertexDetailsMap.values()) {
			if (vertex.isVertexUp) {
				activeVertexCount++;
			}
		}
		Vertex sourceVertex = vertexDetailsMap.get(firstVertex);
		sourceVertex.cost = 0;
		sourceVertex.previous = null;
		Vertex[] vertexArray = new Vertex[activeVertexCount];
		int i = 0;
		for (Vertex vertex : vertexDetailsMap.values()) {
			if (vertex.isVertexUp()) {
				vertexArray[i] = vertex;
				vertex.setPosition(i);
				i++;
			}
		}
		Heap.buildHeap(vertexArray,activeVertexCount);
		int queueSize = activeVertexCount;
		while (queueSize != 0) {
			Vertex vertex = null;
			try {
				vertex = Heap.extractMin(vertexArray, queueSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			queueSize--;
			Iterator iterator = vertex.getAdjacentEdgeDetails().iterator();
			while (iterator.hasNext()) {
				Edge edge = (Edge) iterator.next();
				if (edge.isEdgeUp()) {
					String destinationVertexName = edge.getDestination();
					Vertex destinationVertex = vertexDetailsMap.get(destinationVertexName);
					if (destinationVertex.isVertexUp()) {
						if (destinationVertex.cost > (vertex.cost + edge.getPathCost())) {
							double weight = vertex.cost + edge.getPathCost();
							destinationVertex.cost = weight;
							destinationVertex.setPrevious(vertex);
							int position = destinationVertex.getPosition();
							Heap.heapDecreaseKey(vertexArray, position, destinationVertex);
						}
					}
				}
			}
		}
		double pathCost =  vertexDetailsMap.get(secondVertex).cost;
		printPath(secondVertex);
		DecimalFormat format = new DecimalFormat("##.00");
		System.out.println(" " + format.format(pathCost));
	}
	private void printPath(String desti) {
		Vertex dest = vertexDetailsMap.get(desti);
		if (dest.previous != null) {
			printPath(dest.previous.vertexName);
		}
		System.out.print(dest.vertexName+" ");
	}

	private void clearAll() {
		for (Vertex v : vertexDetailsMap.values())
			v.reset();
	}
	
	public void reachable() {
		for (Vertex vertex : vertexDetailsMap.values()) {
			if (vertex.isVertexUp()) {
				System.out.println(vertex.vertexName);
				breadthFirstSearch(vertex);
			}
		}
	}

	public void breadthFirstSearch(Vertex vertex) {
		Map<String, Vertex> treemap = new TreeMap<String, Vertex>();
		clearAll();
		vertex.setColor("Gray");
		vertex.setCost(0);
		vertex.setPrevious(null);
		PriorityQueue<String> queue = new PriorityQueue<String>();
		queue.add(vertex.vertexName);
		while (!queue.isEmpty()) {
			String vertexStr = queue.remove();
			Vertex vertexV = vertexDetailsMap.get(vertexStr);
			Iterator iterator = vertexV.adjacentEdgeDetails.listIterator();
			while (iterator.hasNext()) {
				Edge edge = (Edge) iterator.next();
				Vertex ver = vertexDetailsMap.get(edge.getDestination());
				if (!ver.vertexName.equals(vertexV.vertexName) && ver.isVertexUp) {
					if (ver.getColor().equals("White")) {
						ver.setColor("Gray");
						ver.cost = vertexV.getCost() + 1.0;
						ver.setPrevious(vertexV);
						queue.add(ver.vertexName);
						treemap.put(ver.vertexName, ver);
					}
				}
			}
			vertexV.setColor("Black");
		}
		for (Vertex ver : treemap.values()) {
			System.out.println("  " + ver.vertexName);
		}
	}

}
