package wf.garnier.spring.security.seven.mfa;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorityAuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.AuthenticationEntryPoint;

public class MissingRoleEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String header = "Basic realm=\"demo\"";
		var reason = HttpStatus.FORBIDDEN.getReasonPhrase();
		if (NestedExceptionUtils.getRootCause(authException) instanceof AuthorizationDeniedException authzDenied
				&& authzDenied.getAuthorizationResult() instanceof AuthorityAuthorizationDecision decision) {
			var missingRoles = decision.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.filter(Objects::nonNull)
				.filter(a -> a.startsWith("ROLE_"))
				.map(a -> a.replaceFirst("ROLE_", ""))
				.collect(Collectors.joining(","));
			header += ", missing_roles=\"" + missingRoles + "\"";
			reason = "Missing the following roles: " + missingRoles;
		}
		response.setHeader("WWW-Authenticate", header);
		response.sendError(HttpStatus.FORBIDDEN.value(), reason);

	}

}
