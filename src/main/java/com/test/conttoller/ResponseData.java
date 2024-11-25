package com.test.conttoller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseData<T> implements Serializable {

	private boolean status;
	private List<String> messages;
	private T payLoad;
	
	public ResponseData() {
		messages = new ArrayList<>();
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the payLoad
	 */
	public T getPayLoad() {
		return payLoad;
	}

	/**
	 * @param payLoad the payLoad to set
	 */
	public void setPayLoad(T payLoad) {
		this.payLoad = payLoad;
	}

	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	
}
