package com.hsryuuu.traffic.application.config

import org.springframework.beans.factory.annotation.Configurable
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configurable
@EnableJpaAuditing
class JpaConfig