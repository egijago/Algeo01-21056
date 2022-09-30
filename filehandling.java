import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.io.FileWriter;   
import java.io.IOException;

public class filehandling {
    static float[] append(float [] list1, float[] list2){
        float[] temp = new float[list1.length+list2.length];
        for (int i = 0;i<list1.length;i++){
            temp[i] = list1[i];
        }
        for (int i = list1.length; i<list1.length+list2.length;i++){
            temp[i] = list2[i-list1.length];
        }
        return temp;
    }
    static float[][] append(float[][] list1,float [][]list2){
        float[][] temp = new float[list1.length+list2.length][];
        for (int i = 0;i<list1.length;i++){
            temp[i] = list1[i];
        }
        for (int i = list1.length; i<list1.length+list2.length;i++){
            temp[i] = list2[i-list1.length];
        }
        return temp;
    }
    static void MatToTxt(float[][] matrix,String path){
        // mengembalikan matrix untuk diformat pada txt
        String str = "";
        for (int row = 0; row < matrix.length; row++){
            for (int col =0; col<matrix[0].length;col++){
                // kolom terakhir
                if (col==matrix[0].length-1){
                    str += String.format("%f\n",matrix[row][col]);
                } else{
                    str += String.format("%f ",matrix[row][col]);
                }
            }
        }
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(str);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
          
    }
    static float[] StrToList(String str){
        //mengembalikan list of number dari hasil bacaan pada string
        float[] temp = new float[0];
        String val ="";
        for (int i=0;i<str.length();i++){
            if (str.charAt(i) == ' ' || str.charAt(i)=='\n'){
                float [] elm = {Float.valueOf((val))};
                temp = append(temp,elm);
                val = "";
            } else {
                val += str.charAt(i);
                if (i==str.length()-1){
                    float [] elm = {Float.valueOf((val))};
                    temp = append(temp,elm);
                    val = "";
                }
            }
        }
        return temp; 
    }
    static float[][] TxtToMat(String path){
        // mengembalikan matrix hasil bacaan file dalam path
        float [][] mat = new float[0][];
        try {
            File object = new File(path);
            Scanner reader = new Scanner(object);
            while (reader.hasNextLine()){
                String data = reader.nextLine();
                float[][] row = {StrToList(data)};
                mat = append(mat,row);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return mat;
    }
    public static void main(String[] args){
        rref.displayMatrix(TxtToMat("test.txt"));
        MatToTxt(TxtToMat("test.txt"), "testres.txt");
    }

}
