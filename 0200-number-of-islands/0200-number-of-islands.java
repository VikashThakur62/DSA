
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid, int i, int j) {

        //Idhar Bounday check ho raha hai 
        if (i < 0 || i >= grid.length ||
            j < 0 || j >= grid[0].length ||
            grid[i][j] == '0') {
            return;
        }

        // Mark as visited
        grid[i][j] = '0';

        // Down
        dfs(grid, i + 1, j);

        // Up
        dfs(grid, i - 1, j);

        // Right
        dfs(grid, i, j + 1);

        // Left
        dfs(grid, i, j - 1);
    }
}