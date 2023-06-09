/*********************************************************************
*
*      Copyright (C) 2005 Andrew Khan
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

import jxl.CellFeatures;

/**
 * Allows the setting of the cell features in this package only
 */
interface CellFeaturesAccessor
{
  /**
   * Sets the cell features
   *
   * @param cf the cell features
   */
  public void setCellFeatures(CellFeatures cf);

  /**
   * Convenience function (due to casting) to get the cell features
   *
   * @return the cell features
   */
  public CellFeatures getCellFeatures();
}
