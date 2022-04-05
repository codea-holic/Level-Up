public class Lec_006_Bits {

    public static int setTrue(int x, int idx){
        int num = (1 << idx);
        return x | num;
    }
    
    public static int setFalse(int x, int idx){
        // complement
        int num = ~(1 << idx);
        return x & num;
    }

    public static void main(String[] args) {
        System.out.println(setTrue(10, 1));
        System.out.println(setFalse(10, 1));
    }
    
}
