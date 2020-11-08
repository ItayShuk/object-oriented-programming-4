import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public abstract class SimpleHashSet implements SimpleSet {

    protected int INITIAL_CAPACITY = 16;

    protected float upperLoadFactor = 0.75F;

    protected float lowerLoadFactor = 0.25F;


    protected SimpleHashSet() {
        this.lowerLoadFactor = 0.25F;
        this.upperLoadFactor = 0.75F;
        this.INITIAL_CAPACITY = 16;
    }


    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }


    public abstract int capacity();

    protected int clamp(int index) {
        return index & (capacity() - 1);
    }

    public float getLowerLoadFactor() {
        return lowerLoadFactor;
    }

    public float getUpperLoadFactor() {
        return upperLoadFactor;
    }

}
