import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyStack<AnyType>
{
    private ArrayList<AnyType> stack = new ArrayList<>();
    public void push(AnyType obj)
    {
      stack.add(obj);
    }
    public AnyType pop()
    {
      if(stack.isEmpty())
      {
         throw new NoSuchElementException();
      }
      return stack.remove(stack.size()-1);
    }
    public boolean isEmpty()
    {
 		return (stack.size() == 0);
    	}
      public int size()
      {
         return stack.size();
      }
   
   public boolean isBalanced(String st)
   {
      MyStack<Character> stk1 = new MyStack<>();
      String stp = st;
      int length = stp.length();
      for(int i = 0; i < length; i++)
      {
         if(stp.charAt(i) == '[' || stp.charAt(i) == '{' || stp.charAt(i) == '(')
         {
            stk1.push(stp.charAt(i));
         }
         else if(stp.charAt(i) == ']')
         {
            if(stk1.size() == 0){ return false;}
            else if(stk1.pop() != '['){ return false;}
         }
         else if(stp.charAt(i) == '}')
         {
            if(stk1.size() == 0){ return false;}
            else if(stk1.pop() != '{'){ return false;}
         }
         else if(stp.charAt(i) == ')')
         {
            if(stk1.size() == 0){ return false;}
            else if(stk1.pop() != '('){ return false;}
         }
                  
      }
      if(stk1.size() != 0)
      {
         return false;
      }
      return true;
   }
}

class TestLinkedList
{
   public static void main(String [] args)
   {
     MyStack<Character> stk = new MyStack<>();
     
     String st = "[({}{})]";
     
     System.out.println("Checking the following string for Balance " + st);
     boolean result = stk.isBalanced(st);
     
     System.out.println("The list balance is " + result);     
   }
}