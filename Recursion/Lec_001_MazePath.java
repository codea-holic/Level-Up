public class Lec_001_MazePath {
    
    public static int mazePath(int sr, int sc, int er, int ec, int [][] dir, String [] dirS, String psf){
        if(sr == er && sc == ec){
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r >= 0 && c >= 0 && r <= er && c <= ec){
                count += mazePath(r, c, er, ec, dir, dirS, psf + dirS[d]);
            }
        }
        return count;
    }

    public static int mazePathJumps(int sr, int sc, int er, int ec, int [][] dir, String [] dirS, String psf){
        if(sr == er && sc == ec){
            System.out.println(psf);
            return 1;
        }
        int count = 0;

        for(int d = 0; d < dir.length; d++){
            // change this for loop for better approach
            for(int rad = 1; rad < Math.max(er, ec); rad++){
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];
                if(r >= 0 && c >= 0 && r <= er && c <= ec){
                    count += mazePathJumps(r, c, er, ec, dir, dirS, psf + dirS[d] + rad);
                }
            }
            /* Another approach
            from line 29
                int r = sr + dir[d][0];
                int c = sc + dir[d][1];
                while(r >= 0 && c >= 0 && r <= er && c <= ec){
                    count += mazePathJumps(r, c, er, ec, dir, dirS, psf + dirS[d] + rad);
                    r = r + dir[d][0];
                    c = c + dir[d][1];
                }
            */
        }
        return count;
    }

    public static int floodFill(int sr, int sc, int er, int ec, int [][] visited, int [][] dir, String [] dirS, String psf){
        if(sr == er && sc == ec){
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(visited[sr][sc] == 0 && r >= 0 && c >= 0 && c <= ec && r <= er){
                visited[sr][sc] = 1;
                count += floodFill(r, c, er, ec, visited, dir, dirS, psf + dirS[d]);
                visited[sr][sc] = 0 ;
            }
        }

        return count;
    }

    public static int floodFillJumps(int sr, int sc, int er, int ec,  boolean [][] visited, int [][] dir,  String [] dirS, String psf){
        if(sr == er && sc == ec){
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        visited[sr][sc] = true;
        for(int d = 0; d < dir.length; d++){
            for(int rad = 1; rad <= Math.max(er, ec); rad++){
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];
                if(r >= 0 && c >= 0 && r <= er && c <= ec && !visited[r][c]){
                    count += floodFillJumps(r, c, er, ec, visited, dir, dirS, psf + dirS[d] + rad);
                }
            }
        }
        visited[sr][sc] = false;

        return count;
    }

    public static void main(String [] args){
        // int [][] dir = {{0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}};
        // String [] dirS = {"r", "w", "d", "n", "l", "e", "u", "s"};
        int [][] dir = {{0,1},{1,0}, {1,1}};
        String [] dirS = {"h", "v", "d"};
        // System.out.println("Rows " + dir.length);
        // System.out.println("Column " + dir[0].length);
        // boolean [][] visited = new boolean[3][3];
        // System.out.println(floodFill(0, 0, 2, 2, visited, dir, dirS, ""));
        // System.out.println(floodFillJumps(0, 0, 2, 2, visited, dir, dirS, ""));
        System.out.println(mazePath(0, 0, 2, 2, dir, dirS, ""));
        // System.out.println(mazePathJumps(0, 0, 2, 2, dir, dirS, ""));
    }
}
