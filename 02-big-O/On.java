//O(n) linear time complxity 
//number of operation grow directly with n .
public class On {
    static void printnum(int n){
        for(int i=0;i<n;i++){//here number of print operations increast at n is increase . 
            System.out.println(i);// operation grows propotional to n . 
        }
    }
    // total operations =n
    // graph is a straight line . 
    public static void main(String[] args) {
        printnum(6);
    }
}
// examples : searching a number in the array . in wrost case we have to check every element . 


/*n = 10
→ 10 iterations

n = 100
→ 100 iterations

n = 1000
→ 1000 iterations */