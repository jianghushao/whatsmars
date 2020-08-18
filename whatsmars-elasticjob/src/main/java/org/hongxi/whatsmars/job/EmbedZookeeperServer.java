/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hongxi.whatsmars.job;

import org.apache.curator.test.TestingServer;

import java.io.File;
import java.io.IOException;

/**
 * Embed ZooKeeper.
 * 
 * <p>
 *     Only used for examples
 * </p>
 */
public final class EmbedZookeeperServer {
    
    private static TestingServer testingServer;
    
    /**
     * Embed ZooKeeper.
     * 
     * @param port ZooKeeper port
     */
    public static void start(final int port) {
        try {
            testingServer = new TestingServer(port, new File(String.format("target/test_zk_data/%s/", System.nanoTime())));
        // CHECKSTYLE:OFF
        } catch (final Exception ex) {
        // CHECKSTYLE:ON
            ex.printStackTrace();
        } finally {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    Thread.sleep(1000L);
                    testingServer.close();
                } catch (final InterruptedException | IOException ignore) {
                }
            }));
        }
    }
}
