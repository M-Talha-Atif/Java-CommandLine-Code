public class BinarySearchTree {
    private BSTNode root;

    public BinarySearchTree() {
        this.root = null; // Initialize root to null
    }

    public BSTNode getRoot() {
        return root;
    }

    public BSTNode get(BSTNode r, Key k) {
        if (r == null) {
          //  System.out.println("here");
            return null;
        }

        int cmp = k.compareTo(r.getRecord().getKey());
        if (cmp == 0) {
            return r;
        } else if (cmp < 0) {
            return get(r.getLeftChild(), k);
        } else {
            return get(r.getRightChild(), k);
        }
    }
    public void insert(BSTNode r, Record d) throws DictionaryException {
     //   System.out.println("Outside Node");
        if (r == null) {

          //  System.out.println("Putting Node");
            // If the current node is null, it means we've reached a leaf node.
            // Create a new node using the provided record.
            BSTNode newNode = new BSTNode(d);
            if (root == null) {
                // If the root is also null, set the new node as the root.
                root = newNode;
            }
        } else {

            // If the current node is not null, continue with the regular insertion process.
            Key key = d.getKey();
            Key currentNodeKey = r.getRecord().getKey(); // Get the key of the current node
            int cmp = key.compareTo(currentNodeKey); // Compare the keys

            if (cmp == 0) {
                throw new DictionaryException("Duplicate key found.");
            } else if (cmp < 0) {
                if (r.getLeftChild() == null) {
                    BSTNode newNode = new BSTNode(d);
                    r.setLeftChild(newNode);
                    newNode.setParent(r);
                } else {
                    insert(r.getLeftChild(), d);
                }
            } else {
                if (r.getRightChild() == null) {
                    BSTNode newNode = new BSTNode(d);
                    r.setRightChild(newNode);
                    newNode.setParent(r);
                } else {
                    insert(r.getRightChild(), d);
                }
            }
        }
    }
    public void printTree(BSTNode root) {
        if (root != null) {
            printTree(root.getLeftChild()); // Traverse left subtree
            System.out.println(root.getRecord().getKey() + ": " + root.getRecord().getDataItem()); // Print current node
            printTree(root.getRightChild()); // Traverse right subtree
        }
    }


    public void remove(BSTNode r, Key k) throws DictionaryException {

            BSTNode nodeToRemove = get(r, k);
            if (nodeToRemove == null) {
             //   System.out.println("called");
                throw new DictionaryException("Key not found in the tree.");
            }

          //  System.out.println("Removing node with key: " + nodeToRemove.getRecord().getKey());

            if (nodeToRemove.isLeaf()) {
                BSTNode parent = nodeToRemove.getParent();
                if (parent != null) {
                    if (parent.getLeftChild() == nodeToRemove) {
                        parent.setLeftChild(null);
                    } else {
                        parent.setRightChild(null);
                    }
                } else {
                    // Node to remove is the root
                    root = null; // Set root to null since the tree will be empty
                  //  System.out.println("Node to remove is the root.");
                }
            } else if (nodeToRemove.getLeftChild() != null && nodeToRemove.getRightChild() == null) {
                BSTNode parent = nodeToRemove.getParent();
                if (parent != null) {
                    nodeToRemove.getLeftChild().setParent(parent);
                    if (parent.getLeftChild() == nodeToRemove) {
                        parent.setLeftChild(nodeToRemove.getLeftChild());
                    } else {
                        parent.setRightChild(nodeToRemove.getLeftChild());
                    }
                } else {
                    // Node to remove is the root
                    root = nodeToRemove.getLeftChild(); // Update root to be the left child of the root
                 //   System.out.println("Node to remove is the root.");
                }
            } else if (nodeToRemove.getRightChild() != null && nodeToRemove.getLeftChild() == null) {
                BSTNode parent = nodeToRemove.getParent();
                if (parent != null) {
                    nodeToRemove.getRightChild().setParent(parent);
                    if (parent.getLeftChild() == nodeToRemove) {
                        parent.setLeftChild(nodeToRemove.getRightChild());
                    } else {
                        parent.setRightChild(nodeToRemove.getRightChild());
                    }
                } else {
                    // Node to remove is the root
                    root = nodeToRemove.getRightChild(); // Update root to be the right child of the root
                  //  System.out.println("Node to remove is the root.");
                }
            } else {
                BSTNode successor = successor(r, k);
              //  System.out.println("Successor node to swap: " + (successor != null ? successor.getRecord().getKey() : "null"));

                if (successor != null) {
                    nodeToRemove.setRecord(successor.getRecord());
                    remove(successor, successor.getRecord().getKey());
                } else {
                 //   System.out.println("No successor found.");
                }
            }

    }


    public BSTNode successor(BSTNode r, Key k) {
        BSTNode node = get(r, k);
        if (node == null) {
            return null; // Return null if the node with the given key doesn't exist
        }
        if (node.getRightChild() != null) {
            return smallest(node.getRightChild()); // Return the smallest node in the right subtree
        } else {
            // Traverse up the tree to find the first ancestor whose left child is also an ancestor of the node
            BSTNode parent = node.getParent();
            if (parent == null) {
                return null; // If the parent is null, it means this node is the maximum in the tree
            }
            while (parent != null && node == parent.getRightChild()) {
                node = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }


    public BSTNode predecessor(BSTNode r, Key k) {
        BSTNode node = get(r, k);
        if (node == null) {
            return null; // Return null if the node with the given key doesn't exist
        }
        if (node.getLeftChild() != null) {
            return largest(node.getLeftChild()); // Return the largest node in the left subtree
        } else {
            // Traverse up the tree to find the first ancestor whose right child is also an ancestor of the node
            BSTNode parent = node.getParent();
            while (parent != null && node == parent.getLeftChild()) {
                node = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }

    public BSTNode smallest(BSTNode r) {
        if (r == null) {
            return null;
        }
        while (r.getLeftChild() != null) {
            r = r.getLeftChild();
        }
        return r;
    }

    public BSTNode largest(BSTNode r) {
        if (r == null) {
            return null;
        }
        while (r.getRightChild() != null) {
            r = r.getRightChild();
        }
        return r;
    }
//    public static void main(String[] args) {
//        // Create a new instance of BSTDictionary
//        BSTDictionary dictionary = new BSTDictionary();
//
//        // Define sample records with integer keys
//        Record[] records = {
//                new Record(new Key("10", 2), "Record with key 10"),
//                new Record(new Key("20", 2), "Record with key 20"),
//                new Record(new Key("30", 2), "Record with key 30"),
//                new Record(new Key("40", 2), "Record with key 40"),
//                new Record(new Key("50", 2), "Record with key 50")
//        };
//
//        // Insert records into the dictionary
//        try {
//            for (Record record : records) {
//                dictionary.put(record);
//            }
//        } catch (DictionaryException e) {
//            System.out.println("Error inserting record: " + e.getMessage());
//        }
//
//        // Test get, successor, and predecessor methods
//        try {
//            // Test get method
//            System.out.println("Get record with key 20: " + dictionary.get(new Key("20", 2)));
//
//            // Test successor method
//            System.out.println("Successor of key 20: " + dictionary.successor(new Key("20", 2)));
//
//            // Test predecessor method
//            System.out.println("Predecessor of key 20: " + dictionary.predecessor(new Key("20", 2)));
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }

}
