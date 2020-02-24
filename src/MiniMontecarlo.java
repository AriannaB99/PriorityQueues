//This is just one of  my test programs


/*•	Initialize all sites to be blocked.
•	Repeat the following until the system percolates:
o	Choose a site (row i, column j) uniformly at random among all blocked sites.
        [If you select an already open site, try again.]
o	Open the site (row i, column j).
•	The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.
*/
import java.util.Random;

public class MiniMontecarlo {

    public static void main (String args[]) {
        int tableSize = 5;
        MiniMontecarlo m = new MiniMontecarlo();

        Site a[][]=new Site[tableSize][tableSize];
        /*char [] sym = {'\u25A0','\u25A2'};  //black square, white square*/
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                Site temp = new Site();
                a[i][j] = temp;
                System.out.print(temp);
            }
            System.out.println("\n");
        }
        System.out.println("\n \n \n ");

        //percolation: when the highest row is 19 and the lowest row in a set is 0

        Sets s = new Sets(a);
        //System.out.println(s);

        Random rand = new Random();


        while (!m.Perocolates( s) && !m.isAllOpen(a)) {
            for (int i = 0; i < 2; i++ ) {
                int randomRow = rand.nextInt(tableSize);
                int randomCol = rand.nextInt(tableSize);

                //if the site is not already open, make it open
                if (!a[randomRow][randomCol].status) {
                    a[randomRow][randomCol].status = true;
                    //checking the same row to see if anything else was opened
                    int checkIndex = randomRow*tableSize + randomCol;
                    for (int k = 0; k < s.elements[checkIndex].length; k++) {
                        if (randomCol >= 1 && a[randomRow][randomCol-1].status) {
                            s.Union(randomRow * tableSize + (randomCol-1), checkIndex);
                            /*System.out.println("Unioned  " + checkIndex +"  and  "+ (randomRow*tableSize+ (randomCol-1)));
                            System.out.println(s.elements[checkIndex][0]);
                            System.out.println(s.elements[(randomRow*tableSize+ (randomCol-1))][0]);
                            int parent = s.elements[checkIndex][0];
                            System.out.println("highest row  "+s.elements[parent][2]);
                            System.out.println("lowest row  "+s.elements[parent][3]);*/
                        }
                        if (randomCol  < tableSize-1 && a[randomRow][randomCol+1].status) {
                            s.Union(randomRow * tableSize + (randomCol+1), checkIndex);
                            /*System.out.println("Unioned  " + checkIndex +"  and  "+ (randomRow*tableSize+ (randomCol+1)));
                            System.out.println(s.elements[checkIndex][0]);
                            System.out.println(s.elements[(randomRow*tableSize+ (randomCol+1))][0]);
                            int parent = s.elements[checkIndex][0];
                            System.out.println("highest row  "+s.elements[parent][2]);
                            System.out.println("lowest row  "+s.elements[parent][3]);*/
                        }
                        //checking the same column to see if anything else was opened
                        if (randomRow >= 1 && a[randomRow-1][randomCol].status) {
                            s.Union((randomRow-1)*tableSize + randomCol, checkIndex);
                           /* System.out.println("Unioned  " + checkIndex +"  and  "+ ((randomRow-1)*tableSize+ (randomCol)));
                            System.out.println(s.elements[checkIndex][0]);
                            System.out.println(s.elements[((randomRow-1)*tableSize+ (randomCol))][0]);
                            int parent = s.elements[checkIndex][0];
                            System.out.println("highest row  "+s.elements[parent][2]);
                            System.out.println("lowest row  "+s.elements[parent][3]);*/
                        }
                        if (randomRow < tableSize-1 && a[randomRow+1][randomCol].status) {
                            s.Union((randomRow+1)*tableSize + randomCol, checkIndex);
                            /*System.out.println("Unioned  " + checkIndex +"  and  "+ ((randomRow+1)*tableSize+ (randomCol)));
                            System.out.println(s.elements[checkIndex][0]);
                            System.out.println(s.elements[((randomRow+1)*tableSize+ (randomCol))][0]);
                            int parent = s.elements[checkIndex][0];
                            System.out.println("highest row  "+s.elements[parent][2]);
                            System.out.println("lowest row  "+s.elements[parent][3]);*/
                        }
                    }

                }
                else i--;
            }

            //every 50 changes, print out the new grid
            m.printGrid(a, tableSize);
            System.out.println("\n \n \n ");

        }

        System.exit(0);
    }

    boolean Perocolates( Sets s) {

        for (int i = 0; i < s.elements.length; i++) {
            //if the highest row is 4 and the lowest row is 0
            if ((s.elements[i][2] == 0) && (s.elements[i][3] == 4)) {
                return true;
            }
        }
        return false;
    }

    void printGrid(Site a[][], int tableSize) {
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println("\n");
        }
    }

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

}
