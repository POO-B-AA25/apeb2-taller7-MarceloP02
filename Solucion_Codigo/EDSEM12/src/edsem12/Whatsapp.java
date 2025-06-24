
package edsem12;

public class Whatsapp {    
    class Node{
        String data;
        Node previous;
        Node next;
        
        public Node(String data){
            this.data = data;
            this.previous = null;
            this.next = null;
        }
    }
    Node head, tail = null;
    
    public void separarpalabras(String oracion){
        String [] palabras = oracion.split(" ");
        for(String palabra : palabras){
            insert(palabra);
        }
    }
    public void insert(String data){
        Node newNode = new Node(data);
        
        //Si la lista esta vacia
        if(head == null){
            head = tail = newNode;
            head.previous = null;
            tail.next = null;
        }
        else{ //el nuevo nodo debe ser añadido luego de tail
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
            tail.next = null;
        }
    }
    public void print(){
        //current apunta a head
        Node current = head;
        if(head == null){
            System.out.println("Lista está vacía");
            return;
        }
        System.out.println("Nodos de doble linked");
        while(current != null){
            System.out.println(current.data + " ");
            current = current.next;
        }
    }
    public void printR(){
        //current apunta a tail
        Node current = tail;
        if(tail == null){
            System.out.println("Lista está vacía");
            return;
        }
        System.out.println("Nodos de doble linked");
        while(current != null){
            System.out.println(current.data + " ");
            current = current.previous;
        }
    }
    public void delete(String key){
        Node current = head, prev = null;
        if(current != null && current.data == key){
            head = current.next;
            current.next = null;
            head.previous = null;
            System.out.println(key + " found and deleted");
            return;
        }
        while(current != null && current.data != key){
            prev = current;
            current = current.next;
        }
        if(current != null){
            prev.next = current.next;
            current.previous = null;
            current.next = null;
            if(current != tail){
                prev.next.previous=prev;
            }
            if(current == tail){
                tail = prev;
            }
            System.out.println(key + " found and deleted");
        }
        if(current == null){
            System.out.println(key + " not found");
        }
        System.out.println("\n");
    }
    public void buscar(){
        
    }
}


