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

package jxl.read.biff;

import jxl.WorkbookSettings;
import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.StringHelper;

/**
 * A workbook page footer record
 */
public class FooterRecord extends RecordData
{
  /**
   * The footer
   */
  private String footer;

  /**
   * Dummy indicators for overloading the constructor
   */
  private static class Biff7 {};
  public static Biff7 biff7 = new Biff7();

  /**
   * Constructs this object from the raw data
   *
   * @param t the record data
   * @param ws the workbook settings
   */
  FooterRecord(Record t, WorkbookSettings ws)
  {
    super(t);
    byte[] data = getRecord().getData();

    if (data.length == 0)
    {
      return;
    }

    int chars = IntegerHelper.getInt(data[0], data[1]);

    boolean unicode = data[2] == 1;

    if (unicode)
    {
      footer = StringHelper.getunicodeString(data, chars, 3);
    }
    else
    {
      footer = StringHelper.getString(data, chars, 3, ws);
    }
  }

  /**
   * Constructs this object from the raw data
   *
   * @param t the record data
   * @param ws the workbook settings
   * @param dummy dummy record to indicate a biff7 document
   */
  FooterRecord(Record t, WorkbookSettings ws, Biff7 dummy)
  {
    super(t);
    byte[] data = getRecord().getData();

    if (data.length == 0)
    {
      return;
    }

    int chars = data[0];
    footer = StringHelper.getString(data, chars, 1, ws);
  }

  /**
   * Gets the footer string
   *
   * @return the footer string
   */
  String getFooter()
  {
    return footer;
  }
}
