package emframework.common.event;

import java.util.Observable;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher extends Observable{

	public void publishEvent(Event e) {
		this.setChanged();
		this.notifyObservers(e);
	}

}
