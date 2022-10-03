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
}