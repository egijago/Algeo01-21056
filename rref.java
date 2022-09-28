import java.text.Format;
import java.util.List;

public class rref{
    public static void displayMatrix(float[][] matrix){
        for (float[] i : matrix){
            System.out.print('[');
            for (float j : i) {
                System.out.print(j);
                System.out.print(' ');
            }
            System.out.print("]\n");
        }
    }

    static float[][] swap(float[][] matrix, int r1,int r2){
        float[] temp = matrix[r1];
        matrix[r1] = matrix[r2];
        matrix[r2] = temp;
        return matrix;
    }

    static float[][] sortZero(float[][] matrix, int col,int r1){
        for (int i=r1;i<matrix.length-1;i++){
            if (matrix[i][col]==0 && matrix[i+1][col]!=0){
                matrix = swap(matrix,i,i+1);
            }
        }
        return matrix;
    } 

    public static float[][] gauss(float[][] matrix){
        int n = matrix.length;
        int col_lead = 0;
        for (int i=0;i<n;i++){
            matrix = sortZero(matrix,col_lead,i);
            while (matrix[i][col_lead] == 0.0 && col_lead < matrix[0].length-2){
                col_lead+=1;
                matrix = sortZero(matrix,col_lead,i);
            }
            if(matrix[i][col_lead]==0.0){
                break;
            } else {
                float factor = matrix[i][col_lead];
                for (int j=0;j<matrix[0].length;j++){
                    matrix[i][j] *= 1/factor;
                }
            }

            for (int row=i+1;row<n;row++){
                float ratio = matrix[row][col_lead]/matrix[i][col_lead];
                for (int col=0;col<matrix[0].length;col++){
                    matrix[row][col] -= ratio * matrix[i][col];
                }
            }
            col_lead +=1;

        }
        return matrix;
    }

    static float[][] gaussJordan(float[][] matrix){
        int n = matrix.length;
        matrix = gauss(matrix);
        for (int i=n-1;i>0;i--){
            int col_lead = 0;
            while (matrix[i][col_lead]==0 && col_lead<(matrix[0].length - 2)){
                col_lead ++;
            }
            if (matrix[i][col_lead] == 0){
                continue;
            }
            for (int row =0;row<i;row++){
                float ratio = matrix[row][col_lead]/matrix[i][col_lead];
                for (int col = 0;col<matrix[0].length;col++){
                    matrix[row][col] -= (ratio * matrix[i][col]);
                }
            }
        }
        return matrix;
    }
    static float [][] elmCol (float[][] matrix, int col){
        // belum beres
        int m = matrix.length;
        int n = matrix[0].length;
        float[][] temp = new float[m][n-1];
        for (int i =0;i<m;i++){
            for (int j= 0; j<temp.length;j++){
                if (j<col){
                    temp[i][j] = matrix[i][j];
                }
                else{
                    //j>col
                    temp[i][j] = matrix[i][j+1];
                }
            }
        }
        return temp;
    }   
    static int searchInCol(String[][] matrix, int col, int val) {
        for (int i = 0;i<matrix.length;i++){
            if (Integer.valueOf(matrix[i][col])== val){
                return i;
                }
        }
        return -1;
    }

    static float[] append(float [] list1, float[] list2){
        float[] temp = new float[list1.length+list2.length];
        for (int i = 0;i<list1.length;i++){
            temp[i] = list1[i];
        }
        for (int i = list1.length; i<list1.length+list2.length;i++){
            temp[i] = list2[i-list1.length];
        }
        return temp;
    }

    static String[][] append(String[][] list1,String [][]list2){
        String[][] temp = new String[list1.length+list2.length][];
        for (int i = 0;i<list1.length;i++){
            temp[i] = list1[i];
        }
        for (int i = list1.length; i<list1.length+list2.length;i++){
            temp[i] = list2[i-list1.length];
        }
        return temp;
    }

    

    static void solve(float[][] matrix){
        String var[] ={"s","t","u","v","w","x","y","z","a","b"};
        int idx = 0;
        String parameter[][] = new String[0][];
        for (int row = 0 ;row<matrix.length;row++){
            int col_lead = 0;
            while (matrix[row][col_lead]==0){
                if (col_lead >= matrix[0].length-2){
                    break;
                }
                col_lead +=1;
            }
            if (matrix[row][col_lead]==0 && matrix[row][col_lead+1] == 0){
                // eq 0 = 0
                continue;
            }
            else if (matrix[row][col_lead] == 0 && matrix[row][col_lead+1] != 0){
                System.out.println("Unsolvable");
            }

            String eq = "";
            // Assign parameter ke variable selain leading one
            for (int col = col_lead+1;col<matrix[0].length-1;col++){
                if (matrix[row][col]==0){
                    continue;
                }
                else if (searchInCol(parameter,0,col+1) == -1){
                    // Variable belum di assign ke parameter
                    System.out.printf("X%d = %s\n",col+1,var[idx]);
                    String[][] lst = {{String.format("%d",col+1),var[idx]}};
                    parameter = append(parameter,lst);
                    idx +=1;
                }
                // Variable sudah di assing ke parameter
                if (eq==""){
                    float coef = -1 * matrix[row][col];
                    eq += String.format("(%f)%s",coef,parameter[searchInCol(parameter,0,col+1)][1]);
                }
                else {
                    float coef = -1 * matrix[row][col];
                    eq += String.format("+ (%f)%s",coef,parameter[searchInCol(parameter,0,col+1)][1]);
                }
            }
            if (eq==""){
                if (matrix[row][col_lead]==0){
                    eq = String.format("0 = ",col_lead+1) + eq+ String.format("(%f)",matrix[row][matrix[0].length-1]);
                }
                else{
                    eq = String.format("X%d = ",col_lead+1) + eq + String.format("(%f)",matrix[row][matrix[0].length-1]);
                }
                }
            else {
                if (matrix[row][matrix[0].length-1] == 0){
                    eq = String.format("X%d = ",col_lead+1) + eq;
                }
                else {
                    eq = String.format("X%d = ",col_lead+1) + eq + String.format("+ (%f)",matrix[row][matrix[0].length-1]);
                }
            }
            System.out.println(eq);
                
            }
        }


    public static void main(String[] args){
        float[][] m = {
            {1,1,-1,-1,1},
            {2,5,-7,-5,-2},
            {2,-1,1,3,4},
            {5,2,-4,2,6}
        };
        float[][] m2 = {
            {1,1,-1,1,0,0},
            {2,5,-7,0,1,0},
            {2,-1,1,0,0,1}};

        float[][] m3 = {
            {1,1,1,1,1},
            {1,1,0,0,0},
            {0,0,0,0,0}
        };
        // displayMatrix(gaussJordan(m2));
        displayMatrix(gaussJordan(m));
        solve(m3);
    }
}