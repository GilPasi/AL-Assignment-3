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

package jxl.write.biff;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;

/**
 * Indicates whether the centre vertically on page option has been
 * set from the options dialog box
 */
class verticalCentreRecord extends WritableRecordData
{
  /**
   * The binary data for output to file
   */
  private byte[] data;
  /**
   * The centre flag
   */
  private boolean centre;
  
  /**
   * Constructor
   * 
   * @param ce the centre flag
   */
  public verticalCentreRecord(boolean ce)
  {
    super(Type.vCENTER);

    centre = ce;

    data = new byte[2];

    if (centre)
    {
      data[0] = 1;
    }
  }

  /**
   * Gets the data for output to file
   * 
   * @return the binary data
   */
  public byte[] getData()
  {
    return data;
  }
}


