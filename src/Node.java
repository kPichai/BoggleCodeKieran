public class Node {
    private boolean isWordEnd;
    private Node[] nextSet;

    // Constructor for node class, each node contains a nextSet of nodes which is 255 long (extended ASCII)
    public Node(boolean isWordEnd) {
        this.nextSet = new Node[26];
        this.isWordEnd = isWordEnd;
    }

    // Getters and Setters for instance variables

    public boolean getWordEnd() {
        return isWordEnd;
    }

    // Setter for isWordEnd
    public void setWordEnd(boolean wordEnd) {
        isWordEnd = wordEnd;
    }

    public Node[] getNextSet() {
        return nextSet;
    }
}