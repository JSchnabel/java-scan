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

import com.hbm.devices.scan.AbstractMessageReceiver; 

/**
 * This class is used to simulate a device.
 * <p>
 * When this class receives a specific configuration message, it responses instantly with a success
 * message.
 * <p>
 * This class is only used for the JUnit tests.
 * 
 * @since 1.0
 *
 */
public class FakeDeviceEmulator extends AbstractMessageReceiver implements MulticastSender {

    private String responseString;
	private boolean closed;
    private String id;

    public FakeDeviceEmulator(String queryID) {
        id = queryID;
		closed = false;    
	}
    public void stop() {}
    public void run() {}
    @Override
    public void close() {closed = true;}
    public boolean isClosed() {return closed;}
    public void sendMessage(String message) {
        if (id.equals("wrong-id")) {
            responseString = "{\"id\":\"" + "some-id" + "\",\"jsonrpc\":\"2.0\",\"result\":0}";
        } else if (id.equals("error")) {
            responseString = "{\"id\":\"" + id + "\",\"jsonrpc\":\"2.0\",\"error\":{\"code\":2,\"message\":\"hello\"}}";
        } else if (id.equals("error-no-message")) {
            responseString = "{\"id\":\"" + id + "\",\"jsonrpc\":\"2.0\",\"error\":{\"code\":2}}";
        } else if (id.equals("error-empty-message")) {
            responseString = "{\"id\":\"" + id + "\",\"jsonrpc\":\"2.0\",\"error\":{\"code\":2,\"message\":\"\"}}";
        } else {
            responseString = "{\"id\":\"" + id + "\",\"jsonrpc\":\"2.0\",\"result\":0}";
        }
        setChanged();
        notifyObservers(responseString);
    }
}
