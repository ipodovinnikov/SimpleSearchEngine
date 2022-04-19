package search;

public enum Strategy {
    ALL("all"), ANY("any"), NONE("none");

    Strategy(String strategy) {
        this.value = strategy;
    }

    String value;
}
