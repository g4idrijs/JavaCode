import java.lang.Thread;

class CreateThreadTest1 {
	public static void main(String[] args) {
		T r=new T();
		r.start();   //T类的线程开始执行
		for(int i=0;i<20;i++){
			System.out.println("主线程正在执行"+i);
		}
		
		MyThread thread1=new MyThread("thread1");  
		MyThread thread2=new MyThread("thread2");  
		thread1.start();
		thread2.start();
		
		Thread t1=new Thread(new T1());
		Thread t2=new Thread(new T2());
		t1.start();
		t2.start();
	}
}

class T extends Thread
{
	public void run(){//重写父类中的run方法
		for(int i=0;i<20;i++){
			System.out.println("创建的线程正在执行"+i);
		}
	}
}

class MyThread extends Thread{
	MyThread(String name){
		super(name);
	}
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(getName()+":"+i);
			if(i%10==0){
				yield();
			}
		}
	}
}

class T1 implements Runnable{
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println("线程1执行中"+i);
			if(i==9){
				System.out.println("线程1已结束"+i);
				break;
			}
		}
	}
}
class T2 implements Runnable{
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println("线程2执行中"+i);
			if(i==9){
				System.out.println("线程2已结束"+i);
				break;
			}
		}
	}
}