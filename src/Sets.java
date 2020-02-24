//union by rank with path compression
public class Sets {

    //[element][0] = parent id
    //[element][1] = size
    //[element][2] = highest_row
    //[element][3] = lowest_row
    public int [][] elements;

    public Sets() {
        elements = null;
    }

    public Sets(Site [][] raw_elements) {
        //this is going to be a grid of sites
        //x x x x x
        //x x x x x
        //x x x x x
        //in this case, i ranges from 0-2 and j ranges from 0 -4
        elements = new int[raw_elements.length * raw_elements[0].length + 1][4];
        //for every row
        for (int i = 0; i <= raw_elements.length-1; i++) {
            //for every column in e/a row, assuming they are all the same length
            for (int j = 0; j <= raw_elements[0].length-1; j++) {
                int index = i*raw_elements[0].length + j;
                raw_elements[i][j].id = index;
                //setting everything to be in its own set
                elements[index][0] = index;
                //giving everything a size of 1
                elements[index][1] = 1;
                //the highest numbered row (the lowest on the grid)
                elements[index][2] = i;
                //so is the lowest row (first on the grid)
               elements[index][3] = i;
            }
        }
    }

    public void Union( int x, int y) {
        int xset = find(x);
        int yset = find(y);
        if (xset == yset) {
            return;
        }
        //if there are more things in the xset than the yset
        if (elements[xset][1] >= elements[yset][1]) {
            //put the thing in yset into xset
            elements[yset][0] = xset;
            elements[xset][1] += elements[yset][1];

            //if the lowest row in the xset > the lowest row in the yset
            if (elements[xset][2] > elements[yset][2]) {
                elements[xset][2] = elements[yset][2];
            }

            //if the highest row in the xset < the highest row in the yset
            if (elements[xset][3] < elements[yset][3]) {
                elements[xset][3] = elements[yset][3];
            }
        }
        else {
            elements[xset][0] = yset;
            elements[yset][1] += elements[xset][1];
            //if the lowest row in the yset > the lowest row in the xset
            if (elements[xset][2] < elements[yset][2]) {
                elements[yset][2] = elements[xset][2];
            }

            //if the highest row in the xset > the highest row in the yset
            if (elements[xset][3] > elements[yset][3]) {
                elements[yset][3] = elements[xset][3];
            }
        }
    }

    //find with path compression
    public int find(int p) {
        //set the parent as the thing we are finding
        int parent = p;
        //while it is not in the same set
        while (parent != elements[parent][0])
            parent = elements[parent][0];

        while (p != parent) {
            int newp = elements[p][0];
            elements[p][0] = parent;
            p = newp;
        }
        return parent;
    }

    //tostring method, mostly for debugging
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
                sb.append("Group:   " + elements[i][0] + "\n");
                sb.append("Size:   "+elements[i][1] + "\n");
                sb.append("Lowest Row:   " + elements[i][2] + "\n");
                sb.append("Highest Row:   " + elements[i][3] + "\n");

        }

        return  sb.toString();
    }
}
