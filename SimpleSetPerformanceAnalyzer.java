import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class SimpleSetPerformanceAnalyzer {

    static SimpleSet[] sets;
    static String[] data1 = Ex4Utils.file2array("src//data1.txt");
    static String[] data2 = Ex4Utils.file2array("src//data2.txt");

    private static boolean ans;

    private static long timeBefore;

    private static long difference;

    private static int TO_MILLI = 1000000;

    private static int ROTATION = 70000;

    public static void main(String[] args) {
        cleaning();
        adding();
    }


    public static void adding() {

        /***
         * data1 loading sets
         */
        System.out.println("Data1 loading");
        //data1 loading
        for (SimpleSet set : sets) {
            timeBefore = System.nanoTime();
            for (String word : data1) {
                set.add(word);
            }
            difference = System.nanoTime() - timeBefore;
            System.out.println(difference / TO_MILLI);
        }


        /***
         * checking if data1 containing hi
         */
        System.out.println("containing hi in data1");

        //containing hi
        for (int i = 0; i < sets.length; i++) {
            if (i != 3) {
                for (int j = 0; j < ROTATION; j++) {
                    sets[i].contains("hi");
                }
            }
            timeBefore = System.nanoTime();
            for (int k = 0; k < ROTATION; k++) {
                ans = sets[i].contains("hi");
            }
            difference = System.nanoTime() - timeBefore;
            System.out.println(ans + " " + difference / ROTATION);
        }

        /***
         * checking if data1 containing -13170890158
         */
        System.out.println("data1 contains -13170890158");

        for (int i = 0; i < sets.length; i++) {
            if (i != 3) {
                for (int j = 0; j < ROTATION; j++) {
                    ans = sets[i].contains("-13170890158");
                }
            }
            timeBefore = System.nanoTime();
            for (int k = 0; k < ROTATION; k++) {
                sets[i].contains("-13170890158");
            }
            difference = System.nanoTime() - timeBefore;
            System.out.println(ans + " " + difference / ROTATION);
        }

        cleaning();

        /***
         * checking if data2 containing 23
         */
        System.out.println("data2 loading");

        //data2 loading
        for (SimpleSet set : sets) {
            timeBefore = System.nanoTime();
            for (String word : data2) {
                set.add(word);
            }
            difference = System.nanoTime() - timeBefore;
            System.out.println(difference / TO_MILLI);
        }

        /***
         * checking if data2 containing 23
         */
        System.out.println("data2 contains 23");

        for (int i = 0; i < sets.length; i++) {
            if (i != 3) {
                for (int j = 0; j < ROTATION; j++) {
                    ans = sets[i].contains("23");
                }
            }
            timeBefore = System.nanoTime();
            for (int k = 0; k < ROTATION; k++) {
                sets[i].contains("23");
            }
            difference = System.nanoTime() - timeBefore;
            System.out.println(ans + " " + difference / ROTATION);
        }

        /***
         * checking if data2 containing hi
         */
        System.out.println("data2 contains hi");

        for (int i = 0; i < sets.length; i++) {
            if (i != 3) {
                for (int j = 0; j < ROTATION; j++) {
                    ans = sets[i].contains("hi");
                }
            }
            timeBefore = System.nanoTime();
            for (int k = 0; k < ROTATION; k++) {
                sets[i].contains("hi");
            }
            difference = System.nanoTime() - timeBefore;
            System.out.println(ans + " " + difference / ROTATION);
        }
    }


    public static void cleaning() {

        sets = new SimpleSet[5];

        sets[0] = new OpenHashSet();

        sets[1] = new ClosedHashSet();

        TreeSet<String> treeSet = new TreeSet<String>();
        sets[2] = new CollectionFacadeSet(treeSet);

        LinkedList<String> linkedSet = new LinkedList<String>();
        sets[3] = new CollectionFacadeSet(linkedSet);

        HashSet<String> hashSet = new HashSet<String>();
        sets[4] = new CollectionFacadeSet(hashSet);
    }


}
