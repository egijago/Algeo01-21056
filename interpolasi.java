import java.math.BigDecimal;
import java.math.RoundingMode;


public class interpolasi {
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

    public static double[] cramer(float[][] matrix, float[] hasil){
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
        return result;
    }

    public static double interpolation(float[][] titik, double absis){
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
        double [] result = cramer(x,y);
        double val = 0;
        for (int i = 0;i<result.length;i++){
            val += result[i] * Math.pow(absis,(double)i);
        }
        System.out.println(val);
        return val;

    }

    public static void main(String[] args) {
        float[][] m = {
            {1,4},
            {3,4},
            {2,6},
            {5,8},
            {9,15}
        };
        // System.out.println(determinant(m));
        // displayMatrix(inverse(m));
        // interpolation(m);
        interpolation(m,0);
    }
}
