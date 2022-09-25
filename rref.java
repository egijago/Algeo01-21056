public class reff{
    static void displayMatrix(float[][] matrix){
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

    static float[][] gauss(float[][] matrix){
        int n = matrix.length;
        int col_lead = 0;
        for (int i=0;i<n-1;i++){
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
    
    public static void main(String[] args){
        float[][] m = {
            {1,1,-1,-1,1},
            {2,5,-7,-5,-2},
            {2,-1,1,3,4},
            {5,2,-4,2,6}
        };
        displayMatrix(gaussJordan(m));
    }
}