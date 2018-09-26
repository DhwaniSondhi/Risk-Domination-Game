package entity;

import java.util.HashMap;
import java.util.HashSet;

public class Config {

    public int numOfContinents = 0;
    public int numOfCountries = 0;
    public int numOfPlayers = 0;

    public HashMap<Integer, HashSet<Integer>> map;

    public Config(int numOfContinents, int numOfCountries, int numOfPlayers) {
        this.numOfContinents = numOfContinents;
        this.numOfCountries = numOfCountries;
        this.numOfPlayers = numOfPlayers;

        map = new HashMap<>();
    }
}
