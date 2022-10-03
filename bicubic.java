public class bicubic extends rref {
    static float[][] bindHorizontal(float[][] m1, float[][] m2){
        float[][] temp = new float [m1.length][m1[0].length+m2[0].length];
        for (int i = 0;i<m1.length;i++){
            for (int j =0; j<m1[0].length;j++){
                temp[i][j] = m1[i][j];
            }
            for (int j =m1[0].length; j<m1[0].length+m2[0].length;j++){
                temp[i][j] = m2[i][j-m1[0].length];
            }
        }
        return temp;
    }

    static float[][] cutCol(float[][] matrix,int c1, int c2){
        float[][] temp = new float[matrix.length][c2-c1+1];
        for (int i = 0;i<matrix.length;i++){
            for (int j =0;j<c2-c1+1;j++){
                temp[i][j] = matrix[i][j+c1];
            }
        }
        return temp;
    }
    static float[][] identity(int n){
        float[][] temp = new float[n][n];
        for (int i =0;i<n;i++){
            for (int j=0;j<n;j++){
                if (i==j){
                    temp[i][j] = 1;
                } else{
                    temp[i][j] = 0;
                }
            }
        }
        return temp;
    }
    static float[][] inverseGJ(float[][] matrix) {
        float [][] bond = bindHorizontal(matrix,identity(matrix.length));
        // bond = gaussJordan(bond);
        System.out.println(bond);
        bond = cutCol(bond,matrix.length,(matrix.length*2)-1);
        return bond;
    }
    static float power(float val, int n){
        float res = 1;
        for (int i =0;i<=n;i++){
            res *= val;
        }
        return res;
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
    static void bicubicInterpolation(float[][] matrix,float a,float b){
        // akan dicari melalui ekspresi f(x,y) = X.A;
        // arrange matrix X;
        float [][] X = new float[16][16];
        for (int row=0 ;row<16;row++){
            int x = (row%4)-1;
            int y = (row/4)-1;
            for (int col =0;col<16;col++){
                int i = (col%4)-1;
                int j = (col/4)-1;
                X[row][col] = power(x,i)*power(y,j);
                // X[i][j] = power()*
            }
        }
        // X inverse
        float[][] Xinv = inverseGJ(X);

        // matrix f(x,y)
        float[][] f = new float[16][1];
        for (int i =0;i<16;i++){
            f[i][0] = matrix[i%4][i/4];
        }

        // A = X inverse . f(x,y)
        float[][] A = multiply(Xinv,f);

        // f(a,b);
        float res = 0;
        for (int k=0;k<15;k++){
            int i = k%4;
            int j = k/4;
            res += A[k][0]*power(a,i)*power(b,j);
        }
        System.out.println(res);

    }
    

    public static void main(String[] args){
        float [][] mat ={
            {153,59,210,96},
            {125,161,72,81},
            {98,101,42,12},
            {21,51,0,16}
        };
        bicubicInterpolation(mat,0,0);
    }
}