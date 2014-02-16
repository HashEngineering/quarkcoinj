package com.google.bitcoin.core;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.Vector;
import fr.cryptohash.BLAKE512;
import fr.cryptohash.BMW512;
import fr.cryptohash.Groestl512;
import fr.cryptohash.Skein512;
import fr.cryptohash.Keccak512;
import fr.cryptohash.JH512;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.management.resources.agent_ko;

/**
 * Created with IntelliJ IDEA.
 * User: HashEngineering
 * Date: 8/13/13
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {
    private static final Logger log = LoggerFactory.getLogger(CoinDefinition.class);

    public static final String coinName = "Quark";
    public static final String coinTicker = "QRK";
    public static final String coinURIScheme = "quark";
    public static final String coinInternalName = "quarkcoin";
    public static final String cryptsyMarketId = "71";
    public static final String cryptsyMarketCurrency = "BTC";
    public static final String PATTERN_PRIVATE_KEY_START = "6";

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;


    public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://quarkexplorer.com/";
    public static final String BLOCKEXPLORER_BASE_URL_TEST = "http://quarkexplorer.com/";

    public static final String DONATION_ADDRESS = "QVJZByN6HdrTuEjAbgXpAnEUxUeeUaoEcA";  //HashEngineering donation QRK address

    enum CoinHash {
        SHA256,
        scrypt,
        quark
    };
    public static final CoinHash coinHash = CoinHash.quark;

    public static boolean checkpointFileSupport = true;
    //Original Values
    public static final int TARGET_TIMESPAN = (int)(10 * 60);  // 10 minutes per difficulty cycle, on average.
    public static final int TARGET_SPACING = (int)(1 * 30);  // 30 seconds per block.
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;  //20 blocks

    public static final int getInterval(int height, boolean testNet) {
            return INTERVAL;
    }
    public static final int getTargetTimespan(int height, boolean testNet) {
            return TARGET_TIMESPAN;
    }
    public static int getMaxTimeSpan(int value, int height, boolean testNet)
    {
            return value * 110 / 100;
    }
    public static int getMinTimeSpan(int value, int height, boolean testNet)
    {
            return value / 2;
    }
    public static int spendableCoinbaseDepth = 240; //main.h: static const int COINBASE_MATURITY

    public static BigInteger COIN = BigInteger.valueOf(100000);
    public static BigInteger CENT = BigInteger.valueOf(1000);
    public static BigInteger mCOIN = BigInteger.valueOf(100);

    public static final int MAX_MONEY = 500000000;                 //main.h:  MAX_MONEY
    public static final String MAX_MONEY_STRING = "500000000";     //main.h:  MAX_MONEY

    public static final BigInteger DEFAULT_MIN_TX_FEE = BigInteger.valueOf(10);   // MIN_TX_FEE
    public static final BigInteger DEFAULT_MIN_RELAY_TX_FEE = BigInteger.valueOf(100);   // MIN_TX_FEE
    public static final BigInteger DUST_LIMIT = CoinDefinition.CENT; //main.h CTransaction::GetMinFee        0.01 coins

    public static final int PROTOCOL_VERSION = 70001;          //version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 209;        //version.h MIN_PROTO_VERSION

    public static final int BLOCK_CURRENTVERSION = 1;   //CBlock::CURRENT_VERSION
    public static final int MAX_BLOCK_SIZE = 1 * 1000 * 1000;


    public static final boolean supportsBloomFiltering = true; //Requires PROTOCOL_VERSION 70000 in the client

    public static final int Port    = 11973;       //protocol.h GetDefaultPort(testnet=false)
    public static final int TestPort = 21973;     //protocol.h GetDefaultPort(testnet=true)

    //
    //  Production
    //
    public static final int AddressHeader = 58;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 9;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS

    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
    public static final long PacketMagic = 0xfea503dd;      //0xfb, 0xc0, 0xb6, 0xdb

    //Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = (0x1e0fffffL);         //main.cpp: LoadBlockIndex
    static public long genesisBlockTime = 1374408079L;                       //main.cpp: LoadBlockIndex
    static public long genesisBlockNonce = (12058113);                         //main.cpp: LoadBlockIndex
    static public String genesisHash = "00000c257b93a36e9a4318a64398d661866341331a984e2b486414fc5bb16ccd"; //main.cpp: hashGenesisBlock
    static public int genesisBlockValue = 1;                                                              //main.cpp: LoadBlockIndex
    //taken from the raw data of the block explorer
    static public String genesisXInBytes = "04ffff001d0104423231204a756c7920323031332c2054686520477561726469616e2c20546573636f20626f7373207361797320636865617020666f6f6420657261206973206f766572";   //"21 July 2013, The Guardian, Tesco boss says cheap food era is over"
    static public String genessiXOutBytes = "04678afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c702b6bf11d5f";

    //net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
            "seed1.qrk.cc",
        //    "seed2.qrk.cc",
         //   "seed3.qrk.cc",
           // "seed4.qrk.cc",
           // "seed5.qrk.cc",
           // "seed6.qrk.cc",
            "seed1.qrkcoin.org",
           // "seed2.qrkcoin.org",
          //  "seed3.qrkcoin.org",
           // "seed4.qrkcoin.org",
           // "seed5.qrkcoin.org",
           // "seed6.qrkcoin.org",
            "seed1.quarkinvest.info",
           // "seed2.quarkinvest.info",
           // "seed3.quarkinvest.info",
           // "seed4.quarkinvest.info",
           // "seed5.quarkinvest.info",
           // "seed6.quarkinvest.info",
            "quarkcoin.no-ip.biz",
            "quarkcoin.mooo.com",
            //"qrk.ignorelist.com",
            "qrk.redirectme.net",
            "qrk.no-ip.biz",

    };

    //
    // TestNet - digitalcoin - not tested
    //
    public static final boolean supportsTestNet = false;
    public static final int testnetAddressHeader = 119;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 199;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0x011a39f7;      //0xfc, 0xc1, 0xb7, 0xdc
    public static final String testnetGenesisHash = "5e039e1ca1dbf128973bf6cff98169e40a1b194c3b91463ab74956f413b2f9c8";
    static public long testnetGenesisBlockDifficultyTarget = (0x1e0fffffL);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 1373481000L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (905523645);                         //main.cpp: LoadBlockIndex





    //main.cpp GetBlockValue(height, fee)
    public static final BigInteger GetBlockReward(int height)
    {
        int COIN = 1;
        BigInteger nSubsidy = Utils.toNanoCoins(15, 0);

        if(height < 1080)
        {
            nSubsidy = Utils.toNanoCoins(2, 0); //2
        }
        else if(height < 2160)
        {
            nSubsidy   = Utils.toNanoCoins(1, 0); //2
        }
        else if(height < 3240)
        {
            nSubsidy   = Utils.toNanoCoins(2, 0); //2
        }
        else if(height < 4320)
        {
            nSubsidy  = Utils.toNanoCoins(5, 0); //5
        }
        else if(height < 5400)
        {
            nSubsidy  = Utils.toNanoCoins(8, 0); //8
        }
        else if(height < 6480)
        {
            nSubsidy = Utils.toNanoCoins(11, 0); //11
        }
        else if(height < 7560)
        {
            nSubsidy  = Utils.toNanoCoins(14, 0); //14
        }
        else if(height < 8640)
        {
            nSubsidy = Utils.toNanoCoins(17, 0); //17
        }
        else if(height < 523800)
        {
            nSubsidy = Utils.toNanoCoins(20, 0); //2
        }
        else
        {
            return nSubsidy.shiftRight(height / subsidyDecreaseBlockCount);
        }
        return nSubsidy;
    }

    public static int subsidyDecreaseBlockCount = 60480;     //main.cpp GetBlockValue(height, fee)

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20); // digitalcoin: starting difficulty is 1 / 2^12

    static public String[] testnetDnsSeeds = new String[] {
          "not supported"
    };
    //from main.h: CAlert::CheckSignature
    public static final String SATOSHI_KEY = "0493e6dc310a0e444cfb20f3234a238f77699806d47909a42481010c5ce68ff04d3babc959cd037bd3aa6ded929f2b9b4aa2f626786cd7f8495e5bb61e9cfebbc4";
    public static final String TESTNET_SATOSHI_KEY = "04218bc3f08237baa077cb1b0e5a81695fcf3f5b4e220b4ad274d05a31d762dd4e191efa7b736a24a32d6fd9ac1b5ebb2787c70e9dfad0016a8b32f7bd2520dbd5";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "org.quark.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "org.quark.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.google.quark.unittest";

    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
        checkpoints.put( 0,     new Sha256Hash("00000c257b93a36e9a4318a64398d661866341331a984e2b486414fc5bb16ccd"));
        checkpoints.put( 41056, new Sha256Hash("000000001f12305bf0443551030d9f18c5d7b1a6b7eb8e899b1b26fc45924ade"));
        checkpoints.put( 81847, new Sha256Hash("00000000c164428877cd4d46e2facc881b6b0a803e44a02c1f3b279ae7d58c32"));
        checkpoints.put(308484, new Sha256Hash("000000016bd2ef95ae4a456c6114cd7736a4219de5b75b2139c840650144e143"));
        checkpoints.put(380481, new Sha256Hash("00000003064d1fdbe86f35bfce8c54f88a80ef773e820ca86ae820ed6c4defcc"));
        checkpoints.put(404998, new Sha256Hash("000000004a815d04f437dd83d84866a8a07865f5b47030668a8096df0615361f"));
        checkpoints.put(411932, new Sha256Hash("000000001f3c7ec7251ebc1670fb3f772b42e25356fa02468c02c89199617cd5"));
        checkpoints.put(423094, new Sha256Hash("0000000007001e561197a35026b7c9bbaf0b9a1c918a41d9e7d638e44459f116"));
        checkpoints.put(443157, new Sha256Hash("000000000b103e119485969439ab2203b5578be3fb8b3aab512ebebaca1bce81"));
        checkpoints.put(458433, new Sha256Hash("000000000318a428560180bb8166321a6b20ae78fc0a9b3c560d30476859b2b5"));
        checkpoints.put(464836, new Sha256Hash("00000000079e9a16f173bf610f2ceddc5659aa7e9df2366dea01e346c37f9692"));
        checkpoints.put(467282, new Sha256Hash("0000000004a17401913be0aa29af7ace3335d58a846938d4fee0c749e4828d1d"));
        checkpoints.put(473033, new Sha256Hash("000000000515c71eb7c3de0574d5f6c632d8de9053c626aba22ae3a9eff67e9c"));
        checkpoints.put(538178, new Sha256Hash("000000000a13e56dc5d7962d4e3a852ff24055aa15096085d8173faf95172f4d"));
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
            return hash9(input);
        } catch (Exception e) {
            return null;
        }
        finally {
            //long time = System.currentTimeMillis()-start;
            //log.info("Quark Hash time: {} ms per block", time);
        }
    }


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
        Sha512Hash [] hash = new Sha512Hash[9];

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
