package com.silicolife.textmining.core.interfaces.other.tree;


import java.util.List;

public interface InterfaceTreeNode<T> {
    InterfaceTreeNode<T> getChild(int childNumber);
    void setChild(int childNumber, InterfaceTreeNode<T> node);
    InterfaceTreeNode<T> getParent();
    void setParent(InterfaceTreeNode<T> parent);
    boolean isLeaf();
    int getNumberOfChildren();
    T getValue();
    void setValue(T value);
    List<InterfaceTreeNode<T>> getChildNodes();    
    // update by HCosta
     boolean compareNodes(InterfaceTreeNode<T> node);
     int getLevelNode();
     void removeNode();
     int getID();
}
