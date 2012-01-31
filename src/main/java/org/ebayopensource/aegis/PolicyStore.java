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

import java.util.List;
public interface PolicyStore
{
    public Policy getPolicy(String id);
    public List<Policy> getAllPolicies();
    public void createPolicy(Policy policy);
    public void updatePolicy(Policy policy);
    public void deletePolicy(Policy policy);
}