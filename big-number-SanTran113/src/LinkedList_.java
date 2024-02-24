import java.util.Objects;
import java.util.Optional;

// -----------LinkedList_.java-----------
public class LinkedList_ {
    private Node top;
    private int count;

    private int spot = 1;

    public LinkedList_() {
        this.top = top;
        this.count = 0;
    }

    public LinkedList_(Node top, int count) {
        this.top = top;
        this.count = count;
    }

    public boolean equals (Object other) {
        if (other == null || this.getClass() != other.getClass())
            return false;
        return Objects.equals(this.top, ((LinkedList_)other).top) && count == ((LinkedList_)other).count;
    }

    public void push(int item) {
        if (this.count != 0) {
            Node temp = new Node(item, this.spot, this.top);
            this.top = temp;
        } else {
            this.top = new Node(item, this.spot, null);

        }
        this.count += 1;
        this.spot += 1;
    }

    public void pop() {
        if (this.count != 0) {
            this.top = this.top.getRest();
        }
        this.count -= 1;
        this.spot -= 1;
    }

    public int getCount() {
        return count;
    }

    public Node getTop() {
        return top;
    }
}
