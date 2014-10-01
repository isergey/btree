package btree;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BTreeTest extends TestCase {

    private List<String> preparedKeys;
    private int preparedKeysCount = 1000_000;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.preparedKeys = new ArrayList<>(this.preparedKeysCount);
        Random rand = new Random();
        for (int i = 0; i < this.preparedKeysCount; i++) {
            this.preparedKeys.add(String.valueOf(rand.nextInt((this.preparedKeysCount - 0)+1) + 0));
        }
    }

    public void testInsert() throws Exception {
        BTree tree = new BTree();
        String retKey = tree.put("1", "val");
        assertEquals(retKey, "1");

        retKey = tree.put("5", "val");
        assertEquals(retKey, "5");

        retKey = tree.put("3", "val");
        assertEquals(retKey, "3");

        retKey = tree.put("4", "val");
        assertEquals(retKey, "4");
    }

    public void testGet() throws Exception {
        BTree tree = new BTree();

        tree.put("4", "4");
        String retVal = tree.get("4");
        assertEquals(retVal, "4");
        assertEquals(tree.getElementCount(), 1);
        assertEquals(tree.getTreeHeight(), 1);

        tree.put("4", "2");
        retVal = tree.get("4");
        assertEquals(retVal, "2");
        assertEquals(tree.getElementCount(), 1);
        assertEquals(tree.getTreeHeight(), 1);

        tree.put("5", "5");
        retVal = tree.get("5");
        assertEquals(retVal, "5");
        assertEquals(tree.getElementCount(), 2);
        assertEquals(tree.getTreeHeight(), 2);

        tree.put("5", "4");
        retVal = tree.get("5");
        assertEquals(retVal, "4");
        assertEquals(tree.getElementCount(), 2);
        assertEquals(tree.getTreeHeight(), 2);

        tree.put("3", "3");
        retVal = tree.get("3");
        assertEquals(retVal, "3");
        assertEquals(tree.getElementCount(), 3);
        assertEquals(tree.getTreeHeight(), 2);

        tree.put("3", "3");
        retVal = tree.get("3");
        assertEquals(retVal, "3");
        assertEquals(tree.getElementCount(), 3);
        assertEquals(tree.getTreeHeight(), 2);

        tree.put("2", "2");
        retVal = tree.get("2");
        assertEquals(retVal, "2");
        assertEquals(tree.getElementCount(), 4);
        assertEquals(tree.getTreeHeight(), 3);

        assertNull(tree.get(null));
        System.out.println("Element count: " + tree.getElementCount());
        System.out.println("Tree height: " + tree.getTreeHeight());
    }

    public void testTime() {
        BTree tree = new BTree();
        long start = System.currentTimeMillis();
        for (String key: this.preparedKeys) {
            tree.put(key, key);
        }
        System.out.println("\nTotal put time: " + (System.currentTimeMillis() - start));
        System.out.println("Element count: " + tree.getElementCount());
        System.out.println("Tree height: " + tree.getTreeHeight());

        start = System.currentTimeMillis();
        long i = 0;
        long comparisons = 0;
        for (String key: this.preparedKeys) {
            i++;
            tree.get(key);
            comparisons += tree.getComparisons();
        }
        System.out.println("\nTotal get time: " + (System.currentTimeMillis() - start));
        System.out.println("\nAverage comparisons: " + (comparisons * 1.0 / i));
        System.out.println("Element count: " + tree.getElementCount());
        System.out.println("Tree height: " + tree.getTreeHeight());
    }


    public void testSearchTime() {

    }

}