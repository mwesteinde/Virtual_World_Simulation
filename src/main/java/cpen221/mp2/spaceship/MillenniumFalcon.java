package cpen221.mp2.spaceship;

import cpen221.mp2.controllers.GathererStage;
import cpen221.mp2.controllers.HunterStage;
import cpen221.mp2.controllers.Spaceship;
import cpen221.mp2.graph.Edge;
import cpen221.mp2.graph.Graph;
import cpen221.mp2.graph.ImGraph;
import cpen221.mp2.graph.Vertex;
import cpen221.mp2.models.Link;
import cpen221.mp2.models.Planet;
import cpen221.mp2.models.PlanetStatus;
import cpen221.mp2.util.Heap;
import javafx.util.Pair;

import java.util.*;

/**
 * An instance implements the methods needed to complete the mission.
 */
public class MillenniumFalcon implements Spaceship {
    long startTime = System.nanoTime(); // start time of rescue phase


    /**
     * The spaceship is on the location given by parameter state.
     * Move the spaceship to Kamino and then return.
     * This completes the first phase of the mission.<br><br>
     * <p>
     * If the spaceship continues to move after reaching Kamino, rather than
     * returning, it will not count. A return from this procedure while
     * not on Kamino count as a failure.<br><br>
     * <p>
     * There is no limit to how many steps you can take, but the score is
     * directly related to how long it takes you to find Kamino.<br><br>
     * <p>
     * At every step, you know only the current planet's ID, the IDs of
     * neighboring planets, and the strength of the signal from Kamino
     * at each planet.<br><br>
     * <p>
     * In this stage of the game,<br>
     * (1) In order to get information about the current state, use
     * functions currentID(), neighbors(), and signal().<br><br>
     * <p>
     * (2) Use method onKamino() to know if your ship is on Kamino.<br><br>
     * <p>
     * (3) Use method moveTo(int id) to move to a neighboring planet
     * with the given ID. Doing this will change state to reflect the
     * ship's new position.
     */
    @Override
    public void hunt(HunterStage state) {
        Graph<Vertex, Edge<Vertex>> uni=new Graph();
        uni.addVertex(new Vertex(state.currentID(),"void"));
        int earthID=state.currentID();
        Set<PlanetStatus> unvisited=new HashSet<>();
        Set<PlanetStatus> visited=new HashSet<>();
        Set<Vertex> unvisitedG=new HashSet<>();
        Set<Vertex> visitedG=new HashSet<>();

        while(!state.onKamino()){
            newNeigh(unvisited,unvisitedG,visited,visitedG,uni,state.neighbors(),state.currentID(),earthID);
            int closestID=findNext(unvisited, visited,unvisitedG, visitedG,uni);
            List<Vertex> path= uni.shortestPath(new Vertex(state.currentID(),"void"),new Vertex(closestID,"void"));
            path.remove(0);
            for(Vertex i:path){
                state.moveTo(i.id());
            }
        }

        return;
    }
    private void newNeigh(Set<PlanetStatus> unvisited,Set<Vertex> unvisitedG,Set<PlanetStatus> visited,Set<Vertex> visitedG,Graph uni,PlanetStatus[] neighbors,int currentID,int earthID){
        for (PlanetStatus i : neighbors) {
            if (!visited.contains(i)) {
                unvisited.add(i);
                unvisitedG.add(new Vertex(i.id(), "void"));
                uni.addVertex(new Vertex(i.id(), "void"));
                uni.addEdge(new Edge(new Vertex(i.id(), "void"),new Vertex(currentID, "void")));

                if (i.id() == earthID&&unvisited.contains(new Vertex(earthID,"void"))) {
                    visited.add(i);
                    visitedG.add(new Vertex(i.id(),"void"));
                    unvisited.remove(i);
                    unvisitedG.remove(new Vertex(i.id(),"void"));
                }


            }
        }
    }
    private int findNext(Set<PlanetStatus> unvisited,Set<PlanetStatus> visited,Set<Vertex> unvisitedG,Set<Vertex> visitedG,Graph uni){
        double closestSig = -1;
        int closestID = -1;

        for (PlanetStatus i : unvisited) {
            if (i.signal() > closestSig) {
                closestID = i.id();
                closestSig = i.signal();
            }
        }
        assert (closestID != -1);
        for (PlanetStatus i : unvisited) {
            if (i.id() == closestID) {
                unvisited.remove(i);
                unvisitedG.remove(new Vertex(i.id(), "void"));
                visited.add(i);
                visitedG.add(new Vertex(i.id(), "void"));
                break;
            }
        }
        return closestID;
    }


    /**
     * The spaceship is on the location given by state. Get back to Earth
     * without running out of fuel and return while on Earth. Your ship can
     * determine how much fuel it has left via method fuelRemaining(), and how
     * much fuel is needed to travel on a link via link's fuelNeeded().<br><br>
     * <p>
     * Each Planet has some spice. Moving to a Planet automatically
     * collects any spice it carries, which increases your score. your
     * objective is to return to earth with as much spice as possible.<br><br>
     * <p>
     * You now have access to the entire underlying graph, which can be
     * accessed through parameter state. currentNode() and earth() return
     * planets of interest, and planets() returns a collection of
     * all planets in the graph.<br><br>
     * <p>
     * Note: Use moveTo() to move to a destination node adjacent to
     * your ship's current node.
     */
    @Override
    public void gather(GathererStage state) {

        //pretty sure we want to hit as many planets as possible on the way back to earth. I think this is a variation of knapsack recursion question
        //same as before, use GathererStage interface methods to get to earth while reaching as many planets as possible


    }

}
