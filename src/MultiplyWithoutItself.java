/*
* 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
* 原理图链接：https://uploadfiles.nowcoder.net/images/20160829/841505_1472459965615_8640A8F86FB2AB3117629E2456D8C652
* */
public class MultiplyWithoutItself {
    public int[] multiply(int[] A) {
        if(A==null) return A;
        int[] B=new int[A.length];  //用一个等长度的数组B存放各连乘的结果
        if(A.length!=0){
            /*
            * 根据原理图，遍历游标i对应的A[i]会被去掉不加入求B[i]的连乘，可以算作在i位置乘以1，所以i位置的值置为1形成对角线，
            * 对角线将正方形分成左右边的下三角和上三角，先求下三角部分，此时先使B[i]等于对角线1左侧连乘的部分，左半部分从上往下，从左到右一行一行相乘
            * */
            B[0]=1;  //当i为0时，B[i]的左半部分只有1，右半部分过会再乘
            for(int i=1;i<A.length;i++){
                B[i]=B[i-1]*A[i-1];  //实际上是利用了递归的思想不断通过上一轮的B[i]求这一轮的B[i]
            }
            int temp=1;  //这里的1实际上是对角线最右下角，B[n-1]最后的那个1，temp存放上三角每一轮从下往上，从右到左一行一行相乘的对角线右半部分的结果
            for(int j=A.length-2;j>=0;j--){  //上三角最底部的对角线在n-2的位置，这样j+1就在n-1的位置
                temp*=A[j+1];  //每上一轮右半部分连乘的结果都再乘以这一轮需要相乘的右半部分元素
                B[j]*=temp;  //之前左半部分连乘的结果再乘以右半部分连乘的结果，就是完整一行连乘并去掉A[i]（对应位置乘以1）的结果
            }
        }
        return B;  //如果数组A的长度为0，会直接返回没填充的空数组B，如果数组长度不为0，则会返回正常结果
    }
}