package com.ck567.mazeserver.string

import java.util.*

class RandomString {
    companion object {
        fun getRandomString(Len: Int): String {
            // 小写的L和大写的I删除
            val baseString = arrayOf(
                    "0", "1", "2", "3", "4",
                    "5", "6", "7", "8", "9",
                    "a", "b", "c", "d", "e",
                    "f", "g", "h", "i", "j",
                    "k", "m", "n", "o",
                    "p", "q", "r", "s", "t",
                    "u", "v", "w", "x", "y",
                    "z", "A", "B", "C", "D",
                    "E", "F", "G", "H",
                    "J", "K", "L", "M", "N",
                    "O", "P", "Q", "R", "S",
                    "T", "U", "V", "W", "X",
                    "Y", "Z")
            var random = Random()
            val length = baseString.size
            var randomString = ""
            for (i in 0 until length) {
                randomString += baseString[random.nextInt(length)]
            }
            random = Random(System.currentTimeMillis())
            var resultStr = ""
            for (i in 0 until Len) {
                resultStr += randomString[random.nextInt(randomString.length - 1)]
            }
            return resultStr
        }
    }

}