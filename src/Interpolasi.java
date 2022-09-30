public class Interpolasi {
    public static double Interpolation(Matrix titik, double absis){
        Matrix x = new Matrix(titik.getNumRow(), titik.getNumRow());
        Matrix y = new Matrix(1, titik.getNumRow());

        for(int i = 0; i < titik.getNumRow(); i++){
            for(int j = 0; j < titik.getNumRow(); j++){
                x.setELMT(i, j, Math.pow(titik.getELMT(i,0), j));
            }
        }

        for(int i = 0; i < titik.getNumRow(); i++){
            y.setELMT(0, i, titik.getELMT(i,1));
        }

        Matrix result = new Matrix(1,x.getNumCol());
        result = Determinan.cramer(x, y);

        double res = 0;
        for(int i = 0; i < result.getNumCol(); i++){
            res += (result.getELMT(0,i) * Math.pow(absis,i));
        }
        return res;
    }
}
