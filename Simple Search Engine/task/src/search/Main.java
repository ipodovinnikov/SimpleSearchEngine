package search;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static search.Helper.Reader.read;


public class Main {
    public static void main(String[] args) throws IOException {

        SearchEngine searchEngine = new SearchEngine();
        Map<String, List<Integer>> revertedIndexMap = searchEngine.createRevertedIndexMap(args);
        Menu menu = new Menu();
        MenuDispatcher menuDispatcher = new MenuDispatcher();
        while(true) {
            menu.show();
            menuDispatcher.dispatch(read(), revertedIndexMap, searchEngine);
        }
    }
}
