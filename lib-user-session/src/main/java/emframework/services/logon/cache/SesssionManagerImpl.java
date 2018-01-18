package emframework.services.logon.cache;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import emframework.common.data.SessionDTO;
import emframework.common.exception.UserNotLoggedInException;
import emframework.util.json.JsonUtil;


@Component
public class SesssionManagerImpl implements SesssionManager {
	private static final String TOKEN = "tk:";
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public SessionDTO getSession(String token) throws UserNotLoggedInException {
		String jsonSession = redisTemplate.opsForValue().get(TOKEN+token);
		if (jsonSession==null) throw new UserNotLoggedInException(token);
		SessionDTO session;
		try {
			session = JsonUtil.parseJson(jsonSession, SessionDTO.class);
		} catch (IOException e) {
			throw new UserNotLoggedInException(token);
		}
		return session;
	}
	
	private void add(String token, SessionDTO session, Long timeoutHours) {
		String jsonSession = JsonUtil.toJsonString(session);
		if (timeoutHours.equals(0)){
			redisTemplate.opsForValue().set(TOKEN+token, jsonSession);
		}else{
			System.out.println("add token " + timeoutHours);
			redisTemplate.opsForValue().set(TOKEN+token, jsonSession, timeoutHours.longValue(), TimeUnit.HOURS);
		}
	}
	
	@Override
	public void removeByToken(String token){
		redisTemplate.delete(TOKEN + token);
	}

	@Override
	public void buildSession(String token, SessionDTO session, Long timeoutHours) {
		add(token,session,timeoutHours);
	}

	@Override
	public String generateUUID() {
		return UUID.randomUUID().toString();
	}

	@Override
	public void addKey(String code){
		String value= "1";
		
		redisTemplate.opsForValue().set(TOKEN+code,value,10,TimeUnit.MINUTES);
		
		}

	@Override
	public String getCode(String code) {
		String jsonSession = redisTemplate.opsForValue().get(TOKEN+code);
		return jsonSession;
	}
	
}