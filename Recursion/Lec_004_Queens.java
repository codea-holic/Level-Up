public class Lec_004_Queens {

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
                count += queenPermutation1D(boxes, tnq, qpsf + 1, psf + "b" + i + "q" + qpsf + " ");
                boxes[i] = false;
            }
        }

        return count;
    }

    // Sub : subsequence Method
    public static int queenCombination1D_Sub(boolean[] boxes, int tnq, int bno, int qpsf, String psf) {
        if (tnq == qpsf) {
            System.out.println(psf);
            return 1;
        }
        if (bno == boxes.length) {
            return 0;
        }

        int count = 0;
        // yes call
        count += queenCombination1D_Sub(boxes, tnq, bno + 1, qpsf + 1, psf + "b" + bno + "q" + qpsf + " ");
        // no call
        count += queenCombination1D_Sub(boxes, tnq, bno + 1, qpsf, psf);
        return count;
    }

    public static int queenPermutation1D_Sub(boolean[] boxes, int idx, int tnq, int qpsf, String psf) {
        if (idx == boxes.length || qpsf == tnq) {
            if (qpsf == tnq) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (!boxes[idx]) {
            boxes[idx] = true;
            count += queenPermutation1D_Sub(boxes, 0, tnq, qpsf + 1, psf + "b" + idx + "q" + qpsf + " ");
            boxes[idx] = false;
        }
        count += queenPermutation1D_Sub(boxes, idx + 1, tnq, qpsf, psf);

        return count;
    }

    public static int queensCombination2D_Sub(boolean[][] boxes, int bno, int tnq, int qpsf, String psf) {
        if (bno >= boxes.length * boxes[0].length || tnq == qpsf) {
            if (tnq == qpsf) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        int r = bno / boxes[0].length;
        int c = bno % boxes[0].length;
        // yes
        count += queensCombination2D_Sub(boxes, bno + 1, tnq, qpsf + 1,
                psf + "b" + "[" + r + "]" + "[" + c + "]" + "q" + qpsf + " ");

        // no
        count += queensCombination2D_Sub(boxes, bno + 1, tnq, qpsf, psf);
        return count;
    }

    public static int queensCombination2D(boolean[][] boxes, int bno, int tnq, int qpsf, String psf) {
        if (tnq == qpsf) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        for (int i = bno; i < boxes.length * boxes[0].length; i++) {
            int r = i / boxes[0].length, c = i % boxes[0].length;
            count += queensCombination2D(boxes, i + 1, tnq, qpsf + 1,
                    psf + "b" + "[" + r + "]" + "[" + c + "]" + "q" + qpsf + " ");
        }
        return count;
    }

    public static int queensPermutation2D(boolean[][] boxes, int tnq, int qpsf, String psf) {
        if (tnq == qpsf) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < boxes.length * boxes[0].length; i++) {
            int r = i / boxes[0].length, c = i % boxes[0].length;
            if (!boxes[r][c]) {
                boxes[r][c] = true;
                count += queensPermutation2D(boxes, tnq, qpsf + 1,
                        psf + "b" + "[" + r + "]" + "[" + c + "]" + "q" + qpsf + " ");
                boxes[r][c] = false;
            }
        }

        return count;
    }

    public static int queensPermutation2D_Sub(boolean[][] boxes, int bno, int tnq, int qpsf, String psf) {
        if (bno >= boxes.length * boxes[0].length || tnq == qpsf) {
            if(tnq == qpsf){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        int r = bno / boxes[0].length;
        int c = bno % boxes[0].length;
        int count = 0;
        // yes
        if (!boxes[r][c]) {
            boxes[r][c] = true;
            count += queensPermutation2D_Sub(boxes, 0, tnq, qpsf + 1,
                    psf + "b" + "[" + r + "]" + "[" + c + "]" + "q" + qpsf + " ");
            boxes[r][c] = false;
        }
        // no
        count += queensPermutation2D_Sub(boxes, bno + 1, tnq, qpsf, psf);
        return count;
    }

    public static void main(String[] args) {
        boolean[] places = new boolean[5];
        // System.out.println(queenCombination1D(places, 3, 0, 0, ""));
        // System.out.println(queenCombination1D_Sub(places, 3, 0, 0, ""));
        // System.out.println(queenPermutation1D(places, 3, 0, ""));
        // System.out.println(queenPermutation1D_Sub(places, 0, 3, 0, ""));
        boolean[][] boxes = new boolean[3][3];
        // System.out.println(queensCombination2D_Sub(boxes, 0, 3, 0, ""));
        // System.out.println(queensCombination2D(boxes, 0, 3, 0, ""));
        // System.out.println(queensPermutation2D(boxes, 3, 0, ""));
        System.out.println(queensPermutation2D_Sub(boxes, 0, 3, 0, ""));
    }
}
