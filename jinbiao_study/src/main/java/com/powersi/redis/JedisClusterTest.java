package com.powersi.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author：Jinbiao
 * @Date：2023/4/23 18:53
 * @Desc：
 */
public class JedisClusterTest {

    public static void main(String[] args) throws IOException {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);

        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("192.168.213.128", 8001));
        jedisClusterNode.add(new HostAndPort("192.168.213.129", 8002));
        jedisClusterNode.add(new HostAndPort("192.168.213.130", 8003));
        jedisClusterNode.add(new HostAndPort("192.168.213.128", 8004));
        jedisClusterNode.add(new HostAndPort("192.168.213.129", 8005));
        jedisClusterNode.add(new HostAndPort("192.168.213.130", 8006));

        JedisCluster jedisCluster = null;
        try {
            //connectionTimeout：指的是连接一个url的连接等待时间
            //soTimeout：指的是连接上一个url，获取response的返回等待时间
            jedisCluster = new JedisCluster(jedisClusterNode, 6000, 5000, 10, "jinbiao", config);
            System.out.println(jedisCluster.set("jinbiao", "666"));
            System.out.println(jedisCluster.get("jinbiao"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedisCluster != null)
                jedisCluster.close();
        }
    }
}
