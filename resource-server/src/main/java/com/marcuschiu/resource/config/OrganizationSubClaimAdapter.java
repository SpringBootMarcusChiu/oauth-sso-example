package com.marcuschiu.resource.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;

import java.util.Collections;
import java.util.Map;

public class OrganizationSubClaimAdapter implements Converter<Map<String, Object>, Map<String, Object>> {

	private final MappedJwtClaimSetConverter delegate = MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());

	/**
	 * see: https://www.baeldung.com/spring-security-oauth-jwt#rs-config
	 * @param claims
	 * @return
	 */
	public Map<String, Object> convert(Map<String, Object> claims) {
		Map<String, Object> convertedClaims = this.delegate.convert(claims);

		String organization = "unknown";
		if (convertedClaims.get("organization") != null) {
			organization = (String) convertedClaims.get("organization");
		}
		convertedClaims.put("organization", organization.toUpperCase());

		return convertedClaims;
	}
}