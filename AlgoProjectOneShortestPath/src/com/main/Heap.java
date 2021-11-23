/*Name: Nehal Kathale
 * Student ID: 801205316
 */
package com.main;


public class Heap {

	public static  void buildHeap(Vertex[] vertexArray, int activeVertexCount) {
		int index = activeVertexCount / 2;
		for (int j = index - 1; j >= 0; j--) {
			minHeapify(vertexArray, j, activeVertexCount);
		}
		
	}
	public static  void minHeapify(Vertex[] vertex, int j, int index) {
		int left, right, smallest;
		left = 2 * j + 1;
		right = 2 * j + 2;
		if (left < index && vertex[left].cost < vertex[j].cost) {
			smallest = left;
		}else
			smallest = j;
		if (right < index && vertex[right].cost < vertex[smallest].cost) {
			smallest = right;
		}
		if (smallest != j) {
			Vertex temp = vertex[smallest];
			Vertex vertex2 = vertex[j];
			vertex2.setPosition(smallest);
			vertex[smallest] = vertex2;
			temp.setPosition(j);
			vertex[j] = temp;
			minHeapify(vertex, smallest, index);
		}
	}
	public static Vertex extractMin(Vertex[] vertexArray, int queueSize) {
		int lastindex = queueSize - 1;
		if (queueSize < 1) {
			System.out.println("Heap Underflow");
			System.exit(0);
		}
		Vertex min = vertexArray[0];
		Vertex ano = vertexArray[lastindex];
		ano.setPosition(0);
		vertexArray[0] = vertexArray[lastindex];
		min.setPosition(lastindex);
		vertexArray[lastindex] = min;
		minHeapify(vertexArray, 0, lastindex);
		return min;
	}
	public static void heapDecreaseKey(Vertex[] vertexArray, int position, Vertex destinationVertex) {
		vertexArray[position] = destinationVertex;
		while (position > 0 && vertexArray[(position - 1) / 2].cost > vertexArray[position].cost) {
			Vertex temp = vertexArray[(position - 1) / 2];
			Vertex temp2 = vertexArray[position];
			temp.setPosition(position);
			vertexArray[position] = temp;
			temp2.setPosition((position - 1) / 2);
			vertexArray[(position - 1) / 2] = temp2;
			position = (position - 1) / 2;
		}
		
	}

}
