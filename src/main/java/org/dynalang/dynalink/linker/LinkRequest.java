/*
   Copyright 2011 Attila Szegedi

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package org.dynalang.dynalink.linker;

import org.dynalang.dynalink.CallSiteDescriptor;

/**
 * Represents a request to link a particular invocation at a particular call site. Instances of these requests are being
 * passed to {@link GuardingDynamicLinker}.
 *
 * @author Attila Szegedi
 */
public interface LinkRequest {
    /**
     * Returns the call site descriptor for the call site being linked.
     *
     * @return the call site descriptor for the call site being linked.
     */
    public CallSiteDescriptor getCallSiteDescriptor();

    /**
     * Returns the arguments for the invocation being linked. The returned array is a clone; modifications to it won't
     * affect the arguments in this request.
     *
     * @return the arguments for the invocation being linked.
     */
    public Object[] getArguments();

    /**
     * Returns the 0th argument for the invocation being linked; this is typically the receiver object.
     *
     * @return the receiver object.
     */
    public Object getReceiver();

    /**
     * Returns a request stripped from runtime context arguments. Some language runtimes will include runtime-specific
     * context parameters in their call sites as few arguments between 0th argument "this" and the normal arguments. If
     * a linker does not recognize such contexts at all, or does not recognize the call site as one with its own
     * context, it can ask for the alternative link request with context parameters and arguments removed, and link
     * against it instead.
     *
     * @return the context-stripped request. If the link request does not have any language runtime specific context
     * parameters, the same link request is returned.
     */
    public LinkRequest withoutRuntimeContext();

    /**
     * Returns a request identical to this one with call site descriptor and arguments replaced with the ones specified.
     *
     * @param callSiteDescriptor the new call site descriptor
     * @param arguments the new arguments
     * @return a new request identical to this one, except with the call site descriptor and arguments replaced with the
     * specified ones.
     */
    public LinkRequest replaceArguments(CallSiteDescriptor callSiteDescriptor, Object[] arguments);
}