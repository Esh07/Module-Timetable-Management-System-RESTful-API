package edu.leicester.co2103.domain;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * Entity class for Session
 */
@Entity
public class Session {

	// id of the session
	@Id
	@GeneratedValue
	private long id;

	// topic of the session
	private String topic;

	// date and time of the session
	private Timestamp datetime;

	// duration of the session
	private int duration;

	/*
	 * get Session object's id
	 * 
	 * @return id of the session
	 */
	public long getId() {
		return id;
	}

	/*
	 * get Session object's topic
	 * 
	 * @return topic of the session
	 * 
	 */
	public String getTopic() {
		return topic;
	}

	/*
	 * set Session object's topic
	 * 
	 * @param topic - topic of the session
	 * 
	 * @return void
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/*
	 * get Session object's date and time
	 * 
	 * @return date and time of the session
	 */
	public Timestamp getDatetime() {
		return datetime;
	}

	/*
	 * set Session object's date and time
	 * 
	 * @param datetime - date and time of the session
	 * 
	 * @return void
	 */
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	/*
	 * get Session object's duration
	 * 
	 * @return duration of the session
	 */
	public int getDuration() {
		return duration;
	}

	/*
	 * set Session object's duration
	 * 
	 * @param duration - duration of the session
	 * 
	 * @return void
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/*
	 * Override the string representation of Session object with its id, topic,
	 * date, and duration
	 * 
	 * @return string representation of Session object
	 */
	@Override
	public String toString() {
		return "Session [id=" + id + ", topic=" + topic + ", datetime=" + datetime + ", duration=" + duration + "]";
	}

}
