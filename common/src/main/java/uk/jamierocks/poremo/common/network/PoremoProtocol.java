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

package uk.jamierocks.poremo.common.network;

import org.spacehq.packetlib.Client;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.crypt.PacketEncryption;
import org.spacehq.packetlib.packet.DefaultPacketHeader;
import org.spacehq.packetlib.packet.PacketHeader;
import org.spacehq.packetlib.packet.PacketProtocol;
import uk.jamierocks.poremo.common.network.login.client.CPacketHandshake;
import uk.jamierocks.poremo.common.network.login.client.CPacketKeepAlive;
import uk.jamierocks.poremo.common.network.login.client.CPacketLoginRequest;
import uk.jamierocks.poremo.common.network.login.server.SPacketHandshake;
import uk.jamierocks.poremo.common.network.login.server.SPacketKeepAlive;
import uk.jamierocks.poremo.common.network.login.server.SPacketLoginRequest;

/**
 * The Poremo Protocol.
 *
 * @author Jamie Mansfield
 */
public class PoremoProtocol extends PacketProtocol {

    private PacketHeader header = new DefaultPacketHeader();

    @Override
    public String getSRVRecordPrefix() {
        return "_minecraft";
    }

    @Override
    public PacketHeader getPacketHeader() {
        return this.header;
    }

    @Override
    public PacketEncryption getEncryption() {
        return null;
    }

    @Override
    public void newClientSession(Client client, Session session) {
        this.registerIncoming(0x00, SPacketKeepAlive.class);
        this.registerIncoming(0x01, SPacketLoginRequest.class);
        this.registerIncoming(0x02, SPacketHandshake.class);

        this.registerOutgoing(0x00, CPacketKeepAlive.class);
        this.registerOutgoing(0x01, CPacketLoginRequest.class);
        this.registerOutgoing(0x02, CPacketHandshake.class);
    }

    @Override
    public void newServerSession(Server server, Session session) {
        this.registerIncoming(0x00, CPacketKeepAlive.class);
        this.registerIncoming(0x01, CPacketLoginRequest.class);
        this.registerIncoming(0x02, CPacketHandshake.class);

        this.registerOutgoing(0x00, SPacketKeepAlive.class);
        this.registerOutgoing(0x01, SPacketLoginRequest.class);
        this.registerOutgoing(0x02, SPacketHandshake.class);
    }

}
