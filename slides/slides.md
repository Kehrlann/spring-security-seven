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
image: /daniel-intro-small.jpg
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
layout: fact
---

# What's gone?

---

## What's gone?

<br>

- `AccessDecisionManager`, `AccessDecisionVoter`, etc
    - Deprecated in Spring Security 5!
    - ⏩ `org.springframework.security:spring-security-access`

---

## What's gone?

<br>

<v-clicks depth="2">

- OAuth2 Password Grant
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
layout: fact
---

# What's new?

---

## Spring Security 7

<br>

1. 🔑📥🧬 Multi-Factor Authentication
1. 🛂 Authorization improvements
1. ⚙️ Modular configuration
1. 🍹 The little details
1. 🔒 OAuth2 new features
1. 🤖 MCP Security

---

## Spring Security 7

<br>

1. **🔑📥🧬 Multi-Factor Authentication**
1. 🛂 Authorization improvements
1. ⚙️ Modular configuration
1. 🍹 The little details
1. 🔒 OAuth2 new features
1. 🤖 MCP Security


---

## References

&nbsp;

#### **<logos-github-icon /> https://github.com/Kehrlann/spring-security-seven**

&nbsp;

- <logos-bluesky /> @garnier.wf
- <logos-firefox /> https://garnier.wf/
- <fluent-emoji-flat-envelope-with-arrow /> contact@garnier.wf

---
layout: image
hideInToc: true
image: /meet-me.jpg
class: end
---

# **Merci 😊**
