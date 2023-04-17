package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class BFS implements IPathfinding  {
    public ArrayList<Node> run(Node startNode, Node endNode) {
        Graph graph = startNode.getGraph();
        Set<Node> visited = new HashSet<Node>();
        LinkedList<Node> queue = new LinkedList<Node>();
        visited.add(startNode);
        queue.add(startNode);
        ArrayList<Node> path = new ArrayList<>();

        while (queue.size() != 0) {

            startNode = queue.poll();
            System.out.print(startNode.getNodeID() + " ");
            path.add(startNode);

            if (startNode.getNodeID() == endNode.getNodeID()) {
                break;
            }

            // Enqueue all adjacent nodes of the dequeued node that have not been visited yet
            for (Edge edge : startNode.getEdges()) {
                Node adjNode = edge.getEndNode();
                if (!visited.contains(adjNode)) {
                    visited.add(adjNode);
                    queue.add(adjNode);
                }
            }
        }
        return path;
    }
}
