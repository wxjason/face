package cn.wxj.common.util;

import cn.wxj.common.bean.rsa.RSA;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @ClassName: RSAUtils
 * @Package cn.wxj.common.util
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/18 11:09
 * @Version V1.0
 */
public class RSAUtils {

    /**
     * 非对称密钥算法
     */
    public static final String KEY_ALGORITHM = "RSA";


    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 512;

    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 53;

    /** *//**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 64;

    private static final String PRIVATE_KEY_TEST = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAj54DOOOPBER2xToJ+ba46fE46OidcLELPXijZVapYdlG1RQT+fMzsABAmkLsi+3R0L6yws6eiHESkDRM7WfNTwIDAQABAkEAg117gQvVA4hEQ38lJL7YQVSeQ5ySdkPbDi1M+CLe6FphM5JytIOpaGbanCSShBNd3NR39QdhjkhWJ/2hTUh10QIhAMNIuJ8a7FRytuTHNpEe+0dZuCYcSapP4sNaNbunuyqjAiEAvET24FSIWq63/qeahQR/8j78jZVlvhtAiqNP1GryyWUCIFtMtIQuYi9ZMojjWm+evHwvRhkLQKWPk8Uhd920HZ1hAiEAr1kQEonI4aTfK6gQq5kwTYBKmtethi09pKJMZMwjSl0CIF7SGJBSBz44S/aUohobhnd+Pw51IWP0JVbCdkq8GFbm";

    /**
     * 初始化密钥对
     *
     * @return Map 甲方密钥的Map
     */
    public static RSA initKey() throws Exception {
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //甲方私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //将密钥存储在Bean中
        RSA rsa = new RSA();
        rsa.setPublicKey(publicKey);
        rsa.setPrivateKey(privateKey);
        return rsa;

    }


    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key       密钥
     * @return String 加密数据
     */
    public static String encryptByPrivateKey(String data, String key) throws Exception {

        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key       密钥
     * @return String 加密数据
     */
    public static String encryptByPublicKey(String data, String key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key));
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return String 解密数据
     */
    public static String decryptByPrivateKey(String data, String key) throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.decodeBase64(data)));
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return String 解密数据
     */
    public static String decryptByPublicKey(String data, String key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key));
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return new String(cipher.doFinal(Base64.decodeBase64(data)));
    }

    /**
     * 取得私钥
     *
     * @param rsa 密钥map
     * @return String 私钥
     */
    public static String getPrivateKey(RSA rsa) {
        Key key = rsa.getPrivateKey();
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param rsa 密钥map
     * @return String 公钥
     */
    public static String getPublicKey(RSA rsa) {
        Key key = rsa.getPublicKey();
        return Base64.encodeBase64String(key.getEncoded());
    }

    /** *//**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptSubByPrivateKey(String data, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        byte[] encryptedData = Base64.decodeBase64(data);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }

    /** *//**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param d 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encryptSubByPublicKey(String d, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        byte[] data = d.getBytes();
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.encodeBase64String(encryptedData);
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //初始化密钥
        //生成密钥对
        RSA rsa = RSAUtils.initKey();
        //公钥
        String publicKey = RSAUtils.getPublicKey(rsa);

        //私钥
        String privateKey = RSAUtils.getPrivateKey(rsa);
        System.out.println("公钥：/n" + publicKey);
        System.out.println("私钥：/n" + privateKey);

        System.out.println("公钥长度：" + publicKey.length());

        System.out.println("私钥长度：" + privateKey.length());

        System.out.println("================密钥对构造完毕,甲方将公钥公布给乙方，开始进行加密数据的传输=============");
        String str = "RSA密码交换算法";
//        System.out.println("/n===========甲方向乙方发送加密数据==============");
//        System.out.println("原文:" + str);
//        //甲方进行数据的加密
//        String code1 = RSAUtils.encryptByPrivateKey(str, privateKey);
//        System.out.println("加密后的数据：" + code1);
//        System.out.println("===========乙方使用甲方提供的公钥对数据进行解密==============");
//        //乙方进行数据的解密
//        String decode1 = RSAUtils.decryptByPublicKey(code1, publicKey);
//        System.out.println("乙方解密后的数据：" + decode1 + "/n/n");
//
//        System.out.println("===========反向进行操作，乙方向甲方发送数据==============/n/n");

        str = "{\"serialNo\":\"100000024_201812251455_354625\",\"tokenUrl\":\"http://iotapp.iot.189.cn:9090/uappt/certifHtml/ICertifFinalReport.action\"}";

        System.out.println("原文:" + str);

        //乙方使用公钥对数据进行加密
        String code2 = RSAUtils.encryptSubByPublicKey(str, publicKey);
        System.out.println("===========乙方使用公钥对数据进行加密==============");
        System.out.println("加密后的数据：" + code2);

        System.out.println("=============乙方将数据传送给甲方======================");
        System.out.println("===========甲方使用私钥对数据进行解密==============");

        //甲方使用私钥对数据进行解密
        String decode2 = RSAUtils.decryptSubByPrivateKey(code2, privateKey);

        System.out.println("甲方解密后的数据：" + decode2);
    }
//
//    public static void main(String[] args) throws Exception {
//        String str = "Od5jUkSfI1qWNHMs5JbA0sTfH2pRdYhmY2oG1hCd0QSxYTEEL0EDmni//F1AmcjypXaEVZMj5sE6gd5TOTfbBw==";
//        System.out.println("解密前:" + str);
//        String str1 = decryptByPrivateKey(str, PRIVATE_KEY_TEST);
//        System.out.println("解密后:" + str1);
//    }
}
