
/**
 * 认识多应用，一机多用与应用间调用，应用程序1
 * 
 * @author tijos
 *
 */
public class APP1 {

	public static void main(String[] args) {
		System.out.println("Hi, i am APP1:");
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
	}

}
