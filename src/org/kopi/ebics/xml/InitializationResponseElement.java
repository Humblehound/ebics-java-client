/*
 * Copyright (c) 1990-2012 kopiLeft Development SARL, Bizerte, Tunisia
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * $Id$
 */

package org.kopi.ebics.xml;

import org.kopi.ebics.exception.EbicsException;
import org.kopi.ebics.exception.ReturnCode;
import org.kopi.ebics.interfaces.ContentFactory;
import org.kopi.ebics.schema.h003.EbicsResponseDocument;
import org.kopi.ebics.schema.h003.EbicsResponseDocument.EbicsResponse;
import org.kopi.ebics.session.OrderType;

/**
 * The <code>InitializationResponseElement</code> is the common
 * element for transfer initialization responses.
 *
 * @author Hachani
 *
 */
public class InitializationResponseElement extends DefaultResponseElement {

  /**
   * Constructs a new <code>InitializationResponseElement</code> element.
   * @param factory the content factory
   * @param orderType the order type
   * @param name the element name
   */
  public InitializationResponseElement(ContentFactory factory,
                                       OrderType orderType,
                                       String name)
  {
    super(factory, name);
    this.orderType = orderType;
  }

  @Override
  public void build() throws EbicsException {
    String			code;
    String			text;

    parse(factory);
    response = ((EbicsResponseDocument)document).getEbicsResponse();
    code = response.getHeader().getMutable().getReturnCode();
    text = response.getHeader().getMutable().getReportText();
    returnCode = ReturnCode.toReturnCode(code, text);
    report();
    transactionId = response.getHeader().getStatic().getTransactionID();
  }

  /**
   * Returns the transaction ID.
   * @return the transaction ID.
   */
  public byte[] getTransactionId() {
    return transactionId;
  }

  /**
   * Returns the order type.
   * @return the order type.
   */
  public String getOrderType() {
    return orderType.getOrderType();
  }

  // --------------------------------------------------------------------
  // DATA MEMBERS
  // --------------------------------------------------------------------

  protected EbicsResponse			response;
  private OrderType				orderType;
  private byte[]				transactionId;
  private static final long 			serialVersionUID = 7684048385353175772L;
}
