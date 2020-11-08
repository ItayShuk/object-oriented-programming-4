public class OpenHashSet extends SimpleHashSet {

    private int elementsNumber;

    private Wrapper[] table;

    /***
     * A default constructor.
     */
    public OpenHashSet() {
        this.upperLoadFactor = 0.75F;
        this.lowerLoadFactor = 0.25F;
        tableCreator(INITIAL_CAPACITY);
    }

    /***
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor
     * @param lowerLoadFactor
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;
        tableCreator(INITIAL_CAPACITY);
    }

    /***
     * Data constructor - builds the hash set by adding the elements one by one.
     * @param data
     */
    public OpenHashSet(java.lang.String[] data) {
        super();
        tableCreator(INITIAL_CAPACITY);
        for (String stringValue : data) {
            add(stringValue);
        }
    }

    /***
     * capacity in class SimpleHashSet
     * @return
     */
    public int capacity() {
        return table.length;
    }

    /***
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return
     */
    public boolean add(String newValue) {
        if (this.contains(newValue)) {
            return false;
        }
        if ((double) (elementsNumber + 1) / this.capacity() > upperLoadFactor) {
            resize(this.capacity() * 2);
        }
        int index = clamp(newValue.hashCode());
        this.table[index].strList.add(newValue);
        elementsNumber++;
        return true;
    }

    /***
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return
     */
    public boolean contains(java.lang.String searchVal) {
        Wrapper lst = table[clamp(searchVal.hashCode())];
        return lst.strList.contains(searchVal);
    }

    /***
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return
     */
    public boolean delete(java.lang.String toDelete) {
        if (contains(toDelete)) {
            if ((double) (elementsNumber - 1) / this.capacity() < lowerLoadFactor) {
                resize(capacity() / 2);
            }
            this.elementsNumber--;
            return table[clamp(toDelete.hashCode())].strList.remove(toDelete);
        }
        return false;
    }


    public int size() {
        return elementsNumber;
    }

    /***
     * resize the table
     * @param capacity
     */
    public void resize(int capacity) {
        Wrapper[] oldTable = table.clone();
        tableCreator(capacity);
        for (Wrapper linkedLst : oldTable) {
            for (String stringValue : linkedLst.strList) {
                int index = clamp(stringValue.hashCode());
                this.table[index].strList.add(stringValue);
            }
        }
    }

    /***
     * create the table
     * @param capacity
     */
    protected void tableCreator(int capacity) {
        this.table = new Wrapper[capacity];
        for (int i = 0; i < capacity; i++) {
            this.table[i] = new Wrapper();
        }
    }
}
