package hashengineering.quark.crypto;

import com.google.bitcoin.core.Sha256Hash;
import com.google.bitcoin.core.Sha512Hash;
import fr.cryptohash.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by HashEngineering on 2/18/14.
 */
@Deprecated
public class Hash9 {

    private static final Logger log = LoggerFactory.getLogger(Hash9.class);
    private static boolean native_library_loaded = false;

    static {

        try {
            System.loadLibrary("hash9");
            native_library_loaded = true;
        }
        catch(UnsatisfiedLinkError e)
        {

        }
        catch(Exception e)
        {
            native_library_loaded = false;
        }
    }

    public static byte[] quarkDigest(byte[] input, int offset, int length)
    {
        byte [] buf = new byte[length];
        for(int i = 0; i < length; ++i)
        {
            buf[i] = input[offset + i];
        }
        return quarkDigest(buf);
    }

    public static byte[] quarkDigest(byte[] input) {
        //long start = System.currentTimeMillis();
        try {

            //return SCrypt.scrypt(input, input, 1024, 1, 1, 32);
            //return hash9(input);
            //byte [] java = hash9(input);
            //long javaTime = System.currentTimeMillis();
            //byte [] nativeResult = hash9_native(input);
            //long nativeTime = System.currentTimeMillis();
            //log.info("Hashing times: native {} ms vs java {} ms\n{}\n{}", nativeTime - javaTime, javaTime - start, new Sha256Hash(nativeResult), new Sha256Hash(java));
            //log.info("Hashing time:  native {} ms", nativeTime - start);
            //return nativeResult;
            return native_library_loaded ? hash9_native(input) : hash9(input);
        } catch (Exception e) {
            return null;
        }
        finally {
            //long time = System.currentTimeMillis()-start;
            //log.info("Quark Hash time: {} ms per block", time);
        }
    }

    static native byte [] hash9_native(byte [] input);


    static byte [] hash9(byte header[]/*, const T1 pbegin, const T1 pend*/)

