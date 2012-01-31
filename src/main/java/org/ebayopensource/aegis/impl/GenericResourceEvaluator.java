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
package org.ebayopensource.aegis.impl;

import java.util.HashMap;
import java.util.List;

import org.ebayopensource.aegis.Environment;
import org.ebayopensource.aegis.Resource;
import org.ebayopensource.aegis.plugin.ResourceEvaluator;

/**
  * This class evaluates a Resource based on the data in the context.
  * Restrictions:
  * All data it needs must be available in the input <code>context</code>
  * Only primitive types that respond correctly to <code>java.lang.Comparable</code> are acceptable
  * TODO : add collection support ( if (x instanceof Collection<?>){})
  */
public class GenericResourceEvaluator implements ResourceEvaluator
{
    public void initialize(HashMap props) 
    {
    }
    public boolean evaluate(Resource reqresource, Resource polresource, List<Environment>  context) throws Exception
    {
        // TODO, check types and  evaluate attributes. For now simply return resource name match
        return reqresource.getName().equals(polresource.getName());
    }
}