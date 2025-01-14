import java.util.Arrays;
import java.util.HashSet;

public class Boggle {
    // Defines HashSet and Trie objects as main data structures
    private static HashSet<String> goodWords;
    private static Trie dict;

    // Main method that finds words and returns the found words in a given boggle game setup
    public static String[] findWords(char[][] board, String[] dictionary) {
        // Initializes instance variables as well as filling the trie with all words in our dictionary
        goodWords = new HashSet<>();
        dict = new Trie();
        initializeTrie(dictionary);

        // Loops through board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // Calls recursive DFS at each board space
                dfs(i, j, "", board);
            }
        }

        // Convert the list into a sorted array of strings, then return the array
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    // Initializes the trie simply by inserting each word from the dictionary
    private static void initializeTrie(String[] dictionary) {
        for (String s : dictionary) {
            dict.insert(s);
        }
    }

    // Checks if a certain prefix exists (as opposed to the whole word)
    public static boolean prefixExists(String prefix) {
        return dict.inTrie(prefix, true);
    }

    // Checks if the word itself exists (must have the end of word boolean indicator)
    public static void searchWordInDict(String word) {
        if (dict.inTrie(word, false)) {
            // If so it adds the word to the HashSet
            goodWords.add(word);
        }
    }

    // Recursive DFS algorithm to search for words starting at a given row or column
    public static void dfs(int row, int col, String prefix, char[][] board) {
        // Checks bounds
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return;
        // Checks if we have visited this board spot, early exit condition
        if (board[row][col] == 'K') return;
        // Checks if we are at a valid prefix, early exit condition
        if (!prefixExists(prefix)) return;

        // Mark this current square as visited and store it
        char temp = board[row][col];
        prefix += board[row][col];
        board[row][col] = 'K';

        // Check if we are at a valid word
        searchWordInDict(prefix);

        // Move to a new square to form more potential words
        dfs(row - 1, col, prefix, board);
        dfs(row + 1, col, prefix, board);
        dfs(row, col - 1, prefix, board);
        dfs(row, col + 1, prefix, board);

        // Revises board so it's not permanently deformed
        board[row][col] = temp;
    }
}