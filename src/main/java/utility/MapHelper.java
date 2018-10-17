package utility;

import com.sun.jmx.remote.internal.ArrayQueue;
import model.Continent;
import model.Country;
import model.GameMap;
import model.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class related to map
 */
public class MapHelper {

    public static boolean bfs(HashMap<Integer, Node> nodeHashMap, Node node) {
        node.color = 1;
        node.distance = 0;
        node.parent = null;
        ArrayQueue<Node> queue = new ArrayQueue<>(nodeHashMap.size());
        queue.add(node);
        while (!queue.isEmpty()) {
            Node dequeuedNode = queue.remove(0);
            if (!GameMap.getInstance().countryGraph.get(dequeuedNode.id).isEmpty()) {
                for (Country country : GameMap.getInstance().countryGraph.get(dequeuedNode.id)) {
                    Node neigbour = nodeHashMap.get(country.id);
                    if (neigbour != null) {
                        if (neigbour.color == 0) {
                            neigbour.color = 1;
                            neigbour.distance = dequeuedNode.distance + 1;
                            neigbour.parent = dequeuedNode;
                            queue.add(neigbour);
                        }
                    }

                }
            }
            dequeuedNode.color = 2;
        }

        for (Map.Entry<Integer, Node> entry : nodeHashMap.entrySet()) {
            if (entry.getValue().color != 2) {
                return false;
            }
        }

        return true;

    }


    /**
     * checks if map is valid
     * Case : All the countries are connected
     */
    public static boolean validateMap() {
        HashMap<Integer, Node> nodeHashMap = new HashMap<>();
        for (Map.Entry<Integer, Country> entry : GameMap.getInstance().countries.entrySet()) {
            int countryId = entry.getKey();
            Node countryNode = new Node(countryId);
            nodeHashMap.put(countryId, countryNode);
        }
        return bfs(nodeHashMap, nodeHashMap.get(1));
    }

    /**
     * checks if map is valid
     * Case : All the countries in Continent are connected
     */
    public static boolean validateContinentGraph() {
        boolean result = false;
        HashMap<Integer, Node> nodeHashMap = new HashMap<>();
        for (Map.Entry<Integer, Continent> entry : GameMap.getInstance().continents.entrySet()) {
            int continentId = entry.getKey();
            int countryId = 1;
            for (Country country : entry.getValue().countries) {
                countryId = country.id;
                Node countryNode = new Node(countryId);
                nodeHashMap.put(countryId, countryNode);
            }
            result = bfs(nodeHashMap, nodeHashMap.get(countryId));
            if (!result) {
                return false;
            }
        }
        return result;
    }


}
