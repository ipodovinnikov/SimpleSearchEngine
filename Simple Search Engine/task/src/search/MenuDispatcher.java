package search;

import java.util.List;
import java.util.Map;

public class MenuDispatcher {

    /**
     * Created by ipodovinnikov (podovinnii@ae.com) on 4/1/22.
     */

    public void dispatch(String selectedOperation, Map<String, List<Integer>> revertedIndexMap, SearchEngine searchEngine) {

        switch (selectedOperation) {
            case "1":
                searchEngine.search(revertedIndexMap);
                break;
            case "2":
                searchEngine.printAll();
                break;
            case "0":
                System.out.println("Bye!\n");
                System.exit(0);
            default:
                System.out.println("Incorrect option! Try again.");
        }
    }
}
