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
     * uses the Kamino signal to move between planets until it reaches Kamino
     * @param state HunterStage starting location is earth and there is a path to Kamino
     * @return when the ship in on Kamino, as indicated by state.onKamino()
     * @modifies state by moving to Kamino
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
            int closestID=findNext(unvisited, visited,unvisitedG, visitedG,uni,state.currentID());
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

                if (i.id() == earthID && unvisited.contains(new Vertex(earthID,"void"))) {
                    visited.add(i);
                    visitedG.add(new Vertex(i.id(),"void"));
                    unvisited.remove(i);
                    unvisitedG.remove(new Vertex(i.id(),"void"));
                }


            }
        }
    }

    private int findNext(Set<PlanetStatus> unvisited,Set<PlanetStatus> visited,Set<Vertex> unvisitedG,Set<Vertex> visitedG,Graph uni,int currentID){
        double closestSig = -100;
        int closestID = -1;
        double tuningConstant=0.05;
        for (PlanetStatus i : unvisited) {
            if (i.signal()-tuningConstant*uni.pathLength(uni.shortestPath(new Vertex(currentID,"void"),new Vertex(i.id(),"void"))) > closestSig) {
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
        ImGraph<Planet, Link> planetGraph = state.planetGraph();
        Set <Link> edges = planetGraph.allEdges();
        Set <Planet> vertices = planetGraph.allVertices();
        List <Planet> pathHome = new ArrayList<>();

        pathHome.add(state.earth());

       // int spiceTotal = spiceSum(state.planetGraph().shortestPath(state.currentPlanet(), state.earth()));
        int spiceShortestPath = spiceSum(state.planetGraph().shortestPath(state.currentPlanet(), state.earth()));

        returnToEarth(findOptimalPath(state.currentPlanet(), state.earth(), planetGraph,0, 0, state, pathHome, spiceShortestPath), state);

        //pretty sure we want to hit as many planets as possible on the way back to earth. I think this is a variation of knapsack recursion question
        //same as before, use GathererStage interface methods to get to earth while reaching as many planets as possible


    }

    private List<Planet> findOptimalPath(Planet current, Planet earth, ImGraph<Planet, Link> planetGraph, int fuelUsed, int spiceCollected, GathererStage state, List<Planet> path, int spiceSum) {
        List <Planet> pathHome = new ArrayList<>();
        List <Planet> potentialPath = new ArrayList<>();
        Map<Planet,Link> adjacentPlanets = new HashMap<>();

        adjacentPlanets = planetGraph.getNeighbours(current);

        for (Map.Entry<Planet,Link> i: adjacentPlanets.entrySet()) {
            if (i.getKey().equals(earth)) {
                return path;
            }

            if (makeItHome(planetGraph.shortestPath(i.getKey(), earth), planetGraph, state, fuelUsed + i.getValue().length())) {
                if (spiceSum(planetGraph.shortestPath(i.getKey(), earth)) + spiceCollected > spiceSum) {
                    path.add(i.getKey());
                    findOptimalPath(i.getKey(), earth, planetGraph, fuelUsed += i.getValue().length(), spiceCollected += i.getKey().spice(), state, path, spiceSum(planetGraph.shortestPath(i.getKey(), earth)) + spiceCollected);
                }
            }
        }

        return pathHome;
    }

    private void returnToEarth(List<Planet> pathHome, GathererStage state) {
        for (Planet i: pathHome) {
            state.moveTo(i);
        }
    }

    private int spiceSum(List<Planet> planetsVisited) {
        int sum = 0;

        for (Planet i: planetsVisited) {
            sum += i.spice();
        }

        return sum;
    }

    private int fuelUsed(List<Planet> planetsVisited, ImGraph<Planet, Link> planetGraph) {
        return planetGraph.pathLength(planetsVisited);
    }

    private boolean makeItHome(List<Planet> planetsVisited, ImGraph<Planet, Link> planetGraph, GathererStage state, int fuelUsed) {
        if (planetGraph.pathLength(planetsVisited) + fuelUsed < state.fuelRemaining()) {
            return true;
        }

        return false;
    }

}
