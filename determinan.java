import java.math.BigDecimal;
import java.math.RoundingMode;

public class determinan {

    public static void main(String[] args){
        float[][] matrix = {
            {1,0,0},
            {0,1,0},
            {0,0,1}
        };

        float[][] m = {
            {1,3,4,5,6},
            {5,4,2,3,1},
            {7,4,2,1,4},
            {2,7,3,2,5},
            {6,1,6,9,0}
        };

        float[] n = {10,2,3};

        cramer(matrix,n);
        System.out.printf("%f",determinan(m));
    }
    public static float[][] copy_matrix(float[][] matrix){
        int n = matrix.length;
        float copy[][] = new float[n][n];

        for(int i=0; i<n;i++){
            for(int j=0; j<n;j++){
                copy[i][j] = matrix[i][j];
            }
        }

        return copy;
    }

    public static float[][] swapRow(float[][] matrix, int a, int b){
        float[][] copy = copy_matrix(matrix);
        float[] temp = copy[a];
        copy[a] = copy[b];
        copy[b] = temp;
        return copy;
    }

    public static void display(float[][] matrix){
        int n = matrix.length;
        for(int i=0; i<n; i++){
            System.out.println(matrix[i]);
        }
    }

    public static float[][] replaceCol(float[][] matrix, float[] rep, int col){
        int kolom = matrix.length;
        float[][] copy = copy_matrix(matrix);
        for(int i=0; i<kolom; i++){
            copy[i][col] = rep[i];
        }
        return copy;
    }

    public static double determinan(float[][] matrix){
        int n = matrix.length;
        float[][] M = copy_matrix(matrix);
        double p = 0;
        double prod = 1.0;

        for(int d=0; d<n; d++){
            float pivot = M[d][d];
            if(pivot == 0){
                boolean found = false;
                for(int j=d+1; j<n; j++){
                    if(M[j][d] != 0){
                        M = swapRow(M, d, j);
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
                pivot = M[d][d];
                for(int i=d+1; i<n; i++){
                    float crScaler = M[i][d] / pivot;
                    for(int j=0; j<n; j++){
                        M[i][j] = M[i][j] - crScaler * M[d][j];
                    }
                }
            }
        }
        for(int i=0; i<n; i++){
            prod *= M[i][i];
        }

        prod = prod * (Math.pow(-1,p));
        BigDecimal bd = new BigDecimal(Double.toString(prod));
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    public static void cramer(float[][] matrix, float[] hasil){
        int n = matrix.length;
        double[] result = new double[n];
        float[][] copy = copy_matrix(matrix);
        double det = determinan(matrix);

        if(det != 0){
            for(int i=0; i<n; i++){
                float[][] mat = replaceCol(copy,hasil,i);
                result[i] = determinan(mat)/det;
            }

            System.out.println("Hasil SPL:");
            for(int i=0; i<n; i++){
                System.out.printf("x%d = %.2f\n",i+1,result[i]);
            }
        }else{
            System.out.println("SPL tidak mempunyai solusi tunggal.");
        }
    }
}