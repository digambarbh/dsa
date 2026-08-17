
//O(n^2) :quadratic time 

// usually happens when have a nested loops and both depends on n 
public class ON2 {
    public static void main(String[] args) {
        printnum(10);
    }
    //graph:grows faster then O(n) make it worse ;

    static void printnum(int n) {
        for (int i = 0; i < n; i++) {// outer loop runs n time 
            for (int j = 0; j < n; j++) {// inner loop runs n time 
                System.out.println(i + " " + j);
            }
        }
    }

    //n*n:n^2 this is the O(n^2) example 

    // if n increases number of itreation/ opreation increases it makes program slow quickley 

    // if n=1000 
    // 1000*1000=1000000 
}
