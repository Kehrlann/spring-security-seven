# Spring security seven

## Intro

## New

show site

## Boring

## MFA

- most awaited feature

- start with mfa app
- Show app with two logins but no mfa
- Show MFA with both authorities
  - Show it works with the admin endpoint
- Show Daniel endpoint, secure with `access`
- Show AuthorizationManagerFActory bean
- Show endpoint by endpoint:
  - password last minute - AuthorizationManagerFactories.multiFactor()
  - Or AllRequiredFactorsAuthorizationManager
- Stronger-password for dynamic resolution:
  - Custom authorization manager for "admin"

## Modular configuration

On Authz server:

- SecurityFilterChain replaces eeverything: authorize /foo
- Customizer<HttpSecurity> -> authz requests authorize /foo
- Customizer<OAuth2AuthorizationServerConfigurer> -> add claim to oidc metadata
- Mention throwing customizer

## Little details

- Authorization.toBuilder in a test
- Password encoder password4j
- CsrfConfigurer#spa : show SPA
  - show CORS fails (ninja?)
  - BREACH support
  - show #spa
- exceptionHandling for particular missing authorities
  - /basic/**
  - exception handling: defaultDeniedHandlerForMissingAuthority
  - use MissingRole

## OAuth2

- Authorization Server:
  - Require Proof Key for Code Exchange or PKCE
    - Show property
  - Dynamic client registration
    - Enable
    - curl
    - enable open

- Client-side
  - in mfa, implement endpoint to register in /oauth2/register
  - OAuth2ClientHttpRequestInterceptor
  - show that you need to get a new token
  - setAuthorizationFailureHandler

- Show resource server
  - http://localhost:8090/hugo/winners?from=2015
  - with token
- Show service client @GetExchange
  - @ClientRegistrationId
  - OAuth2RestClientHttpServiceGroupConfigurer
