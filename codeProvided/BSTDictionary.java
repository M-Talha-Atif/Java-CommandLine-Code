import java.util.ArrayList;
import java.util.List;

public class BSTDictionary implements BSTDictionaryADT {
    private BinarySearchTree bst;

    public BSTDictionary() {
        this.bst = new BinarySearchTree();
    }


    @Override
    public Record get(Key k) {
        BSTNode node = bst.get(bst.getRoot(), k);
        return node != null ? node.getRecord() : null;
    }

    @Override
    public void put(Record d) throws DictionaryException {
        try {
            bst.insert(bst.getRoot(), d);
        } catch (DictionaryException e) {
            throw new DictionaryException("Record with the same key already exists.");
        }
    }

    @Override
    public void remove(Key k) throws DictionaryException {
        try {
            bst.remove(bst.getRoot(), k);

        } catch (DictionaryException e) {
            throw new DictionaryException("Record with the specified key does not exist.");
        }
    }
    @Override
    public Record successor(Key k) {
        BSTNode node = bst.successor(bst.getRoot(), k);
        return node != null ? node.getRecord() : null; // Handle null case
    }

    @Override
    public Record predecessor(Key k) {
        BSTNode node = bst.predecessor(bst.getRoot(), k);
        return node != null ? node.getRecord() : null; // Handle null case
    }


    @Override
    public Record smallest() {
        BSTNode node = bst.smallest(bst.getRoot());
        return node != null ? node.getRecord() : null;
    }

    @Override
    public Record largest() {
        BSTNode node = bst.largest(bst.getRoot());
        return node != null ? node.getRecord() : null;
    }
    // New method to get records with a given prefix
    public List<String> getRecordsWithPrefix(String prefix) {
        List<String> labels = new ArrayList<>();
        traverseForPrefix(bst.getRoot(), prefix, labels);
        return labels;
    }

    // Helper method to traverse the tree and collect labels with the given prefix
    private void traverseForPrefix(BSTNode node, String prefix, List<String> labels) {
        if (node != null) {
            if (node.getRecord().getKey().getLabel().startsWith(prefix)) {
                labels.add(node.getRecord().getKey().getLabel());
            }
            traverseForPrefix(node.getLeftChild(), prefix, labels);
            traverseForPrefix(node.getRightChild(), prefix, labels);
        }
    }
}
