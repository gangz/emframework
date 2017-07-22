package emframework.services.logon.cache;
import emframework.common.data.SessionDTO;
import emframework.common.exception.UserNotLoggedInException;
import emframework.util.json.JsonUtil;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
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
	
	private void add(String token, SessionDTO user, Long timeoutHours) {
		String jsonSession = JsonUtil.toJsonString(user);
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
	public SessionDTO buildSession(String token, Object u, Long timeoutHours) {
		SessionDTO d = new SessionDTO();
		add(token,d,timeoutHours);
		return d;
	}

	@Override
	public String generateUUID() {
		return UUID.randomUUID().toString();
	}
}