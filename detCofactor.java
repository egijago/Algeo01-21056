public class detCofactor{
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
    static float[][] transpose(float[][] matrix){
        int n = matrix.length;
        float[][] temp = new float[n][n];
        for (int i =0;i<n;i++){
            for (int j = 0;j<n;j++){
                temp[i][j] = matrix[j][i];
            }
        }
        return temp;
    }
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

    static float[][] inverse(float[][] matrix){
        int n = matrix.length;
        float[][] cofactor = new float[n][n]; 
        for (int i = 0;i<n;i++){
            for (int j = 0;j<n;j++){
                if ((i+j)%2==0){
                    cofactor[i][j] = determinant(subMatrix(matrix,i,j));
                }
                else {
                    cofactor[i][j] = -1 * determinant(subMatrix(matrix,i,j));
                }
            }
        }
        // cofactor udah jadi
        cofactor = transpose(cofactor);
        float det = determinant(matrix);
        for (int i = 0;i<n;i++){
            for (int j = 0;j<n;j++){
                cofactor[i][j] *= 1/det;
            }
        }
        return cofactor;
    }
    public static void main(String[] args){
        float[][] m = {
            {1,1,-1,-1},
            {2,9,-7,-5},
            {2,-1,1,3},
            {5,2,-4,4}
        };
        System.out.println(determinant(m));
        displayMatrix(inverse(m));

    }
}