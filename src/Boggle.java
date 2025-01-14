import java.util.Arrays;
import java.util.HashSet;

public class Boggle {
    private static HashSet<String> goodWords;
    private static Trie dict;

    public static String[] findWords(char[][] board, String[] dictionary) {
        goodWords = new HashSet<>();
        dict = new Trie();
        initializeTrie(dictionary);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, "", board);
            }
        }

        // Convert the list into a sorted array of strings, then return the array
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    private static void initializeTrie(String[] dictionary) {
        for (String s : dictionary) {
            dict.insert(s);
        }
    }

    public static boolean prefixExists(String prefix) {
        return dict.inTrie(prefix, true);
    }

    public static void searchWordInDict(String word) {
        if (dict.inTrie(word, false)) {
            goodWords.add(word);
        }
    }

    public static void dfs(int row, int col, String prefix, char[][] board) {
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return;
        if (board[row][col] == 'K') return;
        if (!prefixExists(prefix)) return;

        // Mark this square as visited
        char temp = board[row][col];
        prefix += board[row][col];
        board[row][col] = 'K';

        searchWordInDict(prefix);

        dfs(row - 1, col, prefix, board);
        dfs(row + 1, col, prefix, board);
        dfs(row, col - 1, prefix, board);
        dfs(row, col + 1, prefix, board);

        board[row][col] = temp;
    }
}