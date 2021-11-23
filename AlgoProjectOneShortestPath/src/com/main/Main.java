/*Name: Nehal Kathale
 * Student ID: 801205316
 */
package com.main;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		Graph graph = new Graph();
		String sourceAndDestinationDetails[] = null;
		try {
			FileReader fileReader = new FileReader(args[0]);
			try (Scanner graphFile = new Scanner(fileReader);) {
				String query = null;
				while (graphFile.hasNextLine()) {
					query = graphFile.nextLine();
					sourceAndDestinationDetails = query.split(" ");
					graph.generateGraph(sourceAndDestinationDetails[0],sourceAndDestinationDetails[1],Double.parseDouble(sourceAndDestinationDetails[2]),true);
				}
			}
			Scanner scanner = new Scanner(System.in);
			while(performGraphOperation(scanner,graph));
			scanner.close();
		} catch (IOException e) {
			System.err.println("File " + args[0] + " Not Found. End of Program");
		}
	}

	private static boolean performGraphOperation(Scanner scanner, Graph graph) {
		System.out.println("Enter operation you want to perform: ");
		try {
			switch (scanner.next()) {
			case "addedge":
				addEdge(scanner, graph);
				break;
			case "print":
				graph.displayGraph();
				break;
			case "quit":
				System.exit(0);
				return false;
			case "deleteedge":
				deleteEdge(scanner, graph);
				break;
			case "edgedown":
				markEdgeDown(scanner, graph);
				break;
			case "edgeup":
				markEdgeUp(scanner, graph);
				break;
			case "vertexdown":
				markVertexDown(scanner, graph);
				break;
			case "vertexup":
				markVertexUp(scanner, graph);
				break;
			case "path":
				computeShortestPath(scanner, graph);
				break;			
			case "reachable":
				graph.reachable();
				break;			
			default:
				System.out.println("Please enter valid option");
				break;
			}
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return true;
		}		
	}

	private static void addEdge(Scanner scanner, Graph graph) {
		System.out.println("Enter first vertex: ");
		String firstVertex = scanner.next();
		System.out.println("Enter second vertex: ");
		String secondVertex = scanner.next();
		System.out.println("Enter Path cost: ");
		String pathCost = scanner.next();
		graph.addEdge(firstVertex,secondVertex,Double.parseDouble(pathCost),true);
	}
	private static void deleteEdge(Scanner scanner, Graph graph) {
		System.out.println("Enter first vertex: ");
		String firstVertex = scanner.next();
		System.out.println("Enter second vertex: ");
		String secondVertex = scanner.next();
		graph.deletEdge(firstVertex,secondVertex);
	}
	private static void markEdgeUp(Scanner scanner, Graph graph) {
		System.out.println("Enter first vertex: ");
		String firstVertex = scanner.next();
		System.out.println("Enter second vertex: ");
		String secondVertex = scanner.next();
		graph.markEdgeUp(firstVertex,secondVertex);
		
	}
	private static void markEdgeDown(Scanner scanner, Graph graph) {
		System.out.println("Enter first vertex: ");
		String firstVertex = scanner.next();
		System.out.println("Enter second vertex: ");
		String secondVertex = scanner.next();
		graph.markEdgeDown(firstVertex,secondVertex);
		
	}
	private static void markVertexUp(Scanner scanner, Graph graph) {
		System.out.println("Enter vertex name: ");
		String vertexName = scanner.next();
		graph.markVertexUp(vertexName);
		
	}

	private static void markVertexDown(Scanner scanner, Graph graph) {
		System.out.println("Enter vertex name: ");
		String vertexName = scanner.next();
		graph.markVertexDown(vertexName);
		
	}
	private static void computeShortestPath(Scanner scanner, Graph graph) {
		System.out.println("Enter source vertex: ");
		String firstVertex = scanner.next();
		System.out.println("Enter destination vertex: ");
		String secondVertex = scanner.next();
		graph.findShortestPath(firstVertex,secondVertex);
	}
}

