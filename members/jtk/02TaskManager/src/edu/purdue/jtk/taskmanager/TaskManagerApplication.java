package edu.purdue.jtk.taskmanager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import edu.purdue.jtk.taskmanager.tasks.Task;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class TaskManagerApplication extends Application {

	private ArrayList<Task> currentTasks;
	private SharedPreferences sharedPreferences;
	
	@Override
	public void onCreate() {
		super.onCreate();
		if (null == currentTasks) {
			currentTasks = initializeCurrentTasks();
		}
	}
	
	/*
	 * The methods intializeCurrentTasks and saveCurrentTasks were added
	 * as a homework assignment after version 1.  However, they do not
	 * properly handle the task deletion feature introduced in version 2.
	 * Task addition and deletion needs to be centralized so that the
	 * database (in this case, stored in the preferences file) can be
	 * consistently managed (e.g., as an adapter or view).
	 */

	private ArrayList<Task> initializeCurrentTasks() {
		currentTasks = new ArrayList<Task>();

		Context c = getBaseContext();
		sharedPreferences = c.getSharedPreferences("tasks", Context.MODE_PRIVATE);
		
		int n = sharedPreferences.getInt("taskCount", 0);
		for (int i = 0; i < n; i++) {
			String sp = sharedPreferences.getString("task" + i, "");
			Log.i("onCreate", "task: " + i + " = " + sp);
			currentTasks.add(new Task(sp));
		}
		Log.i("onCreate", "done");
		return currentTasks;
	}

	public void setCurrentTasks(ArrayList<Task> currentTasks) {
		this.currentTasks = currentTasks;
	}

	public ArrayList<Task> getCurrentTasks() {
		return currentTasks;
	}
	
	private void saveCurrentTasks() {
		Editor editor = sharedPreferences.edit();
		editor.clear();
		int i = 0;
		for (Task task : currentTasks) {
			editor.putString("task" + i, task.getName());
			i++;
		}
		editor.putInt("taskCount", i);
		editor.commit();
	}
	
	public void addTask(Task t) {
		assert(null != t);
		if (null == currentTasks) {
			currentTasks = new ArrayList<Task>();
		}
		currentTasks.add(t);
		saveCurrentTasks();
	}

	
	/*
	 * The stream versions of saveCurrentTasks and initializeCurrentTasks below don't work.
	 * The stream is saved, but cannot be restored.  Not clear why.
	 */
	
	@SuppressWarnings("unused")
	private void saveCurrentTasksStream() {
		Log.i("addTask", "serializing and storing");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			oos.writeObject(currentTasks);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Editor editor = sharedPreferences.edit();
		editor.putString("tasks", baos.toString());
		editor.commit();
		Log.i("addTask", "done: " + baos.toString());
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private ArrayList<Task> initializeCurrentTasksStream() {
		Context c = getBaseContext();
		sharedPreferences = c.getSharedPreferences("prefs", Context.MODE_PRIVATE);
		
		String sp = sharedPreferences.getString("tasks", "");
		if (sp.length() == 0) {
			Log.i("onCreate", "no 'tasks' preferences");
			currentTasks = new ArrayList<Task>();
		} else {
			Log.i("onCreate", "tasks: " + sp);
			InputStream is = new ByteArrayInputStream(sp.getBytes());
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(is);
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				currentTasks = (ArrayList<Task>) ois.readObject();
			} catch (OptionalDataException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Log.i("onCreate", "done");
		return currentTasks;
	}
}
