# edu-id-claim-provider

`edu-id-claim-provider` is a Maven multi-module project that provides a custom **WSO2 Identity Server** claim provider bundle.

The bundle registers an `EDUIDClaimProvider` as both:

- `org.wso2.carbon.identity.openidconnect.ClaimProvider`
- `org.wso2.carbon.identity.oauth2.token.handlers.claims.JWTAccessTokenClaimProvider`

## What this project does

The provider inspects authorized user attributes during OAuth token generation and looks for the local claim URI `email`.

If `email` is available and non-empty, it adds an additional claim to the token payload:

- Claim name: `eduID`
- Claim value format: `<email>+1234567890`

## Project structure

- `pom.xml` (root): parent aggregator POM.
- `org.wso2.carbon.edu.id.claim.provider/`: OSGi bundle module.
  - `EDUIDClaimProvider.java`: claim provider implementation.
  - `internal/ClaimProviderServiceComponent.java`: OSGi DS component that registers the provider services.

## Prerequisites

- Java 8 (project source/target level is 1.8)
- Maven 3.6+
- A compatible WSO2 Identity Server runtime that supports the imported Carbon Identity/OAuth package ranges in the module POM

## Build

From the repository root:

```bash
mvn clean install
```

The built bundle will be created under:

```text
org.wso2.carbon.edu.id.claim.provider/target/
```

## Deploy to WSO2 Identity Server

1. Build the project.
2. Copy the generated bundle JAR from `org.wso2.carbon.edu.id.claim.provider/target/`.
3. Place it in the server's <IS-HOME>/repository/components/dropins deployment location according to your WSO2 IS distribution.
4. Restart the server
5. When you try out the application flows, make sure email is a requested user attribute and logged in user has an email, if you want to add eduID claim to access token/id token


If you want additional claims, extend the `getAdditionalClaims(...)` methods in `EDUIDClaimProvider` and ensure returned claim keys align with your token/claim dialect requirements.
