package btree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BTree {
    private Node root;
    private Node leftLeaf, rightLeaf;

    private long treeHeight;
    private long elementCount;
    /**
     * Last get comparisons count
     */
    private long comparisons;

    @Nullable
    public String put(@NotNull String key, @Nullable String value) {
        if (this.root == null) {
            this.treeHeight = 1;
            this.root = new Node(key, value);
            this.elementCount++;
            return key;
        }

        long currentTreeDepth = 1;
        Node nextNode = root;

        for (; ; ) {
            int cmp = nextNode.getKey().compareTo(key);
            if (cmp == 0) {
                nextNode.setValue(value);
                break;
            }
            currentTreeDepth++;
            if (cmp > 0) {
                Node nextCandidateNode = nextNode.getLeft();

                if (nextCandidateNode != null) {
                    nextNode = nextCandidateNode;
                } else {
                    Node node = new Node(key, value);
                    this.leftLeaf = node;
                    nextNode.setLeft(node);
                    this.elementCount++;
                    break;
                }
            } else {
                Node nextCandidateNode = nextNode.getRight();
                if (nextCandidateNode != null) {
                    nextNode = nextCandidateNode;
                } else {
                    Node node = new Node(key, value);
                    this.rightLeaf = node;
                    nextNode.setRight(node);
                    this.elementCount++;
                    break;
                }
            }
        }

        if (currentTreeDepth > this.treeHeight) {
            this.treeHeight = currentTreeDepth;
        }

        return key;
    }

    @Nullable
    public String get(@Nullable String key) {
        if (key == null) {
            return null;
        }
        if (this.root == null) {
            return null;
        }
        Node nextNode = root;
        this.comparisons = 0;
        for (; ; ) {
            this.comparisons++;
            int cmp = nextNode.getKey().compareTo(key);
            if (cmp == 0) {
                return nextNode.getValue();
            }
            if (cmp > 0) {
                Node nextCandidateNode = nextNode.getLeft();
                if (nextCandidateNode != null) {
                    nextNode = nextCandidateNode;
                } else {
                    break;
                }
            } else {
                Node nextCandidateNode = nextNode.getRight();
                if (nextCandidateNode != null) {
                    nextNode = nextCandidateNode;
                } else {
                    break;
                }
            }
        }
        return null;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getElementCount() {
        return elementCount;
    }

    public long getTreeHeight() {
        return treeHeight;
    }


}
