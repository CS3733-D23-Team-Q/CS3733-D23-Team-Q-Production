package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Settings {
    private String username;
    private boolean twoFactor;
    private boolean sound;
    private Algorithm algorithm;
    private Voice voice;

    public enum Algorithm {
        ASTAR,
        DFS,
        BFS,
        DIJKSTRA
    }

    public enum Voice {
        MALE,
        FEMALE,
        SNOOP
    }

    public Settings(String username, boolean twoFactor, boolean sound, int algorithm, int voice) {
        this.username = username;
        this.twoFactor = twoFactor;
        this.sound = sound;

        if(algorithm == 0) {
            this.algorithm = Algorithm.ASTAR;
        } else if (algorithm == 1) {
            this.algorithm = Algorithm.DFS;
        } else if (algorithm == 2) {
            this.algorithm = Algorithm.BFS;
        } else if (algorithm == 3) {
            this.algorithm = Algorithm.DIJKSTRA;
        } else {
            this.algorithm = null;
        }

        if(voice == 0) {
            this.voice = Voice.MALE;
        } else if (voice == 1) {
            this.voice = Voice.FEMALE;
        } else if (voice == 2) {
            this.voice = Voice.SNOOP;
        } else {
            this.voice = null;
        }
    }

}
