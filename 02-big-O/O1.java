
//constant time 
public class O1{
    public static void main(String[] args) {
        // asseccing a frist element of any array is a O(1) time complexity 
        int [] arr={10,34,56,6,78,65,45,3};
        System.out.println(arr[0]); // no matter how much the array is accessing frist element is always O(1)

        //operation does not change even if size of n grows 
        System.out.println(addition(20));
        System.out.println(addition(20000));
    
    }
    //dont matter n=500 or n=5 orn=5 billion only one operation.
    static int addition(int num){
        return num+num;
    }
    //O(1) dont mean it only one operation . it means number operation does not grow with n. 
}