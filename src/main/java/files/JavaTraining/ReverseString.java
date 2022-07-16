package files.JavaTraining;

public class ReverseString {
    public static void main(String args[]){
        String s="archana";
        String t="";
        for(int i=s.length()-1;i>=0;i--){
           t= t+ s.charAt(i);

        }
        System.out.println(t);
        if(t.equals(s)){
            System.out.println("String is palindrome");
        }
        else {
            System.out.println("String is not palindrome");
        }
        StringBuffer sb=new StringBuffer("Hello");
        System.out.println(sb.append("world"));
        System.out.println(sb.insert(10,"there"));
        System.out.println(sb.replace(5,7,"aa"));
        System.out.println(sb.reverse());


    }
}
