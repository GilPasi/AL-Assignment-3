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

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

/**
 * Stores the number of build in function groups
 */
class FunctionGroupCountRecord extends WritableRecordData
{
  /**
   * The binary data
   */
  private byte[] data;

  /**
   * The number of built in function groups
   */
  private int numFunctionGroups;

  /**
   * Constructor
   */
  public FunctionGroupCountRecord()
  {
    super(Type.FNGROuPCOuNT);

    numFunctionGroups = 0xe;
    
    data = new byte[2];

    IntegerHelper.getTwoBytes(numFunctionGroups, data, 0);
  }

  /**
   * Gets the binary data for output to file
   * 
   * @return the binary data
   */
  public byte[] getData()
  {
    return data;
  }
}



