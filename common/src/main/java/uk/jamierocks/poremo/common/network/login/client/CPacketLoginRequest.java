/*
 * This file is part of Poremo, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2016, Jamie Mansfield <https://www.jamierocks.uk/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package uk.jamierocks.poremo.common.network.login.client;

import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;
import uk.jamierocks.poremo.common.util.Constants;

import java.io.IOException;

/**
 * The client login request packet.
 *
 * @author Jamie Mansfield
 */
public class CPacketLoginRequest implements Packet {

    private int protocolVersion;
    private String username;
    private String serverPassword;
    private long worldSeed;
    private byte dimension;

    public CPacketLoginRequest(String username, String serverPassword) {
        this(Constants.PROTOCOL_VERSION, username, serverPassword, 0, (byte) 0);
    }

    public CPacketLoginRequest(int protocolVersion, String username, String serverPassword, long worldSeed, byte dimension) {
        this.protocolVersion = protocolVersion;
        this.username = username;
        this.serverPassword = serverPassword;
        this.worldSeed = worldSeed;
        this.dimension = dimension;
    }

    @Override
    public void read(NetInput netInput) throws IOException {
        this.protocolVersion = netInput.readInt();
        this.username = netInput.readString();
        this.serverPassword = netInput.readString();
        this.worldSeed = netInput.readLong();
        this.dimension = netInput.readByte();
    }

    @Override
    public void write(NetOutput netOutput) throws IOException {
        netOutput.writeInt(this.protocolVersion);
        netOutput.writeString(this.username);
        netOutput.writeString(this.serverPassword);
        netOutput.writeLong(this.worldSeed);
        netOutput.writeByte(this.dimension);
    }

    @Override
    public boolean isPriority() {
        return true;
    }

    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    public String getUsername() {
        return this.username;
    }

    public String getServerPassword() {
        return this.serverPassword;
    }

    public long getWorldSeed() {
        return this.worldSeed;
    }

    public byte getDimension() {
        return this.dimension;
    }
}
