package cpen221.mp2;

import cpen221.mp2.graph.Edge;
import cpen221.mp2.graph.Graph;
import cpen221.mp2.graph.Vertex;
import cpen221.mp2.graph.noEdgeFoundException;
import org.junit.Test;

import java.util.*;

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

        List<Vertex> expected = new ArrayList<>();
        expected.add(v3);
        expected.add(v2);
        expected.add(v1);
        expected.add(v4);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);

        assertEquals(e2, g.getEdge(v2, v3));
        assertEquals(expected, g.shortestPath(v3, v4));
        assertEquals(21, g.pathLength(g.shortestPath(v3, v4)));



        assertEquals(21, g.getMST());
    }

    @Test
    public void testCreateGraph2() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 1);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 5);
        Edge<Vertex> e3 = new Edge<>(v1, v3, 7);
        Edge<Vertex> e4 = new Edge<>(v2, v4, 4);
        Edge<Vertex> e5 = new Edge<>(v4, v5, 2);
        Edge<Vertex> e6 = new Edge<>(v5, v3, 6);
        Edge<Vertex> e7 = new Edge<>(v2, v5, 3);


        List<Vertex> expected = new ArrayList<>();
        expected.add(v3);
        expected.add(v2);
        expected.add(v1);
        expected.add(v4);
        expected.add(v5);


        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);

        //assertEquals(e2, g.getEdge(v2, v3));
        //assertEquals(expected, g.shortestPath(v3, v4));
        //assertEquals(21, g.pathLength(g.shortestPath(v3, v4)));



        assertEquals(11, g.getMST());
    }

    @Test
    public void mutator1(){
        List<Vertex> test= new ArrayList<>();
        Vertex tester= new Vertex(1,"hello");
        test.add(tester);
        tester=new Vertex(2,"bye");
        assert(test.get(0).id()==1);
    }

    @Test
    public void mutator2(){
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addEdge(e1);
        e1=new Edge<>(v1,v3, 3);
        assertFalse(g.edge(v1,v3));
        assertTrue(g.edge(v1,v2));


    }

    @Test
    public void graphBasic1(){//coverage from repInv to allEdges
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


        assertEquals(g.edgeLength(v1,v4),9);
        assertEquals(g.edgeLength(v1,v3),Integer.MAX_VALUE);

        assertEquals(g.edgeLengthSum(),21);

        assertTrue(g.remove(e3));
        assertEquals(g.edgeLengthSum(),12);
        assertFalse(g.remove(e3));

        Vertex v5 = new Vertex(5, "E");
        g.addVertex(v5);

        assertTrue(g.remove(v5));
        assertFalse(g.remove(v5));
        Set<Vertex> allVer= new HashSet<>();
        allVer.add(v1);
        allVer.add(v2);
        allVer.add(v4);
        assertFalse(g.allVertices().equals(allVer));

        allVer.add(v3);
        assertTrue(g.allVertices().equals(allVer));

        Set<Vertex> mutator =g.allVertices();
        mutator.remove(v3);
        assertTrue(g.allVertices().equals(allVer));

        Set<Edge<Vertex>> allEdg= new HashSet<>();
        allEdg.add(e1);
        allEdg.add(e2);
        assertTrue(allEdg.equals(g.allEdges()));
        allEdg.add(e3);
        assertNotEquals(allEdg,g.allEdges());

    }

    @Test
    public void graphBasic2() {//coverage from getNeighbors to GetEdge

        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");

        Edge<Vertex> e1 = new Edge<>(v2, v1, 5);
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
        Map<Vertex,Edge<Vertex>> neigh= new HashMap<>();
        neigh.put(v4,e3);
        neigh.put(v2,e1);
        assertTrue(g.getNeighbours(v1).equals(neigh));
        assertTrue(g.getEdge(v1,v2).equals(e1));

        List<Vertex> path=new ArrayList<>();
        path.add(v1);
        path.add(v2);
        path.add(v3);
        assertEquals(g.pathLength(path),12);  //This test seems to fail
        path.add(v4);
        assertEquals(g.pathLength(path),Integer.MAX_VALUE);

        }
        @Test(expected = noEdgeFoundException.class)
    public void except1(){
        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.getEdge(new Vertex(1,"hi"),new Vertex(2,"bye"));
    }

    @Test(expected = noEdgeFoundException.class)
    public void except2(){
        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.edgeLength(new Vertex(1,"hi"),new Vertex(2,"bye"));
    }

    @Test
    public void shortest1(){
        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");

        Edge<Vertex> e1 = new Edge<>(v2, v1, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 1);
        Edge<Vertex> e3 = new Edge<>(v1, v3, 2);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
         List<Vertex> ret=g.shortestPath(v1,v2);

        assertEquals(ret.get(0),new Vertex(1,"A"));
        assertEquals(ret.get(1),new Vertex(3,"C"));

    }

    @Test
    public void minSpan1(){
        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");

        Edge<Vertex> e1 = new Edge<>(v2, v1, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 1);
        Edge<Vertex> e3 = new Edge<>(v1, v3, 2);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        List<Edge<Vertex>> ret=g.minimumSpanningTree();
        assertTrue(ret.contains(e2));
        assertTrue(ret.contains(e3));

    }

    @Test
    public void diam1(){
        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");

        Edge<Vertex> e1 = new Edge<>(v2, v1, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 1);
        Edge<Vertex> e3 = new Edge<>(v1, v3, 2);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        assertEquals(g.diameter(),3);


    }

    @Test
    public void sear1(){
        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");

        Edge<Vertex> e1 = new Edge<>(v2, v1, 5);
        Edge<Vertex> e3 = new Edge<>(v1, v3, 2);
        Edge<Vertex> e4 = new Edge<>(v1, v4, 1);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);

        g.addEdge(e3);
        g.addEdge(e4);
        Set<Vertex> ret=g.search(v1,3);
        assertTrue(ret.contains(v4));
        assertTrue(ret.contains(v3));



    }




}
