package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static search.Helper.Reader.read;
import static search.Strategy.ALL;

public class SearchEngine {

    /**
     * Created by ipodovinnikov (podovinnii@ae.com) on 4/1/22.
     */

    private final List<String> list = new ArrayList<>();

    public void search(Map<String, List<Integer>> invertedIndexMap) {
        Strategy strategy = getStrategy();
        String query = getQuery();
        switch (strategy) {

            case ALL:
                processWithAllStrategy(invertedIndexMap, query);
                break;
            case ANY:
                processWithAnyStrategy(invertedIndexMap, query);
                break;
            case NONE:
                processWithNoneStrategy(invertedIndexMap, query);
                break;
        }
    }

    private void processWithAnyStrategy(Map<String, List<Integer>> invertedIndexMap, String query) {
        if (query.split(" ").length == 1 && invertedIndexMap.get(query) != null) {
            processOneWordQuery(invertedIndexMap, query);
        } else if (query.split(" ").length == 2
                && (invertedIndexMap.get(query.split(" ")[0]) != null
                || invertedIndexMap.get(query.split(" ")[1]) != null)) {
            processTwoWordQueryWithAnyStrategy(invertedIndexMap, query);
        } else if (query.split(" ").length == 3
                && invertedIndexMap.get(query.split(" ")[0]) != null
                && invertedIndexMap.get(query.split(" ")[1]) != null
                && invertedIndexMap.get(query.split(" ")[2]) != null) {
            processThreeWordQueryWithAnyStrategy(invertedIndexMap, query);
        } else {
            System.out.printf("No matching person found%n");
        }
    }

    private void processWithNoneStrategy(Map<String, List<Integer>> invertedIndexMap, String query) {
        if (query.split(" ").length == 1 && invertedIndexMap.get(query) != null) {
            processOneWordQueryWithNoneStrategy(invertedIndexMap, query);
        } else if (query.split(" ").length == 2
                && (invertedIndexMap.get(query.split(" ")[0]) != null
                || invertedIndexMap.get(query.split(" ")[1]) != null)) {
            processTwoWordQueryWithNoneStrategy(invertedIndexMap, query);
        } else if (query.split(" ").length == 3
                && invertedIndexMap.get(query.split(" ")[0]) != null
                && invertedIndexMap.get(query.split(" ")[1]) != null
                && invertedIndexMap.get(query.split(" ")[2]) != null) {
            processThreeWordQueryWithNoneStrategy(invertedIndexMap, query);
        } else {
            System.out.printf("No matching person found%n");
        }
    }

