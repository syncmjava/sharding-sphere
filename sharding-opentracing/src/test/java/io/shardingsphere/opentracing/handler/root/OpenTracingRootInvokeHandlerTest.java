/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
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
 * </p>
 */

package io.shardingsphere.opentracing.handler.root;

import io.shardingsphere.core.executor.sql.execute.threadlocal.ExecutorDataMap;
import io.shardingsphere.core.spi.root.RootInvokeHandlerLoader;
import io.shardingsphere.opentracing.handler.BaseOpenTracingHandlerTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class OpenTracingRootInvokeHandlerTest extends BaseOpenTracingHandlerTest {
    
    private final RootInvokeHandlerLoader loader = RootInvokeHandlerLoader.getInstance();
    
    @Test
    public void assertRootInvoke() {
        loader.start();
        assertTrue(OpenTracingRootInvokeHandler.isTrunkThread());
        assertNotNull(OpenTracingRootInvokeHandler.getActiveSpan().get());
        assertTrue(ExecutorDataMap.getDataMap().containsKey(OpenTracingRootInvokeHandler.ROOT_SPAN_CONTINUATION));
        loader.finish();
        assertFalse(OpenTracingRootInvokeHandler.isTrunkThread());
        assertNull(OpenTracingRootInvokeHandler.getActiveSpan().get());
    }
}
