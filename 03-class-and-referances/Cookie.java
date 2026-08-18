public class Cookie {
    //best practise to keep class veriable private 
    private String color;

    //class constructor always have a same name as a class 
    public Cookie(String color){
        this.color=color;
    }

    // getter method you can access a private property /variable using it 
    public String getColor(){
        return color;
    }

    // setter method you can set a private varible or reassign using it 
    public void setColor(String color){
        this.color=color;
    }

    // instances dont have a direct access to the private properties of class so use a getter and a setter method
     


    public static void main(String[] args) {
        Cookie cookie1=new Cookie("red"); // instance of a class

        Cookie cookietwo=new Cookie("green"); 
    }
}
 