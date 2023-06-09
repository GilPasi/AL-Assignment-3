/*********************************************************************
*
*      Copyright (C) 2002 Andrew Khan
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

package jxl.biff.formula;

import jxl.JXLException;

/**
 * Exception thrown when parsing a formula
 */
public class FormulaException extends JXLException
{
  /**
   * Inner class containing the message
   */
  private static class FormulaMessage
  {
    /**
     * The message
     */
    private String message;

    /**
     * Constructs this exception with the specified message
     *
     * @param m the message
     */
    FormulaMessage(String m)
    {
      message = m;
    }

    /**
     * Accessor for the message
     *
     * @return the message
     */
    public String getMessage()
    {
      return message;
    }
  }

  /**
   */
  static final FormulaMessage uNRECOGNIZED_TOKEN =
    new FormulaMessage("unrecognized token");

  /**
   */
  static final FormulaMessage uNRECOGNIZED_FuNCTION =
    new FormulaMessage("unrecognized function");

  /**
   */
  public static final  FormulaMessage BIFF8_SuPPORTED =
    new FormulaMessage("Only biff8 formulas are supported");

  /**
   */
  static final FormulaMessage LEXICAL_ERROR =
    new FormulaMessage("Lexical error:  ");

  /**
   */
  static final FormulaMessage INCORRECT_ARGuMENTS =
    new FormulaMessage("Incorrect arguments supplied to function");

  /**
   */
  static final FormulaMessage SHEET_REF_NOT_FOuND =
    new FormulaMessage("Could not find sheet");

  /**
   */
  static final FormulaMessage CELL_NAME_NOT_FOuND =
    new FormulaMessage("Could not find named cell");


  /**
   * Constructs this exception with the specified message
   *
   * @param m the message
   */
  public FormulaException(FormulaMessage m)
  {
    super(m.message);
  }

  /**
   * Constructs this exception with the specified message
   *
   * @param m the message
   * @param val the value
   */
  public FormulaException(FormulaMessage m, int val)
  {
    super(m.message + " " + val);
  }

  /**
   * Constructs this exception with the specified message
   *
   * @param m the message
   * @param val the value
   */
  public FormulaException(FormulaMessage m, String val)
  {
    super(m.message + " " + val);
  }
}
