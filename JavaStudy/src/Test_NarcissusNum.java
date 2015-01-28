//打印出所有的"水仙花数(narcissus number)"，所谓"水仙花数"是指一个三位数，
//其各位数字立方和等于该数本身。例如：153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方。

public class Test_NarcissusNum {
    static int b, bb, bbb;
    public static void main(String[] args) {
        System.out.println("\n**********");
        for (int num = 101; num < 1000; num++) {
            Test_NarcissusNum tnn = new Test_NarcissusNum();
            tnn.f(num);
        }
    }

    public void f(int m) {
        bbb = m / 100;
        bb = (m % 100) / 10;
        b = (m % 100) % 10;
        if ((bbb * bbb * bbb + bb * bb * bb + b * b * b) == m) {
            System.out.println(m);
        }
    }
}
