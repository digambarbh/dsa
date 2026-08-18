public class Main {
    public static void main(String[] args) {
        
        Cookie cookie1=new Cookie("green");
        Cookie cookie2=new Cookie("blue");

        System.out.println(cookie1.getColor()); // access the color variable of the cookie class  
        cookie2.setColor("pink");
        System.out.println(cookie2.getColor());
    }
}
