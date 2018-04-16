package cn.xpleaf.bigdata.utils;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *将在计算过程中需要到的ip地址存放到redis中
 * string
 * list
 * hash
 * set
 * zset
 */
public class Redis4IpOps {
    public static void main(String[] args) throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        Map<String, String> ipMap = new HashMap<>();
        InputStream ins = Redis4IpOps.class.getClassLoader().getResourceAsStream("new_ip_en.data");
        BufferedReader br = new BufferedReader(new InputStreamReader(ins));
        String line = null;
        while((line = br.readLine()) != null) {
            String[] fields = line.split("\t");
            ipMap.put(fields[0], fields[1]);
        }
        jedis.hmset("ip_info_en", ipMap);
        JedisUtil.returnJedis(jedis);
    }
}
