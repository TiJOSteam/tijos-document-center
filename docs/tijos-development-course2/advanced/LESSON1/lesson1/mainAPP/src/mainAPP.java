import java.io.IOException;

import tijos.framework.appcenter.TiAPP;
import tijos.framework.appcenter.TiAPPManager;

/**
 * ��ʶ��Ӧ�ã�һ��������Ӧ�ü���ã���Ӧ�ó���
 *
 * @author tijos
 */
public class mainAPP {

    public static void main(String[] args) {
        // ��ȡӦ�ù�����ʵ��
        TiAPPManager mgr = TiAPPManager.getInstance();
        try {
            TiAPP app = null;
            // ö����Ӧ���б�
            TiAPP[] list = mgr.enumerate();
            // ѭ����ӡӦ��ID��Ӧ������
            for (int i = 0; i < list.length; i++) {
                System.out.println("APP ID:" + list[i].getId() + "  APP name:" + list[i].getName());
                // ����ҵ�����ΪAPP1��Ӧ�ã�����Ӧ��ʵ��
                if (list[i].getName().equals("lesson1-APP1")) {
                    app = list[i];
                }
            }
            // �Ѿ���APP1������ִ��APP1����ǰӦ���˳�
            if (app != null) {
                app.execute(true, "P1 P2 P3 P4");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
