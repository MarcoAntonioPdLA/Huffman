public class Node extends Tree {
  public final Tree left, right;
  
  public Node(Tree left, Tree right) {
    super(left.frequency + right.frequency);
    this.left = left;
    this.right = right;
  }
}
