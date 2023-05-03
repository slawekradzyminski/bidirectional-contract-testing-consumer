## Sample Consumer for BiDirectional Contract Testing awesome-testing.com blogpost

## Provider

Provider is described [here](https://www.bezkoder.com/spring-boot-swagger-3/). Source code is
available [here](https://github.com/bezkoder/spring-boot-swagger-3-example). You need to clone and modify it if you wish
to experiment with OpenAPI definition.

`openapi.json` and `incompatibleopenapi.json` was generated via bezkoder's project is available here for simplicity
reasons. [correct](/openapi.json) and [incorrect](/incompatibleopenapi.json).

In bezkoder project there is a bug
in [TutorialController line 57](https://github.com/bezkoder/spring-boot-swagger-3-example/blob/master/src/main/java/com/bezkoder/spring/swagger/controller/TutorialController.java#L57).
Correct implementation is here:

```java
@Content(array = @ArraySchema(schema = @Schema(implementation = Tutorial.class)), mediaType = "application/json")
```

### Publishing provider pact
See `publishProviderPact.sh`

Will work only with proper Pactflow API token

### Publishing consumer pact

```commandline
./mvwn test
./mvwn pact:publish
```

Will work only with proper Pactflow API token
