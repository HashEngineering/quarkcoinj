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
    public static final String coinURIScheme2 = "quarkcoin";
    public static final String coinInternalName = "quarkcoin";
    public static final String cryptsyMarketId = "71";
    public static final String cryptsyMarketCurrency = "BTC";
    public static final String PATTERN_PRIVATE_KEY_START = "6";

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;


    //public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://quarkexplorer.com/";
    //public static final String BLOCKEXPLORER_BASE_URL_TEST = "http://quarkexplorer.com/";
    public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://qrk.blockr.io/";
    public static final String BLOCKEXPLORER_BASE_URL_TEST = "http://qrk.blockr.io/";
    public static final String BLOCKEXPLORER_PATH_URL_PROD = "block/info/";
    public static final String BLOCKEXPLORER_PATH_URL_TEST = "block/info/";

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
            "seed1.quarkfoundation.cc", //95.85.58.230
            "seed2.quarkfoundation.cc", //162.243.46.40
            "seed3.quarkfoundation.cc",// 188.226.154.76
            "seed4.quarkfoundation.cc",// 128.199.215.12
            //"seed5.quarkfoundation.cc",
            //"seed6.quarkfoundation.cc",
            //"seed7.quarkfoundation.cc",
            //"seed8.quarkfoundation.cc",
            "110.174.173.86",
            "87.220.147.144",
            "seed1.qrk.cc",      //162.243.253.209     ***
        //    "seed2.qrk.cc",    //95.85.4.223
         //   "seed3.qrk.cc",    //192.241.195.203
            "seed4.qrk.cc",    //192.241.151.155 ***
            "seed5.qrk.cc",    //95.85.2.86      ***
           // "seed6.qrk.cc",    //162.243.138.170
           // "seed1.qrkcoin.org",   //192.241.151.155  (duplicate)
           // "seed2.qrkcoin.org", //95.85.4.223        (duplicate)
            "seed3.qrkcoin.org", //193.68.21.25 ***
           // "seed4.qrkcoin.org",  //95.85.4.223       (duplicate)
           // "seed5.qrkcoin.org",  //162.243.138.170   (duplicate)
           // "seed6.qrkcoin.org",  //95.85.2.86        (duplicate)
           // "seed1.quarkinvest.info",  //192.241.151.155 (duplicate)
           // "seed2.quarkinvest.info",  //95.85.2.86      (duplicate)
           // "seed3.quarkinvest.info",  //162.243.138.170 (duplicate)
           // "seed4.quarkinvest.info",  //95.85.4.223     (duplicate)
           // "seed5.quarkinvest.info",  //192.241.195.203 (duplicate)
           // "seed6.quarkinvest.info",  //192.241.151.155 (duplicate)
            //"quarkcoin.no-ip.biz",       //95.85.2.86    (duplicate)
            "quarkcoin.mooo.com",   //192.241.171.45  ***
            //"qrk.ignorelist.com", //162.243.138.170      (duplicate)
            //"qrk.redirectme.net",  //162.243.253.209  ***  (duplicate)
            //"qrk.no-ip.biz",       //192.241.151.155       (duplicate)

    };
    public static int minBroadcastConnections = 0;   //0 for default; we need more peers.
    //
    // TestNet - quarkcoin - not tested
    //
    public static final boolean supportsTestNet = false;
    public static final int testnetAddressHeader = 119;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 199;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0x011a39f7;      //0xfc, 0xc1, 0xb7, 0xdc
    public static final String testnetGenesisHash = "5e039e1ca1dbf128973bf6cff98169e40a1b194c3b91463ab74956f413b2f9c8";
    static public long testnetGenesisBlockDifficultyTarget = (0x1e0fffffL);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 1373481000L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (905523645);                         //main.cpp: LoadBlockIndex


    static final long _COIN = 100000;
    static final BigInteger nGenesisBlockRewardCoin = COIN;
    static final BigInteger nBlockRewardStartCoin = BigInteger.valueOf(2048 * _COIN);
    static final BigInteger nBlockRewardMinimumCoin = COIN;

    //main.cpp GetBlockValue(height, fee)
    static final BigInteger GetBlockValue(int nHeight)
    {
        if (nHeight == 0)
        {
            return nGenesisBlockRewardCoin;
        }

        BigInteger nSubsidy = BigInteger.valueOf(2048L * 100000L);

        // Subsidy is cut in half every 60480 blocks (21 days)
        //nSubsidy >>= (nHeight / 60480);
        nSubsidy = nSubsidy.shiftRight(nHeight / 60480);

        // Minimum subsidy
        if (nSubsidy.compareTo(nBlockRewardMinimumCoin) < 0)
        {
            nSubsidy = nBlockRewardMinimumCoin;
        }

        return nSubsidy;
    }
/*    public static final BigInteger GetBlockReward(int height)
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
    }*/

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




}
