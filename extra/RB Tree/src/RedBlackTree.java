
public class RedBlackTree {

	public static class RBTree {
		public static class Node {
			Node left, right, parent;
			int val;

			public Node(Node left, Node right, Node parent, int val) {
				// super();
				this.left = left;
				this.right = right;
				this.parent = parent;
				this.val = val;
			}

			Node grandparent() {
				if (parent != null)
					return this.parent.parent;
				else
					return null;
			}

			Node uncle() {
				Node g = grandparent();
				if (g == null)
					return null; // No grandparent means no uncle
				if (this.parent == g.left)
					return g.right;
				else
					return g.left;
			}
		
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
