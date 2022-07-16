package files.JavaTraining;

public class StringConcept {


    public static void main(String args[]){
        String a="JavaTraining";
       System.out.println(a.charAt(2));
        System.out.println(a.indexOf("T"));
        System.out.println(a.substring(5));
       String splitOne= a.split("T")[1];
       System.out.println(splitOne);

       String arr[]=a.split("T");
       System.out.println(arr[0]);
    }
}
