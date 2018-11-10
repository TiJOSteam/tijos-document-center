import java.io.IOException;

import tijos.framework.appcenter.TiAPP;
import tijos.framework.appcenter.TiAPPManager;

/**
 * 认识多应用，一机多用与应用间调用，主应用程序
 * 
 * @author tijos
 *
 */
public class mainAPP {

	public static void main(String[] args) {
		// 获取应用管理器实例
		TiAPPManager mgr = TiAPPManager.getInstance();
		try {
			TiAPP app = null;
			// 枚举因应用列表
			TiAPP[] list = mgr.enumerate();
			// 循环打印应用ID和应用名字
			for (int i = 0; i < list.length; i++) {
				System.out.println("APP ID:" + list[i].getId() + "  APP name:" + list[i].getName());
				// 如果找到名字为APP1的应用，保存应用实例
				if (list[i].getName().equals("lesson1-APP1")) {
					app = list[i];
				}
			}
			// 已经找APP1，立即执行APP1，当前应用退出
			if (app != null) {
				app.execute(true, "P1 P2 P3 P4");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
