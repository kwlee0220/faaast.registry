/*
 * Copyright (c) 2021 Fraunhofer IOSB, eine rechtlich nicht selbstaendige
 * Einrichtung der Fraunhofer-Gesellschaft zur Foerderung der angewandten
 * Forschung e.V.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.fraunhofer.iosb.ilt.faaast.registry.service.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.spi.FilterReply;


/**
 * Allows to set different log levels for FA³ST package and all other packages at run-time.
 */
public class FaaastFilter extends ch.qos.logback.core.filter.Filter<ILoggingEvent> {
    private static final String PACKAGE_FAAAST = "de.fraunhofer.iosb.ilt.faaast";
    private static Level levelFaaast = Level.WARN;
    private static Level levelExternal = Level.WARN;

    /**
     * Get level of logging for faaast packages.
     *
     * @return level of logging
     */
    public static Level getLevelFaaast() {
        return levelFaaast;
    }


    /**
     * Set level of logging for faaast packages.
     *
     * @param level of logging
     */
    public static void setLevelFaaast(Level level) {
        levelFaaast = level;
    }


    /**
     * Get level of logging for external packages.
     *
     * @return level of logging
     */
    public static Level getLevelExternal() {
        return levelExternal;
    }


    /**
     * Set level of logging for external packages.
     *
     * @param level of logging
     */
    public static void setLevelExternal(Level level) {
        levelExternal = level;
    }


    @Override
    public FilterReply decide(ILoggingEvent e) {
        if (e.getLoggerName().startsWith(PACKAGE_FAAAST) && e.getLevel().isGreaterOrEqual(levelFaaast)) {
            return FilterReply.ACCEPT;
        }
        if (!e.getLoggerName().startsWith(PACKAGE_FAAAST) && e.getLevel().isGreaterOrEqual(levelExternal)) {
            return FilterReply.ACCEPT;
        }
        return FilterReply.DENY;
    }
}
