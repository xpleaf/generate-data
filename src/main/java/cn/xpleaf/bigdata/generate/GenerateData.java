package cn.xpleaf.bigdata.generate;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.util.*;

public class GenerateData {

    static Map<Integer, Integer> appid = new HashMap<Integer, Integer>();
    static Map<Integer, String> mid = new HashMap<Integer, String>();//mid就是机器码 或者用户唯一的标识，存储在浏览器cookie中
    static Map<Integer, String> ip = new HashMap<Integer, String>();
    static Map<Integer, Integer> userid = new HashMap<Integer, Integer>();
    static Map<Integer, String> request = new HashMap<Integer, String>();
    static Map<Integer, Integer> status = new HashMap<Integer, Integer>();
    static Map<Integer, String> http_referer = new HashMap<Integer, String>();
    static Map<Integer, String> user_agent = new HashMap<Integer, String>();
    static Map<Integer, String> param = new HashMap<Integer, String>();

    /*
     * 随机生成国内IP地址
     */
    public static String getRandomIp() {

        // ip范围
        int[][] range = {{607649792, 608174079},// 36.56.0.0-36.63.255.255
                {1038614528, 1039007743},// 61.232.0.0-61.237.255.255
                {1783627776, 1784676351},// 106.80.0.0-106.95.255.255
                {2035023872, 2035154943},// 121.76.0.0-121.77.255.255
                {2078801920, 2079064063},// 123.232.0.0-123.235.255.255
                {-1950089216, -1948778497},// 139.196.0.0-139.215.255.255
                {-1425539072, -1425014785},// 171.8.0.0-171.15.255.255
                {-1236271104, -1235419137},// 182.80.0.0-182.92.255.255
                {-770113536, -768606209},// 210.25.0.0-210.47.255.255
                {-569376768, -564133889}, // 222.16.0.0-222.95.255.255
        };

        Random rdint = new Random();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
    }

