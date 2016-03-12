
public class RedBlackTree {

	public static class RBTree {
		public static enum Colors {
			RED, BLACK
		}

		public static class Node {
			Node left, right, parent;
			int val;
			Colors color;

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

			void swapChildForParent() {
				this.parent = this;
			}

			void insert_case1() {
				if (this.parent == null) {
					this.color = Colors.BLACK;
				} else {
					if (this.parent.color == Colors.BLACK) {
						return; /* Tree is still valid */
					} else {
						Node u = uncle();
						Node g;

						if ((u != null) && (u.color == Colors.RED)) {
							this.parent.color = Colors.BLACK;
							u.color = Colors.BLACK;
							g = grandparent();
							g.color = Colors.RED;
							insert_case1();
						} else {
							g = grandparent();

							if ((this == this.parent.right) && (this.parent == g.left)) {
								rotateLeft(g);

								// this = this.left;
								this.left.swapChildForParent();

							} else if ((this == this.parent.left) && (this.parent == g.right)) {
								rotateRight(g);

								// this = this.right;
								this.right.swapChildForParent();
							}
							g = grandparent();

							this.parent.color = Colors.BLACK;
							g.color = Colors.RED;
							if (this == this.parent.left)
								rotateRight(g);
							else
								rotateLeft(g);
						}
					}
				}
			}

			void rotateLeft(Node g) {
				Node saved_p = g.left;
				Node saved_left_n = this.left;
				g.left = this;
				this.left = saved_p;
				saved_p.right = saved_left_n;
			}

			void rotateRight(Node g) {
				Node saved_p = g.right;
				Node saved_right_n = this.right;
				g.right = this;
				this.right = saved_p;
				saved_p.left = saved_right_n;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
