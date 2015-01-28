//判断字符串为空
class str1 {
	public static void main(String[] args) {
		String yString =new String();
		System.out.println(yString);
		System.out.println(yString=="");  //false
		System.out.println(yString.equals("")); //true
		    
		String string=null;
		System.out.println(string ==null);  //true
		System.out.println(string =="");	//false
		System.out.println(string.equals(null)); //NullPointerException
	}
}

//String a = null; //引用为空，既没有分配内存
//
//String b = new string(); //b其实创建了内存，但它是一个空串，既是"";
//
//这就是空值和空串的区别
//
//Java判断字符串是否为空的四种方法
//
//1、if(str == null || str.equals(""))                //效率低
//
//2、if(str == null || str.length()< = 0)           //推荐使用
//
//3、if(str == null || str.isEmpty())                //Java SE 6.0 开始提供
//
//4、if(str == null || str == "")