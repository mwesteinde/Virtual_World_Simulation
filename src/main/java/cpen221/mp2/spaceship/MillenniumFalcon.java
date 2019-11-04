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
        double closestSig = -100;  //magic numbers
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
        List <Planet> neighborsHome = new ArrayList<>();
        Planet current = state.currentPlanet();
        Map <Planet, Integer> visitedPlanets = new HashMap<>();
        Planet last = null;
        Planet next = null;

        while (makeItHome(current, planetGraph, state, 0)) {
            addVisits(current, visitedPlanets);
            neighborsHome = getAllPoints(planetGraph, current, state);
            next = findNextPlanet(neighborsHome, state, visitedPlanets, last);

            if (next == null && state.currentPlanet().equals(state.earth())) {
                return;
            }

            state.moveTo(next);
            last = current;
            current = next;
        }

    }






        //pretty sure we want to hit as many planets as possible on the way back to earth. I think this is a variation of knapsack recursion question
        //same as before, use GathererStage interface methods to get to earth while reaching as many planets as possible

        //adds times visited per planet to map
    private void addVisits(Planet current, Map <Planet, Integer> visitedPlanets) {
        if (visitedPlanets.containsKey(current)) {
            int value = visitedPlanets.get(current);
            visitedPlanets.put(current, value + 1);
        } else {
            visitedPlanets.put(current, 1);
        }
    }

    //gets all points that will make it home
    private List <Planet> getAllPoints(ImGraph<Planet, Link> planetGraph, Planet current, GathererStage state) {
        Map <Planet, Link> neighbors =  new HashMap<>();
        List <Planet> neighborsHome = new ArrayList<>();

        neighbors = planetGraph.getNeighbours(current);

        for (Map.Entry<Planet, Link> i: neighbors.entrySet()) {
            if (makeItHome(i.getKey(), planetGraph, state, i.getValue().fuelNeeded())) {
                neighborsHome.add(i.getKey());
            }
        }
        return neighborsHome;
    }

    //finds next planet
    private Planet findNextPlanet(List <Planet> neighborsHome, GathererStage state, Map <Planet, Integer> visitedPlanets, Planet last) {
        Planet next = null;
        boolean done1 = true;
        int maxSpice = 0;
        int maxVisits = Integer.MAX_VALUE;

        for (Planet i:neighborsHome) {
            if (i.spice() > maxSpice) {
                next = i;
                maxSpice = i.spice();
                done1 = false;
            } else if (i.spice() == maxSpice && done1) {
                if (!i.equals(last)) {
                    boolean sentinel1 = visitedPlanets.containsKey(i);
                    if (sentinel1) {
                        if (visitedPlanets.get(i) < maxVisits) {
                            next = i;
                            maxSpice = i.spice();
                            maxVisits = visitedPlanets.get(i);
                        }
                    } else {
                        next = i;
                        maxSpice = i.spice();
                    }
                } else if (next == null) {
                    next = i;
                }

            }
        }
        return next;
    }

    private boolean makeItHome(Planet current, ImGraph<Planet, Link> planetGraph, GathererStage state, int size) {
        if (planetGraph.pathLength(planetGraph.shortestPath(current, state.earth())) < state.fuelRemaining() - size) {
            return true;
        }

        return false;
    }

}
