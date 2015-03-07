//模拟产生16个日志对象，并且需要运行16秒才能打印完这些日志。
//在程序中添加4个线程去调用parseLog()方法来分头打印这16个日志对象，
//程序只需4秒即可打印完这些日志对象。
class ThreadTest {
	public static void main(String[] args) {
		System.out.println("bagin:" + (System.currentTimeMillis()/1000));
		for (int i=0;i<16;i++) {
			final String log = "" + (i+1); {
				ThreadTest.parseLog(log);
			}
		}
	}
	public static void parseLog(String log) {
		//		模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
		System.out.println(log + ":" + (System.currentTimeMillis()/1000));
//		修改程序代码，开四个线程让16个对象在4秒钟打完。
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}