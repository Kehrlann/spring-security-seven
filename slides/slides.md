---
theme: default
class: 'text-center'
highlighter: prism
lineNumbers: true
transition: none
# use UnoCSS
css: unocss
aspectRatio: "16/9"
colorSchema: "light"
canvasWidth: 1024
---

# **Spring Security Seven**

## *New and noteworthy*

<br>

### Daniel Garnier-Moiroux

Spring I/O, 2026-04-14


---
layout: image-right
image: /daniel-broken-shoulder.jpg
hideInToc: true
class: smaller
---

#### Daniel
### Garnier-Moiroux
<br>

Software Engineer

- <logos-spring-icon /> Spring
- <logos-bluesky /> @garnier.wf
- <logos-firefox /> https://garnier.wf/
- <logos-github-icon /> github.com/Kehrlann/
- <fluent-emoji-flat-envelope-with-arrow /> contact@garnier.wf

---

## Spring Security 7

<br>

It's here 🤩

It's packed with new features!

https://docs.spring.io/spring-security/reference/7.0/whats-new.html

---

## Spring Security 7

<br>

1. 📋 The boring stuff
1. 🔑📥🧬 Multi-Factor Authentication
1. ⚙️ Modular configuration
1. 🔒 OAuth2 new features
1. 🤖 MCP Security

---

## Spring Security 7

<br>

1. **📋 The boring stuff**
1. 🔑📥🧬 Multi-Factor Authentication
1. ⚙️ Modular configuration
1. 🔒 OAuth2 new features
1. 🤖 MCP Security

---

## Module changes

<br>

<v-clicks depth="1">

- `AccessDecisionManager`, `AccessDecisionVoter`, etc
    - ⏩ `org.springframework.security:spring-security-access`
    - (Deprecated in Spring Security 5!)
- Kerberos
    - Before: `org.springframework.security.kerberos` version 2.x
    - After: `org.springframework.security` version 7.x
- Authorization Server
    - Jump from 2.x to 7.x

</v-clicks>

---

## What's gone?

<br>

<v-clicks depth="2">

- OAuth2 Password Grant in client
    - I know you're sad 😢
    - But:
</v-clicks>

<br>

<v-click>

> The Password grant type is a legacy way to exchange a user's credentials for an access token. Because the client application has to collect the user's password and send it to the authorization server, it is not recommended that this grant be used at all anymore.
>
> &nbsp;
>
> *Source: https://oauth.net/2/grant-types/password/*
</v-click>

---

## What's gone?

<br>

<v-clicks depth="4">

- `.and()`
    - `.and()`
        - `.and()`
            - it's gone!
</v-clicks>

<v-click>
```java
// The Future™ (actually, the Present)
http.authorizeHttpRequests(
  authz -> authz.anyRequest().permitAll()
)

// Does not compute
http.authorizeHttpRequests()
  .anyRequest().permitAll()
  .and()
```
</v-click>

---

## Spring Security 7

<br>

1. 📋 The boring stuff
1. **🔑📥🧬 Multi-Factor Authentication**
1. ⚙️ Modular configuration
1. 🔒 OAuth2 new features
1. 🤖 MCP Security

---

## Multi-factor authentication

<br>

The longest-awaited feature of Spring Security!

https://github.com/spring-projects/spring-security/issues/2603

---

## Multi-Factor Authentication

Built around:

- `FactorGrantedAuthority` - special authority
- `@EnableMultiFactorAuthentication`
- `AuthorizationManagerFactory` to adapt `.hasRole(...)` etc

Use with:

- App-wide with `@EnableMFA(authorities = ...)`
- Endpoint-by-endpoint with `AuthorizationManager<...>`
    - `AllRequiredFactorsAuthorizationManager`

---

## Time-based MFA

"Authentication must be recent"

```java
// AuthorizationManagerFactory
var passwordLastMinute = AuthorizationManagerFactories.multiFactor()
	.requireFactor((factor) -> factor
			.passwordAuthority()
			.validDuration(Duration.ofSeconds(30))
	)
	.build();

// usage:
return http
		.authorizeHttpRequests(authz -> {
					authz.requestMatchers("/password")
							.access(passwordLastMinute.authenticated());
					// ...
				})
		// ...

```

---

## Spring Security 7

<br>

1. 📋 The boring stuff
1. 🔑📥🧬 Multi-Factor Authentication
1. **⚙️ Modular configuration**
1. 🔒 OAuth2 new features
1. 🤖 MCP Security

---

## Modular configuration

<br>

No need for a full `SecurityFilterChain` bean.

Use `Customizer` beans for `HttpSecurity` and top-level configurers.

A variant `ThrowingCustomizer` handles exceptions.

---

## Spring Security 7

<br>

1. 📋 The boring stuff
1. 🔑📥🧬 Multi-Factor Authentication
1. ⚙️ Modular configuration
1. **🔒 OAuth2 new features**
1. 🤖 MCP Security

---

## OAuth2 new features

<br>

- Authorization Server:
    - Dynamic Client Registration for OAuth2
    - Enfore Proof Key for Code Exchange by default
- Client:
    - Support HTTP Service Client
    - Use `@ClientRegistrationId` + `OAuth2RestClientHttpServiceGroupConfigurer`

---

## Spring Security 7

<br>

1. 📋 The boring stuff
1. 🔑📥🧬 Multi-Factor Authentication
1. ⚙️ Modular configuration
1. 🔒 OAuth2 new features
1. **🤖 MCP Security**

---

## MCP Security

<br>

📦 https://github.com/spring-ai-community/mcp-security/

&nbsp;

Track Model Context Protocol (MCP) authorization spec
<br>
<small>(that's hard, it changes every 3 months)</small>

Support for MCP: Servers, Clients, and for MCP-compliant authorization-server

---
layout: two-cols
---

<div style="height: 100%; display: flex; flex: row; justify-content: center; align-items: center;" >
    <img src="/mcp-session-springio.png" style="height:100%;" >
</div>

::right::

### MCP deep dive

Tomorrow, Apr 15th, 11:30

Track 3

---

## References

&nbsp;

#### **<logos-github-icon /> https://github.com/Kehrlann/spring-security-seven**

&nbsp;

- <logos-bluesky /> @garnier.wf
- <logos-firefox /> https://garnier.wf/
- <fluent-emoji-flat-envelope-with-arrow /> contact@garnier.wf

---
layout: two-cols
---

<div style="height: 100%; display: flex; flex: row; justify-content: center; align-items: center;" >
    <img src="/testing-spring-boot-applications-cover.png" style="" >
</div>

::right::

<div style="height: 100%; display: flex; flex-flow: column; justify-content: center; align-items: center;" >
    <img src="/manning-affiliate-qr-code.png" style="display: block;" >
    <div>https://hubs.la/Q04bFz560</div>
    <br>
    <div>45% off everything with code</div>
    <br>
    <div><b>springio26</b></div>
    <br>
    <div>(until May 2nd)</div>
</div>


---
layout: image
hideInToc: true
image: /meet-me.jpg
class: end
---

# **Merci 😊**
