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

import jxl.biff.CountryCode;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;


/**
 * Record containing the localization information
 */
class CountryRecord extends WritableRecordData
{
  /**
   * The user interface language
   */
  private int language;

  /**
   * The regional settings
   */
  private int regionalSettings;

  /**
   * Constructor
   */
  public CountryRecord(CountryCode lang, CountryCode r)
  {
    super(Type.COuNTRY);

    language = lang.getvalue();
    regionalSettings = r.getvalue();
  }

  public CountryRecord(jxl.read.biff.CountryRecord cr)
  {
    super(Type.COuNTRY);

    language = cr.getLanguageCode();
    regionalSettings = cr.getRegionalSettingsCode();
  }

  /**
   * Retrieves the data to be written to the binary file
   * 
   * @return the binary data
   */
  public byte[] getData()
  {
    byte[] data = new byte[4];

    IntegerHelper.getTwoBytes(language, data, 0);
    IntegerHelper.getTwoBytes(regionalSettings, data, 2);

    return data;
  }
}








