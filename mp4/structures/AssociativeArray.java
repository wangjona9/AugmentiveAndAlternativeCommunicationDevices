package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Jonathan Wang
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * fail("Original array does not contain expected value");
   * 
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  public KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> clonedArray = new AssociativeArray<>(); // Instantiate clone

    for (int i = 0; i < this.size; i++) { // Loop through clone and set values to it
      clonedArray.set(this.pairs[i].key, this.pairs[i].value);
    }

    return clonedArray;
  } // clone()

  /**
   * Convert the array to a string.
   */
  /**
   * Convert the array to a string.
   */
  public String toString() {
    if (size == 0) {
      return "{}";
    }

    String contents = "{ ";
    for (int i = 0; i < size; i++) { // Loop through array
      if (i > 0) { // If size is not empty
        contents += ", ";
      }
      // Format for both empty and nonempty arrays
      contents += this.pairs[i].key.toString() + ": " + this.pairs[i].value.toString();
    }
    contents += " }"; // Close array
    return contents;
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  public void set(K key, V value) {
    if (key == null) {
      throw new IllegalArgumentException("Error: Cannot use a null key");
    }

    for (int i = 0; i < size; i++) { // Loop through array
      if (this.pairs[i].key.equals(key)) {
        this.pairs[i].value = value; // Set array value to input value
        return;
      }
    }

    // If key doesn't exist, add a new pair
    if (size == pairs.length) {
      expand(); // Expand if necessary
    }

    pairs[size++] = new KVPair<>(key, value);
  }

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   *                              when the key does not appear in the associative
   *                              array.
   */
  public V get(K key) throws KeyNotFoundException {
    if (key == null) {
      throw new IllegalArgumentException("Error: Cannot use a null key");
    }

    for (int i = 0; i < size; i++) { // Loop through array
      if (this.pairs[i].key.equals(key)) {
        System.out.println(this.pairs[i].value); // Print index value to screen
        return this.pairs[i].value; // Return index value
      }
    }
    throw new KeyNotFoundException(); // Throw exception if key is not found
  } // get(K)

  /**
   * Determine if key appears in the associative array.
   */
  public boolean hasKey(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Error: Cannot use a null key");
    }

    for (int i = 0; i < size; i++) {
      if (this.pairs[i].key.equals(key)) {
        return true; // Return true if key is found
      }
    }
    return false;
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
    for (int i = 0; i < size; i++) {
      if (this.pairs[i].key.equals(key)) {
        pairs[i] = pairs[size - 1]; // Move last element to the removed position
        pairs[size - 1] = null; // Set the last element to null to avoid duplicate reference
        size--;
        return;
      }
    }
  } // remove(K)

  /**
   * Determine how many values are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  public int find(K key) throws KeyNotFoundException {
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        return i;
      }
    }
    throw new KeyNotFoundException(); // Throw an exception if the key is not found
  } // find(K)

} // class AssociativeArray
