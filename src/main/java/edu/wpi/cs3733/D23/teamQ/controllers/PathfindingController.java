package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.Alert;
import edu.wpi.cs3733.D23.teamQ.Pathfinding.Star2;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Location;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.animation.Interpolator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;
import net.kurobako.gesturefx.*;

public class PathfindingController {
    Qdb qdb = Qdb.getInstance();
    // Stage stage = App.getPrimaryStage();
    Alert alert = new Alert();
    GesturePane pane;
    boolean ready4Second;
    Node start;
    Node target;
    List<Line> previousPath;
    Text text;
    List<Image> floors;
    int floor;
    List<Button> previousNodes;
    List<Integer> restNodes;
    List<Integer> cafeNodes;
    List<Integer> nodeIds;
    List<Pair<Integer, Integer>> highlightedNodes;
    // List<Integer> highlightedNodes;
    List<Pair<Integer, Integer>> l1nodes;
    List<Pair<Integer, Integer>> l2nodes;
    List<Pair<Integer, Integer>> ffnodes;
    List<Pair<Integer, Integer>> sfnodes;
    List<Pair<Integer, Integer>> tfnodes;
    List<String> allSelections;
    // boolean elev;

    @FXML
    HBox root;
    @FXML
    Group parent;
    @FXML
    ImageView map;
    @FXML
    Button previousFloor;
    @FXML
    Button nextFloor;
    @FXML
    CheckBox restCheck;
    @FXML
    CheckBox cafeCheck;
    @FXML
    ComboBox<String> startSelect;
    @FXML
    ComboBox<String> endSelect;

