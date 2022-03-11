package ir.majj.alibaba.models

import com.fasterxml.jackson.annotation.JsonProperty

class User(
    @JsonProperty("id") val id: Int,
    @JsonProperty("email") val email: String,
    @JsonProperty("first_name") val firstName: String,
    @JsonProperty("last_name") val lastName: String,
    @JsonProperty("avatar") val avatar: String,
)