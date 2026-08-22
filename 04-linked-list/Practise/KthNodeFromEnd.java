package Practise;

public class KthNodeFromEnd {
    
    public Node findNOde(int k){

        Node slow=head;
        Node fast=head;
        for(int i=0;i<k;i++){
            if(fast==null{
                return null;
            }
            fast=fast.next;
        }

        while(fast!=null){
            fast=fast.next;
            slow=slow.next;
        }

    }
}
