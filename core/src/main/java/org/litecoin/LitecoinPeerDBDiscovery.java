/*
 * Copyright 2013 Matt Corallo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.litecoin;

import com.google.bitcoin.core.*;
import com.google.bitcoin.net.discovery.PeerDBDiscovery;
import com.google.bitcoin.net.discovery.PeerDiscoveryException;

import java.io.File;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import com.google.bitcoin.core.CoinDefinition;

/**
 * A version of PeerDBDiscovery that only returns nodes which support Bloom filters according to Litecoin's BLOOM bit
 */
public class LitecoinPeerDBDiscovery extends PeerDBDiscovery {
    // Ugly hack to only let the PeerDB know about peers with NODE_BLOOM (1<<1)
    // Wrap the connected event listener and intercept calls to it
    private static class WrappedEventListener extends AbstractPeerEventListener {
        PeerEventListener parent;
        NetworkParameters params;
        WrappedEventListener(NetworkParameters params, PeerEventListener parent) {
            this.params = params;
            this.parent = parent;
        }
        @Override
        public Message onPreMessageReceived(Peer p, Message m) {
            if (m instanceof AddressMessage) {
                AddressMessage newMessage = new AddressMessage(params);
                for (PeerAddress addr : ((AddressMessage) m).getAddresses())
                    if (!CoinDefinition.supportsBloomFiltering || addr.getServices().and(BigInteger.valueOf(1 << 1)).equals(BigInteger.valueOf(1 << 1)))
                        newMessage.addAddress(addr);
                return parent.onPreMessageReceived(p, newMessage);
            }
            return m;
        }
        @Override
        public void onPeerConnected(Peer p, int peerCount) {
            if (!CoinDefinition.supportsBloomFiltering || ((p.getPeerVersionMessage().localServices & (1<<1)) == (1<<1) &&
                    p.getPeerVersionMessage().clientVersion >= 70002))
                parent.onPeerConnected(p, peerCount);
            else
                p.close();
        }

        @Override
        public void onPeerDisconnected(Peer p, int peerCount) {
            if (!CoinDefinition.supportsBloomFiltering || (p.getPeerVersionMessage() != null && (p.getPeerVersionMessage().localServices & (1<<1)) == (1<<1)))
                parent.onPeerDisconnected(p, peerCount);
        }
    }
      
    private static class PeerGroupWrapper extends PeerGroup {
        private PeerGroup parent;
        NetworkParameters params;
        private PeerGroupWrapper(NetworkParameters params, PeerGroup peerGroup) {
            super(params);
            this.params = params;
            parent = peerGroup;
        }
        @Override
        public void addEventListener(PeerEventListener listener) {
            parent.addEventListener(new WrappedEventListener(params, listener));
        }
    }

    public LitecoinPeerDBDiscovery(NetworkParameters params, File db, PeerGroup group) {
        super(params, db, new PeerGroupWrapper(params, group));
    }
}

