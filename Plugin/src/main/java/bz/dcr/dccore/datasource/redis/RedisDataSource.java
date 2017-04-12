package bz.dcr.dccore.datasource.redis;

import redis.clients.jedis.Jedis;

public class RedisDataSource {

    private Jedis jedis;


    public RedisDataSource(String host, int port) {
        jedis = new Jedis(host, port);
    }


    public void auth(String password) {
        jedis.auth(password);
    }

}
