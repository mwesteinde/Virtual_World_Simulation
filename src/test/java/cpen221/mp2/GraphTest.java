package cpen221.mp2;

import cpen221.mp2.graph.Edge;
import cpen221.mp2.graph.Graph;
import cpen221.mp2.graph.Vertex;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void testCreateGraph() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);

        assertEquals(e2, g.getEdge(v2, v3));
        assertEquals(21, g.shortestPath(v2, v2));
    }
    @Test
    public void notFinal(){
        List<Vertex> test= new ArrayList<>();
        Vertex tester= new Vertex(1,"hello");
        test.add(tester);
        tester=new Vertex(2,"bye");
        assert(test.get(0).id()==1);
    }
    @Test
    public void notFinal2(){
        List<Edge> test= new ArrayList<>();
        Edge tester= new Edge(new Vertex(1,""),new Vertex(2,""),20);
        test.add(tester);
        assert(test.contains(new Edge(new Vertex(1,""),new Vertex(2,""),10)));


    }
    @Test
    public void graphBasic(){
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();

        g.addVertex(v1);

        assertFalse(g.addVertex(v1));
        assertTrue(g.vertex(v1));
        assertFalse(g.vertex(v2));

        g.addVertex(v2);
        assertTrue(g.addEdge(e1));
        assertFalse(g.addEdge(e1));
        assertFalse(g.addEdge(e2));

        assertTrue(g.edge(e1));
        assertFalse(g.edge(e2));

        assertTrue(g.edge(v1,v2));
        assertFalse(g.edge(v1,v3));

        assertEquals(g.edgeLength(v1,v2),5);


        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        e1=new Edge<>(v3,v1,2);
        assertEquals(g.edgeLength(v1,v4),9);

    }




}
