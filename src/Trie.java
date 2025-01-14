public class Trie {
    private Node root;

    // Constructor for Trie
    public Trie() {
        // Stores a root node as instance variable
        this.root = new Node(false);
    }

    // Insert method that takes in a string and inserts it into the trie
    public void insert(String insertString) {
        // Loops through string (similar to going down layers in trie)
        Node curPos = root;
        for (int i = 0; i < insertString.length(); i++) {
            // Checks if our next layer is null
            if (curPos.getNextSet()[insertString.charAt(i) - 'a'] == null) {
                curPos.getNextSet()[insertString.charAt(i) - 'a'] = new Node(false);
            }
            // Increments down a layer
            curPos = curPos.getNextSet()[insertString.charAt(i) - 'a'];
        }
        curPos.setWordEnd(true);
    }

    // inTrie is a search algorithm that searches for a given string in a trie
    public boolean inTrie(String searchFor, boolean searchingForPrefix) {
        Node curPos = root;
        // Loops through indices in string and checks if each exists
        for (int i = 0; i < searchFor.length(); i++) {
            curPos = curPos.getNextSet()[searchFor.charAt(i) - 'a'];
            // Early exit condition
            if (curPos == null) {
                return false;
            }
        }
        // If it gets through the whole string checks if final position is end of word
        return searchingForPrefix || curPos.getWordEnd();
    }

    // Getter for root node
    public Node getRoot() {
        return root;
    }
}