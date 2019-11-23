package com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.Member
import org.slf4j.LoggerFactory
import org.springframework.util.StringUtils
import java.util.*
import java.util.regex.Pattern
import javax.annotation.PostConstruct


class JwtFactory(private val tokenIssuer: String, private val tokenSigningKey: String) {
    private var algorithm: Algorithm? = null
    private var jwtVerifier: JWTVerifier? = null

    @PostConstruct
    fun init() {
        algorithm = Algorithm.HMAC256(tokenSigningKey)
        jwtVerifier = JWT.require(algorithm!!).build()
    }

    fun generateToken(member: Member): String {
        return JWT.create()
            .withIssuer(tokenIssuer)
            .withClaim(CLAIM_NAME_MEMBER_ID, member.id)
            .sign(algorithm!!)
    }

    fun decodeToken(authorizationHeader: String): Optional<Int> {
        try {
            val token = extractAccessToken(authorizationHeader)
            val decodedJWT = jwtVerifier!!.verify(token)
            val claims = decodedJWT.claims
            val idClaim = claims[CLAIM_NAME_MEMBER_ID]
            if (idClaim == null) {
                log.warn("Failed to decode jwt token. header:$authorizationHeader")
                return Optional.empty()
            }
            return Optional.of(idClaim.asInt()!!)
        } catch (ex: IllegalArgumentException) {
            log.warn("Failed to extract token from header. header:$authorizationHeader", ex)
            return Optional.empty()
        } catch (ex: JWTVerificationException) {
            log.warn("Invalid access Token. header:$authorizationHeader", ex)
            return Optional.empty()
        }

    }

    private fun extractAccessToken(header: String): String {
        if (StringUtils.isEmpty(header)) {
            throw IllegalArgumentException("Invalid authorization header. header:$header")
        }
        val matcher = PATTERN_AUTHORIZATION_HEADER.matcher(header)
        if (!matcher.matches()) {
            throw IllegalArgumentException("Invalid authorization header. header:$header")
        }
        return matcher.group(1)
    }

    companion object {
        private val log = LoggerFactory.getLogger(JwtFactory::class.java)
        private val PATTERN_AUTHORIZATION_HEADER = Pattern.compile("[Bb]earer (.+)")
        private val CLAIM_NAME_MEMBER_ID = "id"
    }
}
