package ir.majj.alibaba.models

import com.fasterxml.jackson.annotation.JsonProperty

class Support(
    @JsonProperty("url") val url : String,
    @JsonProperty("text") val text : String
)