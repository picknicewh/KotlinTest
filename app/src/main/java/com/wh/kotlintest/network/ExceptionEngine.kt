package com.wh.kotlintest.network


import android.net.ParseException
import com.google.gson.JsonParseException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.adapter.rxjava.HttpException
import java.net.ConnectException


class ExceptionEngine {

    /**
     * 约定异常
     */
    internal object ERROR {
        /**
         * 未知错误
         */
        val UNKNOWN = 1000
        /**
         * 解析错误
         */
        val PARSE_ERROR = 1001
        /**
         * 网络错误
         */
        val NETWORD_ERROR = 1002
        /**
         * 协议出错
         */
        val HTTP_ERROR = 1003

        /**
         * 证书出错
         */
        val SSL_ERROR = 1005

        /**
         * 连接超时
         */
        val TIMEOUT_ERROR = 1006
    }

    class ResponeThrowable(throwable: Throwable, var code: Int) : Exception(throwable) {
        override var message: String? = null
    }

    inner class ServerException : RuntimeException() {
        var code: Int = 0
        override var message: String? = null
    }

    companion object {

        private val UNAUTHORIZED = 401
        private val FORBIDDEN = 403
        private val NOT_FOUND = 404
        private val REQUEST_TIMEOUT = 408
        private val INTERNAL_SERVER_ERROR = 500
        private val BAD_GATEWAY = 502
        private val SERVICE_UNAVAILABLE = 503
        private val GATEWAY_TIMEOUT = 504

        fun handleException(e: Throwable): ResponeThrowable {
            val ex: ResponeThrowable
            if (e is HttpException) {
                ex = ResponeThrowable(e, ERROR.HTTP_ERROR)
                if (e.code()== UNAUTHORIZED||e.code()== FORBIDDEN||e.code()== NOT_FOUND||
                        e.code()== REQUEST_TIMEOUT||e.code()== GATEWAY_TIMEOUT||e.code()== INTERNAL_SERVER_ERROR
                        ||e.code()== BAD_GATEWAY ||e.code()== SERVICE_UNAVAILABLE){
                }else{
                    ex.message = "网络错误"
                }
                return ex
            } else if (e is ServerException) {
                val resultException = e
                ex = ResponeThrowable(resultException, resultException.code)
                ex.message = resultException.message
                return ex
            } else if (e is JsonParseException
                    || e is JSONException
                    || e is ParseException) {
                ex = ResponeThrowable(e, ERROR.PARSE_ERROR)
                ex.message = "解析错误"
                return ex
            } else if (e is ConnectException) {
                ex = ResponeThrowable(e, ERROR.NETWORD_ERROR)
                ex.message = "连接失败"
                return ex
            } else if (e is javax.net.ssl.SSLHandshakeException) {
                ex = ResponeThrowable(e, ERROR.SSL_ERROR)
                ex.message = "证书验证失败"
                return ex
            } else if (e is ConnectTimeoutException) {
                ex = ResponeThrowable(e, ERROR.TIMEOUT_ERROR)
                ex.message = "连接超时"
                return ex
            } else if (e is java.net.SocketTimeoutException) {
                ex = ResponeThrowable(e, ERROR.TIMEOUT_ERROR)
                ex.message = "连接超时"
                return ex
            } else {
                ex = ResponeThrowable(e, ERROR.UNKNOWN)
                ex.message = e.message
                return ex
            }

        }
    }

}
