public class detCofactor{
    static float[][] subMatrix(float[][] matrix, int xRow, int xCol){
        int n = matrix.length;
        float[][] temp = new float[n-1][n-1];
        
        int idxRow = 0;

        for (int i=0;i<n;i++) {
            int idxCol = 0;
            for (int j=0;j<n;j++) {
                if (i != xRow && j != xCol) {
                    temp[idxRow][idxCol] = matrix[i][j];
                    idxCol++;
                    if (idxCol == n-1) {
                        idxCol = 0;
                        idxRow++;
                    }
                } 
            }  
        }
        return temp;
    }

    static float determinant(float[][] matrix){
        int sign = 1;
        float det = 0 ;
        int n = matrix.length;

        if (n==1){
            return matrix[0][0];
        }
        
        float[][] cofactor = new float[n-1][n-1];

        for (int i=0;i<n;i++){
            cofactor = subMatrix(matrix,0,i);
            det += sign * matrix[0][i] * determinant(cofactor);
            sign *= -1;
        }
        return det;
    }
    public static void main(String[] args){
        float[][] m = {
            {1,1,-1,-1},
            {2,9,-7,-5},
            {2,-1,1,3},
            {5,2,-4,4}
        };
        System.out.println(determinant(m));}
}