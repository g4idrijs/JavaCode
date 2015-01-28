//单例模式指的是一个类只有一个实例它通过将构造函数属性设为private无法让我们通过new来实现多个实例。
public class Test_Singleton {
    public static void main(String[] args) {
        System.out.println("\n**********");
	Singleton singleton = Singleton.getSingleton();
	Singleton singleton2 = Singleton.getSingleton();
	System.out.println(singleton==singleton2);//输出结果为true
	}
}

class Singleton {
    private static Singleton singleton;
    private Singleton() {}
    public static Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}