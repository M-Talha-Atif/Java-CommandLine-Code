public class BSTNode {
    private Record item;
    private BSTNode leftChild;
    private BSTNode rightChild;
    private BSTNode parent;

    public BSTNode(Record item) {
        this.item = item;
    }

    public Record getRecord() {
        return item;
    }

    public void setRecord(Record d) {
        this.item = d;
    }

    public BSTNode getLeftChild() {
        return leftChild;
    }

    public BSTNode getRightChild() {
        return rightChild;
    }

    public BSTNode getParent() {
        return parent;
    }

    public void setLeftChild(BSTNode u) {
        this.leftChild = u;
    }

    public void setRightChild(BSTNode u) {
        this.rightChild = u;
    }

    public void setParent(BSTNode u) {
        this.parent = u;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }
}
