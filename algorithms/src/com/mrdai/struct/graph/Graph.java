package com.mrdai.struct.graph;

import java.util.LinkedList;

/**
 * Data structure for a sparse undirected graph with given number of vertices.
 */
public class Graph {
    private final LinkedList<Integer>[] edges;
    private int edgeNum = 0;

    /**
     * Creates a {@code Graph} with the given number of vertices.
     *
     * @param V the given number of vertices.
     */
    @SuppressWarnings("unchecked")
    public Graph(int V) {
        edges = new LinkedList[V];
        for (int i = 0; i < edges.length; i++)
            edges[i] = new LinkedList<>();
    }

    /**
     * Returns the number of vertices in this {@code Graph}.
     *
     * @return the number of vertices in this {@code Graph}.
     */
    public int V() {
        return edges.length;
    }

    /**
     * Returns the number of edges in this {@code Graph}.
     *
     * @return the number of edges in this {@code Graph}.
     */
    public int E() {
        return edgeNum;
    }

    /**
     * Adds an undirected edge to the {@code Graph} connecting vertex {@code v} and {@code w}.
     *
     * @param v the vertex connected by this edge.
     * @param w the another vertex connected by this edge.
     */
    public void addEdge(int v, int w) {
        edges[v].add(w);
        edges[w].add(v);
        edgeNum++;
    }

    /**
     * Returns all vertices that are adjacent to the given vertex {@code v}.
     *
     * @param v the given vertex.
     * @return all vertices that are adjacent to the given vertex.
     */
    public Iterable<Integer> adj(int v) {
        return edges[v];
    }

}
