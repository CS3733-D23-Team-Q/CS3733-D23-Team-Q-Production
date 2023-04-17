package edu.wpi.cs3733.D23.teamQ.Pathfinding.old;

import static edu.wpi.cs3733.D23.teamQ.Pathfinding.old.Star.aStar;

import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import java.util.ArrayList;

public class PathMain {
  public static void main(String[] args) {
    Node head = new Node(0, 0, 0);
    head.setG(0);
    Node n1 = new Node(1, 0, 1);
    Node n2 = new Node(0, 1, 2);
    Node n3 = new Node(1, 1, 3);
    Node n4 = new Node(2, 1, 4);
    Node n5 = new Node(3, 1, 5);
    Node n6 = new Node(3, 0, 6);
    Node n7 = new Node(4, 1, 7);
    Node n8 = new Node(5, 0, 8);
    Node n9 = new Node(1, 2, 9);
    Node n10 = new Node(2, 2, 10);
    Node n11 = new Node(3, 2, 11);
    Node n12 = new Node(4, 2, 12);
    Node n13 = new Node(5, 2, 13);
    Node goal = n8;

    ArrayList<Node> these = aStar(head, goal);
    ArrayList<Integer> toPrint = new ArrayList<Integer>();
    double avg = 0;
    for (Node thisOne : these) {
      avg += thisOne.getWeight();
      System.out.println(thisOne.getNodeID());
    }
    avg = avg / these.size();
    for (Node thatOne : these) {
      if (thatOne.getWeight() > avg) {
        continue;
      } else if (!toPrint.contains(thatOne.getNodeID())) {
        toPrint.add(thatOne.getNodeID());
      }
    }

    /* Node res = aStar(n1, goal);
    printPath(res);*/
  }
}
