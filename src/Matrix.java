public class Matrix {
    private int row, col;
    private double matrix[][];

    // KONSTRUKTOR MATRIX
    public Matrix(){
        this.row = 0;
        this.col = 0;
    }

    public Matrix(int row, int col){
        this.row = row;
        this.col = col;
        this.matrix = new double[row][col];
    }

    public Matrix(int row, int col, double c){
        this.row = row;
        this.col = col;
        this.matrix = new double [row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                this.matrix[i][j] = c;
            }
        }
    }

    // GET AND SET
    public int getNumRow(){
        return this.row;
    }

    public int getNumCol(){
        return this.col;
    }

    public double getELMT(int row, int col){
        return this.matrix[row][col];
    }

    public void setELMT(int row, int col, double value){
        this.matrix[row][col] = value;
    }

    public double getDiagonalELMT(int d){
        return this.matrix[d][d];
    }

    // operasi primitif matrix
    public boolean isSquare(){
        return this.getNumRow() == this.getNumCol();
    }

    public boolean isIdentity(){
        boolean isIdentity = this.isSquare();
        if(isIdentity){
            for(int i = 0; i < this.row; i++){
                for(int j = 0; j < this.col; j++){
                    if (i==j){
                        if(this.getELMT(i,j) != 1){
                            isIdentity = false;
                            break;
                        }
                    }else{
                        if(this.getELMT(i,j) != 0){
                            isIdentity = false;
                            break;
                        }
                    }
                }
            }
        }
        return isIdentity;
    }

    public void displayMatrix(){
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.col; j++){
                System.out.print(this.matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static Matrix copyMatrix(Matrix a){
        Matrix res = new Matrix(a.getNumRow(),a.getNumCol());
        for(int i = 0; i < a.getNumRow(); i++){
            for(int j = 0; j < a.getNumCol(); j++){
                res.matrix[i][j] = a.matrix[i][j];
            }
        }
        return res;
    }

    public static Matrix swapRow(Matrix matrix, int row1, int row2){
        Matrix hasil = new Matrix();
        hasil = copyMatrix(matrix);
        for(int i = 0; i < matrix.getNumCol(); i++){
            hasil.setELMT(row1, i, matrix.getELMT(row2, i));
            hasil.setELMT(row2, i, matrix.getELMT(row1, i));
        }
        return hasil;
    }

    public static Matrix swapCol(Matrix matrix, int col1, int col2){
        Matrix hasil = new Matrix();
        hasil = copyMatrix(matrix);
        for(int j = 0; j < matrix.getNumRow(); j++){
            hasil.setELMT(j, col1, matrix.getELMT(j, col2));
            hasil.setELMT(j, col2, matrix.getELMT(j, col1));
        }
        return hasil;
    }

    public static Matrix transpose(Matrix matrix){
        Matrix hasil = new Matrix(matrix.getNumCol(), matrix.getNumRow());
        for(int i = 0; i < matrix.getNumCol(); i++){
            for(int j = 0; j < matrix.getNumRow(); j++){
                hasil.setELMT(i,j, matrix.getELMT(j,i));
            }
        }
        return hasil;
    }

    public static Matrix addMatrix(Matrix a, Matrix b){
        Matrix c = new Matrix(a.getNumRow(), a.getNumCol());
        for(int i = 0; i < a.getNumRow(); i++){
            for(int j = 0; j < a.getNumCol(); j++){
                c.setELMT(i, j, a.getELMT(i,j) + b.getELMT(i,j));
            }
        }
        return c;
    }

    public static Matrix subtractMatrix(Matrix a, Matrix b){
        Matrix c = new Matrix(a.getNumRow(), a.getNumCol());
        for(int i = 0; i < a.getNumRow(); i++){
            for(int j = 0; j < a.getNumCol(); j++){
                c.setELMT(i, j, a.getELMT(i,j) - b.getELMT(i,j));
            }
        }
        return c;
    }

    public static double timesRowCol(Matrix a, Matrix b, int row, int col){
        double res = 0;
        for(int i = 0; i < a.getNumCol(); i++){
            res += a.getELMT(row, i) * b.getELMT(i, col);
        }
        return res;
    }

    public static Matrix multiplyMatrix(Matrix a, Matrix b){
        Matrix c = new Matrix(a.getNumRow(), b.getNumCol());
        for(int i = 0; i < a.getNumRow(); i++){
            for(int j = 0; j < b.getNumCol(); j++){
                double res = timesRowCol(a, b, i, j);
                c.setELMT(i, j, res);
            }
        }
        return c;
    }

    public static void rowTimesK(Matrix matrix, double k, int row){
        for(int i = 0; i < matrix.getNumRow(); i++){
            matrix.setELMT(row, i, k * matrix.getELMT(row, i));
        }
    }

    public static void splitAugmentedMatrix(Matrix augmented, Matrix square, Matrix hasil){
        int colSquare = augmented.getNumCol()-1;
        int rowSquare = augmented.getNumRow();
        for(int i = 0; i < rowSquare; i++){
            for(int j = 0; j < colSquare; j++){
                square.setELMT(i,j, augmented.getELMT(i, j));
            }
        }
        for(int k = 0; k < rowSquare; k++){
            hasil.setELMT(0,k, augmented.getELMT(k, colSquare));
        }
    }
}
