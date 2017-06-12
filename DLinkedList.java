public class DLinkedList{

    private DNode _header, _trailer;  // dummy (sentinel) nodes
    private int _size;



    // constructor
    public DLinkedList(){
	_header = new DNode(null, null, null);
	_trailer = new DNode(null,_header, null);
	_header.setNext(_trailer);
	_size  = 0;
    }

    // accessor methods
    public int size(){
	return _size;
    }

    public boolean isEmpty(){
	return _size == 0;
    }

    public DNode getFirst(){
	if (isEmpty()) throw new IllegalStateException("empty list");
	return _header.getNext();
    }

    public DNode getLast(){
	if (isEmpty()) throw new IllegalStateException("empty list");
	return _trailer.getPrevious();
    }


    public DNode getPrevious(DNode current){
	if (current == _header)
	    throw new IllegalArgumentException("cannot move back past header");
	return current.getPrevious();
    }

    public DNode getNext(DNode current){
	if (current == _trailer)
	    throw new IllegalArgumentException("cannot move forward past trailer");
	return current.getNext();

    }

    //*******************************************************************
    // inserts node a before node b.
    // An exception is thrown if b is the header
    public void addBefore(DNode b, DNode a){
	DNode prevB = getPrevious(b); // throws exception
	prevB.setNext(a);
	a.setNext(b);
	a.setPrevious(prevB);
	b.setPrevious(a);
	_size++;
    }

    public void addLast(DNode node){
	addBefore(_trailer, node);
    }

    public void addLast(Team value){
	addLast(new DNode(value,null,null));
    }


    //*******************************************************************
    // inserts node b after node a
    // throw exception if b is the trailer node
    public void addAfter(DNode a, DNode b){
	DNode afterA = getNext(a);
	a.setNext(b);
	b.setPrevious(a);
	afterA.setPrevious(b);
	b.setNext(afterA);
	_size++;
    }


    public void addFirst(DNode current){
	addAfter(_header,current);
    }

    public void addFirst(Team value){
	addFirst(new DNode(value,null,null));
    }


    //*******************************************************************
    public DNode get(int i){
	if (i < 0 || i >= size())
	    throw new IndexOutOfBoundsException("index < 0 || index >= size()");
	DNode current = null;
	if ( i < size() / 2){
	    current = _header;
	    for (int j = 0; j <= i ; j++){
		current = current.getNext();
		//System.out.print("h");
	    }
	}
	else{
	    current = _trailer;
	    for (int j = 0; j < size() - i; j++){
		current = current.getPrevious();
		//System.out.print("t");
	    }
	}
	return current;
    }

    public String toString(){
	String ans = "";
	DNode current = _header.getNext();
	while (current != _trailer){
	    ans += current.getValue().getName() + ", ";
	    current = current.getNext();
	}
	int len = ans.length();
	if (len > 0) ans = ans.substring(0,len - 2);
	ans = "[" + ans + " ]";
	return ans;
    }

}
