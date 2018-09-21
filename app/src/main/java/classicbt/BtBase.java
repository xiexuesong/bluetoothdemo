package classicbt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 2018/9/18.
 */

public class BtBase {

    /**
     * 读取文件
     * @param is
     */
    public void readFile(InputStream is){
        byte[] data = new byte[1024];
        int len ;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            while ((len = is.read(data))!= -1){
                baos.write(data,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
