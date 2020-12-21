public abstract class Tree implements Comparable<Tree> {
  public final int frequency;

  public Tree(int frequency) {
    this.frequency = frequency;
  }

  public int compareTo(Tree tree) {
    return frequency - tree.frequency;
  }
}