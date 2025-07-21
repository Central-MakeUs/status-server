package com.statoverflow.status.domain.auth.service;

import org.springframework.stereotype.Service;

public interface OAuthService {

    String getProviderId(String code);
}
