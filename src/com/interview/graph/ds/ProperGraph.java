package com.interview.graph.ds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProperGraph<T> {
	private List<Edge<T>> allEdges;
	private Map<Long, Vertex<T>> allVertex;
	boolean isDirected = false;

	public ProperGraph(boolean isDirected) {
		allEdges = new ArrayList<Edge<T>>();
		allVertex = new HashMap<Long, Vertex<T>>();
		this.isDirected = isDirected;
	}

	public void addEdge(long id1, long id2) {
		addEdge(id1, id2, 0);
	}

	// This works only for directed graph because for undirected graph we can end up
	// adding edges two times to allEdges
	public void addVertex(Vertex<T> vertex) {
		if (allVertex.containsKey(vertex.getId())) {
			return;
		}
		allVertex.put(vertex.getId(), vertex);
		for (Edge<T> edge : vertex.getEdges()) {
			allEdges.add(edge);
		}
	}

	public Vertex<T> addSingleVertex(long id) {
		if (allVertex.containsKey(id)) {
			return allVertex.get(id);
		}
		Vertex<T> v = new Vertex<T>(id);
		allVertex.put(id, v);
		return v;
	}

	public Vertex<T> getVertex(long id) {
		return allVertex.get(id);
	}

	public void addEdge(long id1, long id2, int weight) {
		Vertex<T> vertex1 = null;
		if (allVertex.containsKey(id1)) {
			vertex1 = allVertex.get(id1);
		} else {
			vertex1 = new Vertex<T>(id1);
			allVertex.put(id1, vertex1);
		}
		Vertex<T> vertex2 = null;
		if (allVertex.containsKey(id2)) {
			vertex2 = allVertex.get(id2);
		} else {
			vertex2 = new Vertex<T>(id2);
			allVertex.put(id2, vertex2);
		}

		Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight);
		allEdges.add(edge);
		vertex1.addAdjacentVertex(edge, vertex2);
		if (!isDirected) {
			vertex2.addAdjacentVertex(edge, vertex1);
		}

	}

	public List<Edge<T>> getAllEdges() {
		return allEdges;
	}

	public Collection<Vertex<T>> getAllVertex() {
		return allVertex.values();
	}

	public void setDataForVertex(long id, T data) {
		if (allVertex.containsKey(id)) {
			Vertex<T> vertex = allVertex.get(id);
			vertex.setData(data);
		}
	}
}

class Vertex<T> {
	long id;
	private T data;
	private List<Edge<T>> edges = new ArrayList<>();
	private List<Vertex<T>> adjacentVertex = new ArrayList<>();

	Vertex(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void addAdjacentVertex(Edge<T> e, Vertex<T> v) {
		edges.add(e);
		adjacentVertex.add(v);
	}

	public String toString() {
		return String.valueOf(id);
	}

	public List<Vertex<T>> getAdjacentVertexes() {
		return adjacentVertex;
	}

	public List<Edge<T>> getEdges() {
		return edges;
	}

	public int getDegree() {
		return edges.size();
	}
}

class Edge<T> {
	private boolean isDirected = false;
	private Vertex<T> vertex1;
	private Vertex<T> vertex2;
	private int weight;

	Edge(Vertex<T> vertex1, Vertex<T> vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}

	Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected, int weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
		this.isDirected = isDirected;
	}

	Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.isDirected = isDirected;
	}

	Vertex<T> getVertex1() {
		return vertex1;
	}

	Vertex<T> getVertex2() {
		return vertex2;
	}

	int getWeight() {
		return weight;
	}

	public boolean isDirected() {
		return isDirected;
	}

}
