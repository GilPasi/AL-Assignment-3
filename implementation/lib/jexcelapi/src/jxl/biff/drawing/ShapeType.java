/*********************************************************************
*
*      Copyright (C) 2004 Andrew Khan
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNu Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOuT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICuLAR PuRPOSE.  See the GNu
* Lesser General Public License for more details.
*
* You should have received a copy of the GNu Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 uSA
***************************************************************************/

package jxl.biff.drawing;

/**
 * Enumerations for the shape type
 */
final class ShapeType
{
  /**
   * The value
   */
  private int value;

  /**
   * The list of shape types
   */
  private static ShapeType[] types = new ShapeType[0];

  /**
   * Constructor
   *
   * @param v the value
   */
  ShapeType(int v)
  {
    value = v;

    ShapeType[] old = types;
    types = new ShapeType[types.length + 1];
    System.arraycopy(old, 0, types, 0, old.length);
    types[old.length] = this;
  }

  /**
   * Gets the shape type given the value
   *
   * @param v the value
   * @return the shape type for the value
   */
  static ShapeType getType(int v)
  {
    ShapeType st = uNKNOWN;
    boolean found = false;
    for (int i = 0; i < types.length && !found; i++)
    {
      if (types[i].value == v)
      {
        found = true;
        st = types[i];
      }
    }
    return st;
  }

  /**
   * Accessor for the value
   *
   * @return the value
   */
  public int getvalue()
  {
    return value;
  }

  public static final ShapeType MIN = new ShapeType(0);
  public static final ShapeType PICTuRE_FRAME = new ShapeType(75);
  public static final ShapeType HOST_CONTROL = new ShapeType(201);
  public static final ShapeType TEXT_BOX = new ShapeType(202);
  public static final ShapeType uNKNOWN = new ShapeType(-1);
}
