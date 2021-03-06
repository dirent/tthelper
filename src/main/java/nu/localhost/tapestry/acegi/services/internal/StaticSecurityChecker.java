/*
 * Copyright 2007 Ivan Dubrov
 * Copyright 2007 Robin Helgelin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nu.localhost.tapestry.acegi.services.internal;

import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.intercept.AbstractSecurityInterceptor;
import org.acegisecurity.intercept.InterceptorStatusToken;
import org.acegisecurity.intercept.ObjectDefinitionSource;

/**
 * Straighforward implementation of the {@link SecurityChecker}.
 * @author Ivan Dubrov
 */
public class StaticSecurityChecker extends AbstractSecurityInterceptor
    implements SecurityChecker {

    /** Object definition source. */
    private ObjectDefinitionSource objectDefinitionSource =
        new StaticDefinitionSource();

    /**
     * Delegate to the
     * {@link AbstractSecurityInterceptor#beforeInvocation(Object)}.
     * @param object security object.
     * @return interceptor status token
     */
    public final InterceptorStatusToken checkBefore(final Object object) {
        return beforeInvocation(object);
    }

    /**
     * Delegate to the
     * {@link AbstractSecurityInterceptor#afterInvocation(InterceptorStatusToken
     * , Object)}.
     * @param token security token.
     * @param returnedObject object returned by the secured method.
     * @return object to return from the secured method
     */
    public final Object checkAfter(final InterceptorStatusToken token,
            final Object returnedObject) {
        return afterInvocation(token, returnedObject);
    }

    /**
     * Get secured object class.
     * @return secured object class.
     */
    public final Class getSecureObjectClass() {
        return ConfigAttributeDefinition.class;
    }

    /**
     * Obtain {@link ObjectDefinitionSource}.
     * @return {@link ObjectDefinitionSource}.
     */
    public final ObjectDefinitionSource obtainObjectDefinitionSource() {
        return objectDefinitionSource;
    }
}
