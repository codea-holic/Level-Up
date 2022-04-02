public class Lec_005_Queens {

    public static int queenCombination1D(boolean[] boxes, int tnq, int bno, int qpsf, String psf) {
        if (tnq == qpsf) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = bno; i < boxes.length; i++) {
            count += queenCombination1D(boxes, tnq, i + 1, qpsf + 1, psf + "q" + i);
        }
        return count;
    }

    public static int queenPermutation1D(boolean[] boxes, int tnq, int qpsf, String psf) {
        if (tnq == qpsf) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < boxes.length; i++) {
            if (!boxes[i]) {
                boxes[i] = true;
                count += queenPermutation1D(boxes, tnq, qpsf + 1, psf + "q" + i);
                boxes[i] = false;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        boolean[] places = new boolean[5];
        // System.out.println(queenCombination1D(places, 3, 0, 0, ""));
        System.out.println(queenPermutation1D(places, 3, 0, ""));
    }
}