    private void processOneWordQueryWithNoneStrategy(Map<String, List<Integer>> invertedIndexMap, String query) {
        List<Integer> finalList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            finalList.add(i);
        }
        List<Integer> tobeRemovedList = new ArrayList<>();
        if (invertedIndexMap.get(query) != null) {
            tobeRemovedList.addAll(invertedIndexMap.get(query));
        }
        finalList.removeAll(tobeRemovedList);
        System.out.printf("%d persons found:%n", finalList.size());
        for (Integer integer : finalList) {
            System.out.println(list.get(integer));
        }
    }

    private void processTwoWordQueryWithNoneStrategy(Map<String, List<Integer>> invertedIndexMap, String query) {
        List<Integer> finalList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            finalList.add(i);
        }
        List<Integer> tobeRemovedList = new ArrayList<>();
        if (invertedIndexMap.get(query.split(" ")[0]) != null) {
            tobeRemovedList.addAll(invertedIndexMap.get(query.split(" ")[0]));
        }
        if (invertedIndexMap.get(query.split(" ")[1]) != null) {
            tobeRemovedList.addAll(invertedIndexMap.get(query.split(" ")[1]));
        }
        finalList.removeAll(tobeRemovedList);
        System.out.printf("%d persons found:%n", finalList.size());
        for (Integer integer : finalList) {
            System.out.println(list.get(integer));
        }
    }

    private void processThreeWordQueryWithNoneStrategy(Map<String, List<Integer>> invertedIndexMap, String query) {
        List<Integer> finalList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            finalList.add(i);
        }
        List<Integer> tobeRemovedList = new ArrayList<>();
        if (invertedIndexMap.get(query.split(" ")[0]) != null) {
            tobeRemovedList.addAll(invertedIndexMap.get(query.split(" ")[0]));
        }
        if (invertedIndexMap.get(query.split(" ")[1]) != null) {
            tobeRemovedList.addAll(invertedIndexMap.get(query.split(" ")[1]));
        }
        if (invertedIndexMap.get(query.split(" ")[2]) != null) {
            tobeRemovedList.addAll(invertedIndexMap.get(query.split(" ")[2]));
        }
        finalList.removeAll(tobeRemovedList);
        System.out.printf("%d persons found:%n", finalList.size());
        for (Integer integer : finalList) {
            System.out.println(list.get(integer));
        }
    }

    private void processWithAllStrategy(Map<String, List<Integer>> invertedIndexMap, String query) {
        if (query.split(" ").length == 1 && invertedIndexMap.get(query) != null) {
            processOneWordQuery(invertedIndexMap, query);
        } else if (query.split(" ").length == 2
                && invertedIndexMap.get(query.split(" ")[0]) != null
                && invertedIndexMap.get(query.split(" ")[1]) != null) {
            processTwoWordQueryWithAllStrategy(invertedIndexMap, query);
        } else if (query.split(" ").length == 3
                && invertedIndexMap.get(query.split(" ")[0]) != null
                && invertedIndexMap.get(query.split(" ")[1]) != null
                && invertedIndexMap.get(query.split(" ")[2]) != null) {
            processThreeWordQueryWithAllStrategy(invertedIndexMap, query);
        } else {
            System.out.printf("No matching person found%n");
        }
    }

    private void processTwoWordQueryWithAnyStrategy(Map<String, List<Integer>> invertedIndexMap, String query) {
        List<Integer> finalList = new ArrayList<>();
        if (invertedIndexMap.get(query.split(" ")[0]) != null) {
            finalList.addAll(invertedIndexMap.get(query.split(" ")[0]));
        }
        if (invertedIndexMap.get(query.split(" ")[1]) != null) {
            finalList.addAll(invertedIndexMap.get(query.split(" ")[1]));
        }
        System.out.printf("%d persons found:%n", finalList.size());
        for (Integer integer : finalList) {
            System.out.println(list.get(integer));
        }
    }

    private void processThreeWordQueryWithAnyStrategy(Map<String, List<Integer>> invertedIndexMap, String query) {
        List<Integer> finalList = new ArrayList<>();
        if (invertedIndexMap.get(query.split(" ")[0]) != null) {
            finalList.addAll(invertedIndexMap.get(query.split(" ")[0]));
        }
        if (invertedIndexMap.get(query.split(" ")[1]) != null) {
            finalList.addAll(invertedIndexMap.get(query.split(" ")[1]));
        }
        if (invertedIndexMap.get(query.split(" ")[2]) != null) {
            finalList.addAll(invertedIndexMap.get(query.split(" ")[2]));
        }
        System.out.printf("%d persons found:%n", finalList.size());
        for (Integer integer : finalList) {
            System.out.println(list.get(integer));
        }
    }

    private void processThreeWordQueryWithAllStrategy(Map<String, List<Integer>> invertedIndexMap, String query) {
        List<Integer> list1 = invertedIndexMap.get(query.split(" ")[0]);
        List<Integer> list2 = invertedIndexMap.get(query.split(" ")[1]);
        List<Integer> list3 = invertedIndexMap.get(query.split(" ")[2]);
        List<Integer> finalList = new ArrayList<>(list1);
        finalList.retainAll(list2);
        finalList.retainAll(list3);
        System.out.printf("%d persons found:%n", finalList.size());
        for (Integer integer : finalList) {
            System.out.println(list.get(integer));
        }
    }

    private void processTwoWordQueryWithAllStrategy(Map<String, List<Integer>> invertedIndexMap, String query) {
        List<Integer> list1 = invertedIndexMap.get(query.split(" ")[0]);
        List<Integer> list2 = invertedIndexMap.get(query.split(" ")[1]);
        List<Integer> finalList = new ArrayList<>(list1);
        finalList.retainAll(list2);
        System.out.printf("%d persons found:%n", finalList.size());
        for (Integer integer : finalList) {
            System.out.println(list.get(integer));
        }
    }

    private void processOneWordQuery(Map<String, List<Integer>> invertedIndexMap, String query) {
        System.out.printf("%d persons found:%n", invertedIndexMap.get(query).size());
        for (int k = 0; k < invertedIndexMap.get(query).size(); k++) {
            System.out.println(list.get(invertedIndexMap.get(query).get(k)));
        }
    }

    private String getQuery() {
        System.out.println("Enter a name or email to search all suitable people");
        return read().toLowerCase();
    }

    private Strategy getStrategy() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        switch (read().toLowerCase()) {
            case "all":
                return ALL;
            case "any":
                return Strategy.ANY;
            case "none":
                return Strategy.NONE;
        }
        return ALL;
    }

    public void printAll() {
        for (String s : list) {
            System.out.println(s);
        }
    }

    public Map<String, List<Integer>> createRevertedIndexMap(String... args) throws IOException {
        Map<String, List<Integer>> map = new TreeMap<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(args[1]))) {
            int counter = 0;
            while (reader.ready()) {
                String line = reader.readLine();
                list.add(line);
                line = line.toLowerCase();
                int wordsCount = line.split(" ").length;
                switch (wordsCount) {
                    case 1:
                        mapOneWordLine(map, counter, line);
                        break;
                    case 2:
                        mapTwoWordLine(map, counter, line);
                        break;
                    case 3:
                        mapThreeWordLine(map, counter, line);
                }
                counter++;
            }
        }
        return map;
    }

    private void mapOneWordLine(Map<String, List<Integer>> map, int counter, String line) {
        if (map.containsKey(line)) {
            map.get(line).add(counter);
        } else {
            map.put(line, new CopyOnWriteArrayList<>(Collections.singleton(counter)));
        }
    }

    private void mapTwoWordLine(Map<String, List<Integer>> map, int counter, String line) {
        mapFirstTwoWords(map, counter, line);
    }

    private void mapThreeWordLine(Map<String, List<Integer>> map, int counter, String line) {
        mapFirstTwoWords(map, counter, line);
        mapThirdWord(map, counter, line);
    }

    private void mapThirdWord(Map<String, List<Integer>> map, int counter, String line) {
        if (map.containsKey(line.split(" ")[2])) {
            map.get(line.split(" ")[2]).add(counter);
        } else {
            map.put(line.split(" ")[2], new CopyOnWriteArrayList<>(Collections.singleton(counter)));
        }
    }

    private void mapFirstTwoWords(Map<String, List<Integer>> map, int counter, String line) {
        if (map.containsKey(line.split(" ")[0])) {
            map.get(line.split(" ")[0]).add(counter);
        } else {
            map.put(line.split(" ")[0], new CopyOnWriteArrayList<>(Collections.singleton(counter)));
        }
        if (map.containsKey(line.split(" ")[1])) {
            map.get(line.split(" ")[1]).add(counter);
        } else {
            map.put(line.split(" ")[1], new CopyOnWriteArrayList<>(Collections.singleton(counter)));
        }
    }
}
