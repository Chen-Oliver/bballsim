public class CircleList{

    private Node<Team> _cursor;
    private int _size;

    // constructor 
    public CircleList(){
 _cursor = null;
 _size = 0;
    }

    public Node<Team> getCursor(){
 return _cursor;
    }


    // Adds a node after the cursor
    // If the list is empty adds at the cursor
    public void add(Node<Team> newNode){
 Node<Team> t ;
 if (isEmpty()){
     
     _cursor = newNode;
     t = _cursor;
 }
 else{
     t = _cursor.getNext();
     _cursor.setNext(newNode);
 }
 newNode.setNext(t);
 _size++;
    }
    public Team nextTeam(){
      if (isEmpty()) throw new IllegalStateException("empty list");
      Team ans=getCursor().getValue();
      advance();
      return ans;
    }  

    public int size(){
 return _size;
    }
    
    public void retreat(){//moves cursor back one
      for (int i=0;i<size()-1;i++)
        advance();
    }

    public boolean isEmpty(){
 return size() == 0;
    }
    public void advance(){
 _cursor = _cursor.getNext();
    }

    public Node<Team> remove(){
 if (isEmpty()) throw new IllegalStateException("empty list");
 Node<Team> ans = _cursor.getNext();
 Node<Team> n = ans.getNext();
 ans.setNext(null);
 if (size() == 1) _cursor = null;
 else _cursor.setNext(n);
 --_size;
 return ans;
    }


    public String toString(){
 String ans = "";
 Node<Team> t = _cursor;
 for (int i = 0; i < size(); i++){
     ans += t + ", ";
     t = t.getNext();
 } 
 int len = ans.length();
 if (len > 0) ans = ans.substring(0,len - 2);
 return "[" + ans + "]";
    }


}
