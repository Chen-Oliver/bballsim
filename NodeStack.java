public class NodeStack<E> implements Stack<E>{

    private int _size;
    private Node<E> _stackHead;

    public NodeStack(){
	_size = 0;
	_stackHead = null;
    }

    public int size(){
	return _size;
    }

    public boolean isEmpty(){
	return size() == 0;
    }
    //  O(1)
    public void push(E value){
	_stackHead = new Node<E>(value, _stackHead);
	_size++;
    }
    // O(1)
    public E top() throws EmptyStackException{
	if (isEmpty())
	    throw new EmptyStackException("Stack empty");
	return _stackHead.getValue();
    }
    // O(1)
    public E pop() throws EmptyStackException{
	E ans = top();
	_stackHead = _stackHead.setNext(null);
	_size--;
	return ans;
    }

    public String toString(){
        String ans ="[";
        if (size() > 0) ans += _stackHead.getValue();
        if (size() > 1) {
	    Node<E> curr = _stackHead.getNext();
            for (int i = 1; i < size(); i++){
                ans += ", " + curr.getValue();
		curr = curr.getNext();
	    }

	}
        ans += "]";
        return ans;
    }
    /*public static void main(String[] args){
      Stack<Integer> a = new NodeStack<Integer>();
      a.push(1);
      a.push(2);
      System.out.println(a.pop() + " and " + a.pop());
    }*/

}
