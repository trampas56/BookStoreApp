package com.example.BookStoreApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="notify")
public class Notify {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="notify_id")
	private int notifyId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userProfile_id")
	private UserProfile userProfile;
	
	@JoinColumn(name="notification_message")
	private String notificationMessage;
	
	public Notify(UserProfile userProfile, String notificationMessage) {
		this.userProfile = userProfile;
		this.notificationMessage = notificationMessage;
	}
	
	public Notify() { }
	
	public int getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(int notifyId) {
		this.notifyId = notifyId;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public String getNotificationMessage() {
		return notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
}
