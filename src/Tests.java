//Another test program

public class Tests {

    public static void main(String args[]) {
        Site a[][]=new Site[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Site temp = new Site();
                a[i][j] = temp;
                //System.out.println(i + " " +j + "created");
            }
        }

        System.out.println("Original Set with all elements");
        Sets s = new Sets(a);
        System.out.println(s);
        System.out.println("Unioning 1 and 2");
        s.Union(1, 2);
        System.out.println(s);
        System.out.println("Doing a find on 1 and printing out which group it is in");
        System.out.println(s.find(1));
        System.out.println("Unioning 12 and 3");
        s.Union(12, 3);
        //System.out.println(s);
        System.out.println("Unioning 2 and 12");
        s.Union(2, 12);
        System.out.println(s);
        System.out.println("Unioning 17 and 9");
        s.Union(12, 9);
        System.out.println(s);



    }
}
