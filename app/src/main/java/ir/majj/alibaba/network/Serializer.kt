package ir.majj.alibaba.network

import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Converter
import retrofit2.converter.jackson.JacksonConverterFactory

object Serializer {
    private val jackson: ObjectMapper = ObjectMapper()

    fun getConverterFactory(): Converter.Factory = JacksonConverterFactory.create(jackson)
}
