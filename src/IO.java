import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.io.FileWriter;   
import java.io.IOException;

public class IO {
    public static Matrix append(Matrix list1, Matrix list2){
        Matrix temp = new Matrix(1,list1.getNumCol()+list2.getNumCol());
        for(int i = 0; i < list1.getNumCol(); i++){
            temp.setELMT(0,i, list1.getELMT(0,i));
        }
        for(int i = list1.getNumCol(); i < list1.getNumCol()+list2.getNumCol(); i++){
            temp.setELMT(0,i, list2.getELMT(0,i-list1.getNumCol()));
        }
        return temp;
    }

    public static Matrix append2(Matrix matrix1, Matrix matrix2){
        Matrix temp = new Matrix(matrix1.getNumRow()+matrix2.getNumRow(),matrix1.getNumCol());
        for(int i = 0; i < matrix1.getNumRow(); i++){
            for(int j = 0; j < matrix1.getNumCol(); j++){
                temp.setELMT(i,j, matrix1.getELMT(i,j));
            }
        }
        for(int i = matrix1.getNumRow(); i < matrix1.getNumRow()+matrix2.getNumRow(); i++){
            for(int j = 0; j < matrix2.getNumCol(); j++){
                temp.setELMT(i,j, matrix2.getELMT(i-matrix1.getNumRow(),j));
            }
        }
        return temp;
    }

    public static void MatToTxt(Matrix matrix, String path){
        String str = "";
        for(int row = 0; row < matrix.getNumRow(); row++){
            for(int col = 0; col < matrix.getNumCol(); col++){
                if(col == matrix.getNumCol()-1){
                    str += String.format("%f\n", matrix.getELMT(row,col));
                }else{
                    str += String.format("%f", matrix.getELMT(row,col));
                }
            }
        }
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(str);
            writer.close();
            System.out.println("Berhasil menulis matrix ke dalam file.");
        } catch (IOException e){
            System.out.println("Gagal menulis matrix ke file.");
            e.printStackTrace();
        }
    }

    public static Matrix StrToList(String str){
        Matrix temp = new Matrix();
        String val = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' ' || str.charAt(i) == '\n'){
                
            }
        }
    }
}
