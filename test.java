public class test{
    static float[] append(float []list1,float[]list2){
        float[] temp = new float[list1.length+list2.length];
        for (int i = 0;i<list1.length;i++){
            temp[i] = list1[i];
        }
        for (int i = list1.length; i<list1.length+list2.length;i++){
            temp[i] = list2[i-list1.length];
        }
        return temp;
    }
    static String[][] append(String[][] list1,String [][]list2){
        String[][] temp = new String[list1.length+list2.length][];
        for (int i = 0;i<list1.length;i++){
            temp[i] = list1[i];
        }
        for (int i = list1.length; i<list1.length+list2.length;i++){
            temp[i] = list2[i-list1.length];
        }
        return temp;
    }
        
    public static void main(String[] args){
        float[] a = {1,2,3};
        float[] b = {4,5,6};
        String[][] x = {{"a","1"},{"b","2"}};
        String[][] y = {{"c","3"},{"d","4"}};
        String[][] matrix = append(x,y);
        // for (int i = 0; i < matrix.length; i ++){
        //     for (int j = 0 ; j < matrix[0].length;j++){
        //         System.out.print(matrix[i][j]);
        //     }
        //     System.out.println();
        int c = 1;
        String d ="1";
        int e = Integer.valueOf(d);
        System.out.println(String.format("%d",c));
        System.out.println(String.format("%d",c)==d);
        System.out.println(c==e);  
        }
    }
    