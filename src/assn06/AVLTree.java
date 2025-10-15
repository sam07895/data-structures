package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;

    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    private AVLTree<T> rotateLeft() {
        if (_right == null) {
            return this;
        }
        AVLTree<T> newRoot = _right;
        _right = newRoot._left;
        newRoot._left = this;

        updateHeight();
        updateSize();
        newRoot.updateHeight();
        newRoot.updateSize();

        return newRoot;
    }

    private AVLTree<T> rotateRight() {
        if (_left == null) {
            return this;
        }
        AVLTree<T> newRoot = _left;
        _left = newRoot._right;
        newRoot._right = this;

        updateHeight();
        updateSize();
        newRoot.updateHeight();
        newRoot.updateSize();

        return newRoot;
    }

    private int height(AVLTree<T> node) {
        return node == null ? -1 : node._height;
    }

    @Override
    public boolean isEmpty() {
        return _value == null;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot insert null element.");
        }

        if (isEmpty()) {
            _value = element;
            _height = 0;
            _size = 1;
            _left = new AVLTree<>();
            _right = new AVLTree<>();
            return this;
        }

        int compareResult = element.compareTo(_value);

        if (compareResult < 0) {
            _left = (AVLTree<T>) _left.insert(element);
        } else if (compareResult > 0) {
            _right = (AVLTree<T>) _right.insert(element);
        } else {
            return this;
        }

        updateHeight();
        updateSize();

        return rebalance();
    }

    private void updateHeight() {
        _height = 1 + Math.max(height(_left), height(_right));
    }

    private void updateSize() {
        _size = 1 + ( _left != null ? _left.size() : 0 ) + ( _right != null ? _right.size() : 0 );
    }

    private AVLTree<T> rebalance() {
        updateHeight();
        updateSize();
        int balance = getBalanceFactor();

        if (balance > 1) {
            if (_left != null) {
                int leftBalance = _left.getBalanceFactor();
                if (leftBalance >= 0) {
                    return rotateRight();
                } else {
                    if (_left._right != null) {
                        _left = _left.rotateLeft();
                        return rotateRight();
                    } else {
                        return rotateRight();
                    }
                }
            }
        } else if (balance < -1) {
            if (_right != null) {
                int rightBalance = _right.getBalanceFactor();
                if (rightBalance <= 0) {
                    if (_right._right != null) {
                        return rotateLeft();
                    } else {
                        return rotateLeft();
                    }
                } else {
                    if (_right._left != null) {
                        _right = _right.rotateRight();
                        return rotateLeft();
                    } else {
                        return rotateLeft();
                    }
                }
            }
        }
        return this;
    }

    private int getBalanceFactor() {
        return height(_left) - height(_right);
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
        if (isEmpty()) {
            return this;
        }
        if (element == null) {
            return this;
        }

        int compareResult = element.compareTo(_value);

        if (compareResult < 0) {
            if (_left != null) {
                _left = (AVLTree<T>) _left.remove(element);
            }
        } else if (compareResult > 0) {
            if (_right != null) {
                _right = (AVLTree<T>) _right.remove(element);
            }
        } else {
            if (_left != null && _right != null) {
                if (_right != null && !_right.isEmpty()) {
                    T successorValue = _right.findMin();
                    _value = successorValue;
                    _right = (AVLTree<T>) _right.remove(successorValue);
                } else {
                    return _left;
                }
            } else if (_left != null) {
                return _left;
            } else if (_right != null) {
                return _right;
            } else {
                _value = null;
                _left = null;
                _right = null;
                _height = -1;
                _size = 0;
                return this;
            }
        }

        updateHeight();
        updateSize();
        return rebalance();
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot find minimum in an empty tree");
        }
        AVLTree<T> current = this;
        while (current._left != null && !current._left.isEmpty()) {
            current = current._left;
        }
        return current._value;
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot find maximum in an empty tree");
        }
        if (_right != null && !_right.isEmpty()) {
            return _right.findMax();
        }
        return _value;
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            return false;
        }
        if (isEmpty()){
            return false;
        }
        int compareResult = element.compareTo(_value);
        if (compareResult == 0) {
            return true;
        } else if (compareResult < 0) {
            return _left != null && _left.contains(element);
        } else {
            return _right != null && _right.contains(element);
        }
    }

    @Override
    public boolean rangeContain(T start, T end) {
        if (start.compareTo(end) > 0) {
            return false;
        }
        int s = (Integer) start;
        int e = (Integer) end;
        for (int i = s; i <= e; i++) {
            if (!contains((T) Integer.valueOf(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        return _right;
    }
}
