import java.time.ZonedDateTime;

/**
 * @author wrmeng
 * @create 2023-09-03 -22:30
 **/

public class T2 {
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);// 2023-09-03T22:31:54.989+08:00[Asia/Shanghai]
        //ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York")); // 用指定时区获取当前时间
        //System.out.println(zny);
    }

}
