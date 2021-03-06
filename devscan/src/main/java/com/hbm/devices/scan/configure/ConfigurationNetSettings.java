/*
 * Java Scan, a library for scanning and configuring HBM devices.
 *
 * The MIT License (MIT)
 *
 * Copyright (C) Hottinger Baldwin Messtechnik GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.hbm.devices.scan.configure;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * This class stores the network properties which are send in a configuration request
 * 
 * @since 1.0
 *
 */
public final class ConfigurationNetSettings implements Serializable {

    private static final long serialVersionUID = 8240137946038695796L;

    private ConfigurationDefaultGateway defaultGateway;

    @SerializedName("interface")
    private ConfigurationInterface iface;

    /**
     * This constructor is used to instantiate a {@link ConfigurationNetSettings} object. The default Gateway is
     * not changed.
     * 
     * @param iface
     *            the interface settings
     */
    public ConfigurationNetSettings(ConfigurationInterface iface) {
        this(iface, null);
    }

    /**
     * This constructor is used to instantiate a {@link ConfigurationNetSettings} object.
     * 
     * @param iface
     *            the interface settings
     * @param defaultGateway
     *            the new defaultGateway
     * @throws IllegalArgumentException if interface is {@code null}.
     */
    public ConfigurationNetSettings(ConfigurationInterface iface, ConfigurationDefaultGateway defaultGateway) {
        if (iface == null) {
            throw new IllegalArgumentException("No interface given!");
        }
        this.iface = iface;
        this.defaultGateway = defaultGateway;
    }

    /**
     * 
     * @return returns the default gateway settings
     */
    public ConfigurationDefaultGateway getDefaultGateway() {
        return defaultGateway;
    }

    /**
     * 
     * @return returns the interface settings
     */
    public ConfigurationInterface getInterface() {
        return iface;
    }
}
