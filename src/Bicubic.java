public class Bicubic {
    public static Matrix bindHorizontal(Matrix m1, Matrix m2){
        Matrix temp = new Matrix(m1.getNumRow(),m1.getNumCol()+m2.getNumCol());
        for (int i = 0;i<m1.getNumRow();i++){
            for (int j =0; j<m1.getNumCol();j++){
                temp.setELMT(i, j, m1.getELMT(i, j));
            }
            for (int j =m1.getNumCol(); j<m1.getNumCol()+m2.getNumCol();j++){
                temp.setELMT(i, j, m2.getELMT(i, j-m1.getNumCol()));
            }
        }
        return temp;
    }

    public static Matrix cutCol(Matrix matrix,int c1, int c2){
        Matrix temp = new Matrix(matrix.getNumRow(), c2-c1+1);
        for (int i = 0;i<matrix.getNumRow();i++){
            for (int j =0;j<c2-c1+1;j++){
                temp.setELMT(i, j, matrix.getELMT(i, j+c1));
            }
        }
        return temp;
    }
    public static Matrix identity(int n){
        Matrix temp = new Matrix(n, n);
        for (int i =0;i<n;i++){
            for (int j=0;j<n;j++){
                if (i==j){
                    temp.setELMT(i, j, 1);
                } else{
                    temp.setELMT(i, j, 0);
                }
            }
        }
        return temp;
    }
    static Matrix inverseGJ(Matrix matrix) {
        Matrix bond = bindHorizontal(matrix,identity(matrix.getNumRow()));
        // bond = gaussJordan(bond);
        bond = SPL.gaussJordan(bond);
        bond = cutCol(bond,matrix.getNumRow(),(matrix.getNumRow()*2)-1);
        return bond;
    }
    static float power(float val, int n){
        float res = 1;
        for (int i =0;i<=n;i++){
            res *= val;
        }
        return res;
    }
    static Matrix multiply(Matrix m1,Matrix m2){
        Matrix temp = new Matrix(m1.getNumRow(), m2.getNumCol());
        for (int i=0;i<m1.getNumRow();i++){
            for (int j =0;j<m2.getNumCol();j++){
                temp.setELMT(i, j, 0);
                for (int k = 0;k<m2.getNumRow();k++){
                    temp.setELMT(i, j, temp.getELMT(i, j)+m1.getELMT(i, k)*m2.getELMT(k, j));
                }
            }
        }
        return temp;
    }
    static void bicubicInterpolation(Matrix matrix,float a,float b){
        // akan dicari melalui ekspresi f(x,y) = X.A;
        // arrange matrix X;
        Matrix X = new Matrix(16, 16);
        for (int row=0 ;row<16;row++){
            int x = (row%4)-1;
            int y = (row/4)-1;
            for (int col =0;col<16;col++){
                int i = (col%4)-1;
                int j = (col/4)-1;
                X.setELMT(row, col, power(x, i)*power(y, j));
                // X[i][j] = power()*
            }
        }
        // X inverse
        Matrix Xinv = inverseGJ(X);

        // matrix f(x,y)
        Matrix f = new Matrix(16, 1);
        for (int i =0;i<16;i++){
            f.setELMT(i, 0, matrix.getELMT(i%4, i/4));;
        }

        // A = X inverse . f(x,y)
        Matrix A = multiply(Xinv,f);

        // f(a,b);
        float res = 0;
        for (int k=0;k<15;k++){
            int i = k%4;
            int j = k/4;
            res += A.getELMT(k, 0)*power(a,i)*power(b,j);
        }
        System.out.println(res);

    }

    
}
