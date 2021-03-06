
public class Tree {
	public static enum NodeType {
		OPERATOR, OPERAND
	}

	public static class Node {
		static final char OPERATOR_PLUS = '+';
		static final char OPERATOR_MINUS = '-';
		static final char OPERATOR_MULT = '*';
		static final char OPERATOR_DIV = '/';
		static final char OPERATOR_POW = '^';

		Node left, right, parent;
		String value;
		NodeType type;

		public Node(String value) {
			this(null, null, null, value, Node.getNodeType(value));
		}

		public Node(Node left, Node right, Node parent, String value) {
			this(left, right, parent, value, Node.getNodeType(value));
		}

		public Node(Node parent, String value) {
			this(null, null, parent, value, Node.getNodeType(value));
		}

		public Node(Node left, Node right, Node parent, String value, NodeType type) {
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.value = value;
			this.type = type;
		}

		public void add(String v){
//			3 4 + 5 6 + +
			
			if (this.right == null)
				this.right = new Node(this, v);
			else
				if (this.right.type == NodeType.OPERATOR)
					this.right.add(v);
				else
					if (this.left == null)
						this.left = new Node(this, v);
					else
						if (this.right.type == NodeType.OPERATOR)
							this.right.add(v);
						
		}
		
		public void addLeft(String v){
			this.left = new Node(this, v);
		}

		public void addRigth(String v){
			this.right = new Node(this, v);
		}

		public static NodeType getNodeType(String value) {
			switch (value.toCharArray()[0]) {
			case OPERATOR_PLUS:
			case OPERATOR_MINUS:
			case OPERATOR_MULT:
			case OPERATOR_DIV:
			case OPERATOR_POW:
				return NodeType.OPERATOR;
			default:
				return NodeType.OPERAND;
			}
		}
	}

	Node root;

	public void add(String v) {
		if (root == null)
			root = new Node(v);
		else
//			root.add(v);
			if (root.right == null)
				root.right = new Node(root, v);
			else
				if (root.right.type == NodeType.OPERATOR){
					root = root.right;
					root.add(v);
				}
				else
					if (root.left == null)
						root.left = new Node(root, v);
					else
						if (root.left.type == NodeType.OPERATOR)
							root = root.left;
							root.add(v);

		
	}

}
