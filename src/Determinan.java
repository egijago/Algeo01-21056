import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.FileWriter;
import java.io.IOException;

public class Determinan {

    public static Matrix subMatrix(Matrix matrix, int xRow, int xCol){
        int n = matrix.getNumRow();
        Matrix temp = new Matrix(n-1, n-1);

        int idxRow = 0; 
        
        for(int i = 0; i < n; i++){
            int idxCol = 0;
            for(int j = 0; j < n; j++){
                if(i != xRow && j != xCol){
                    temp.setELMT(idxRow, idxCol, matrix.getELMT(i, j));
                    idxCol++;
                    if(idxCol == (n-1)){
                        idxCol = 0;
                        idxRow++;
                    }
                }
            }
        }
        return temp;
    }

    public static double detRowRed(Matrix matrix){
        int n = matrix.getNumRow();
        Matrix copy = Matrix.copyMatrix(matrix);
        double p = 0;
        double prod = 1.0;

        for(int d = 0; d < n; d++){
            double pivot = copy.getDiagonalELMT(d);
            if(pivot == 0){
                boolean found = false;
                for(int j  = d + 1; j < n; j++){
                    if(copy.getELMT(j, d) != 0){
                        copy = Matrix.swapRow(copy, d, j);
                        p += 1;
                        found = true;
                        break;
                    }

                    if(!found){
                        prod = 0;
                    }
                }
            }
            if(prod != 0){
                pivot = copy.getDiagonalELMT(d);
                for(int i = d + 1; i < n; i++){
                    double crScaler = copy.getELMT(i, d) / pivot;
                    for(int j = 0; j < n; j++){
                        // M[i][j] = M[i][j] - crScaler * M[d][j];
                        copy.setELMT(i, j, copy.getELMT(i,j) - (copy.getELMT(d,j) * crScaler));
                    }
                }
            }
        }

        for(int i = 0; i < n; i++){
            prod *= copy.getDiagonalELMT(i);
        }

        prod = prod * (Math.pow(-1,p));
        BigDecimal bd = new BigDecimal(Double.toString(prod));
        bd = bd.setScale(100, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    public static double detCofactor(Matrix matrix){
        int sign = 1;
        double det = 0;
        int n = matrix.getNumRow();

        if(n==1){
            return matrix.getELMT(0, 0);
        }

        Matrix cofactor = new Matrix(n-1,n-1);
        for(int i = 0; i < n; i++){
            cofactor = subMatrix(matrix,0,i);
            det += sign * matrix.getELMT(0, i) * detCofactor(cofactor);
            sign *= -1;
        }
        return det;
    }

    public static void fileDeterminan(double det, String path){
        String str = String.format("Hasil determinan matrix adalah %.4f",det);
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(str);
            writer.close();
            System.out.println("Berhasil menulis hasil ke dalam file.");
        } catch (IOException e){
            System.out.println("Gagal menulis hasil ke file.");
            e.printStackTrace();
        }
    }
}
