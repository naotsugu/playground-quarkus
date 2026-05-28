# panache

## Running the application in dev mode

```shell
./gradlew quarkusDev
```
press `w` to open browser


## Packaging

```shell
./gradlew build
java -jar build/quarkus-app/quarkus-run.jar
```

## Uber-jar

```shell
./gradlew build -Dquarkus.package.jar.type=uber-jar
java -jar build/*-runner.jar
```

## entity

```java
@Entity
public class Person {
    @Id
    @GeneratedValue
    public UUID id;
    public String name;
    public LocalDate birth;
    public Status status;
}
```


```java
@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public Person findByName(String name){
        return find("name", name).firstResult();
    }

    public List<Person> findAlive(){
        return list("status", Status.Alive);
    }
}
```

```java
@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @GET
    public List<Person> get() {
        return personRepository.listAll(Sort.by("name"));
    }

    @POST
    @Transactional
    public Response create(Person person) {
        if (person.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        personRepository.persist(person);
        return Response.ok(person).status(201).build();
    }

    @GET
    public long count(){
        return personRepository.count();
    }

}
```

## application.properties

```properties
quarkus.banner.enabled=false
quarkus.datasource.db-kind=h2
quarkus.datasource.jdbc.url=jdbc:h2:mem:default;DB_CLOSE_DELAY=-1
quarkus.datasource.jdbc.driver=org.h2.Driver
quarkus.hibernate-orm.database.generation=drop-and-create
```

## gradle.properties

```properties
org.gradle.configuration-cache=false
```

## build.gradle.kts

```kotlin
plugins {
    java
    alias(libs.plugins.quarkus)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(enforcedPlatform(libs.quarkus.bom))
    implementation("io.quarkus:quarkus-rest-jackson")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-jdbc-h2")

    testImplementation("io.quarkus:quarkus-junit")
    testImplementation("io.rest-assured:rest-assured")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
```
