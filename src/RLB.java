public class RLB {
    public static String regression(Matrix matrix){
        int row = matrix.getNumRow();
        int col = matrix.getNumCol();

        Matrix y = new Matrix(row,1);
        Matrix x = new Matrix(row, col);

        for(int i = 0; i < row; i++){
            y.setELMT(i, 0, matrix.getELMT(i, col-1));
            x.setELMT(i, 0, 1);

            for(int j = 1; j < col; j++){
                x.setELMT(i, j, matrix.getELMT(i, j-1));
            }
        }

        Matrix b = Matrix.multiplyMatrix(Matrix.multiplyMatrix(Inverse.inverseCofactor(Matrix.multiplyMatrix(Matrix.transpose(x), x)), Matrix.transpose(x)),y);

        b.displayMatrix();
        String eq = "y = ";
        for(int i = 0; i < b.getNumRow(); i++){
            if(b.getELMT(i,0) == 0){
                continue;
            }
            else{
                if (i==0){
                    eq += String.format("%f",b.getELMT(0, 0));
                }
                else{
                    if(eq == ""){
                        eq += String.format("(%f)x%d", b.getELMT(i,0),i);
                    }else{
                        eq += String.format(" + (%f)x%d", b.getELMT(i,0),i);
                    }
                }
            }
        }
        System.out.println(eq);
        return eq;
    }
}
