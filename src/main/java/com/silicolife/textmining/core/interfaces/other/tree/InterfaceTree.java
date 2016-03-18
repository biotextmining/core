package com.silicolife.textmining.core.interfaces.other.tree;

import java.util.Iterator;

public interface InterfaceTree<T>{
    InterfaceTreeNode<T> root();
    InterfaceTreeNode<T> parent(InterfaceTreeNode<T> node);
    Iterator<InterfaceNode<T>> children(InterfaceNode<T> node);
    int size();
    boolean isEmpty();
    Iterator<com.silicolife.textmining.core.interfaces.other.tree.InterfaceNode<T>> nodeIterator();
    void swapNodes(InterfaceNode<T> node,InterfaceNode<T> node1);
    void addTree(InterfaceNode<T> nodeToReplace,InterfaceNode<T> tree);
    int depth();
    int height();
}
