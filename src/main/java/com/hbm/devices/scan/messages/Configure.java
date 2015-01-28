/*
 * Java Scan, a library for scanning and configuring HBM devices.
 *
 * The MIT License (MIT)
 *
 * Copyright (C) Stephan Gatzka
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

package com.hbm.devices.scan.messages;

/**
 * Network configuration request datagrams are dedicated requests, so only the device addressed by
 * request/configure/device/uuid must answer with a network configuration response datagram.
 * 
 * @since 1.0
 */
public class Configure extends JsonRpc {

    private String id;
    private ConfigureParams params;

    private Configure() {
        super("configure");
    }

    /**
     * @param params
     *            the configuration parameters, which should be sent to a device
     * @param queryId
     *            A value of any type, which is used to match the response with the request that it
     *            is replying to.
     */
    public Configure(ConfigureParams params, String queryId) {
        this();
        this.params = params;
        this.id = queryId;
    }

    public ConfigureParams getParams() {
        return params;
    }

    @Override
    public String toString() {
        return params.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Configure)) {
            return false;
        }
        final Configure rhs = (Configure)o;
        return this.getJSONString().equals(rhs.getJSONString());
    }

    @Override
    public int hashCode() {
        return getJSONString().hashCode();
    }

    public String getQueryId() {
        return id;
    }
}
