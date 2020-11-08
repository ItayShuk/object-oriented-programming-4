import java.util.Collection;

public class CollectionFacadeSet implements SimpleSet {

    private Collection<String> collection;

    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection) {
        this.collection = collection;
    }

    public boolean add(java.lang.String newValue) {
        if (collection.contains(newValue)) {
            return false;
        }
        collection.add(newValue);
        return true;
    }

    public boolean contains(java.lang.String searchVal) {
        return collection.contains(searchVal);
    }

    public boolean delete(java.lang.String toDelete) {
        return collection.remove(toDelete);
    }

    public int size() {
        return collection.size();
    }


}
