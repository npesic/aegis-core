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

import java.util.List;
import org.ebayopensource.aegis.Advice;
import org.ebayopensource.aegis.Decision;
import org.ebayopensource.aegis.Effect;
import org.ebayopensource.aegis.Obligation;
import org.ebayopensource.aegis.Policy;
import org.ebayopensource.aegis.debug.Debug;
import org.ebayopensource.aegis.plugin.ConflictResolver;

/**
  * Implements a Deny-Overrides algorithm.
  */
public class SimpleDenyOverridesConflictResolver implements ConflictResolver
{
    public void resolve(Policy currentpolicy, Decision currentdecision, Decision conditiondecision)
    {
        int effect = currentpolicy.getEffect().get();
        if (conditiondecision.getType() == Decision.RULE_MATCH) {
            // We have a concrete decision for this policy here
            switch (currentdecision.getType() ) {
                case Effect.UNKNOWN :
                    // Easy decision - switch to policy decision
                    currentdecision.setType(effect);
                    copyObligations(conditiondecision, currentdecision, true);
                    break;
                case Effect.PERMIT :
                    if (effect == Effect.DENY) {
                        // Conflict - DENY wins
                        currentdecision.setType(effect);
                        // reset advices from condition decision
                        Debug.message("DOPConflictResolver","ph1 : reset Advices");
                        currentdecision.resetAdvices();
                        copyObligations(conditiondecision, currentdecision, true);
                    } else {
                        // ignore PERMIT - we already have a PERMIT
                        // Append obligations
                        copyObligations(conditiondecision, currentdecision, false);
                    }
                    break;
                case Effect.DENY :
                    // Already DENIED - copy obligations
                    copyObligations(conditiondecision, currentdecision, false);
                    break;
            }
        } else { // RULE_NOMATCH
            // We have a policy where certain conditions do not match
            switch (currentdecision.getType() ) {
                case Effect.UNKNOWN :
                    if (effect == Effect.PERMIT) {
                        // Copy advices...
                        if (conditiondecision.getAdvices() != null) {
                            for (Advice adv : conditiondecision.getAdvices()) {
                                currentdecision.addAdvice(adv);
                            }
                        }
                    } else {
                        // Leave the policy as inconclusive
                    }
                    break;
                case Effect.PERMIT :
                    // No action -preserve original decision
                    break;
                case Effect.DENY :
                    // No action -preserve original decision
                    break;
            }
        }
    }
    public void resolveFinal(Decision decision)
    {
        int effect = decision.getType();
        if (effect == Decision.EFFECT_DENY)
            decision.resetAdvices();
        if (effect == Decision.EFFECT_UNKNOWN)
            decision.setType(Decision.EFFECT_DENY);
    }
    private void copyObligations(Decision from, Decision to, boolean reset) 
    {
        // Remove existing obligations, replace with new obl
        if (reset)
            to.resetObligations();
        List<Obligation> condobls = 
            from.getObligations();
        if (condobls != null) {
            for (Obligation o : condobls) {
               to.addObligation(o);
            }
        }
    }
}
