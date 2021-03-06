/*******************************************************************************
 * 
 *  Copyright (c) 2006-2012 eBay Inc. All Rights Reserved.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
*******************************************************************************/
package org.ebayopensource.aegis;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
  * Base Exception class for PEF.
  * Handles i18n messages via PEFMessages.properties resource bundle
  */
public class PolicyException extends RuntimeException
{
   
    private String m_errorid = null;
    private Object[] m_params = null;
    public final String RESOURCE_BUNDLE = "PEFMessages";
    
    public PolicyException(String errorid) 
    {
        super(errorid);
        m_errorid = errorid;
    }
    public PolicyException(String errorid,  Object... params) 
    {
        super(errorid+":"+params);
        m_errorid = errorid;
        m_params = params;
    }
    public String getLocalizedMessage()
    {
        ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_BUNDLE);
        if (rb != null) {
            return  MessageFormat.format(rb.getString(m_errorid), m_params);
        }
        return(m_errorid);
    }
}
