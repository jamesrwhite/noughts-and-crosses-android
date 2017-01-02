package com.JamesWhite.NoughtsAndCrosses;

/**
 * Player basic info about the player
 *
 * @author James White
 */
public class Player {

  private String name;
  private int type; // -1 or 1 (represents Nought or Cross)

  /**
   * getName
   *
   * @return the name
   */
  public String getName() {

    return name;
  }

  /**
   * setName
   *
   * @param name the name to set
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * getType
   *
   * @return the type the player is using, 1 or 0
   */
  public int getType() {

    return type;
  }

  /**
   * setType
   *
   * @param type set the type the player is using, 1 or 0
   */
  public void setType(int type) {

    this.type = type;
  }
}
