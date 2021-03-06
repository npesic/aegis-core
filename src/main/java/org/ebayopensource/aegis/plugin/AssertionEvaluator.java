
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
package org.ebayopensource.aegis.plugin;

import java.util.HashMap;
import java.util.List;

import org.ebayopensource.aegis.Context;
import org.ebayopensource.aegis.Assertion;
import org.ebayopensource.aegis.Decision;

/**
  * Plugin interface for evaluating <code>Assertion</code>s in a policy
  */
public interface AssertionEvaluator
{
    public void initialize(Context context);
    public Object getValue(String id, Context context);
    public boolean isMember(String parentcategory, Object parentname, Object member, Context ctx);
    public Decision evaluate(Assertion assertion, Context context) throws Exception;
}
