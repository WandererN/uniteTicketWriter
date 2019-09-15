package com.jh.uniteticketwriter.extensions

fun <T> Array<T>.toHexString(bytesInLine: Int = -1): String {
    val builder = StringBuilder()
    var byteCounter = 0
    for (b in this) {
        builder.append(String.format("%02x", b))
        if (bytesInLine != -1) {
            byteCounter++
            if (byteCounter % bytesInLine == 0) {
                builder.append("\n")
            }
        }
    }
    return builder.toString()
}