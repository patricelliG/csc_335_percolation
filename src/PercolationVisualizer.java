public class PercolationVisualizer
{
    public static void main(String args[])
    {
        int[] input = StdIn.readAllInts(); 
        int n = input[0];
        
        //Initialize the Percolation Structure
        Percolation perc = new Percolation(n);
        
        //Initialize the drawing window
        StdDraw.setCanvasSize();
        StdDraw.setXscale();
        StdDraw.setYscale();
        StdDraw.clear(StdDraw.BLACK);

        //Read in sites to be opened and add them to the structure
        int row, col; //Row and column to be opened
        for (int i = 1; i < input.length-1; i = i + 2)
        {
           row = input[i]; 
           col = input[i + 1];
           perc.open(row, col);
        }

        //Draw the visualization
        double x, y;
        double N = (double) n;
        double offset = .4/N;
        for (int i = 0; i < n; i++)
        {
             for (int j = 0; j < n; j++)
            {
                //Select proper color
                if (perc.isFull(i,j))
                {
                    System.out.println("FULL");
                    StdDraw.setPenColor(StdDraw.BLUE);
                }
                else if (perc.isOpen(i,j))
                {
                    System.out.println("OPEN");
                    StdDraw.setPenColor(StdDraw.WHITE);
                }
                else
                {
                    System.out.println("BLOCKED");
                    StdDraw.setPenColor(StdDraw.BLACK);
                }

                //Calculate this cells position
                x = (i)/N + (.5)*(1/N);
                y = (j)/N + (.5)*(1/N);
                //Draw cell, in order to draw correctly we must flip the axis and invert the y
                StdDraw.filledSquare(y,1-x,offset);
            }
        }
    }
}
