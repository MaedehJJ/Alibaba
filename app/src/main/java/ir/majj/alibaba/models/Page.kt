package ir.majj.alibaba.models

import com.fasterxml.jackson.annotation.JsonProperty

class Page<T>(
    @JsonProperty("page") val pageNumber: Int,
    @JsonProperty("per_page") val itemsCount: Int,
    @JsonProperty("total") val totalItems: Int,
    @JsonProperty("total_pages") val totalPages: Int,
    @JsonProperty("data") val data: List<T>,
) {
    fun hasNextPage() = pageNumber < totalPages
}