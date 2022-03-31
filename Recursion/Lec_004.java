public class Lec_004 {

    public static int nQueensCombn(int [] boxes, int queens, int idx, int qpsf, String psf){
        if(qpsf == queens){
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for(int i = idx; i < boxes.length; i++){
            count += nQueensCombn(boxes, queens, i + 1, qpsf + 1, psf + "q" + i);
        }
        
        return count;
    }

    public static void main(String [] args){
        System.out.println(nQueensCombn(new int[6], 3, 0, 0, ""));
    }
}   