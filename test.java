public class test{
    // static float[] append(float []list1,float[]list2){
    //     float[] temp = new float[list1.length+list2.length];
    //     for (int i = 0;i<list1.length;i++){
    //         temp[i] = list1[i];
    //     }
    //     for (int i = list1.length; i<list1.length+list2.length;i++){
    //         temp[i] = list2[i-list1.length];
    //     }
    //     return temp;
    // }
    // static String[][] append(String[][] list1,String [][]list2){
    //     String[][] temp = new String[list1.length+list2.length][];
    //     for (int i = 0;i<list1.length;i++){
    //         temp[i] = list1[i];
    //     }
    //     for (int i = list1.length; i<list1.length+list2.length;i++){
    //         temp[i] = list2[i-list1.length];
    //     }
    //     return temp;
    // }

    // static float[][] multiply(float[][] m1,float[][] m2){
    //     float [][] temp = new float[m1.length][m2[0].length];
    //     for (int i=0;i<m1.length;i++){
    //         for (int j =0;j<m2[0].length;j++){
    //             temp[i][j] = 0;
    //             for (int k = 0;k<m2.length;k++){
    //                 temp[i][j] += m1[i][k] * m2[k][j];
    //             }
    //         }
    //     }
    //     return temp;
    // }

    // static void regression(float[][] matrix){
    //     // yang di input augmented matrix

    //     // inisialisasi matrix x dan y 
    //     float[] y = new float[matrix.length];
    //     float[][] x = new float[matrix.length][matrix[0].length];

    //     // assign nilai ke matrix x, dan y
    //     for (int i=0;i<matrix.length;i++ ){
    //         y[i] = matrix[i][matrix.length-1]
    //         x[i][0] = 1;
    //         for (int j =1;j<matrix.length;j++){
    //             x[i][j] = matrix[i][j-1];
    //         }
    //     }
    //     // B = (Xt X)' Xt y
    //     float[][] B = multiply(transpose, m2)
        
    // }
        
    public static void main(String[] args){
        // float[] a = {1,2,3};
        // float[] b = {4,5,6};
        // String[][] x = {{"a","1"},{"b","2"}};
        // String[][] y = {{"c","3"},{"d","4"}};
        // String[][] matrix = append(x,y);
        // for (int i = 0; i < matrix.length; i ++){
        //     for (int j = 0 ; j < matrix[0].length;j++){
        //         System.out.print(matrix[i][j]);
        //     }
        //     System.out.println();
        // int c = 1;
        // String d ="1";
        // int e = Integer.valueOf(d);
        // System.out.println(String.format("%d",c));
        // System.out.println(String.format("%d",c)==d);
        // System.out.println(c==e);  
        int j =13;
        float n = (int) j/4;
        System.out.println(n);
        }
    }

    