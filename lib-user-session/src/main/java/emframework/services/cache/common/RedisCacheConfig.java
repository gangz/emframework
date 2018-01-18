package emframework.services.cache.common;


import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
/**
 * http://caseyscarborough.com/blog/2014/12/18/caching-data-in-spring-using-redis/
 *
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    
	@Autowired RedisTemplate<String, String> stringJacksonRedisTemplate;
	@Bean
	public CacheManager cacheManager() {
		RedisCacheManager cacheManager = new RedisCacheManager(stringJacksonRedisTemplate);
		cacheManager.setDefaultExpiration(300);
		return cacheManager;
	}

	@Bean
	public KeyGenerator keyGenerator() {
	    return new KeyGenerator() {
	        @Override
	        public Object generate(Object target, Method method, Object... params) {
	            StringBuilder sb = new StringBuilder();
	            String[] value = new String[1];
	            Cacheable cacheable = method.getAnnotation(Cacheable.class);
	            if (cacheable != null) {
	                value = cacheable.value();
	            }
	            CachePut cachePut = method.getAnnotation(CachePut.class);
	            if (cachePut != null) {
	                value = cachePut.value();
	            }
	            CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
	            if (cacheEvict != null) {
	                value = cacheEvict.value();
	            }
	            sb.append(value[0]);
	            for (Object obj : params) {
	                sb.append(":" + obj.toString());
	            }
	            return sb.toString();
	        }
	    };
	}
}