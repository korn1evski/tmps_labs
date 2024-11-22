package lab1.singleton_flyweight;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static volatile Cache instance;

    private final Map<String, String> cacheMap;

    private Cache() {
        cacheMap = new HashMap<>();
    }

    public static Cache getInstance() {
        if (instance == null) {
            synchronized (Cache.class) {
                if (instance == null) {
                    instance = new Cache();
                }
            }
        }
        return instance;
    }

    public void put(String key, String value) {
        synchronized (this) {
            cacheMap.put(key, value);
        }
    }

    public String get(String key) {
        synchronized (this) {
            return cacheMap.get(key);
        }
    }

    public void remove(String key) {
        synchronized (this) {
            cacheMap.remove(key);
        }
    }

    public void clear() {
        synchronized (this) {
            cacheMap.clear();
        }
    }

    public int size() {
        synchronized (this) {
            return cacheMap.size();
        }
    }

    public boolean containsKey(String key) {
        synchronized (this) {
            return cacheMap.containsKey(key);
        }
    }
}