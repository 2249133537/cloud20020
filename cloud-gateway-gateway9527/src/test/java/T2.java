import java.time.ZonedDateTime;

/**
 * @Author: 杨璨玮
 * @Date: 2022/11/1
 * @Description:
 */
public class T2 {
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now(); //获取当前默认时区以及时间
        System.out.println(zbj);
        //2022-11-01T17:47:18.108+08:00[Asia/Shanghai]
    }
}
