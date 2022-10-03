public class Inverse {

    public static Matrix inverseGJ (Matrix matrix){
        int n = matrix.getNumRow();
        Matrix invers = new Matrix(n,n*2);
        Matrix hasil = new Matrix(n,n);

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                invers.setELMT(i,j,matrix.getELMT(i,j));
            }
        }

        for(int p = 0; p < n; p++){
            for(int q = 0; q < n; q++){
                if(p == q){
                    invers.setELMT(p, q+n, 1);
                }else if(p != q){
                    invers.setELMT(p, q+n, 0);
                }
            }
        }

        invers = SPL.rref(invers);

        for(int i = 0; i < n; i++){
            for(int j = n; j < 2*n; j++){
                hasil.setELMT(i, j-n, (hasil.getELMT(i, j-n) + invers.getELMT(i,j)));
            }
        }
        return hasil; 
    }

    public static Matrix inverseCofactor(Matrix matrix){
        int n = matrix.getNumRow();
        Matrix cofactor = new Matrix(n,n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if ((i+j) % 2 == 0){
                    cofactor.setELMT(i,j, Determinan.detCofactor(Determinan.subMatrix(matrix,i,j)));
                }
                else{
                    cofactor.setELMT(i,j, -1*(Determinan.detCofactor(Determinan.subMatrix(matrix,i,j))));
                }
            }
        }
        cofactor = Matrix.transpose(cofactor);
        double det = Determinan.detCofactor(matrix);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                cofactor.setELMT(i,j, cofactor.getELMT(i,j) * (1/det));
            }
        }
        return cofactor;
    }

    public static void fileInverse(Matrix matrix, String path){
        IO.MatrixToFile(matrix, path);
    }
}