package btree;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Node {
    private final String key;
    private String value;
    private Node left, right;

    public Node(@NotNull String key, @Nullable String value) {
        this.key = key;
        this.value = value;
    }

    public final String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(@NotNull Node node) {
        this.right = node;
    }

    public void setLeft(@NotNull Node node) {
        this.left = node;
    }

    public void setValue(@Nullable String value) {
        this.value = value;
    }
}
