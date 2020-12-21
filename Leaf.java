public class Leaf extends Tree {
  public final char character;

  public Leaf(int frequency, char character) {
    super(frequency);
    this.character = character;
  }
}
