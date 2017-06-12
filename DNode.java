public class DNode{

    private Team _value;
    private DNode _previous, _next;


    public DNode(Team value, DNode previous, DNode next){
 _value = value;
 _previous = previous;
 _next = next;
    }

    // accessor methods
    public Team getValue(){
 return _value;
    }

    public DNode getNext(){
 return _next;
    }

    public DNode getPrevious(){
 return _previous;
    }

    // modifier methods
    public Team setValue(Team newValue){
 Team ans = getValue();
 _value = newValue;
 return ans;
    }

    public DNode setNext(DNode newNext){
 DNode ans = getNext();
 _next = newNext;
 return ans;
    }

    public DNode setPrevious(DNode newPrevious){
 DNode ans = getPrevious();
 _previous = newPrevious;
 return ans;
    }
public String toString(){
 return _value.getName();
    }


}
