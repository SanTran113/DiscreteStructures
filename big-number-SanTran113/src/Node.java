import java.util.Objects;

public class Node {
    private int value;
    private Node rest;
    private int spot;

    public Node(int value, int spot, Node rest) {
        this.value = value;
        this.spot = spot;
        this.rest = rest;
    }

    public int getValue() {
        return value;
    }

    public int getSpot() {
        return spot;
    }

    public Node getRest() {
        return rest;
    }

    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass())
            return false;

        return value == ((Node) other).value && spot == ((Node)other).spot
                && Objects.equals(this.rest, ((Node) other).rest);
    }


}
