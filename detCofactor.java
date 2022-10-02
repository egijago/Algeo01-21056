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
        int m= matrix.length;
        int n= matrix[0].length;
        float[][] temp = new float[n][m];
        for (int i =0;i<n;i++){
            for (int j = 0;j<m;j++){
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

    
    static float[][] multiply(float[][] m1,float[][] m2){
        float [][] temp = new float[m1.length][m2[0].length];
        for (int i=0;i<m1.length;i++){
            for (int j =0;j<m2[0].length;j++){
                temp[i][j] = 0;
                for (int k = 0;k<m2.length;k++){
                    temp[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return temp;
    }

    static void regression(float[][] matrix){
        // yang di input augmented matrix

        // inisialisasi matrix x dan y 
        float[][] Y = new float[matrix.length][1];
        float[][] X = new float[matrix.length][matrix[0].length];

        // assign nilai ke matrix x, dan y
        for (int i=0;i<matrix.length;i++ ){
            Y[i][0] = matrix[i][matrix[0].length-1];
            X[i][0] = 1;
            for (int j =1;j<matrix[0].length;j++){
                X[i][j] = matrix[i][j-1];
            }
        }
        // B = (Xt X)' Xt y
        float[][] B = multiply(multiply(inverse(multiply(transpose(X),X)),transpose(X)),Y);
        displayMatrix(B);
        String eq = "y =";
        for (int i =0;i<B.length;i++){
            if (B[i][0] == 0){
                continue;
            }
            else {
                if (i==0){
                    eq += String.format(" %f",B[0][0]);
                }
                else {
                    if (eq==""){
                        eq += String.format(" (%f)X%d",B[i][0],i);
                    }else{
                        eq += String.format(" + (%f)X%d",B[i][0],i);
                    }
                }
            }
        }
        System.out.println(eq);
        }

        public static void interpolation(float[][] titik){
            int n = titik.length;
            float[] y = new float[n];
            for(int i = 0; i < n; i++){
                y[i] = titik[i][1];
            }
            float[][] x = new float[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    x[i][j] = (float)Math.pow((double)titik[i][0],(double)j);
                }
            }
            // sudah dpt matrix x, solve dengan salah satu metode SPL
            determinan.cramer(x,y);
            
        }
       
    
      
    public static void main(String[] args){
        float[][] m = {
            {1,2,3},
            {2,3,4},
            {3,4,5},
            {5,6,7},
            {7,8,9}
        };
        // System.out.println(determinant(m));
        // displayMatrix(inverse(m));
        // interpolation(m);
        regression(m);
    }
}