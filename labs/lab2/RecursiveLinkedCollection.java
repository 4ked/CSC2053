/**
 * @author Max Goeke
 */

public class RecursiveLinkedCollection<T> implements CollectionInterface<T> {

	LLNode<T> front;
	int size;
	
	public boolean add(T element) {
		if(element == null) {
			return false;
		} else {
			front = recAdd(front, element);
		}
		return true;
	}
	
	private LLNode<T> recAdd(LLNode<T> node, T info) {
		if(node != null) {
			node.setLink(recAdd(node.getLink(), info));
		} else {
			node = new LLNode<T>(info);
			size++;
		}
		return node;
	}

	public T get(T target) {
		return recGet(front, target);
	}
	
	private T recGet(LLNode<T> node, T info) {
		if(contains(info) == false) {
			return null;
		} else if(node.getInfo().equals(info)) {
			return info;
		} else {
			return recGet(node.getLink(), info);
		}
	}

	public boolean remove(T element) {
		if(element == null)
			return false;
		
		LLNode<T> res = recRemove(front, element);
		return res != null;
	}

	private LLNode<T> recRemove(LLNode<T> node, T info) {
		// check for initial node
		if(front.getInfo().equals(info)) {
			LLNode<T> temp = front;
			front = front.getLink();
			temp.setLink(null);
			size--;
			return temp;
		}
		// check all remaining nodes
		LLNode<T> nxt = node.getLink();
		if(nxt.getInfo().equals(info)) {
			node.setLink(nxt.getLink());
			nxt.setLink(null);
			size--;
		} else {
			recRemove(nxt, info);
		}
		return node;
	}
	
	public boolean contains(T target) {
		return recContains(front, target);
	}
	
	private boolean recContains(LLNode<T> node, T target) {
		if(node == null) {
			return false;
		} else if(node.getInfo().equals(target)) {
			return true;
		} else {
			return recContains(node.getLink(), target);
		}
	}

	public boolean isFull() {
		return false;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return recSize(front);
	}

	public int recSize(LLNode<T> node) {
		if(node == null) {
			return 0;
		}
		return (1 + recSize(node.getLink()));
	}
	
	public String toString() {
		if(front == null) {
			return "";
		}
		String ret = front.getInfo().toString();
		LLNode<T> temp = front.getLink();
		while(temp != null) {
			ret += ", " + temp.getInfo().toString();
			temp = temp.getLink();
		}
		return ret;
	}

}
