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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;

import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.hbm.devices.scan.configure.ConfigurationSender;
import com.hbm.devices.scan.configure.FakeMulticastSender;
import com.hbm.devices.scan.messages.ConfigurationRequest;
import com.hbm.devices.scan.messages.ConfigureDevice;
import com.hbm.devices.scan.messages.ConfigureInterface;
import com.hbm.devices.scan.messages.ConfigureNetSettings;
import com.hbm.devices.scan.messages.ConfigureParams;
import com.hbm.devices.scan.messages.Interface.Method;

public class ConfigurationSenderTest {

    private FakeMulticastSender fs;
    private ConfigurationSender cs;
    private JsonParser parser;

    @Before
    public void setUp() {
        fs = new FakeMulticastSender();
        cs = new ConfigurationSender(fs);
        parser = new JsonParser();
    }

    @Test
    public void parseCorrectConfig() {
        ConfigureDevice device = new ConfigureDevice("0009E5001571");
        ConfigureNetSettings settings = new ConfigureNetSettings(new ConfigureInterface("eth0", Method.DHCP, null));
        ConfigureParams configParams = new ConfigureParams(device, settings);
        ConfigurationRequest conf = new ConfigurationRequest(configParams, "TEST-UUID");
 
        try {
            cs.sendConfiguration(conf);
        } catch (IOException e) {
            fail("Got IOException during test: " + e);
        }
        String correctOutParsed = "{\"params\":{\"device\":{\"uuid\":\"0009E5001571\"},\"netSettings\":{\"interface\":{\"name\":\"eth0\",\"configurationMethod\":\"dhcp\"}},\"ttl\":1},\"id\":\"TEST-UUID\",\"jsonrpc\":\"2.0\",\"method\":\"configure\"}";
        JsonElement correct = parser.parse(correctOutParsed);
        JsonElement sent = parser.parse(fs.getLastSent());
        assertEquals("Configuration request and check string are not equal", sent, correct);
    }

    @Test(expected=IllegalArgumentException.class)
    public void parseNullConfigure() {
        try {
            cs.sendConfiguration(null);
            fail("Method didn't throw expected IllegalArgumentException");
        } catch (IOException e) {
        }
    }
}