package wf.garnier.spring.security.seven.authorization.server;

import org.junit.jupiter.api.Test;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.crypto.password4j.Argon2Password4jPasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;

class UtilsTests {


    @Test
	void argon2() {
        var encoder = new Argon2Password4jPasswordEncoder();

        var encoded = encoder.encode("password");
        System.out.println(encoded);
    }

    @Test
	void bcrypt() {
        var encoder = new BcryptPassword4jPasswordEncoder();

        var encoded = encoder.encode("password");
        System.out.println(encoded);
    }

    @Test
	void authentications() {
        var auth = new TestingAuthenticationToken("daniel", "pw", AuthorityUtils.createAuthorityList("ROLE_user", "ROLE_admin"));

        var newAuth = auth.toBuilder()
                .authorities(authorities -> authorities.add(FactorGrantedAuthority.fromAuthority(FactorGrantedAuthority.OTT_AUTHORITY)))
                .build();

        assertThat(newAuth.getAuthorities()).hasSize(3);

    }
}
