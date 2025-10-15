package assn01;

public class Part2 {
    public static void main(String[] args) {
        short sh = 32767;
        System.out.println("The value of sh is: " + sh);
        method2();
    }
    public static void method2() {
        int n2 = 0xACF;
        System.out.println("The decimal value of ACF is: " + n2);
        method3();
    }
    public static void method3() {
        char[] a3 = {'a','z'};
        int[] b3 = {'a','z'};
        System.out.println(a3[0] + " " + a3[1]);
        System.out.println(b3[0] + " " + b3[1]);
    }
}
