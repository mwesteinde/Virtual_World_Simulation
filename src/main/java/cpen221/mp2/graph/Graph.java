package cpen221.mp2.graph;

import java.util.*;

/**
 * Represents a graph with vertices of type V.
 *
 * @param <V> represents a vertex type
 */
public class Graph<V extends Vertex, E extends Edge<V>> implements ImGraph<V, E>, IGraph<V, E> {
    Set<V> vertices= new HashSet<>();
    Set<E> edges = new HashSet<>();

    // TODO: Implement this type
    //Dante


    /**
     * Add a vertex to the graph
     *
     * @param v vertex to add
     * @return true if the vertex was added successfully and false otherwise
     */
    @Override
    public boolean addVertex(V v) {
        if(vertices.contains(v)||v.equals(null)){
            return false;
        }
        vertices.add((V)(new Vertex(v.id(),v.name())));
        return true;
    }

    /**
     * Check if a vertex is part of the graph
     *
     * @param v vertex to check in the graph
     * @return true of v is part of the graph and false otherwise
     */
    @Override
    public boolean vertex(V v) {
        if(vertices.contains(v)){
            return true;
        }
        return false;
    }

    /**
     * Add an edge of the graph
     *
     * @param e the edge to add to the graph
     * @return true if the edge was successfully added and false otherwise
     */

    @Override
    public boolean addEdge(E e) {

        if(edges.contains(e)||e.equals(null)){
            return false;
        }
        if(vertices.contains(e.v1())&&vertices.contains(e.v2())) {
            edges.add((E)(new Edge(e.v1(),e.v2(),e.length())));
            return true;
        }
        return false;
    }


    /**
     * Check if an edge is part of the graph
     *
     * @param e the edge to check in the graph
     * @return true if e is an edge in the graoh and false otherwise
     */
    @Override
    public boolean edge(E e) {
        if(edges.contains(e)){
            return true;
        }
        return false;
    }

    /**
     * Check if v1-v2 is an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge
     * @return true of the v1-v2 edge is part of the graph and false otherwise
     */
    @Override
    public boolean edge(V v1, V v2) {
      if(edges.contains(new Edge(v1,v2))){
          return true;
      }
      return false;
    }

    /**
     * Determine the length on an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge
     * @return the length of the v1-v2 edge if this edge is part of the graph
     */
    @Override
    public int edgeLength(V v1, V v2) {
        if(edge(v1,v2)){
            Set<E> neigh = allEdges(v1);
            for(Edge i:neigh){
                if(i.v2().equals(v2)){
                   return i.length();
                }
            }
        }
        throw new noEdgeFoundException();
    }

    /**
     * Obtain the sum of the lengths of all edges in the graph
     *
     * @return the sum of the lengths of all edges in the graph
     */
    @Override
    public int edgeLengthSum() {
        int total=0;
        for(Edge i:edges){
            total+=i.length();
        }
        return total;
    }

    /**
     * Remove an edge from the graph
     *
     * @param e the edge to remove
     * @return true if e was successfully removed and false otherwise
     */

    @Override
    public boolean remove(E e) {
        if(edges.contains(e)){
            edges.remove(e);
            return true;
        }
        return false;
    }

    /**
     * Remove a vertex from the graph
     *
     * @param v the vertex to remove
     * @return true if v was successfully removed and false otherwise
     */

    @Override
    public boolean remove(V v) {
        if(vertices.contains(v)){
            vertices.remove(v);
            return true;
        }
        return false;
    }

    /**
     * Obtain a set of all vertices in the graph.
     * Access to this set **should not** permit graph mutations.
     *
     * @return a set of all vertices in the graph
     */
    @Override
    public Set<V> allVertices() {
        Set<V> all=new HashSet<V>();
        for(V i:vertices){
            all.add(i);
        }
        return all;
    }

    /**
     * Obtain a set of all edges incident on v.
     * Access to this set **should not** permit graph mutations.
     *
     * @param v the vertex of interest
     * @return all edges incident on v
     */

    @Override
    public Set<E> allEdges(V v) {
        Set<E> onv=new HashSet<E>();
        for(E i:edges){
            if(i.v1().equals(v)||i.v2().equals(v)){
                onv.add(i);
            }
        }
        return onv;
    }

