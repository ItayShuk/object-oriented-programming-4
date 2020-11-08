public class ClosedHashSet extends SimpleHashSet {

    private String[] stringList;

    private String flag = new String("");

    private int elementsNumber = 0;

    /***
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor
     * @param lowerLoadFactor
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super();
        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;
        this.stringList = new String[16];
    }

    /***
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet() {
        this.upperLoadFactor = 0.75F;
        this.lowerLoadFactor = 0.25F;
        this.stringList = new String[16];
    }

    /***
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data
     */
    public ClosedHashSet(java.lang.String[] data) {
        super();
        this.stringList = new String[16];
        for (String stringValue : data) {
            add(stringValue);
        }
    }

    /***
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return
     */
    public boolean add(java.lang.String newValue) {
        if (contains(newValue)) {
            return false;
        }
        if (((float) (this.elementsNumber + 1) / capacity()) > this.upperLoadFactor) {
            resize(capacity() * 2);
        }
        int index = legalIndex(newValue);
        stringList[index] = newValue;
        elementsNumber++;
        return true;
    }


    /***
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return
     */
    public boolean delete(java.lang.String toDelete) {
        if (contains(toDelete)) {
            if ((double) (elementsNumber - 1) / this.stringList.length < this.lowerLoadFactor) {
                resize(capacity() / 2);
            }
            int index = containsId(toDelete);
            this.stringList[index] = flag;
            this.elementsNumber--;
            return true;
        }
        return false;
    }


    /***
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return
     */
    public boolean contains(String searchVal) {
        return !(containsId(searchVal) == -1);
    }


    /***
     * Return the index of the contained searchVal
     * @param searchVal
     * @return
     */
    public int containsId(java.lang.String searchVal) {
        for (int i = 0; i < this.capacity(); i++) {
            int index = indexGen(searchVal.hashCode(), i);
            if (this.stringList[index] != null && this.stringList[index] != flag
                    && this.stringList[index].equals(searchVal)) {
                return index;
            }
            if (this.stringList[index] == null) {
                return -1;
            }
        }
        return -1;
    }

    /***
     * Generate the index according to the hashCode
     * @param index
     * @param i
     * @return
     */
    public int indexGen(int index, int i) {
        return (index + ((i + i * i) / 2)) & (capacity() - 1);
    }

    /***
     * Resizing the list
     * @param capacity
     */
    public void resize(int capacity) {
        String[] oldList = stringList.clone();
        this.stringList = new String[capacity];
        for (int i = 0; i < oldList.length; i++) {
            if (oldList[i] != null && oldList[i] != flag) {
                int index = legalIndex(oldList[i]);
                stringList[index] = oldList[i];
            }
        }
    }

    /***
     * Finds legal index to put the string value
     * @param stringVal
     * @return
     */
    public int legalIndex(String stringVal) {
        for (int i = 0; i < capacity(); i++) {
            int index = indexGen(stringVal.hashCode(), i);
            if (stringList[index] == null || stringList[index] == flag) {
                return index;
            }
        }
        return -1;
    }


    /***
     * returns capacity
     * @return
     */
    public int capacity() {
        return this.stringList.length;
    }

    public int size() {
        return elementsNumber;
    }

    public float getLowerLoadFactor() {
        return lowerLoadFactor;
    }

    public float getUpperLoadFactor() {
        return upperLoadFactor;
    }

}