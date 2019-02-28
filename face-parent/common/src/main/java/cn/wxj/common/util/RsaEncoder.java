package cn.wxj.common.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

/**
 * @ClassName: RsaEncoder
 * @Package cn.wxj.common.util
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/18 11:09
 * @Version V1.0
 */
public class RsaEncoder {

    /**
     * 非对称密钥算法
     */
    public static final String KEY_ALGORITHM = "Rsa";


    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 53;

    /**
     * 公钥
     */
    private static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI/T5487AA9iHnrJMMnFMR1BcBZbP9Mq4NgPP3eD5untyKGHaQYiowrvHc23QWCLpBZvesfDJ0e88G6whS2GLCMCAwEAAQ==";



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
        String str = "{\"timestamp\":1548921946,\"tokenUrl\":\"http://iotapp.iot.189.cn:9090/uappt/certifHtml/ICertifFinalReport.action\"}";
        System.out.println("加密前:" + str);
        String str1 = encryptSubByPublicKey(str, PUBLIC_KEY);
        System.out.println("加密后:" + str1);
    }
}
