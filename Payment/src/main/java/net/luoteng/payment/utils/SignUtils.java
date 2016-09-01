/*
 * Copyright 2016 suetming <suetming.ma at gmail.com>.
 *
 * This work is licensed under the 
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
 * To view a copy of this license, visit 
 *
 *      http://creativecommons.org/licenses/by-nc-nd/3.0/
 *
 * or send a letter to Creative Commons, 444 Castro Street, Suite 900, 
 * Mountain View, California, 94041, USA.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.luoteng.payment.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import net.luoteng.enums.SignType;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public class SignUtils {

    private static final String ALGORITHM = "RSA";

    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    private static final String DEFAULT_CHARSET = "UTF-8";

    public static String sign(String content, String privateKey, SignType signType) {
        switch(signType) {
            case RSA:
                try {
                    PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
                    KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
                    PrivateKey priKey = keyf.generatePrivate(priPKCS8);

                    java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

                    signature.initSign(priKey);
                    signature.update(content.getBytes(DEFAULT_CHARSET));

                    byte[] signed = signature.sign();

                    return Base64.encode(signed);
                } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | UnsupportedEncodingException | SignatureException e) {
                }
                break;
            case MD5:
                String plain = content + privateKey;
                return DigestUtils.md5Hex(getContentBytes(plain, DEFAULT_CHARSET));
        }
        
        return null;
    }
    
    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
    
}
