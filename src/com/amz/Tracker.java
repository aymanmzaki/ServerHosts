package com.amz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.List;
public class Tracker {
	
	private static Tracker tracker;
	
	
	private   Map<String,List<Integer>> trackerPoolMap;
	private   Map<String, List<Integer>> freeSlotsMap;
	
	private Tracker() {
		trackerPoolMap = new HashMap<>();
		freeSlotsMap= new HashMap<>();
	}
	
	public static Tracker getInstance() {
		if (tracker ==null ) {
			tracker=new Tracker();
		} 
		return tracker;
	}
	
	public void allocate(String hostName) {
		
		List<Integer> serverNumberList= trackerPoolMap.get(hostName);
		List<Integer> freeSlots = freeSlotsMap.get(hostName);
		
		if (serverNumberList != null && freeSlots!=null && !freeSlots.isEmpty()) {
			serverNumberList.add(freeSlots.get(freeSlots.size()-1));
			freeSlots.remove(freeSlots.size()-1);
		
		}else {
			if(Objects.isNull(serverNumberList) && Objects.isNull(freeSlots)) {
				serverNumberList=new ArrayList<>();
			}
			serverNumberList.add(serverNumberList.size()+1);	
		} 
		
		System.out.println(hostName+":"+serverNumberList.get(serverNumberList.size()-1));
		
		trackerPoolMap.put(hostName, serverNumberList);
		freeSlotsMap.put(hostName, freeSlots);
	}
	
	public boolean deallocate(String hostName) {
		
		String [] serverName = hostName.split(":");
		
		
		List<Integer> serverList = trackerPoolMap.get(serverName[0]);
		List<Integer> freeSlots = freeSlotsMap.get(serverName[0]);
		
		if (serverList==null) return false;
		if(freeSlots == null) freeSlots= new ArrayList<>();
		freeSlots.add(serverList.get(Integer.valueOf(serverName[1])-1));
		serverList.remove(serverList.get(Integer.valueOf(serverName[1])-1));
		
		freeSlotsMap.put(serverName[0],freeSlots);
		 
		return true;
	}
	
	

}
