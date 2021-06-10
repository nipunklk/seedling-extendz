package com.hrandika.seedling.spring.utils

import java.util.HashMap


class ResponseMessage {

    companion object {
        fun createMessage(message: String?): HashMap<String?, String?>? {
            return object : HashMap<String?, String?>() {
                init {
                    put("message", message)
                }
            }
        } // creteMessage()


        fun getError(message: String?): HashMap<String?, String?>? {
            return object : HashMap<String?, String?>() {
                init {
                    put("error", message)
                }
            }
        } // creteMessage()
    }


}