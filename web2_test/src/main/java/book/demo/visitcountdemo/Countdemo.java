package book.demo.visitcountdemo;

public class Countdemo {

    public static void main (String[] args) {
            Thread test = new newThread();
            test.start();
    }

    static  class newThread extends  Thread {

        @Override
        public void run () {
            Test.addcount();
        }
    }

    static class Test {

        public  static  int num = 0;
        public   static void addcount() {
            synchronized(Test.class) {
                num++;
                System.out.println(num);
            }
        }
    }
}