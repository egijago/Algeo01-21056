public class Interpolasi {
    public static double Interpolation(Matrix titik, double absis){
        Matrix x = new Matrix(titik.getNumRow(), titik.getNumRow()+1);

        for(int i = 0; i < titik.getNumRow(); i++){
            for(int j = 0; j < titik.getNumRow(); j++){
                x.setELMT(i, j, Math.pow(titik.getELMT(i,0), j));
            }
        }

        for(int i = 0; i < titik.getNumRow(); i++){
            x.setELMT(i, titik.getNumRow(), titik.getELMT(i,1));
        }

        Matrix result = new Matrix(1,x.getNumRow());
        result = SPL.cramer(x,false);

        double res = 0;
        for(int i = 0; i < result.getNumCol(); i++){
            res += (result.getELMT(0,i) * Math.pow(absis,i));
        }

        String fx = "f(x) = ";
        for(int i = 0; i < result.getNumCol(); i++){
            if(i == 0){
                fx += String.format("%f + ", result.getELMT(0,i));
            }else if(i == 1){
                fx += String.format("(%f)x + ",result.getELMT(0,i));
            }else if(i > 1 && i < result.getNumCol()-1){
                fx += String.format("(%f)x^%d + ", result.getELMT(0,i),i);
            }else if(i == result.getNumCol()-1){
                fx += String.format("(%f)x^%d",result.getELMT(0,i),i );
            }
        }
        System.out.println(fx);
        System.out.printf("Taksiran untuk f(%f) ialah: %f\n",absis, res);
        return res;
    }
    public static void main(String[] args) {
        Matrix coba = new Matrix(7,2);
        coba.setELMT(0, 0, 0.1);
        coba.setELMT(0, 1, 0.003);
        coba.setELMT(1, 0, 0.3);
        coba.setELMT(1, 1, 0.067);
        coba.setELMT(2, 0, 0.5);
        coba.setELMT(2, 1, 0.148);
        coba.setELMT(3, 0, 0.7);
        coba.setELMT(3, 1, 0.248);
        coba.setELMT(4, 0, 0.9);
        coba.setELMT(4, 1, 0.370);
        coba.setELMT(5, 0, 1.1);
        coba.setELMT(5, 1, 0.518);
        coba.setELMT(6, 0, 1.3);
        coba.setELMT(6, 1, 0.697);

        double hasil = Interpolation(coba, 1.28);
        System.out.println(hasil);
    }
}