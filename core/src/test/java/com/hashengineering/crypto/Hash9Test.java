package com.hashengineering.crypto;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
/**
 * Created by HashEngineering on 6/30/14.
 */
public class Hash9Test {
    private static final Logger log = LoggerFactory.getLogger(Hash9Test.class);
    @Test
    public void Hash9Test1() throws Exception {

        for (int j = 0; j < 10; ++j)
        {
            //log.info("start j = "+ j +": ");
            byte [] header = new byte[80];
            for (int i = 0; i < 80; ++i)
                header[i] = (byte)(i*j);

            byte [] quark_a = Hash9.digest(header);
            byte [] quark_b = Hash9.digest(header, 0, 80);

            //log.info("a: " + quark_a.toString() + "\nb:" + quark_b.toString()+"\n");

            if(Arrays.equals(quark_a, quark_b) == false)
                throw new Exception("hash values do not match");


            //old method
            byte [] hash9_a = Hash9.digest(header);
            byte [] hash9_b = Hash9.digest(header, 0, 80);

            if(Arrays.equals(hash9_a, hash9_b) == false)
                throw new Exception("old hash values do not match");

            if(Arrays.equals(quark_a, hash9_a) == false)
                throw new Exception("new/old hash values do not match");

            //log.info("end");
        }
    }

    public void speedTest(int i)
    {

    }

    @Test
    public void speedTest() throws Exception {

        final int count = 10000;
        byte [][] headers = new byte [count][];

        for (int j = 0; j < count; ++j)
        {
            //log.info("start j = "+ j +": ");
            headers[j] = new byte[80];
            for (int i = 0; i < 80; ++i)
                headers[j][i] = (byte)(i*j);
        }
        long start;
        long end;



        start = System.currentTimeMillis();
        for (int j = 0; j < count; ++j)
        {
            byte [] hash = Hash9.digest(headers[j]);
        }
        end = System.currentTimeMillis();
        log.info("New formula Quark A: " + (end-start) + "ms");

        start = System.currentTimeMillis();
        for (int j = 0; j < count; ++j)
        {
            byte [] hash = Hash9.digest(headers[j]);
        }
        end = System.currentTimeMillis();

        log.info("Old formula Hash9 A: " + (end-start) + "ms");
        start = System.currentTimeMillis();
        for (int j = 0; j < count; ++j)
        {
            byte [] hash = hashengineering.quark.crypto.Hash9.quarkDigest(headers[j]);
        }
        end = System.currentTimeMillis();
        log.info("New formula Quark A: " + (end-start) + "ms");

        start = System.currentTimeMillis();
        for (int j = 0; j < count; ++j)
        {
            byte [] hash = Hash9.digest(headers[j], 0, 80);
        }
        end = System.currentTimeMillis();
        log.info("New formula Quark B: " + (end-start) + "ms");



        start = System.currentTimeMillis();
        for (int j = 0; j < count; ++j)
        {
            byte [] hash = hashengineering.quark.crypto.Hash9.quarkDigest(headers[j], 0, 80);
        }
        end = System.currentTimeMillis();
        log.info("Old formula Hash9 B: " + (end-start) + "ms");

        start = System.currentTimeMillis();
        for (int j = 0; j < count; ++j)
        {
            byte [] hash = Hash9.digest(headers[j]);
        }
        end = System.currentTimeMillis();
        log.info("New formula Quark A: " + (end-start) + "ms");

        start = System.currentTimeMillis();
        for (int j = 0; j < count; ++j)
        {
            byte [] hash = Hash9.digest(headers[j], 0, 80);
        }
        end = System.currentTimeMillis();
        log.info("New formula Quark B: " + (end-start) + "ms");
    }
}
