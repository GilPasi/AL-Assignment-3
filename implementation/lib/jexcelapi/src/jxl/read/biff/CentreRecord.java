/*********************************************************************
*
*      Copyright (C) 2005 Andrew Khan, Adam Caldwell
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

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;

/**
 * Record which indicates the whether the horizontal center option has been set
 */
class CentreRecord extends RecordData
{
	/**
	 * The centre flag
	 */
	private boolean centre;

	/**
	 * Constructor
	 *
	 * @param t the record to constructfrom
	 */
	public CentreRecord(Record t)
	{
		super(t);
		byte[] data = getRecord().getData();
		centre = IntegerHelper.getInt(data[0], data[1]) != 0;
	}

	/**
   * Accessor for the centre flag
   *
	 * @return Returns the centre flag.
	 */
	public boolean isCentre()
	{
		return centre;
	}
}
