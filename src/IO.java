import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.io.FileWriter;   
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;

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
        int n = 0;
        if(matrix1.getNumCol() > matrix2.getNumCol()){
            n = matrix1.getNumCol();
        }else{
            n = matrix2.getNumCol();
        }

        Matrix temp = new Matrix(matrix1.getNumRow()+matrix2.getNumRow(),n);
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

    public static void MatrixToFile(Matrix matrix, String path){
        String str = "";
        for(int row = 0; row < matrix.getNumRow(); row++){
            for(int col = 0; col < matrix.getNumCol(); col++){
                if(col == matrix.getNumCol()-1){
                    str += String.format("%f\n", matrix.getELMT(row,col));
                }else{
                    str += String.format("%f ", matrix.getELMT(row,col));
                }
            }
        }
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(str);
            writer.close();
            System.out.println("Berhasil menulis matriks ke dalam file.");
        } catch (IOException e){
            System.out.println("Gagal menulis matriks ke file.");
            e.printStackTrace();
        }
    }

    public static Matrix StringToList(String str){
        Matrix temp = new Matrix();
        String val = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' ' || str.charAt(i) == '\n'){
                Matrix elm = new Matrix(1,1);
                elm.setELMT(0,0, Double.valueOf(val));
                temp = append(temp,elm);
                val = "";
            }else{
                val += str.charAt(i);
                if(i == str.length()-1){
                    Matrix elmt = new Matrix(1,1);
                    elmt.setELMT(0, 0, Double.valueOf(val));
                    temp = append(temp,elmt);
                    val = "";
                }
            }
        }
        return temp;
    }

    public static Matrix FileToMatrix(String path){
        Matrix mat = new Matrix();
        try{
            File object = new File(path);
            Scanner reader = new Scanner(object);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                Matrix row = StringToList(data);
                mat = append2(mat,row);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan.");
            e.printStackTrace();
        }
        return mat;
    }

    public static Matrix inputTerminalToMatrix(){
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.print("Masukkan jumlah baris matrix: ");
        int baris = 0, kolom = 0;
        try{
            baris = Integer.parseInt(bufferedReader.readLine());
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.print("Masukkan jumlah kolom matrix: ");
        try{
            kolom = Integer.parseInt(bufferedReader.readLine());
        }catch(IOException e){
            e.printStackTrace();
        }

        Matrix output = new Matrix(baris,kolom);

        for(int i = 0; i < baris; i++){
            for(int j = 0; j < kolom; j++){
                System.out.printf("Masukkan elemen [%d][%d]: ",i,j);
                double elmt = 0;
                try{
                    elmt = Double.parseDouble(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }
                output.setELMT(i,j,elmt);
            }
        }
        return output;
    }

    public static Matrix inputTerminalToMatrixSqrt(){
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.print("Masukkan jumlah baris & kolom matrix: ");
        int n = 0;
        try{
            n = Integer.parseInt(bufferedReader.readLine());
        }catch(IOException e){
            e.printStackTrace();
        }

        Matrix output = new Matrix(n,n);

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.printf("Masukkan elemen [%d][%d]: ",i,j);
                double elmt = 0;
                try{
                    elmt = Double.parseDouble(bufferedReader.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }
                output.setELMT(i,j,elmt);
            }
        }
        return output;
    }

    public static void StringToFile(String str, String path){
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

    public static Matrix TitikInterpolasiToMatrix(Matrix matrix){
        Matrix hasil = new Matrix();
        int row = matrix.getNumRow();
        int col = matrix.getNumCol();
        Matrix titik = new Matrix(row-1,col);
        
        for(int i = 0; i < matrix.getNumRow()-1; i++){
            for(int j = 0; j < matrix.getNumCol(); j++){
                titik.setELMT(i, j, matrix.getELMT(i, j));
            }
        }

        hasil = Matrix.copyMatrix(titik);
        return hasil;
    }

    public static double TitikInterpolasiToAbsis(Matrix matrix){
        double x = 0;
        int row = matrix.getNumRow();
        x = matrix.getELMT(row-1,0);
        return x;
    }

    // public static void inputInterpolasi(Matrix matrix, double absis){

    // }

    public static void main(String[] args){
    }
}
