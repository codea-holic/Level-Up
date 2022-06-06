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

    // Sub : subsequence Method, qpsf : queen placed so far
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
            if (tnq == qpsf) {
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

    public static boolean isQueenSafe(boolean[][] boxes, int r, int c) {

        for (int j = 0, i = r; j < boxes[0].length; j++) {
            if (boxes[i][j]) {
                return false;
            }
        }

        for (int i = 0, j = c; i < boxes.length; i++) {
            if (boxes[i][j]) {
                return false;
            }
        }

        for (int i = r, j = c; i < boxes.length && j < boxes[0].length; i++, j++) {
            if (boxes[i][j])
                return false;
        }

        for (int i = r, j = c; i >= 0 && j >= 0; i--, j--) {
            if (boxes[i][j])
                return false;
        }

        for (int i = r, j = c; i >= 0 && j < boxes[0].length; i--, j++) {
            if (boxes[i][j])
                return false;
        }

        for (int i = r, j = c; j >= 0 && i < boxes.length; i++, j--) {
            if (boxes[i][j])
                return false;
        }

        return true;
    }

    /**
     * Not Optimized:
     * We not need to check for each and every box because, We can say that every
     * row contains only one queen So, as soon as we placed any queen we change the
     * row, then it will be little Optimized. However, there are so many optimization
     * we can do in nQueen problem.
     * @tnq : total no. of Queens
     * @qpsf : queen placed so far
     * @psf : path so far
     */
    public static int nQueens(boolean[][] boxes, int bno, int tnq, int qpsf, String psf) {
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
        if (isQueenSafeVector(boxes, r, c)) {
            boxes[r][c] = true;
            count += nQueens(boxes, bno + 1, tnq, qpsf + 1, psf + "(" + r + "," + c + ")" + " ");
            boxes[r][c] = false;
        }
        count += nQueens(boxes, bno + 1, tnq, qpsf, psf);
        return count;
    }


    public static boolean isQueenSafeVector(boolean [][] board, int r, int c){

        int [][] dir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {1, 0}, {0, 1}, {1, 1}, {1, -1}};
        int n = board.length;
        for(int d = 0; d < dir.length; d++){
            for(int rad = 1; rad <= n; rad++){
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];

                if(x >= 0 && y >= 0 && x < n && y < n){
                    if(board[x][y]) return false;
                } else break;
            }
        }
        return true;
    }

    // Time complexity: (n)^q;
    public static int nQueenAlternative(boolean[][] boxes, int rowNo, int tnq, String psf) {
        if(rowNo >= boxes.length || tnq == 0){
            if(tnq == 0){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        for(int i = rowNo, j = 0; j < boxes[0].length; j++){
            if(isQueenSafeVector(boxes, i, j)){
                boxes[i][j] = true;
                count += nQueenAlternative(boxes, rowNo + 1, tnq - 1, psf + "(" + i + "," + j + ")" + " ");
                boxes[i][j] = false;
            }
            count += nQueenAlternative(boxes, rowNo + 1, tnq, psf);
        }
        return count;
    }

    // Time complexity: (n X n)^q;
    public static int nQueensPermutation(boolean [][] board, int tnq, String psf){
        if(tnq == 0){
            System.out.println(psf);
            return 1;
        }

        int count = 0, n = board.length; 
        for(int i = 0; i < n * n; i++){
            int r = i / n, c = i % n;
            if(!board[r][c] && isQueenSafeVector(board, r, c)){
                board[r][c] = true;
                count += nQueensPermutation(board, tnq - 1, psf + "(" + r + "," + c + ")" + " ");
                board[r][c] = false;
            }
        }

        return count;
    }


    // From 8th April
    static boolean[] rows, cols, diags, adiags;
    public static int nqueen_01_BranchAndBound(int n, int tnq, int bno, String psf) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int bidx = bno; bidx < n * n; bidx++) {
            int r = bidx / n, c = bidx % n;
            if (!rows[r] && !cols[c] && !diags[r - c + n - 1] && !adiags[r + c]) {
                rows[r] = cols[c] = diags[r - c + n - 1] = adiags[r + c] = true;
                count += nqueen_01_BranchAndBound(n, tnq - 1, bidx + 1, psf + "(" + r + "," + c + ") ");
                rows[r] = cols[c] = diags[r - c + n - 1] = adiags[r + c] = false;
            }
        }
        return count;
    }
    
    static int row = 0, col = 0, diag = 0, adiag = 0;
    public static int nqueen_02(int n, int tnq, int bno, String psf) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int bidx = bno; bidx < n * n; bidx++) {
            int r = bidx / n, c = bidx % n;
            if ((row & (1 << r)) == 0 && (col & (1 << c)) == 0 && (diag & (1 << (r - c + n - 1))) == 0
                    && (adiag & (1 << (r + c))) == 0) {
                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r - c + n - 1));
                adiag ^= (1 << (r + c));

                count += nqueen_02(n, tnq - 1, bidx + 1, psf + "(" + r + "," + c + ") ");

                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r - c + n - 1));
                adiag ^= (1 << (r + c));
            }
        }
        return count;
    }

    
    public static void main(String[] args) {
        // boolean[] places = new boolean[5];
        // System.out.println(queenCombination1D(places, 3, 0, 0, ""));
        // System.out.println(queenCombination1D_Sub(places, 3, 0, 0, ""));
        // System.out.println(queenPermutation1D(places, 3, 0, ""));
        // System.out.println(queenPermutation1D_Sub(places, 0, 3, 0, ""));
        boolean[][] boxes = new boolean[4][4];
        // System.out.println(queensCombination2D_Sub(boxes, 0, 3, 0, ""));
        // System.out.println(queensCombination2D(boxes, 0, 3, 0, ""));
        // System.out.println(queensPermutation2D(boxes, 3, 0, ""));
        // System.out.println(queensPermutation2D_Sub(boxes, 0, 3, 0, ""));
        // System.out.println(nQueens(boxes, 0, 4, 0, ""));
        // System.out.println(nQueenAlternative(boxes, 0, 4, ""));
        System.out.println(nQueensPermutation(boxes, 4, ""));
        int n = 4, tnq = 4;
        // rows = new boolean[n];
        // cols = new boolean[n];
        // diags = new boolean[n + n - 1];
        // adiags = new boolean[n + n - 1];
        // System.out.println(nqueen_01_BranchAndBound(n, tnq, 0, ""));
    }
}
