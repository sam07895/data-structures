package assn04;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;


	public NonEmptyBST(T element) {

		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	public void setElement(T element) {
		this._element = element;
	}

	public void setLeft(BST<T> left) {
		this._left = left;
	}

	public void setRight(BST<T> right) {
		this._right = right;
	}

	public static void main(String[] args) {
		NonEmptyBST<Integer> bst = new NonEmptyBST<>(10);
		bst.setElement(20);  // Change the element
		bst.setLeft(new EmptyBST<>());  // Set left child
		bst.setRight(new EmptyBST<>());  // Set right child

		System.out.println(bst.getElement());  // Should output 20
		System.out.println(bst.getLeft().isEmpty());  // Should output true
		System.out.println(bst.getRight().isEmpty());  // Should output true
	}


	// TODO: insert
	@Override
	public BST<T> insert(T element) {
		if (element.compareTo(_element) < 0) {
			_left = _left.insert(element);
		} else if (element.compareTo(_element) > 0) {
			_right = _right.insert(element);
		}
		return this;
	}

	// TODO: findMin
	@Override
	public T findMin() {
		if (_left.isEmpty()) {
			return _element;
		} else {
			return _left.findMin();
		}
	}

	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if (element.compareTo(_element) < 0) {
			_left = _left.remove(element);
		} else if (element.compareTo(_element) > 0) {
			_right = _right.remove(element);
		} else {
			if (_left.isEmpty() && _right.isEmpty()) {
				return new EmptyBST<>();
			} else if (_left.isEmpty()) {
				return _right;
			} else if (_right.isEmpty()) {
				return _left;
			} else {
				T minVal = _right.findMin();
				_element = minVal;
				_right = _right.remove(minVal);
			}
		}
		return this;
	}
	
	// TODO: replace_range
	@Override
	public BST<T> replace_range(T start, T end, T newValue) {
		BST<T> cleaned = clearRange(this, start, end);
		if (newValue.compareTo(start) < 0 || newValue.compareTo(end) > 0) {
			return cleaned.insert(newValue);  // Insert only if the new value is outside the range
		}
		return cleaned;
	}


	private BST<T> clearRange(BST<T> node, T start, T end) {
		if (node.isEmpty()) {
			return node;
		}
		if (!(node instanceof NonEmptyBST)) {
			throw new IllegalArgumentException("Expected NonEmptyBST instance");
		}
		NonEmptyBST<T> nonEmptyNode = (NonEmptyBST<T>) node;
		T currentElement = nonEmptyNode.getElement();

		if (currentElement.compareTo(start) >= 0 && currentElement.compareTo(end) <= 0) {
			if (nonEmptyNode.getLeft().isEmpty() && nonEmptyNode.getRight().isEmpty()) {
				return new EmptyBST<>();
			} else if (nonEmptyNode.getRight().isEmpty()) {
				return clearRange(nonEmptyNode.getLeft(), start, end);
			} else if (nonEmptyNode.getLeft().isEmpty()) {
				return clearRange(nonEmptyNode.getRight(), start, end);
			} else {
				T minVal = nonEmptyNode.getRight().findMin();
				nonEmptyNode.setElement(minVal);
				nonEmptyNode.setRight(nonEmptyNode.getRight().remove(minVal));
				return clearRange(nonEmptyNode, start, end);
			}
		} else {
			nonEmptyNode.setLeft(clearRange(nonEmptyNode.getLeft(), start, end));
			nonEmptyNode.setRight(clearRange(nonEmptyNode.getRight(), start, end));
			return nonEmptyNode;
		}
	}


	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		System.out.print(_element + " ");
		if (!_left.isEmpty()) _left.printPreOrderTraversal();
		if (!_right.isEmpty()) _right.printPreOrderTraversal();
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		if (!_left.isEmpty()) _left.printPostOrderTraversal();
		if (!_right.isEmpty()) _right.printPostOrderTraversal();
		System.out.print(_element + " ");
	}

	// Do not change the methods below
	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
