package edu.wpi.cs3733.D23.teamQ.Pathfinding;

import edu.wpi.cs3733.D23.teamQ.db.obj.Edge;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.*;

public class BFS implements IPathfinding {

  @Override
  public ArrayList<Node> run(Node start, Node target) {
    ArrayList<Node> visited =
        new ArrayList<>(); // this list will hold nodes that get visited, but unique ones
    Queue<Node> queue = new LinkedList<>();
    ArrayList<Node> returnList =
        new ArrayList<Node>(); // this list is the path that gets returned, with repeat visits

    String endFloor = target.getFloor();
    queue.add(start);
    visited.add(start);
    returnList.add(start);

    while (!queue.isEmpty()) {
      Node n = queue.poll();
      boolean nodeChosen = false;
      if (n == target) {
        for (int i = 0; i < returnList.size(); i++) {
          if (i + 1 < returnList.size()
              && !returnList.get(i).hasConnection(returnList.get(i + 1))) {
            System.out.println();
            System.out.println("OOPS at node " + returnList.get(i));
            System.out.println();
            System.out.println("Next node is " + returnList.get(i + 1));
          }
        }
        return returnList;
      }
      if (n.getLocation().getNodeType().equalsIgnoreCase("ELEV")
          && !n.getFloor().equalsIgnoreCase(endFloor)
          && !nodeChosen) { // branch to send to correct floor, if needed
        ArrayList<Node> elevatorNodes = new ArrayList<Node>();
        for (Edge edge : n.getEdges()) {
          elevatorNodes.add(edge.getStartNode());
          elevatorNodes.add(edge.getEndNode());
        }
        for (Node node : elevatorNodes) {
          if (node.getFloor().equalsIgnoreCase(endFloor) && !node.equals(n)) {
            visited.add(node);
            queue.add(node);
            returnList.add(node);
            nodeChosen = true;
            break;
          }
        }
      }
      if (n.getLocation().getNodeType().equalsIgnoreCase("ELEV")
          && n.getFloor().equalsIgnoreCase(endFloor)
          && !nodeChosen) { // branch to send to same floor, if needed
        ArrayList<Node> elevatorNodes = new ArrayList<Node>();
        for (Edge edge : n.getEdges()) {
          elevatorNodes.add(edge.getStartNode());
          elevatorNodes.add(edge.getEndNode());
        }
        for (Node node : elevatorNodes) {
          if (node.getFloor().equalsIgnoreCase(n.getFloor())
              && !visited.contains(node)
              && !node.equals(n)) { // might have to exclude ele
            visited.add(node); // vators as well
            queue.add(node);
            returnList.add(node);
            System.out.println();
            System.out.println(
                " nodes seen by ELEVATOR SAME FLOOR branch "
                    + elevatorNodes
                    + "AT NODE "
                    + n.getNodeID()
                    + " WITH SNAME "
                    + n.getLocation().getShortName()
                    + " AND WENT TO NODE "
                    + node.getLocation().getNodeType()
                    + " WITH NODE ID "
                    + node.getNodeID()
                    + " AND NAME "
                    + node.getLocation().getShortName());
            nodeChosen = true;
            break;
          }
        }
        System.out.println();
        System.out.println(
            " ALGORITHM TRIGGERED AT NODE "
                + n
                + " WITH PREVIOUS "
                + returnList.get(returnList.size() - 1));
        int i = 1;
        ArrayList<Node> nodesRevisited = new ArrayList<Node>();
        while (!nodeChosen) { // sometimes, elevators only lead to other elevators. In this case,
          elevatorNodes.clear(); // we need to go one node back, and look for another unvisited one.
          for (Edge ex : visited.get(visited.size() - i).getEdges()) {
            elevatorNodes.add(ex.getStartNode());
            elevatorNodes.add(ex.getEndNode());
          }
          // list of nodes we go back to
          nodesRevisited.add(visited.get(visited.size() - i));
          System.out.println("ADDED NODE " + visited.get(visited.size() - i));
          i++;
          for (Node node : elevatorNodes) {
            if (!visited.contains(node)
                && !node.equals(n)
                && node.getFloor().equalsIgnoreCase(n.getFloor())
                && !node.getLocation().getNodeType().equalsIgnoreCase("ELEV")) {
              System.out.println();
              System.out.println(
                  "BEFORE ADDING TO LIST, PREVIOUS NODE IS "
                      + returnList.get(returnList.size() - 1));
              System.out.println();
              returnList.addAll(nodesRevisited);
              returnList.add(node);
              visited.add(node);
              queue.add(node);
              System.out.println();
              System.out.println(
                  "nodes revisited by ELEVATOR branch "
                      + " WITH PREVIOUS NODE "
                      + returnList.get(returnList.size() - 1)
                      // + nodesRevisited
                      + "AT NODE "
                      + n.getNodeID()
                      + " AND WENT TO NODE "
                      + node.getLocation().getNodeType()
                      + " WITH NODE ID "
                      + node.getNodeID()
                      + " AND NAME "
                      + node.getLocation().getShortName());
              nodeChosen = true;
              break;
            }
          }
        }
      }
      if (n.getLocation().getNodeType().equalsIgnoreCase("STAI")
          && !n.getFloor().equalsIgnoreCase(endFloor)
          && !nodeChosen) { // branch to send to correct floor, if needed
        ArrayList<Node> stairNodes = new ArrayList<Node>();
        for (Edge edge : n.getEdges()) {
          stairNodes.add(edge.getStartNode());
          stairNodes.add(edge.getEndNode());
        }
        for (Node node : stairNodes) {
          if (node.getFloor().equalsIgnoreCase(endFloor) && !node.equals(n)) {
            returnList.add(node);
            visited.add(node);
            queue.add(node);
            nodeChosen = true;
            break;
          }
        }
      }
      if (n.getLocation().getNodeType().equalsIgnoreCase("STAI")
          && n.getFloor().equalsIgnoreCase(endFloor)
          && !nodeChosen) { // branch to send to same floor, if needed
        ArrayList<Node> stairNodes = new ArrayList<Node>();
        for (Edge edge : n.getEdges()) {
          stairNodes.add(edge.getStartNode());
          stairNodes.add(edge.getEndNode());
        }
        for (Node node : stairNodes) {
          if (node.getFloor().equalsIgnoreCase(n.getFloor()) && !node.equals(n)) {
            returnList.add(node);
            visited.add(node);
            queue.add(node);
            nodeChosen = true;
            break;
          }
        }
        int i = 1;
        ArrayList<Node> nodesRevisited = new ArrayList<Node>();
        while (!nodeChosen) { // sometimes, stairs only lead to other stairs. In this case,
          stairNodes.clear(); // we need to go one node back, and look for another unvisited one.
          for (Edge ex : visited.get(visited.size() - i).getEdges()) {
            stairNodes.add(ex.getStartNode());
            stairNodes.add(ex.getEndNode());
          }
          nodesRevisited.add(visited.get(visited.size() - i)); // list of nodes we go back to
          i++;
          for (Node node : stairNodes) {
            if (!visited.contains(node)
                && !node.getLocation().getNodeType().equalsIgnoreCase("STAI")
                && !node.equals(n)) {
              returnList.addAll(nodesRevisited);
              returnList.add(node);
              System.out.println();
              System.out.println("Node revisted by STAIR branch " + nodesRevisited);
              visited.add(node);
              queue.add(node);
              nodeChosen = true;
              break;
            }
          }
        }
      }
      for (Edge edge :
          n.getEdges()) { // normal situation choice branches, one for end nodes, one for start
        Node neighbor = edge.getEndNode();
        if (!visited.contains(neighbor)
            && n.getFloor().equalsIgnoreCase(neighbor.getFloor())
            && !neighbor.equals(n)
            && !nodeChosen) {
          visited.add(neighbor);
          returnList.add(neighbor);
          queue.add(neighbor);
          nodeChosen = true;
        }
        if (!visited.contains(edge.getStartNode())
            && n.getFloor().equalsIgnoreCase(edge.getStartNode().getFloor())
            && !n.equals(edge.getStartNode())
            && !nodeChosen) {
          visited.add(edge.getStartNode());
          returnList.add(edge.getStartNode());
          queue.add(edge.getStartNode());
          nodeChosen = true;
        }
      }
      if (queue.isEmpty() && !n.equals(target)) { // if we run into a dead end
        int i = 1;
        ArrayList<Node> nodesRevisited = new ArrayList<Node>();
        ArrayList<Node> backupNodes = new ArrayList<Node>();
        while (!nodeChosen) {
          backupNodes.clear();
          for (Edge ex : visited.get(visited.size() - i).getEdges()) {
            backupNodes.add(ex.getStartNode());
            backupNodes.add(ex.getEndNode());
          }
          nodesRevisited.add(visited.get(visited.size() - i));
          i++;
          for (Node node : backupNodes) {
            if (!visited.contains(node)
                && node.getFloor().equalsIgnoreCase(n.getFloor())
                && !node.equals(n)) {
              returnList.addAll(nodesRevisited);
              returnList.add(node);
              System.out.println();
              System.out.println(
                  "Nodes revisted by DEAD END branch happened at node "
                      + n
                      + nodesRevisited
                      + " AND WENT TO "
                      + node);
              visited.add(node);
              queue.add(node);
              nodeChosen = true;
              break;
            }
          }
        }
      }
    }
    return returnList;
  }
}
