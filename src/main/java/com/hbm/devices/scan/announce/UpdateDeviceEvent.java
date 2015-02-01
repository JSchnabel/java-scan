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

package com.hbm.devices.scan.announce;

import com.hbm.devices.scan.messages.CommunicationPath;

/**
 * This event is emitted by an {@link com.hbm.devices.scan.announce.DeviceMonitor}.
 * <p>
 * The event is notified when an announce method from a device that is known, but has changed some
 * data in the announce message, is received. The event contains the old and the new announce
 * information stored in a {@link CommunicationPath}
 *
 * @since 1.0
 */
public final class UpdateDeviceEvent {

    private final CommunicationPath oldCommunicationPath;
    private final CommunicationPath newCommunicationPath;

    UpdateDeviceEvent(CommunicationPath oldPath, CommunicationPath newPath) {
        this.oldCommunicationPath = oldPath;
        this.newCommunicationPath = newPath;
    }

    public CommunicationPath getOldCommunicationPath() {
        return this.oldCommunicationPath;
    }

    public CommunicationPath getNewCommunicationPath() {
        return this.newCommunicationPath;
    }

}