    /*
     * 将十进制转换成ip地址
     */
    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";

        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);

        return x;
    }

    private static void initData() {

        //web
        appid.put(0, 1000);
        //android
        appid.put(1, 1001);
        //ios
        appid.put(2, 1002);
        //ipad
        appid.put(3, 1003);

        ip.put(0, "61.235.47.3");
        ip.put(1, "61.172.249.96");
        ip.put(2, "211.155.234.99");
        ip.put(3, "211.167.248.22");
        ip.put(4, "60.12.227.208");
        ip.put(5, "221.8.9.6 80");
        ip.put(6, "121.9.221.188");
        ip.put(7, "58.23.234.26");
        ip.put(8, "202.100.64.2");
        ip.put(9, "202.96.128.166");
        ip.put(10, "123.151.14.54");
        ip.put(11, "61.128.192.68");
        ip.put(12, "202.103.225.68");
        ip.put(13, "202.98.192.67");
        ip.put(14, "219.147.198.230");
        ip.put(15, "202.103.24.68");
        ip.put(16, "218.2.2.2");
        ip.put(17, "219.148.162.31");
        ip.put(18, "219.146.0.130");
        ip.put(19, "218.30.19.40");
        ip.put(20, "116.228.111.118");
        ip.put(21, "218.6.200.139");
        ip.put(22, "222.172.200.68");
        ip.put(23, "202.101.172.35");
        ip.put(24, "202.98.0.68");
        ip.put(25, "202.99.192.66");
        ip.put(26, "202.103.96.112");
        ip.put(27, "202.96.69.38");
        ip.put(28, "61.128.114.166");
        ip.put(29, "221.13.65.34");
        ip.put(30, "223.220.158.58");
        ip.put(31, "60.12.227.208");
        ip.put(32, "221.8.9.6 80");
        ip.put(33, "121.9.221.188");
        ip.put(34, "58.23.234.26");
        ip.put(35, "202.100.64.2");
        ip.put(36, "218.30.19.40");
        ip.put(37, "116.228.111.118");
        ip.put(38, "218.6.200.139");
        ip.put(39, "222.172.200.68");
        ip.put(40, "202.101.172.35");
        ip.put(41, "202.96.128.166");
        ip.put(42, "123.151.14.54");
        ip.put(43, "61.128.192.68");
        ip.put(44, "202.103.225.68");
        ip.put(45, "202.98.192.67");

        // 生成500个mid，唯一的id，此id第一次会种在浏览器的cookie里
        for (int i = 0; i < 500; i++) {
            mid.put(i, UUID.randomUUID().toString());
        }

        //init userId
        Random random = new Random();
        for (int i = 0; i < 5000; i++) {
            userid.put(i, 10000 + random.nextInt(1000) + i);
        }

        //request  HTTP协议 1.1和 1.0
        request.put(0, "GET /tologin HTTP/1.1");
        request.put(1, "GET /userList HTTP/1.1");
        request.put(2, "GET /user/add HTTP/1.1");
        request.put(3, "POST /updateById?id=21 HTTP/1.1");
        request.put(4, "GET /top HTTP/1.1");
        request.put(5, "GET /tologin HTTP/1.0");
        request.put(6, "GET /index HTTP/1.1");
        request.put(7, "POST /stat HTTP/1.1");
        request.put(8, "GET /getDataById HTTP/1.0");
        request.put(9, "GET /tologin HTTP/1.1");
        request.put(10, "POST /check/init HTTP/1.1");
        request.put(11, "GET /check/detail HTTP/1.1");
        request.put(12, "GET /top HTTP/1.0");
        request.put(13, "POST /passpword/getById?id=11 HTTP/1.1");
        request.put(14, "GET /update/pass HTTP/1.0");

        http_referer.put(0, "/tologin");
        http_referer.put(1, "/userList");
        http_referer.put(2, "/user/add");
        http_referer.put(3, "/updateById?id=21");
        http_referer.put(4, "/top");
        http_referer.put(5, "/tologin");
        http_referer.put(6, "/index");
        http_referer.put(7, "/stat");
        http_referer.put(8, "/getDataById");
        http_referer.put(9, "/tologin");
        http_referer.put(10, "/check/init");
        http_referer.put(11, "/check/detail");
        http_referer.put(12, "/top");
        http_referer.put(13, "/passpword/getById?id=11");
        http_referer.put(14, "/update/pass");


        //status
        //200 ok
        status.put(0, 200);
        //404 not found
        status.put(1, 404);
        //408 Request Timeout
        status.put(2, 408);
        // 500 Internal Server Error
        status.put(3, 500);
        //504 Gateway Timeout
        status.put(4, 504);
        //302 请求重定向
        status.put(5, 302);


        user_agent.put(0, "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT6.0)");
        user_agent.put(1, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT5.2)");
        user_agent.put(2, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT5.1)");
        user_agent.put(3, "Mozilla/4.0 (compatible; MSIE 5.0; WindowsNT)");
        user_agent.put(4, "Mozilla/5.0 (Windows; U; Windows NT 5.2)Gecko/2008070208 Firefox/3.0.1");
        user_agent.put(5, "Mozilla/5.0 (Windows; U; Windows NT 5.1)Gecko/20070309 Firefox/2.0.0.3");
        user_agent.put(6, "Mozilla/5.0 (Windows; U; Windows NT 5.1)Gecko/20070803 Firefox/1.5.0.12");
        user_agent.put(7, "Mozilla/5.0 (Windows; U; Windows NT 5.2)AppleWebKit/525.13 (KHTML, like Gecko) Version/3.1Safari/525.13");
        user_agent.put(8, "Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/525.13 (KHTML,like Gecko) Chrome/0.2.149.27 Safari/525.13");
        user_agent.put(9, "Mozilla/5.0 (iPhone; U; CPU like Mac OS X)AppleWebKit/420.1 (KHTML, like Gecko) Version/3.0 Mobile/4A93Safari/419.3");
        user_agent.put(10, "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
        user_agent.put(11, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.3");
        user_agent.put(12, "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");


        param.put(0, "{\"ugctype\":\"consumer\",\"userId\":\"10022\",\"coin\":\"50\",\"number\":\"2\"}");
        param.put(1, "{\"ugctype\":\"consumer\",\"userId\":\"20202\",\"coin\":\"20\",\"number\":\"3\"}");
        param.put(2, "{\"ugctype\":\"consumer\",\"userId\":\"40604\",\"coin\":\"10\",\"number\":\"2\"}");
        param.put(3, "{\"ugctype\":\"recharge\",\"userId\":\"20202\",\"rmb\":\"50\",\"number\":\"10\"}");
        param.put(4, "{\"ugctype\":\"recharge\",\"userId\":\"40604\",\"rmb\":\"100\",\"number\":\"20\"}");
        param.put(5, "{\"ugctype\":\"recharge\",\"userId\":\"10022\",\"rmb\":\"100\",\"number\":\"20\"}");
        param.put(6, "{\"ugctype\":\"fav\",\"userId\":\"10608\",\"item\":\"10\"}");
        param.put(7, "{\"ugctype\":\"fav\",\"userId\":\"10709\",\"item\":\"11\"}");
        param.put(8, "{\"ugctype\":\"fav\",\"userId\":\"10207\",\"item\":\"12\"}");
        param.put(9, "{\"ugctype\":\"fav\",\"userId\":\"40604\",\"item\":\"13\"}");
    }

    /**
     * Referer是当前网页的上一个网页
     * 也就是说当前网页是经由哪一个网页进入的
     */
    static class AccessLogger implements Runnable {

        @Override
        public void run() {
            //appid	ip	mid	userid	login_type	request	 status	http_referer	user_agent	time
            initData();
            Random r = new Random();
            Logger accessLogger = Logger.getLogger("accessMy");
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                accessLogger.info(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s"
                        , appid.get(r.nextInt(appid.size()))
                        , ip.get(r.nextInt(ip.size()))
                        , mid.get(r.nextInt(mid.size()))
                        , userid.get(r.nextInt(userid.size()))
                        , r.nextInt(2)
                        , request.get(r.nextInt(request.size()))
                        , status.get(r.nextInt(status.size()))
                        , http_referer.get(r.nextInt(http_referer.size()))
                        , user_agent.get(r.nextInt(user_agent.size())), new Date().getTime()));
            }
        }
    }

    public static void main(String[] args) throws Exception {

        //多线程
        new Thread(new AccessLogger()).start();
    }

}
