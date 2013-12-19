public class PercolationStats
{
    public static void main(String args[])
    {
        int N, T; //Percolation size, Number of tests
        int testNumber = 0;
        double[] openCellsRecord; //Number of open cells to achieve percolation
        double[] times; //Time taken for each experiment
        N = Integer.parseInt(args[0]);
        T = Integer.parseInt(args[1]);
        openCellsRecord = new double[T];
        times = new double[T];

        //Run experiments
        for (int t = 0; t < T; t++)
        {

            Stopwatch sw = new Stopwatch();
            Percolation p = new Percolation(N);
            double openCount = 0; //Number of cells open 
            int i, j; //Index of site to open
            //Open sites until we achieve percolation
            while (!p.percolates())
            {
                //Pick a random site to open
                i = StdRandom.uniform(N);
                j = StdRandom.uniform(N);
                //Is this already open?
                if (!p.isOpen(i,j))
                {
                    //Open it and increase the count
                    p.open(i,j);
                    openCount++;
                }
                else
                {
                    //Do nothing
                }
            }
            //We are out of the loop, we must have achieved percolation
            //Store the number of open sites
            openCellsRecord[testNumber] = openCount;
            //Store the time taken
            times[testNumber] = sw.elapsedTime();
            //Start new test
            testNumber++;
        }

        //Calculate the stats

        //Compute the ratio of open sites for each test
        double[] openRatio = new double[T];
        for (int i = 0; i < openCellsRecord.length; i++)
            openRatio[i] = openCellsRecord[i] / (N*N);

        //Compute the mean ratio
        double mean = StdStats.mean(openRatio);

        //Compute the standard deviation
        double stddev = StdStats.stddev(openRatio);

        //Compute the confidence interval
        double confIntHi = mean + ((1.96*stddev)/Math.sqrt(T));
        double confIntLow = mean - ((1.96*stddev)/Math.sqrt(T));

        //Compute the total time
        double totalTime = 0;
        for (int i = 0; i < times.length; i++)
            totalTime = totalTime + times[i];

        //Compute the mean time
        double meanTime = StdStats.mean(times);

        //Compute the standard deviation
        double stddevTime = StdStats.stddev(times); 

        //Display results to stdout
        System.out.println("mean percolation threshold = " + mean);
        System.out.println("stddev                      = " + stddev);
        System.out.println("95% confidence interval     = [" + confIntLow + ", " + confIntHi + "]");
        System.out.println("total time                  = " + totalTime);
        System.out.println("mean time per experiment    = " + meanTime);
        System.out.println("stddev                      = " + stddevTime);
    }
}
