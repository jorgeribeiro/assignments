package tree;

import java.util.*;

@SuppressWarnings("rawtypes")
public class BSTree<T extends Comparable> implements Iterator<T>{
	protected BSTNode<T> root;
	private int numItems;
	private int currentIndex;
	private List<T> storage;
	
	public BSTree() {
		root = null;
		numItems = 0;
		storage = new ArrayList<T>();
	}
	
	public BSTNode<T> getRoot() {
		return root;
	}
	
	private void retrieveNodes(BSTNode<T> node) {
		if(node == null) return;
		else{
			retrieveNodes(node.getLeft());
			storage.add(node.getInfo());
			retrieveNodes(node.getRight());
		}
	}
	
	private String recToString(BSTNode<T> cursor) {
		if(cursor == null) return "";
		else {
			return recToString(cursor.getLeft()) + cursor.getInfo().toString() + " " +
					recToString(cursor.getRight());
		}
	}

	public String toString() {
		return recToString(root);
	}
	
	@SuppressWarnings("unchecked")
	private BSTNode<T> recAdd(T item, BSTNode<T> cursor) {
		BSTNode<T> newNode = new BSTNode<T>(item);
		if(cursor == null) cursor = newNode;
		else{
			if(cursor.getInfo().compareTo(item) < 0)
				cursor.setRight(recAdd(item, cursor.getRight()));
			else cursor.setLeft(recAdd(item, cursor.getLeft()));
		}		
		return cursor;
	}
	public void add(T item) {
		root = recAdd(item, root);
		numItems++;
	}
	
	public int size() {
		return recSize(root);
	}
	
	private int recSize(BSTNode<T> cursor) {
		if(cursor == null) return 0;
		else
			return 1 + recSize(cursor.getLeft()) + recSize(cursor.getRight());
	}
	
	@SuppressWarnings("unchecked")
	public void addI(T item) {
		BSTNode<T> newNode = new BSTNode<T>(item);
		if(root == null){
			root = newNode;
			numItems++;
			return;
		}
		
		BSTNode<T> cursor = root, precursor = null;
		while(cursor != null){
			precursor = cursor;
			if(cursor.getInfo().compareTo(item) < 0)
				cursor = cursor.getRight();
			else cursor = cursor.getLeft();
		}
		
		if(precursor.getInfo().compareTo(item) < 0) 
			precursor.setRight(newNode);
		else
			precursor.setLeft(newNode);
		numItems++;
	}
	
	private int recLeafCount(BSTNode<T> cursor) { // question #22
		if(cursor == null) return 0;
		else if(cursor.getLeft() == null && cursor.getRight() == null) 
			return 1;
		else
			return recLeafCount(cursor.getLeft()) + recLeafCount(cursor.getRight());
	}
	
	public int leafCount() { // question #22
		return recLeafCount(root);
	}
	
	private int recSingleParent(BSTNode<T> cursor) { // question #23
		if(cursor == null) return 0;
		else if(cursor.getLeft() == null && cursor.getRight() != null)
			return recSingleParent(cursor.getRight()) + 1;
		else if(cursor.getLeft() != null && cursor.getRight() == null)
			return recSingleParent(cursor.getLeft()) + 1;
		else
			return recSingleParent(cursor.getLeft()) + recSingleParent(cursor.getRight());
	}
	
	public int singleParent() { // question #23
		return recSingleParent(root);
	}
	
	public Iterator<T> getIterator() {
		storage.clear();
		retrieveNodes(root);
		currentIndex = 0;
		return this;
	}

	@Override
	public boolean hasNext() {
		return currentIndex < storage.size();
	}

	@Override
	public T next() {
		T temp = storage.get(currentIndex);
		currentIndex++; 
		return temp;
	}

}

class BSTNode<T> {
	T info;
	BSTNode<T> left, right;
	
	public BSTNode(T item) {
		info = item;
		left = right = null;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public BSTNode<T> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	public BSTNode<T> getRight() {
		return right;
	}

	public void setRight(BSTNode<T> right) {
		this.right = right;
	}	
}
