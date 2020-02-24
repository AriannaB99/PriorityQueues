import java.util.Random;

public class MonteCarlo {

    public static void main (String args[]) {
        int tableSize = 20;
        MonteCarlo m = new MonteCarlo();

        Site a[][]=new Site[tableSize][tableSize];

        System.out.println("Performing Experiment once and displaying progess");
        float percolation1 = m.performExperiment(tableSize, a, true);
        System.out.println("\n\n\n");

        System.out.println("Performing Experiment " + tableSize*tableSize + " times and outputting " +
                        "average percolation threshold");
        float sum = 0;
        for (int i = 0; i < tableSize*tableSize; i++) {
           sum += m.performExperiment(tableSize, a, false);
        }

        sum = sum/(tableSize*tableSize);
        System.out.println("Average percolation threshold:  " + sum);

        System.exit(0);
    }

    //method to test whether we can percolate through the grid
    boolean Perocolates( Sets s, int tableSize) {
        for (int i = 0; i < s.elements.length; i++) {
            //if the highest row in a group is 4 and the lowest row is 0
            if ((s.elements[i][2] == 0) && (s.elements[i][3] == tableSize- 1)) {
                return true;
            }
        }
        return false;
    }

    //prints out the unicode symbols for the grid
    void printGrid(Site a[][], int tableSize) {
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println("\n");
        }
    }

    //fallback method to see if we have opened every site
    boolean isAllOpen(Site a[][]) {
        for (int i = 0; i < a.length; i++ ) {
            for (int j = 0; j < a[0].length; j++) {
                if (!a[i][j].status) {
                    return false ;
                }
            }
        }
        return true;
    }

    //calculates the percolation threshold
    float percolationThreshold(Site a[][], int tableSize) {
        int open = 0;
        //for every open site, increment the open count
        for (int i = 0; i < a.length; i++ ) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j].status) {
                    open++;
                }
            }
        }
        //divide by the total number of sites
        float threshold = (float)open/(tableSize*tableSize);
        return threshold;
    }

    //this method performs the experiment once
    float performExperiment(int tableSize, Site a [][], boolean print) {
        //creates a grid of sites and prints out the initial state
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                Site temp = new Site();
                a[i][j] = temp;
                if (print) {
                    System.out.print(temp);
                }
            }
            if (print) {
                System.out.println("\n");
            }
        }
        if (print) {
            System.out.println("\n \n \n ");
        }

        Sets s = new Sets(a);
        Random rand = new Random();

        //this randomly opens the sites until we have opened all of them or we can percolate
        while (!Perocolates(s, tableSize) && !isAllOpen(a)) {
            for (int i = 0; i < 50; i++ ) {
                int randomRow = rand.nextInt(tableSize);
                int randomCol = rand.nextInt(tableSize);

                //if the site is not already open, make it open
                if (!a[randomRow][randomCol].status) {
                    a[randomRow][randomCol].status = true;

                    int checkIndex = randomRow*tableSize + randomCol;

                    for (int k = 0; k < s.elements[checkIndex].length; k++) {
                        //checking the same row to see if anything else was opened
                        if (randomCol >= 1 && a[randomRow][randomCol-1].status) {
                            s.Union(randomRow * tableSize + (randomCol-1), checkIndex);
                        }
                        if (randomCol  < tableSize-1 && a[randomRow][randomCol+1].status) {
                            s.Union(randomRow * tableSize + (randomCol+1), checkIndex);
                        }
                        //checking the same column to see if anything else was opened
                        if (randomRow >= 1 && a[randomRow-1][randomCol].status) {
                            s.Union((randomRow-1)*tableSize + randomCol, checkIndex);
                        }
                        if (randomRow < tableSize-1 && a[randomRow+1][randomCol].status) {
                            s.Union((randomRow+1)*tableSize + randomCol, checkIndex);
                        }

                        if (Perocolates(s, tableSize)) {
                            i = 50;
                        }
                    }

                }
                //if the site was already open, decrement so we can newly open 50 sites
                else i--;
            }

            //every 50 changes, print out the new grid
            if (print) {
                printGrid(a, tableSize);
                System.out.println("\n \n \n ");
            }

        }

        float percolation = percolationThreshold(a, tableSize);

        if (print) {

            System.out.println("Percolation Threshold:  " + percolation);
        }
        return percolation;
    }

}



