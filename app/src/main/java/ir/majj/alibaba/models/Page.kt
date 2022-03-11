package ir.majj.alibaba.models

import com.fasterxml.jackson.annotation.JsonProperty as SerializedName

class Page<T>(
    @SerializedName("page") val pageNumber: Int,
    @SerializedName("per_page") val itemsCount: Int,
    @SerializedName("total") val totalItems: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("data") val data: List<T>,
    @SerializedName("support") val support: Support
) {
    fun hasNextPage() = pageNumber < totalPages
}