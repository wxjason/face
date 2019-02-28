package cn.wxj.common.bean.rsa;

import lombok.Data;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @ClassName: Rsa
 * @Package cn.wxj.common.bean.rsa
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/24 16:58
 * @Version V1.0
 */
@Data
public class Rsa {

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Override
    public String toString() {
        return "Rsa{" +
                "publicKey='" + publicKey + '\'' +
                ", privateKey='" + privateKey + '\'' +
                '}';
    }
}
