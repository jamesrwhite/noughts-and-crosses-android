package com.JamesWhite.NoughtsAndCrosses;

public class Player {
	
	private String name;
	private int type; // 1 or 0 (represents Nought or Cross)

	/**
	 * @return the name
	 */
	public String getName() {
		
		return name;
		
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		
		this.name = name;
		
	}

	/**
	 * @return the type the player is using, 1 or 0
	 */
	public int getType() {
		
		return type;
		
	}

	/**
	 * @param type set the type the player is using, 1 or 0
	 */
	public void setType(int type) {
		
		this.type = type;
		
	}

}
