import java.util.PriorityQueue;

public class Huffman {
  public static Tree buildTree(int[] frequency) {
    PriorityQueue<Tree> trees = new PriorityQueue<Tree>();
    for(int i = 0; i < frequency.length; i++) {
      if(frequency[i] != 0) {
        trees.add(new Leaf(frequency[i], (char) i));
      }
    }
    while(trees.size() > 1) {
      Tree left = trees.poll();
      Tree right = trees.poll();
      trees.add(new Node(left, right));
    }
    return trees.poll();
  }

  public static void printCodesTable(Tree tree, String code) {
    if(tree instanceof Node) {
      Node node = (Node) tree; 
      code += "0";
      printCodesTable(node.left, code);
      code = code.substring(0, code.length() - 1);
      code += "1";
      printCodesTable(node.right, code);
      code = code.substring(0, code.length() - 1);
    }
    else if(tree instanceof Leaf) {
      Leaf leaf = (Leaf) tree;
      System.out.println("Símbolo: " + leaf.character + "\tFrecuencia: " + leaf.frequency + "\tCódigo: " + code);
    }
  }

  public static String getCode(Tree tree, char character, String code) {
    if(tree instanceof Node) {
      Node node = (Node) tree; 
      code += "0";
      String left = getCode(node.left, character, code);
      code = code.substring(0, code.length() - 1);
      code += "1";
      String right = getCode(node.right, character, code);
      code = code.substring(0, code.length() - 1);
      if(left == null) return right;
      else return left;
    }
    else if(tree instanceof Leaf) {
      Leaf leaf = (Leaf) tree;
      if(character == leaf.character) return code;
    }
    return null;
  }
  public static String encode(Tree tree, String text) {
    String encodeText = "";
    for (char character : text.toCharArray()){
      encodeText += getCode(tree, character, "");
    }
    return encodeText;
  }

  public static String decode(Tree tree, String encodeText) {
    String decodeText = "";
    Node node = (Node) tree;
    for (char character : encodeText.toCharArray()){
      if (character == '0') {
        if (node.left instanceof Leaf) { 
          decodeText += ((Leaf) node.left).character;
          node = (Node) tree;
        }
        else node = (Node) node.left;
      }
      else {
        if (node.right instanceof Leaf) {
          decodeText += ((Leaf) node.right).character;
          node = (Node) tree;
        }
        else node = (Node) node.right;
      }
    }
    return decodeText;
  }

  public static void main(String[] args) {
    String text = "Texto de prueba";
    System.out.println("Texto sin codificar: " + text + "\n");
    int[] frequency = new int[256];
    for(char character : text.toCharArray()) {
      frequency[character]++;
    }
    Tree tree = buildTree(frequency);
    System.out.println("\t\t\t\tTABLA DE CÓDIGOS");
    printCodesTable(tree, "");
    System.out.println();
    String encodeText = encode(tree, text);
    System.out.println("Texto codificado: " + encodeText + "\n");
    String decodeText = decode(tree, encodeText);
    System.out.println("Texto decodificado: " + decodeText);
  }
}