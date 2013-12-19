package cn.com.sinosoft.ruleEngine.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CheckResult {
	
	private final List<CheckMessage> CheckMessages = Collections
			.synchronizedList(new ArrayList<CheckMessage>());

	public Collection<CheckMessage> getCheckMessages() {
		return Collections.unmodifiableCollection(CheckMessages);
	}
	public void addMessage(String message) {
		this.CheckMessages.add(new CheckMessage(null, null, message));
	}
	

	public void addCheckMessage(Object target, String field, String message) {
		this.CheckMessages.add(new CheckMessage(target, field, message));
	}

	public boolean hasCheckMessages() {
		if (this.CheckMessages.size() > 0) {
			return true;
		}
		return false;
	}
	
}
