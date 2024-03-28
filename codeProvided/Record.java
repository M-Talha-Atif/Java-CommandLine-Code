public class Record {
    private Key theKey;
    private String data;

    public Record(Key k, String theData) {
        this.theKey = k;
        this.data = theData;
    }

    public Key getKey() {
        return theKey;
    }

    public String getDataItem() {
        return data;
    }

    @Override
    public String toString() {
        return "Record with key " + theKey.getLabel() + ": " + data;
    }
}
