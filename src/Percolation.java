public class Percolation 
{

    private int n; // The original sized passed to the constructor
    //private UF percUF; //Uncomment in order to use plain union find
    private UF percUF; 
    private int[][] percFillStatus; //-1 for blocked, 0 for open

    // Create N-by-N grid, with all sites blocked
    public Percolation(int N)
    {
        // Save off passed value
        n = N;

        // Initialize the union find tree
        percUF = new UF(N*N);

        // Initialize the status array to "-1's" indicating blocked 
        percFillStatus = new int[N][N];
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                percFillStatus[i][j] = -1;
        }
    }

    // Open site (row i, col j) if it is not already
    public void open(int i, int j)
    {
        checkRange(i, j);
        if (percFillStatus[i][j] != 0)
        {
            percFillStatus[i][j] = 0;
            linkAdjCells(i,j);
        }
        else
        { 
             // Already open
             // Do nothing
        } 
    }

    // Is site (row i, col j) open?
    public boolean isOpen(int i, int j)
    {
        checkRange(i, j);
        if (percFillStatus[i][j] == 0)
            return true;
        else
            return false;
    }

    // Is site (row i, col j) full?
    public boolean isFull(int i, int j)
    {
        checkRange(i, j);
        if (percFillStatus[i][j] == 0)
        {
            // Check if this cell is connected to a top cell
            for (int k = 0; k < n; k++)
            {
                if (percUF.connected(k,i*n+j))
                    return true;
            }
        }
        return false;
    }

    // Does the system percolate?
    public boolean percolates()
    {
        // Loop though bottom row
        for (int j = 0; j < n; j++)
        {
            if (isFull(n-1,j))
                return true;
        }
        // Looped though all available cells
        return false;
    }

    // Link any adjacent open cells
    private void linkAdjCells(int i, int j)
    {
        // Check the cells surrounding the current cell
        // If they are open, union them

        // Check the cell above current 
        if ((i - 1 >= 0) && (percFillStatus[i-1][j] == 0))
            percUF.union((i*n + j), ((i-1)*n) + j);

        // Check the cell below the current cell 
        if ((i + 1 < n) && (percFillStatus[i+1][j] == 0))
            percUF.union((i*n + j), ((i+1)*n) + j);

        // Check the cell to the left of the current cell 
        if ((j-1 >= 0) && (percFillStatus[i][j-1] == 0))
            percUF.union((i*n + j), (i*n) + (j - 1));

        // Check the cell to the right of the current cell
        if ((j+1 < n) && (percFillStatus[i][j+1] == 0))
            percUF.union((i*n + j), (i*n) + (j + 1));
    }

    private void checkRange(int i, int j)
    {
        if (i < 0 || i >= n || j < 0 || j >= n)
           throw new ArrayIndexOutOfBoundsException(); 
    }
}
