package com.jiangjian.study.spring.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheConfig {

    /*--------------------------------使用EhCache的配置----------------------------------*/
    /**
     * creates an instance of EhCacheCache-Manager by passing in an instance of an
     * Ehcache CacheManager . This particular bit of injection can be confusing
     * because both Spring and Ehcache define a CacheManager type. To be clear,
     * Ehcache’s CacheManager is being injected into Spring’s EhCacheCacheManager
     * (which implements Spring’s CacheManager implementation).
     * @return
     */
    @Bean
    public EhCacheCacheManager cacheManager() {
        return new EhCacheCacheManager();
    }

    /**
     * The ehcache() method creates and returns an instance of EhCacheManagerFactoryBean .
     * Because it’s a factory bean (that is,it implements Spring’s FactoryBean interface),
     * the bean that is registered in the Spring application context isn’t an instance of
     * EhCacheManagerFactoryBean but rather is an instance of CacheManager , suitable for
     * injection into EhCacheCacheManager.
     * @return
     */
    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
        //ClassPathResource relative to root class path
        ehCacheFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheFactoryBean;
    }

    /*--------------------------------使用Redis的配置----------------------------------*/
    /*
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisCF) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisCF);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    */
}
