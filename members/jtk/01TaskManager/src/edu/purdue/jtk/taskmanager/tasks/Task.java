package edu.purdue.jtk.taskmanager.tasks;

import java.io.Serializable;

public class Task implements Serializable {
	
	/**
	 * Generated by Eclipse
	 */
	private static final long serialVersionUID = 5527258407135652423L;
	
	private String name;

	public Task(String taskName) {
		name = taskName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
}