    /**
     * Obtain a set of all edges in the graph.
     * Access to this set **should not** permit graph mutations.
     *
     * @return all edges in the graph
     */

    @Override
    public Set<E> allEdges() {
        Set<E> all=new HashSet<E>();
        for(E i:edges){
            all.add(i);
        }
        return all;
    }


    /**
     * Obtain all the neighbours of vertex v.
     * Access to this map **should not** permit graph mutations.
     *
     * @param v is the vertex whose neighbourhood we want.
     * @return a map containing each vertex w that neighbors v and the edge between v and w.
     */

    @Override
    public Map<V, E> getNeighbours(V v) {
        Map<V, E> neigh = new HashMap<>();
        Set<E> edges = allEdges(v);
        for (Edge<V> i : edges) {
            if (i.v1().equals(v)) {
                neigh.put(i.v2(), (E) i);
            }
            if (i.v2().equals(v)) {
                neigh.put(i.v1(), (E) i);
            }

        }
        assert (neigh.size() == edges.size());
        return neigh;
    }



    /**
     * Find the edge that connects two vertices if such an edge exists.
     * This method should not permit graph mutations.
     *
     * @param v1 one end of the edge
     * @param v2 the other end of the edge
     * @return the edge connecting v1 and v2
     * @throws noEdgeFoundException if no edge exists
     */

    @Override
    public E getEdge(V v1, V v2) {
        Edge<V> lookingFor = new Edge<V>(v1,v2);
        Set<E> neigh = allEdges(v1);
        for(Edge i:neigh){
            if(i.v2().equals(v2)){
                return (E)i;
            }

        }
        throw new noEdgeFoundException();
    }

    /**
     * Compute the length of a given path
     *
     * @param path indicates vertices on a existing given path
     * @return the length of path
     */

    @Override
    public int pathLength(List<V> path) {
        int plength=0;
        for(int i=0;i<path.size()-1;i++){
            plength+=edgeLength(path.get(i),path.get(i+1));
        }
        return plength;
    }


//end of Dante's section

    @Override
    public List<V> shortestPath(V source, V sink) {
        return null;
    }

    @Override
    public List<E> minimumSpanningTree() {
        return null;
    }

    @Override
    public int diameter() {
        return 0;
    }

    @Override
    public Set<V> search(V v, int range) {
        return null;
    }



    public boolean repInvarient(){
        return true;
        //all edges correlate to two verticies

    }



    //// add all new code above this line ////

    /**
     * This method removes some edges at random while preserving connectivity
     * <p>
     * DO NOT CHANGE THIS METHOD
     * </p>
     * <p>
     * You will need to implement allVertices() and allEdges(V v) for this
     * method to run correctly
     *</p>
     * <p><strong>requires:</strong> this graph is connected</p>
     *
     * @param rng random number generator to select edges at random
     */
    public void pruneRandomEdges(Random rng) {
        class VEPair {
            V v;
            E e;

            public VEPair(V v, E e) {
                this.v = v;
                this.e = e;
            }
        }
        /* Visited Nodes */
        Set<V> visited = new HashSet<>();
        /* Nodes to visit and the cpen221.mp2.graph.Edge used to reach them */
        Deque<VEPair> stack = new LinkedList<VEPair>();
        /* Edges that could be removed */
        ArrayList<E> candidates = new ArrayList<>();
        /* Edges that must be kept to maintain connectivity */
        Set<E> keep = new HashSet<>();

        V start = null;
        for (V v : this.allVertices()) {
            start = v;
            break;
        }
        if (start == null) {
            // nothing to do
            return;
        }
        stack.push(new VEPair(start, null));
        while (!stack.isEmpty()) {
            VEPair pair = stack.pop();
            if (visited.add(pair.v)) {
                keep.add(pair.e);
                for (E e : this.allEdges(pair.v)) {
                    stack.push(new VEPair(e.distinctVertex(pair.v), e));
                }
            } else if (!keep.contains(pair.e)) {
                candidates.add(pair.e);
            }
        }
        // randomly trim some candidate edges
        int iterations = rng.nextInt(candidates.size());
        for (int count = 0; count < iterations; ++count) {
            int end = candidates.size() - 1;
            int index = rng.nextInt(candidates.size());
            E trim = candidates.get(index);
            candidates.set(index, candidates.get(end));
            candidates.remove(end);
            remove(trim);
        }
    }



//
}