    @FXML
    public void initialize() throws IOException {
        // elev = false;
        allSelections = new ArrayList<>();
        l1nodes = new ArrayList<>();
        l2nodes = new ArrayList<>();
        ffnodes = new ArrayList<>();
        sfnodes = new ArrayList<>();
        tfnodes = new ArrayList<>();
        highlightedNodes = new ArrayList<>();
        nodeIds = new ArrayList<>();
        restNodes = new ArrayList<>();
        cafeNodes = new ArrayList<>();
        previousNodes = new ArrayList<>();
        floors = new ArrayList<>();
        // Image gf = new Image("/00_thegroundfloor.png");
        Image l1 = new Image("/00_thelowerlevel1.png");
        Image l2 = new Image("/00_thelowerlevel2.png");
        Image ff = new Image("/01_thefirstfloor.png");
        Image sf = new Image("/02_thesecondfloor.png");
        Image tf = new Image("/03_thethirdfloor.png");
        // floors.add(gf); // 0
        floors.add(l1); // 0
        floors.add(l2); // 1
        floors.add(ff); // 2
        floors.add(sf); // 3
        floors.add(tf); // 4
        floor = 2;
        ready4Second = false;
        previousPath = new ArrayList<>();
        addButtons("1");
        pane = new GesturePane();
        pane.setContent(parent);
        pane.setFitMode(GesturePane.FitMode.COVER);
        root.getChildren().add(pane);

        pane.setOnMouseClicked(
                e -> {
                    if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                        Point2D pivotOnTarget =
                                pane.targetPointAt(new Point2D(e.getX(), e.getY()))
                                        .orElse(pane.targetPointAtViewportCentre());
                        pane.animate(Duration.millis(200))
                                .interpolateWith(Interpolator.EASE_BOTH)
                                .zoomBy(pane.getCurrentScale(), pivotOnTarget);
                    }
                });
    }

    public void addButtons(String f) {
        List<Node> nodes = qdb.retrieveAllNodes();
        List<Node> fNodes = new ArrayList<>();
        restNodes.removeAll(restNodes);
        cafeNodes.removeAll(cafeNodes);
        for (Node n : nodes) {
            int nodeid = n.getNodeID();
            nodeIds.add(nodeid);
            Location location = qdb.retrieveLocation(nodeid);
            String nodetype = location.getNodeType();
            String lname = location.getLongName();
            if (n.getFloor().equals(f)) {
                fNodes.add(n);
            }
            if (!nodetype.equals("HALL")) {
                startSelect.getItems().add(lname);
                endSelect.getItems().add(lname);
                allSelections.add(lname);
            }
        }
        for (Node n : fNodes) {
            int x = n.getXCoord() / 5;
            int y = n.getYCoord() / 5;
            int nodeid = n.getNodeID();
            Location location = qdb.retrieveLocation(nodeid);
            String nodetype = location.getNodeType();
            String sname = location.getShortName();
            String lname = location.getLongName();
            Button node = new Button();
            node.setLayoutX(x);
            node.setLayoutY(y);
            node.setStyle(
                    "-fx-background-radius: 5em;"
                            + "-fx-min-width: 3px;"
                            + "-fx-min-height: 3px;"
                            + "-fx-max-width: 3px;"
                            + "-fx-max-height: 3px;"
                            + "-fx-background-insets: 0px;");
            parent.getChildren().add(node);
            int index = parent.getChildren().indexOf(node);
            node.setOnMouseEntered(
                    e -> {
                        // String nodeid = "";
                        text = new Text(x + 3, y + 3, sname);
                        text.setStyle("-fx-font-size: 8px;");
                        parent.getChildren().add(text);
                    });
            node.setOnMouseExited(
                    e -> {
                        parent.getChildren().remove(text);
                    });
            node.setOnMouseClicked(
                    e -> {
                        if (!ready4Second) {
                            ready4Second = true;
                            start = n;
                            removeLines(previousPath);
                            if (highlightedNodes.size() > 0) {
                                for (int i = 0; i < highlightedNodes.size(); i++) {
                                    if (highlightedNodes.get(i).getValue() == floor) {
                                        unhighlight(highlightedNodes.get(i).getKey());
                                    }
                                }
                                highlightedNodes.removeAll(highlightedNodes);
                            }
                            highlight(node, "red");
                            highlightedNodes.add(new Pair<>(index, floor));
                        } else {
                            target = n;
                            ready4Second = false;
                            highlight(node, "red");
                            highlightedNodes.add(new Pair<>(index, floor));
                            try {
                                // removeLines(previousPath);
                                // progress bar of generating a new path
                                previousPath = drawLinesf(start, target, f);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
            previousNodes.add(node);
            Pattern pattern1 = Pattern.compile("(?i)(restroom|bathroom)");
            Matcher matcher1 = pattern1.matcher(lname);
            if (matcher1.find()) {
                restNodes.add(index);
            }
            Pattern pattern2 = Pattern.compile("(?i)cafe");
            Matcher matcher2 = pattern2.matcher(lname);
            if (matcher2.find()) {
                cafeNodes.add(index);
            }
            switch (f) {
                case "L1":
                    l1nodes.add(new Pair<>(nodeid, index));
                    break;
                case "L2":
                    l2nodes.add(new Pair<>(nodeid, index));
                    break;
                case "1":
                    ffnodes.add(new Pair<>(nodeid, index));
                    break;
                case "2":
                    sfnodes.add(new Pair<>(nodeid, index));
                    break;
                case "3":
                    tfnodes.add(new Pair<>(nodeid, index));
                    break;
            }
        }
    }

    public void removeButtons() {
        for (Button b : previousNodes) {
            parent.getChildren().remove(b);
        }
    }

    public List<Line> addLines(List<Node> path) {
        List<Line> lines = new ArrayList<>();
        for (int i = path.size() - 1; i >= 1; i--) {
            // for (int i = 0; i < path.size() - 1; i++) {
            Node n = path.get(i);
            Node next = path.get(i - 1);
            // Node next = path.get(i + 1);
            int x1 = n.getXCoord() / 5;
            int y1 = n.getYCoord() / 5;
            int x2 = next.getXCoord() / 5;
            int y2 = next.getYCoord() / 5;
            Line line = new Line(x1, y1, x2, y2);
            line.setStyle("-fx-stroke: blue;");
            line.setStrokeWidth(3);
            parent.getChildren().add(line);
            lines.add(line);
        }
        return lines;
    }

    public void removeLines(List<Line> lines) {
        if (lines.size() > 0) {
            for (Line line : lines) {
                parent.getChildren().remove(line);
            }
        }
    }

    public List<Line> drawLinesf(Node start, Node target, String floor) throws IOException {
        List<Node> path = new ArrayList<>();
        //    if (elev) {
        //      path = Star2.aStarElev(start, target);
        //    } else {
        path = Star2.aStar(start, target);
        //    }
        List<Node> fpath = new ArrayList<>();
        for (Node n : path) {
            if (n.getFloor().equals(floor)) {
                fpath.add(n);
            }
        }
        List<Line> lines = new ArrayList<>();
        if (fpath.size() > 0) {
            lines = addLines(fpath);
        }
        if (path.size() == 0) {
            alert.alertBox("No solution", "Failed to find a path");
        }
        return lines;
    }

    public String whichFloorS() {
        String f = "";
        switch (floor) {
            case 0:
                f = "L1";
                break;
            case 1:
                f = "L2";
                break;
            case 2:
                f = "1";
                break;
            case 3:
                f = "2";
                break;
            case 4:
                f = "3";
                break;
        }
        return f;
    }

    public int whichFloorI(String floor) {
        int f = 0;
        switch (floor) {
            case "L1":
                f = 0;
                break;
            case "L2":
                f = 1;
                break;
            case "1":
                f = 2;
                break;
            case "2":
                f = 3;
                break;
            case "3":
                f = 4;
                break;
        }
        return f;
    }

    public List<Pair<Integer, Integer>> setCF(List<Pair<Integer, Integer>> cfnodes) {
        switch (floor) {
            case 0:
                cfnodes = l1nodes;
                break;
            case 1:
                cfnodes = l2nodes;
                break;
            case 2:
                cfnodes = ffnodes;
                break;
            case 3:
                cfnodes = sfnodes;
                break;
            case 4:
                cfnodes = tfnodes;
                break;
        }
        return cfnodes;
    }

    public void previousFloorClicked() throws IOException {
        String f = "";
        if (floor == 1) {
            previousFloor.setDisable(true);
        }
        if (nextFloor.isDisable()) {
            nextFloor.setDisable(false);
        }
        if (floor > 0) {
            floor--;
            f = whichFloorS();
        }
        Image previous = floors.get(floor);
        map.setImage(previous);
    /*
    if (highlightedNodes.size() > 0) {
      for (int i = 0; i < highlightedNodes.size(); i++) {
        unhighlight(highlightedNodes.get(i).getKey());
      }
    }
     */
        removeButtons();
        removeLines(previousPath); // remove previous floor's path
        addButtons(f); // add current floor's path
        if (restCheck.isSelected()) { // true
            highlight(restNodes, "lightgreen"); // size 12
        } else {
            unhighlight(restNodes);
        }
        if (cafeCheck.isSelected()) {
            highlight(cafeNodes, "yellow");
        } else {
            unhighlight(cafeNodes);
        }
        // if on the same floor
        if (highlightedNodes.size() > 0) {
            for (int i = 0; i < highlightedNodes.size(); i++) {
                if (highlightedNodes.get(i).getValue() == floor) {
                    highlight(highlightedNodes.get(i).getKey(), "red");
                }
            }
        }
        if (!ready4Second && start != null && target != null) {
            previousPath = drawLinesf(start, target, f);
        }
    }

    public void nextFloorClicked() throws IOException {
        String f = "";
        if (floor == 3) {
            nextFloor.setDisable(true);
        }
        if (previousFloor.isDisable()) {
            previousFloor.setDisable(false);
        }
        if (floor < 4) {
            floor++;
            f = whichFloorS();
        }
        Image next = floors.get(floor);
        map.setImage(next);
    /*
    if (highlightedNodes.size() > 0) {
      for (int i = 0; i < highlightedNodes.size(); i++) {
        unhighlight(highlightedNodes.get(i).getKey());
      }
    }
     */
        removeButtons();
        removeLines(previousPath);
        addButtons(f);
        if (restCheck.isSelected()) { // true
            highlight(restNodes, "lightgreen"); // size 12
        } else {
            unhighlight(restNodes);
        }
        if (cafeCheck.isSelected()) {
            highlight(cafeNodes, "yellow");
        } else {
            unhighlight(cafeNodes);
        }
        // if on the same floor
        if (highlightedNodes.size() > 0) {
            for (int i = 0; i < highlightedNodes.size(); i++) {
                if (highlightedNodes.get(i).getValue() == floor) {
                    highlight(highlightedNodes.get(i).getKey(), "red");
                }
            }
        }
        if (!ready4Second && start != null && target != null) { // && previousPath.size() > 0
            previousPath = drawLinesf(start, target, f);
        }
    }

    public void highlight(List<Integer> nodes, String color) {
        String border = "-fx-border-color: ";
        border = border + color + ";";
        ObservableList<javafx.scene.Node> children = parent.getChildren();
        for (int i : nodes) {
            javafx.scene.Node child = children.get(i);
            child.setStyle(
                    "-fx-background-radius: 5em;"
                            + "-fx-min-width: 3px;"
                            + "-fx-min-height: 3px;"
                            + "-fx-max-width: 3px;"
                            + "-fx-max-height: 3px;"
                            + "-fx-background-insets: 0px;"
                            + border);
        }
    }

    public void highlight(Button node, String color) {
        String border = "-fx-border-color: ";
        border = border + color + ";";
        node.setStyle(
                "-fx-background-radius: 5em;"
                        + "-fx-min-width: 3px;"
                        + "-fx-min-height: 3px;"
                        + "-fx-max-width: 3px;"
                        + "-fx-max-height: 3px;"
                        + "-fx-background-insets: 0px;"
                        + border);
    }

    public void highlight(int node, String color) {
        String border = "-fx-border-color: ";
        border = border + color + ";";
        ObservableList<javafx.scene.Node> children = parent.getChildren();
        Button child = (Button) children.get(node);
        child.setStyle(
                "-fx-background-radius: 5em;"
                        + "-fx-min-width: 3px;"
                        + "-fx-min-height: 3px;"
                        + "-fx-max-width: 3px;"
                        + "-fx-max-height: 3px;"
                        + "-fx-background-insets: 0px;"
                        + border);
    }

    public void unhighlight(List<Integer> nodes) {
        ObservableList<javafx.scene.Node> children = parent.getChildren();
        for (int i : nodes) {
            javafx.scene.Node child = children.get(i);
            child.setStyle(
                    "-fx-background-radius: 5em;"
                            + "-fx-min-width: 3px;"
                            + "-fx-min-height: 3px;"
                            + "-fx-max-width: 3px;"
                            + "-fx-max-height: 3px;"
                            + "-fx-background-insets: 0px;");
        }
    }

    public void unhighlight(int node) {
        ObservableList<javafx.scene.Node> children = parent.getChildren();
        javafx.scene.Node child = children.get(node);
        child.setStyle(
                "-fx-background-radius: 5em;"
                        + "-fx-min-width: 3px;"
                        + "-fx-min-height: 3px;"
                        + "-fx-max-width: 3px;"
                        + "-fx-max-height: 3px;"
                        + "-fx-background-insets: 0px;");
    }

    public void cafeChecked() {
        if (cafeCheck.isSelected()) {
            highlight(cafeNodes, "yellow");
        } else {
            unhighlight(cafeNodes);
        }
    }

    public void restChecked() {
        if (restCheck.isSelected()) {
            highlight(restNodes, "lightgreen");
        } else {
            unhighlight(restNodes);
        }
    }

    public void elevChecked() {
    }

    public void startSelected() throws IOException {
        List<Pair<Integer, Integer>> cfnodes = new ArrayList<>();
        String lname = startSelect.getValue();
        if (!lname.equals("")) {
            int index = startSelect.getSelectionModel().getSelectedIndex();
            int nodeid = nodeIds.get(index);
            Node n = qdb.retrieveNode(nodeid);
            String nodeFloor = n.getFloor();
            int f = whichFloorI(nodeFloor);
            int crossFloors = Math.abs(f - floor);
            if (!ready4Second) {
                ready4Second = true;
                start = n;
                removeLines(previousPath);
                if (highlightedNodes.size() > 0) {
                    for (int i = 0; i < highlightedNodes.size(); i++) {
                        if (highlightedNodes.get(i).getValue() == floor) {
                            unhighlight(highlightedNodes.get(i).getKey());
                        }
                    }
                    highlightedNodes.removeAll(highlightedNodes);
                }
            }
            if (f < floor) {
                for (int i = 0; i < crossFloors; i++) {
                    previousFloorClicked();
                }
            }
            if (f > floor) {
                for (int i = 0; i < crossFloors; i++) {
                    nextFloorClicked();
                }
            }
            cfnodes = setCF(cfnodes);
            for (int i = 0; i < cfnodes.size(); i++) {
                if (cfnodes.get(i).getKey() == nodeid) {
                    highlight(cfnodes.get(i).getValue(), "red"); // button
                    highlightedNodes.add(
                            new Pair<>(
                                    cfnodes.get(i).getValue(),
                                    floor)); // int index = parent.getChildren().indexOf(node);
                }
            }
        }
    }

    public void endSelected() throws IOException {
        List<Pair<Integer, Integer>> cfnodes = new ArrayList<>();
        String f = whichFloorS();
        String lname = endSelect.getValue();
        if (!lname.equals("")) {
            int index = endSelect.getSelectionModel().getSelectedIndex();
            int nodeid = nodeIds.get(index);
            Node n = qdb.retrieveNode(nodeid);
            String nodeFloor = n.getFloor();
            int nodef = whichFloorI(nodeFloor);
            int crossFloors = Math.abs(nodef - floor);
            if (ready4Second) {
                target = n;
                ready4Second = false;
                try {
                    // removeLines(previousPath);
                    previousPath = drawLinesf(start, target, f);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (nodef < floor) {
                for (int i = 0; i < crossFloors; i++) {
                    previousFloorClicked();
                }
            }
            if (nodef > floor) {
                for (int i = 0; i < crossFloors; i++) {
                    nextFloorClicked();
                }
            }
            cfnodes = setCF(cfnodes);
            for (int i = 0; i < cfnodes.size(); i++) {
                if (cfnodes.get(i).getKey() == nodeid) {
                    highlight(cfnodes.get(i).getValue(), "red"); // button
                    highlightedNodes.add(
                            new Pair<>(
                                    cfnodes.get(i).getValue(),
                                    floor)); // int index = parent.getChildren().indexOf(node);
                }
            }
        }
    }

    public void clearButtonClicked() {
        List<Pair<Integer, Integer>> cfnodes = new ArrayList<>();
        removeLines(previousPath);
        start = null;
        target = null;
        // previousPath.removeAll(previousPath);
        restCheck.setSelected(false);
        cafeCheck.setSelected(false);
        // unhighlight(restNodes);
        // unhighlight(cafeNodes);
        cfnodes = setCF(cfnodes);
        for (int i = 0; i < cfnodes.size(); i++) {
            unhighlight(cfnodes.get(i).getValue());
        }
        highlightedNodes.removeAll(highlightedNodes);
        startSelect.setValue(null);
        endSelect.setValue(null);
    }
}
