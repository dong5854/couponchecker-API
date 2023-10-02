package com.dong.couponchecker;

import com.dong.couponchecker.config.JpaAuditingConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(JpaAuditingConfig.class)
@SpringBootApplication
public class CouponCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponCheckerApplication.class, args);
	}

}
