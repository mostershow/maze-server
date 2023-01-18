//package com.icesimba.ecommerce.biz.string
//
//import okhttp3.internal.and
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.stereotype.Component
//import java.io.UnsupportedEncodingException
//import java.lang.StringBuilder
//import java.security.MessageDigest
//import java.security.NoSuchAlgorithmException
//import java.util.*
//
//@Component
//class SignUtil {
//
//    @Value("\${agiso.appsecret}")
//    fun setAppSecret(appSecret: String) {
//        SignUtil.appSecret = appSecret
//    }
//
//
//    companion object{
//        private var appSecret: String? = null
//
//        fun checkSign(json: String, timestamp: Long): String{
//            val map: MutableMap<String, String> = HashMap()
//            map["json"] = json
//            map["timestamp"] = java.lang.String.valueOf(timestamp)
//            //参考签名算法
//            println("secret:$appSecret")
//            return sign(map, appSecret)
//        }
//
//        // 参数签名
//        @Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class)
//        fun sign(params: Map<String, String?>, appSecret: String?): String {
//            val keys = params.keys.toTypedArray()
//            Arrays.sort(keys)
//            val query = StringBuilder()
//            query.append(appSecret)
//            for (key in keys) {
//                val value = params[key]
//                query.append(key).append(value)
//            }
//            query.append(appSecret)
//            val md5byte: ByteArray = encryptMD5(query.toString())
//            return byte2hex(md5byte)
//        }
//
//        // byte数组转成16进制字符串
//        private fun byte2hex(bytes: ByteArray): String {
//            val sign = StringBuilder()
//            for (i in bytes.indices) {
//                val hex = Integer.toHexString(bytes[i] and 0xFF)
//                if (hex.length == 1) {
//                    sign.append("0")
//                }
//                sign.append(hex.toUpperCase())
//            }
//            return sign.toString()
//        }
//
//        // Md5摘要
//        @Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class)
//        fun encryptMD5(data: String): ByteArray {
//            val md5: MessageDigest = MessageDigest.getInstance("MD5")
//            return md5.digest(data.toByteArray(charset("UTF-8")))
//        }
//    }
//}