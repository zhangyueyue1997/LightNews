//package com.pb.common.Utils;
//
//
//import cn.hutool.json.JSONObject;
//import net.sf.json.JSONObject;
//import org.springframework.web.context.ContextLoader;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.servlet.ServletContext;
//import java.io.FileReader;
//import java.io.IOException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Properties;
//import java.util.ResourceBundle;
//
//
//public class WxConfigLoadUtil {
//    private final static ResourceBundle resource;
//    private static Properties props = new Properties();
//
//    static{
//        resource = ResourceBundle.getBundle("conf/wx");
//    }
//
//    public static String getWxConfigValueByKey(String keyWord){
//        return resource.getString(keyWord);
//    }
//
//    public static String getBestNewConfig(String str){
//        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
//        ServletContext servletContext = webApplicationContext.getServletContext();
//        try {
//            props.load(new FileReader(servletContext.getRealPath("/sysconfig")+"/wxconfig.properties"));
//            return props.getProperty(str);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    //SHA1加密算法
//    public static String SHA1(String decript) {
//        try {
//            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
//            digest.update(decript.getBytes());
//            byte messageDigest[] = digest.digest();
//            // Create Hex String
//            StringBuffer hexString = new StringBuffer();
//            // 字节数组转换为 十六进制 数
//            for (int i = 0; i < messageDigest.length; i++) {
//                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
//                if (shaHex.length() < 2) {
//                    hexString.append(0);
//                }
//                hexString.append(shaHex);
//            }
//            return hexString.toString();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//
//    /**
//     * 使用Authorization_code来获取Access Token。
//     *
//     * @param code 授权码Authorization Code
//     * @return AccessToken对象的封装。
//     * @throws BaiduOAuthException OAuthException异常类
//     */
//    public static JSONObject getAccessTokenByAuthorizationCode(String code) throws Exception {
//
//
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WxConfigLoadUtil.getWxConfigValueByKey("AppID")
//                + "&secret="+WxConfigLoadUtil.getWxConfigValueByKey("AppSecret")
//                + "&code="+code
//                + "&grant_type=authorization_code";
//
//        JSONObject jsonObject = HttpsUtils.findWxAccessToken(url, "GET");
//
//        return jsonObject;
//    }
//
//
//    /**
//     * 检查 AccessToken 是否过期
//     * @param access_token
//     * @param openid
//     * @return
//     * @throws Exception
//     */
//    public static JSONObject chickAccessToken(String access_token, String openid) throws Exception {
//
//        String chickUrl="https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openid;
//
//        JSONObject chickuserInfo = HttpsUtils.findWxAccessToken(chickUrl, "GET");
//
//        return chickuserInfo;
//    }
//    /**
//     * 使用Refresh Token来获取Access Token
//     * @param openid
//     * @param refresh_token
//     * @return
//     * @throws Exception
//     */
//    public static JSONObject getAccessTokenByRefreshToken(String openid, String refresh_token) throws Exception {
//
//        String refreshTokenUrl="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+openid+"&grant_type=refresh_token&refresh_token="+refresh_token;
//        JSONObject refreshInfo = HttpsUtils.findWxAccessToken(refreshTokenUrl, "GET");
//
//        return refreshInfo;
//    }
//
//
//    /**
//     * 获取用户信息
//     * @param access_token
//     * @param openid
//     * @return
//     * @throws Exception
//     */
//    public static JSONObject getUserInfo(String access_token, String openid) throws Exception {
//
//        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token
//                + "&openid="+openid
//                + "&lang=zh_CN";
//
//        JSONObject userInfo = HttpsUtils.findWxAccessToken(infoUrl, "GET");
//
//        return userInfo;
//    }
//
//}