package com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.executor

import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.entity.ImageContainer
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.MemberRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.repository.OAuthRepository
import com.depromeet.plzdisturb.deprothonplzdisturbapi.domain.vo.AccessToken
import com.depromeet.plzdisturb.deprothonplzdisturbapi.presentation.login.jwt.JwtFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Login(
        private val kakaoRepository: OAuthRepository,
        private val memberRepository: MemberRepository,
        private val jwtFactory: JwtFactory
) : Executor<Login.Param, AccessToken> {
    @Transactional
    override fun execute(param: Param): AccessToken {
        // 카카오 api 호출
        val kakaoAccessToken = param.kakaoAccessToken
        val kakaoUserResponse = kakaoRepository.getUserInfo(kakaoAccessToken.value)
        // 멤버가 있는지 확인
        val member = memberRepository.getByOAuthUserId("kakao", kakaoUserResponse.id.toString())
        if (member != null) {
            return AccessToken(jwtFactory.generateToken(member))
        }
        // 있으면 로그인 없으면 생성
        return AccessToken(
                jwtFactory.generateToken(
                        memberRepository.add(
                                kakaoUserResponse.getUserName(),
                                ImageContainer.Image(kakaoUserResponse.getProfileImage()),
                                "kakao",
                                kakaoUserResponse.id.toString()
                        )
                )
        )
    }

    data class Param(
            val kakaoAccessToken: AccessToken
    ) : Executor.Param
}