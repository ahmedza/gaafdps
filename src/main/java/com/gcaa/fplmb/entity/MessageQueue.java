package com.gcaa.fplmb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class MessageQueue {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
	@Column(name="message_id", insertable=false, updatable=false)
	private Long messageId;
	
	@OneToOne
	@JoinColumn(name="message_id")
	private AftnMessageEntity message;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public AftnMessageEntity getMessage() {
		return message;
	}

	public void setMessage(AftnMessageEntity message) {
		this.message = message;
	}
	
}
