package utility;

import com.sun.jmx.remote.internal.ArrayQueue;
import entity.Country;
import entity.GameMap;
import entity.Node;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class related to map
 * */
public class MapHelper {

    public static boolean bfs(HashMap<Integer, Node> nodeHashMap, Node node){
        node.color = 1;
        node.distance= 0;
        node.parent = null;

        ArrayQueue<Node> queue = new ArrayQueue<>(nodeHashMap.size());
        queue.add(node);
        while (!queue.isEmpty()){
            Node dequeuedNode = queue.remove(0);
            if(!GameMap.getInstance().countryGraph.get(dequeuedNode.id).isEmpty()){
                for (Country country : GameMap.getInstance().countryGraph.get(dequeuedNode.id)) {
                    Node neigbour = nodeHashMap.get(country.id);
                    if(neigbour.color == 0){
                        neigbour.color = 1;
                        neigbour.distance = dequeuedNode.distance + 1;
                        neigbour.parent = dequeuedNode;
                        queue.add(neigbour);
                    }
                }
            }
            dequeuedNode.color = 2;
        }

        for (Map.Entry<Integer, Node> entry : nodeHashMap.entrySet()) {
//            System.out.println(entry.getValue());
            if(entry.getValue().color != 2){
                return false;
            }
        }

        return true;

    }


    /**
     * checks if map is valid
     * */
    public static boolean validateMap() {
        HashMap<Integer, Node> nodeHashMap = new HashMap<>();
        for (Map.Entry<Integer, Country> entry : GameMap.getInstance().countries.entrySet()) {
            int countryId = entry.getKey();
            Node countryNode = new Node(countryId);
            nodeHashMap.put(countryId, countryNode);
        }
        return bfs(nodeHashMap,nodeHashMap.get(1));
    }

}
