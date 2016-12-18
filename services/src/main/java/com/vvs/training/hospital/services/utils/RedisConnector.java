
package com.vvs.training.hospital.services.utils;
 
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
 
public class RedisConnector {
    private Jedis cli;
    
    /**
     * constructor: connect to Redis server and authorization
     * @param host
     * @param port
     * @param password
     */
    public RedisConnector(String host, int port, String password) {
        cli = new Jedis(host, port, 5000);
        cli.auth(password);
        try {
            cli.connect();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
 
    /**
     * calculate keys count (eg count of active sessions)
     * @return
     */
    public long getKeysCount() {
        return cli.dbSize();
    }
 
    /**
     * get all keys that begin with "begin" string
     * @param begin
     * @return
     */
    public Set<String> getAllKeys(String begin) {
        return cli.keys(begin+"*");
    }
 
    /**
     * set session values as map
     * @param sess
     * @param m
     * @return
     */
    public boolean setAll(String sess, Map<String, String> m) {
        String r = cli.hmset(sess, m);
        return (r.equals("OK"));
    }
 
    /**
     * set session values as map and set expire the session
     * @param sess
     * @param m
     * @param expire
     * @return
     */
    public boolean setAll(String sess, Map<String, String> m, int expire) {
        boolean rez = false;
        String r = cli.hmset(sess, m);
        long er = cli.expire(sess, expire);
        rez = (r.equals("OK") && er>0);
        return rez;
    }
 
    /**
     * set one of session values
     * @param sess
     * @param key
     * @param value
     * @return
     */
    public boolean set(String sess, String key, String value) {
        boolean rez = false;
        Map m = getAll(sess);
        if (m!=null) {
            m.put(key, value);
            rez = setAll(sess, m);
        }
        return rez;
    }
    
    /**
     * set or update session values and set expire
     * @param sess
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean set(String sess, String key, String value, int expire) {
        boolean rez = false;
        Map m = getAll(sess);
        if (m!=null) {
            m.put(key, value==null ? "" : value);
            rez = setAll(sess, m, expire);
        }
        return rez;
    }
    /**
     * check if session exists
     * @param sess
     * @return
     */
    public boolean isExists(String sess) {
        return cli.exists(sess);
    }
    /**
     * get session values
     * @param sess
     * @return
     */
    public Map<String, String> getAll(String sess) {
        return cli.hgetAll(sess);
    }
 
    /**
     * det session values and prolongs session
     * @param sess
     * @param expire
     * @return
     */
    public Map<String, String> getAll(String sess, int expire) {
        Map m = cli.hgetAll(sess);
        cli.hmset(sess, m);
        cli.expire(sess, expire);
        return m;
    }
 
    /**
     * get one of session values
     * @param sess
     * @param key
     * @return
     */
    public String get(String sess, String key) {
        return cli.hget(sess, key);
    }
 
    /**
     * delete session with all session data
     * @param sess
     * @return
     */
    public boolean del(String sess) {
        Long del = cli.del(sess);
        return del>0;
    }
 
    /**
     * close connection
     */
    public void close() {
      if (cli.isConnected()) {
            cli.disconnect();
        }
    }
}