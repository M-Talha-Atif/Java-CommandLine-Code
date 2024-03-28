public class Key {
    private String label;
    private int type;

    public Key(String theLabel, int theType) {
        this.label = theLabel.toLowerCase(); // Convert label to lowercase
        this.type = theType;
    }

    public String getLabel() {
        return label;
    }

    public int getType() {
        return type;
    }

    public int compareTo(Key k) {
        // Compare labels lexicographically
        int labelComparison = this.label.compareTo(k.getLabel());

        // If labels are equal, compare types
        if (labelComparison == 0) {
            return Integer.compare(this.type, k.getType());
        } else {
            return labelComparison;
        }
    }

    public static void main(String[] args) {
        // Test cases
        Key A = new Key("car", 2);
        Key B = new Key("car", 2);
        Key C = new Key("car", 3);
        Key D = new Key("house", 1);

        // Testing equality
        System.out.println("Test case 1: A equals B -> " + (A.compareTo(B) == 0));

        // Testing label precedes type
        System.out.println("Test case 2: A precedes C -> " + (A.compareTo(C) < 0));

        // Testing label precedes another label
        System.out.println("Test case 3: A precedes D -> " + (A.compareTo(D) < 0));

        // Testing non-equality

        System.out.println("Test case 4: A does not equal C -> " + (A.compareTo(C) != 0));
        System.out.println("Test case 5: A does not precede B -> " + (A.compareTo(B) >= 0));

    }
}
