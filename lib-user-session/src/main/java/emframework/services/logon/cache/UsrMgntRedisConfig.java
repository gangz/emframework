package emframework.services.logon.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import emframework.common.data.SessionDTO;
import emframework.util.redis.RedisObjectSerializer;


@Configuration
public class UsrMgntRedisConfig {
	@Bean
	public RedisTemplate<String, SessionDTO> redisTemplateUserCacheDTO(RedisConnectionFactory factory) {
		RedisTemplate<String, SessionDTO> template = new RedisTemplate<String, SessionDTO>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
	
}