    {

        //log.info("hash9 start: ");
        /*sph_blake512_context     ctx_blake;
        sph_bmw512_context       ctx_bmw;
        sph_groestl512_context   ctx_groestl;
        sph_jh512_context        ctx_jh;
        sph_keccak512_context    ctx_keccak;
        sph_skein512_context     ctx_skein;*/
        //static unsigned char pblank[1];

        //#ifndef QT_NO_DEBUG
        //std::string strhash;
        //strhash = "";
        //#endif

        //byte [] contents = {8};
        //Sha512Hash mask = Sha512Hash.create(contents);
        //Sha512Hash zero = Sha512Hash.ZERO_HASH;

        //uint512 hash[9];
        Sha512Hash[] hash = new Sha512Hash[9];

        //sph_blake512_init(&ctx_blake);
        BLAKE512 blake512 = new BLAKE512();


        // ZBLAKE;
        //sph_blake512(&ctx_blake, (pbegin == pend ? pblank : static_cast<const void*>(&pbegin[0])), (pend - pbegin) * sizeof(pbegin[0]));
        hash[0] = new Sha512Hash(blake512.digest(header));
        //sph_blake512_close(&ctx_blake, static_cast<void*>(&hash[0]));
        //log.info("hash[0] blake: " + hash[0].toString());
        //sph_bmw512_init(&ctx_bmw);
        BMW512 bmw = new BMW512();
        // ZBMW;
        //sph_bmw512 (&ctx_bmw, static_cast<const void*>(&hash[0]), 64);
        hash[1] = new Sha512Hash(bmw.digest(hash[0].getBytes()));
        //sph_bmw512_close(&ctx_bmw, static_cast<void*>(&hash[1]));
        //log.info("hash[1] bmw: " + hash[1].toString());
        //if ((hash[1] & mask) != zero)
        //if((hash[1].bitwiseAnd(mask) != zero))
        if((hash[1].getBytes()[0] & 8) != 0)
        {
            //sph_groestl512_init(&ctx_groestl);
            Groestl512 groestl = new Groestl512();
            // ZGROESTL;
            //sph_groestl512 (&ctx_groestl, static_cast<const void*>(&hash[1]), 64);
            hash[2] = new Sha512Hash(groestl.digest(hash[1].getBytes()));
            //sph_groestl512_close(&ctx_groestl, static_cast<void*>(&hash[2]));
            //log.info("hash[2] groestl: " + hash[2].toString());
        }
        else
        {
            //sph_skein512_init(&ctx_skein);
            Skein512 skein = new Skein512();
            // ZSKEIN;
            //sph_skein512 (&ctx_skein, static_cast<const void*>(&hash[1]), 64);
            hash[2] = new Sha512Hash(skein.digest(hash[1].getBytes()));
            //sph_skein512_close(&ctx_skein, static_cast<void*>(&hash[2]));
            //log.info("hash[2] skein: " + hash[2].toString());
        }

        //sph_groestl512_init(&ctx_groestl);
        Groestl512 groestl = new Groestl512();
        // ZGROESTL;
        //sph_groestl512 (&ctx_groestl, static_cast<const void*>(&hash[2]), 64);
        hash[3] = new Sha512Hash(groestl.digest(hash[2].getBytes()));
        //sph_groestl512_close(&ctx_groestl, static_cast<void*>(&hash[3]));
        //log.info("hash[3] groestl: " + hash[3].toString());
        //sph_jh512_init(&ctx_jh);
        JH512 jh = new JH512();
        // ZJH;
        //sph_jh512 (&ctx_jh, static_cast<const void*>(&hash[3]), 64);
        //sph_jh512_close(&ctx_jh, static_cast<void*>(&hash[4]));
        hash[4] = new Sha512Hash(jh.digest(hash[3].getBytes()));
        //log.info("hash[4] jh: " + hash[4].toString());
        //if ((hash[4] & mask) != zero)
        //if((hash[4].bitwiseAnd(mask) != zero))
        if((hash[4].getBytes()[0] & 8) != 0)
        {
            //  sph_blake512_init(&ctx_blake);
            BLAKE512 blake5 = new BLAKE512();
            // ZBLAKE;
            //sph_blake512 (&ctx_blake, static_cast<const void*>(&hash[4]), 64);
            //sph_blake512_close(&ctx_blake, static_cast<void*>(&hash[5]));
            hash[5] = new Sha512Hash(blake5.digest(hash[4].getBytes()));
            // log.info("hash[5] blake: " + hash[5].toString());
        }
        else
        {
            //sph_bmw512_init(&ctx_bmw);
            BMW512 bmw5 = new BMW512();
            // ZBMW;
            //sph_bmw512 (&ctx_bmw, static_cast<const void*>(&hash[4]), 64);
            //sph_bmw512_close(&ctx_bmw, static_cast<void*>(&hash[5]));
            hash[5] = new Sha512Hash(bmw5.digest(hash[4].getBytes()));
            //log.info("hash[5] bmw: " + hash[5].toString());
        }

        //sph_keccak512_init(&ctx_keccak);
        Keccak512 keccak = new Keccak512();
        // ZKECCAK;
        //sph_keccak512 (&ctx_keccak, static_cast<const void*>(&hash[5]), 64);
        //sph_keccak512_close(&ctx_keccak, static_cast<void*>(&hash[6]));
        hash[6] = new Sha512Hash(keccak.digest(hash[5].getBytes()));
        //log.info("hash[6] keccak: " + hash[6].toString());
        //sph_skein512_init(&ctx_skein);
        Skein512 skein = new Skein512();
        // SKEIN;
        //sph_skein512 (&ctx_skein, static_cast<const void*>(&hash[6]), 64);
        //sph_skein512_close(&ctx_skein, static_cast<void*>(&hash[7]));
        hash[7] = new Sha512Hash(skein.digest(hash[6].getBytes()));
        //log.info("hash[7] skein: " + hash[7].toString());
        //if ((hash[7] & mask) != zero)
        //if((hash[7].bitwiseAnd(mask) != zero))
        if((hash[7].getBytes()[0] & 8) != 0)
        {
            //sph_keccak512_init(&ctx_keccak);
            Keccak512 keccak7 = new Keccak512();
            // ZKECCAK;
            //sph_keccak512 (&ctx_keccak, static_cast<const void*>(&hash[7]), 64);
            //sph_keccak512_close(&ctx_keccak, static_cast<void*>(&hash[8]));
            hash[8] = new Sha512Hash(keccak7.digest(hash[7].getBytes()));
            //log.info("hash[8] keccak: " + hash[8].toString());
        }
        else
        {
            //sph_jh512_init(&ctx_jh);
            JH512 jh7 = new JH512();
            // ZJH;
            //sph_jh512 (&ctx_jh, static_cast<const void*>(&hash[7]), 64);
            //sph_jh512_close(&ctx_jh, static_cast<void*>(&hash[8]));
            hash[8] = new Sha512Hash(jh7.digest(hash[7].getBytes()));
            //log.info("hash[8] jh: " + hash[8].toString());
        }
        //log.info("hash[8] trim256: " + hash[8].trim256().toString());

        return hash[8].trim256().getBytes();
    }
}
