package com.jh.uniteticketwriter.extensions

import java.io.InputStream
import java.io.OutputStream

fun OutputStream.writeString(s: String) {
    write(s.length)
    write(s.toByteArray())
}

fun InputStream.readString(): String {
    val size = read()
    val arr = ByteArray(size)
    read(arr)
    return String(arr)
}
