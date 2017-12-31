
/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        clear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void clear( )
    {
        beginMarker = new Node<AnyType>( null, null, null );
        endMarker = new Node<AnyType>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<AnyType>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corrsponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corrsponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        
        return p.data;
    }
    /**
     * Receives two index positions as parameters, and swap the node at these positions
     * by changing the links, provided both positions are within the current size
     */
    public void swap(int idx1, int idx2)
    {
      if (idx1 > idx2)
      {
         int t;
         t = idx1;
         idx1 = idx2;
         idx2 = t;
      }
      
      Node<AnyType> p;
      Node<AnyType> q;
      p = getNode(idx1);
      q = getNode(idx2);
            
      if(p.next == q)
      {
         p.next = q.next;
         q.prev = p.prev;
         q.next.prev = p;
         p.prev.next = q;
         p.prev = q;
         q.next = p;
                  
      }
      else
      {
         Node<AnyType> t = new Node(p.data, p.prev,p.next);
         
          p.next.prev = q;
          q.next.prev = p;
          q.prev.next = p;
          p.prev.next = q;
          p.next = q.next;
          p.prev = q.prev;
          q.prev = t.prev;
          q.next = t.next;
      }
      
    } 
    
    /**
     * Receives an integer (positive or negative) and shifts the list this
     * many positions forward (if positive) or backward (if negative)
     */
    public void shift(int i)
    {
    
      if(i>0)
      {
         for(int j = 0; j < i; j++)
         {
            for(int k = 0; k < size() - 1; k++)
            {
               swap(k, k+1);
            }
         }   
      }
      else
      {
         for(int j = 0; j < (i*(-1)); j++)
         {
            for(int k = size() - 1; k > 0; k--)
            {
               swap(k, k-1);
            }
         } 
      }
    }
    /**
     * Receives an index position and number of elements as parameters, and removes elements beginning at the index position for the number of
     * elements specified, provided the index position is within the size and together with the number of elements does not exceed the size
     */
    public void erase(int idx, int num)
    {
      for(int i = 0; i < num; i ++)
      {
         remove(idx);
      }
      
    }
    
    
    /**
     * Receives another MyLinkedList and an index position as parameters, and copies the list from the passed list     
     * into the list at the specified position, provided the index position does not exceed the size
     */
    public void insertList(int idx, MyLinkedList<AnyType> lst2)
    {
      Node<AnyType> p;
      p = getNode(idx);
      
      lst2.beginMarker.next.prev = p.prev;
      p.prev.next = lst2.beginMarker.next;
      lst2.endMarker.prev.next = p;
      p.prev = lst2.endMarker.prev;

      theSize += lst2.size();
             
    }
    

    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );
        
        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );
    
        return new String( sb );
    }
    
    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            okToRemove = false;       
        }
    }
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<Integer>( );
        
        for( int i = 0; i < 10; i++ )
            lst.add( i );
        for( int i = 20; i < 30; i++ )
            lst.add( 0, i );
        //System.out.println( lst );
        
        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );
        System.out.println("All index start from 0.");
        System.out.println("/******************************/");
        System.out.println("Part 1 a. solution:");
        System.out.print("Original List: ");
        System.out.println( lst );
        lst.swap(1,5);
        System.out.print("List after swapping 1st and 5th node: ");
        System.out.println( lst );
        System.out.println("/******************************/");
        System.out.println("/******************************/");
        System.out.println("Part 1 b. solution:");
        System.out.print("Original List: ");
        System.out.println( lst );
        lst.shift(-5);
        System.out.print("List after shifting -5: ");
        System.out.println( lst );
        System.out.println("/******************************/");
        System.out.println("/******************************/");
        System.out.println("Part 1 c. solution:");
        System.out.print("Original List: ");
        System.out.println( lst );
        lst.erase(1, 5);
        System.out.print("List after removing 5 elements from 1st index: ");
        System.out.println( lst );
        System.out.println("/******************************/");
        
        MyLinkedList<Integer> lst2 = new MyLinkedList<Integer>( );
        
        for( int i = 100; i < 105; i++ )
            lst2.add(i);
              
        System.out.println("/******************************/");
        System.out.println("Part 1 d. solution:");
        System.out.print("Original List: ");
        System.out.println( lst );
        System.out.print("Another List: ");
        System.out.println( lst2 );
        lst.insertList(5, lst2);
        System.out.print("List after inserting Another List into Original List at 5th position: ");
        System.out.println( lst );
        System.out.println("/******************************/");


        
        // java.util.Iterator<Integer> itr = lst.iterator( );
//         while( itr.hasNext( ) )
//         {
//             itr.next( );
//             itr.remove( );
//             System.out.println( lst );
//         }
    }
}