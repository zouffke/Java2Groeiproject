package be.kdg.kollections.sets;

import be.kdg.kollections.lists.ArrayList;
import be.kdg.kollections.lists.List;

public class TreeSet<T extends Comparable<T>> implements Set<T> {
    //region vars
    private static class TreeNode<V extends Comparable<V>> {
        private V value;
        private TreeNode<V> left;
        private TreeNode<V> right;

        public TreeNode(V value) {
            this.value = value;
        }
    }

    private TreeNode<T> root;
    private int size = 0;

    //endregion
    private void add(TreeNode<T> parent, T value) {
        int comparison = value.compareTo(parent.value);
        if (comparison < 0) {
            if (parent.left == null) {
                parent.left = new TreeNode<>(value);
                size++;
            } else {
                add(parent.left, value);
            }
        } else if (comparison > 0) {
            if (parent.right == null) {
                parent.right = new TreeNode<>(value);
                size++;
            } else {
                add(parent.right, value);
            }
        }
    }

    //region Override

    @Override
    public void add(T value) {
        if (this.root == null) {
            this.root = new TreeNode<T>(value);
            size++;
        } else {
            add(root, value);
        }
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        addToList(list, root);
        return list;
    }

    //inorder!
    private void addToList(List<T> list, TreeNode<T> node) {
        if (node.left != null) {
            addToList(list, node.left);
        }
        list.add(node.value);
        if (node.right != null) {
            addToList(list, node.right);
        }
    }


    private TreeNode<T> remove(TreeNode<T> node, T value) {
        if (node == null) {
            return null;
        }

        int comparison = value.compareTo(node.value);
        if (comparison < 0) {
            node.left = remove(node.left, value);
        } else if (comparison > 0) {
            node.right = remove(node.right, value);
        } else {
            // case node with no leafs
            if (node.right == null && node.left == null) {
                return null;
            }
            // case node with one child
            else if (node.right == null) {
                return node.left;
            } else if (node.left == null) {
                return node.right;
            }
            // case node with two children
            else {
                TreeNode<T> minLeft = findMin(node.right);
                node.value = minLeft.value;
                node.right = remove(node.right, minLeft.value);
            }
        }
        return node;
    }

    private TreeNode<T> findMin(TreeNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public boolean remove(T value) {
        int oldSize = size;
        root = remove(root, value);
        return size != oldSize;
    }

    @Override
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(TreeNode<T> node, T element) {
        if (node == null) return false;
        if (node.value.equals(element)) return true;
        return contains(node.left, element) || contains(node.right, element);
    }

    @Override
    public int size() {
        return size;
    }
    //endregion
}
