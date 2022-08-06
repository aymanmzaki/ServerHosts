package com.amz;

public class App {

	public static void main(String[] args) {
		Tracker tracker = Tracker.getInstance();

		tracker.allocate("apibox");
		tracker.allocate("apibox");
		tracker.deallocate("apibox:1");
		tracker.allocate("apibox");
		tracker.allocate("sitebox");
	}

}
