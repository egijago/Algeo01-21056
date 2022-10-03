import java.io.FileWriter;
import java.io.IOException;

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
        return res;
    }

    public static void fileInterpolation(Matrix titik, double absis, String path){
        Matrix x = new Matrix(titik.getNumRow(), titik.getNumRow()+1);

        for(int i = 0; i < titik.getNumRow(); i++){
            for(int j = 0; j < titik.getNumRow(); j++){
                x.setELMT(i, j, Math.pow(titik.getELMT(i,0), j));
            }
        }

        for(int i = 0; i < titik.getNumRow(); i++){
            x.setELMT(i, titik.getNumRow(), titik.getELMT(i,1));
        }

        Matrix hasil = new Matrix(1,x.getNumRow());
        hasil = SPL.cramer(x,false);
        String str = "f(x) = ";
        for(int i = 0; i < hasil.getNumCol(); i++){
            if(i == 0){
                str += String.format("%f + ", hasil.getELMT(0,i));
            }else if(i == 1){
                str += String.format("(%f)x + ",hasil.getELMT(0,i));
            }else if(i > 1 && i < hasil.getNumCol()-1){
                str += String.format("(%f)x^%d + ", hasil.getELMT(0,i),i);
            }else if(i == hasil.getNumCol()-1){
                str += String.format("(%f)x^%d\n",hasil.getELMT(0,i),i );
            }
        }
        double res = 0;
        for(int i = 0; i < hasil.getNumCol(); i++){
            res += (hasil.getELMT(0,i) * Math.pow(absis,i));
        }

        str += String.format("Taksiran untuk f(%f) ialah: %f\n",absis, res);
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
    public static void main(String[] args) {
        Matrix test = new Matrix(7,2);
        test.setELMT(0,0,0.4);
        test.setELMT(0,1,0.043);
        test.setELMT(1,0,0.7);
        test.setELMT(1,1,0.005);
        test.setELMT(2,0,0.11);
        test.setELMT(2,1,0.058);
        test.setELMT(3,0,0.14);
        test.setELMT(3,1,0.072);
        test.setELMT(4,0,0.17);
        test.setELMT(4,1,0.1);
        test.setELMT(5,0,0.2);
        test.setELMT(5,1,0.13);
        test.setELMT(6,0,0.23);
        test.setELMT(6,1,0.147);
        
        fileInterpolation(test, 0.2, "../test/hasilinterpolasi1.txt");
    }
    
}