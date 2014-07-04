/*
 * Copyright 2011 Google Inc.
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

package com.google.bitcoin.core;

import com.subgraph.orchid.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.bitcoin.core.Utils.doubleDigest;

/**
 * A protocol message that contains a repeated series of block headers, sent in response to the "getheaders" command.
 * This is useful when you want to traverse the chain but know you don't care about the block contents, for example,
 * because you have a freshly created wallet with no keys.
 */
public class CheckpointMessage extends Message {
    private static final Logger log = LoggerFactory.getLogger(CheckpointMessage.class);

    static final String mainPubKey = CoinDefinition.SATOSHI_KEY;
    static final String testPubKey = CoinDefinition.TESTNET_SATOSHI_KEY;
    static String masterPrivKey;

    int version = 1;
    Sha256Hash hashCheckpoint;
    byte [] message;
    byte [] signature;


    public CheckpointMessage(NetworkParameters params, byte[] payload) throws ProtocolException {
        super(params, payload, 0);
    }

    @Override
    public void bitcoinSerializeToStream(OutputStream stream) throws IOException {
        stream.write(version);
        stream.write(hashCheckpoint.getBytes());

        stream.write(new VarInt(message.length).encode());
        stream.write(message);
        stream.write(new VarInt(signature.length).encode());
        stream.write(signature);
    }

    @Override
    protected void parseLite() throws ProtocolException {
        if (length == UNKNOWN_LENGTH) {
            int saveCursor = cursor;
            //skip the version and hash
            length = 4 + 32;
            //skip the message
            //long size = readVarInt();
            //length += VarInt.sizeOf(size) + size;
            //skip the signature
            long size = readVarInt();
            length += VarInt.sizeOf(size) + size;
            cursor = saveCursor;
        }
    }

    @Override
    void parse() throws ProtocolException {


        long size = readVarInt();
        int saveCursor = cursor;
        message = readBytes((int)size);

        cursor = saveCursor;
        //this information is contained in the message;
        version = (int)readUint32();
        hashCheckpoint = new Sha256Hash(Utils.reverseBytes(readBytes(32)));

        size = readVarInt();
        signature = readBytes((int)size);

    }

    @Override
    public Sha256Hash getHash() {
        return new Sha256Hash(Utils.reverseBytes(doubleDigest(message)));
    }

    boolean checkSignature()
    {
       String masterPubKey = params.getId().equals(NetworkParameters.ID_TESTNET) ? testPubKey : mainPubKey;
       try {
           ECKey key = new ECKey(null, Hex.decode(masterPubKey));
           if(!key.verify(getHash().getBytes(), signature))
               return false;
       }
       catch (Exception e)
       {
           return false;
       }

       return true;
    }
    boolean processCheckpoint()
    {
        if(!checkSignature())
            return false;

        return false;
    }
}
