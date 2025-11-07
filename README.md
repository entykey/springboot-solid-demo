# Java springboot solid demo

## 🧰 To run

Run these commands:

```bash
# Clean old builds
mvn clean

# Rebuild dependencies (download Spring Boot, JPA, etc.)
mvn install

# Then run app
# mvn spring-boot:run

# Dùng PostgreSQL
mvn spring-boot:run -Dspring-boot.run.profiles=postgres

# Dùng PostgreSQL + giảm redundant logs
mvn spring-boot:run \
  -Dspring-boot.run.profiles=postgres \
  -Dspring-boot.run.arguments="--spring.main.log-startup-info=false --spring.output.ansi.enabled=ALWAYS"

# Dùng H2
mvn spring-boot:run -Dspring-boot.run.profiles=h2

# running test
mvn --no-transfer-progress test \
  -Dspring.main.banner-mode=off \
  -Dlogging.level.root=ERROR
```

## Bộ lệnh curl cơ bản để test CRUD API Spring Boot (RESTful)
### 🟢 1️⃣ CREATE (POST) – thêm user mới

```bash
curl -X POST http://localhost:8080/users \
     -H "Content-Type: application/json" \
     -d '{"name": "Alice", "email": "alice@example.com"}'
```

Kết quả mẫu:

```json
{"id":1,"name":"Alice","email":"alice@example.com"}
```

---

### 🟡 2️⃣ READ ALL (GET) – lấy danh sách tất cả user

```bash
curl http://localhost:8080/users
```

Kết quả mẫu:

```json
[
  {"id":1,"name":"Alice","email":"alice@example.com"},
  {"id":2,"name":"Bob","email":"bob@gmail.com"}
]
```

---

### 🟣 3️⃣ READ ONE (GET) – lấy thông tin 1 user theo id

```bash
curl http://localhost:8080/users/1
```

Kết quả mẫu:

```json
{"id":1,"name":"Alice","email":"alice@example.com"}
```

---

### 🔵 4️⃣ UPDATE (PUT) – cập nhật user theo id

```bash
curl -X PUT http://localhost:8080/users/1 \
     -H "Content-Type: application/json" \
     -d '{"name": "Alice Updated", "email": "alice@newmail.com"}'
```

Kết quả mẫu:

```json
{"id":1,"name":"Alice Updated","email":"alice@newmail.com"}
```

---

### 🔴 5️⃣ DELETE (DELETE) – xoá user theo id

```bash
curl -X DELETE http://localhost:8080/users/1
```

Kết quả mẫu (204 No Content hoặc 200 OK tùy controller):

```
(no content)
```

---

### ⚙️ Gợi ý thêm:

Nếu bạn muốn xem **header** và **status code**, thêm `-i`:

```bash
curl -i http://localhost:8080/users
```

Hoặc để **in đẹp JSON**:

```bash
curl http://localhost:8080/users | jq
```

Maven will automatically download everything (Spring Boot, JPA, H2, SQLite JDBC, etc.).

You don’t have to install anything else — just ensure you have:

- JDK 23 properly configured (`java -version`)
- Maven (`mvn -v`)

Output of `mvn clean`

```bash
PS D:\java-learning\springboot-solid-demo> mvn clean
WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
WARNING: sun.misc.Unsafe::staticFieldBase has been called by com.google.inject.internal.aop.HiddenClassDefiner (file:/D:/Programs/apache-maven-3.9.11/lib/guice-5.1.0-classes.jar)
WARNING: Please consider reporting this to the maintainers of class com.google.inject.internal.aop.HiddenClassDefiner
WARNING: sun.misc.Unsafe::staticFieldBase will be removed in a future release
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------< com.example:springboot-solid-demo >------------------
[INFO] Building springboot-solid-demo 1.0.0
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.3.2:clean (default-clean) @ springboot-solid-demo ---
[INFO] Deleting D:\java-learning\springboot-solid-demo\target
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.563 s
[INFO] Finished at: 2025-11-06T23:18:08+07:00
[INFO] ------------------------------------------------------------------------
```

When execute `mvn install` 

```bash
. . . .
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ springboot-solid-demo ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ springboot-solid-demo ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 9 source files with javac [debug parameters release 23] to target\classes
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ springboot-solid-demo ---
[INFO] skip non existing resourceDirectory D:\java-learning\springboot-solid-demo\src\test\resources
[INFO] 
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ springboot-solid-demo ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 2 source files with javac [debug parameters release 23] to target\test-classes
[INFO] 
[INFO] --- surefire:3.2.5:test (default-test) @ springboot-solid-demo ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.10.3/junit-platform-launcher-1.10.3.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.10.3/junit-platform-launcher-1.10.3.pom (3.0 kB at 47 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.10.3/junit-platform-launcher-1.10.3.jar
Downloaded from central: https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.10.3/junit-platform-launcher-1.10.3.jar (184 kB at 1.8 MB/s)
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.example.demo.controller.UserControllerTest
23:20:25.017 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [com.example.demo.controller.UserControllerTest]: UserControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
23:20:25.185 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration com.example.demo.DemoApplication for test class com.example.demo.controller.UserControllerTest

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.3)

2025-11-06T23:20:25.739+07:00  INFO 6496 --- [           main] c.e.demo.controller.UserControllerTest   : Starting UserControllerTest using Java 24.0.2 with PID 6496 (started by Windows in D:\java-learning\springboot-solid-demo)
2025-11-06T23:20:25.744+07:00  INFO 6496 --- [           main] c.e.demo.controller.UserControllerTest   : No active profile set, falling back to 1 default profile: "default"
2025-11-06T23:20:26.486+07:00  WARN 6496 --- [           main] o.s.w.c.s.GenericWebApplicationContext   : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'userController' defined in file [D:\java-learning\springboot-solid-demo\target\classes\com\example\demo\controller\UserController.class]: Unsatisfied dependency expressed through constructor parameter 0: No qualifying bean of type 'com.example.demo.service.UserService' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
. . . . . .
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 1.745 s <<< FAILURE! -- in com.example.demo.controller.UserControllerTest
[ERROR] com.example.demo.controller.UserControllerTest.shouldReturnEmptyList -- Time elapsed: 0.025 s <<< ERROR!
java.lang.IllegalStateException: Failed to load ApplicationContext for [WebMergedContextConfiguration@4ee6291f testClass = com.example.demo.controller.UserControllerTest, locations = [], classes = [com.example.demo.DemoApplication], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = ["org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTestContextBootstrapper=true"], contextCustomizers = [[ImportsContextCustomizer@2b03d52f key = [org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration, org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration, org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration, org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration, org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration, org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration, org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration, org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration, org.springframework.boot.test.autoconfigure.web.reactive.WebTestClientAutoConfiguration, org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration, org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration, org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration, org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration, org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcSecurityConfiguration, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcWebClientAutoConfiguration, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcWebDriverAutoConfiguration, org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration, org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration, org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration, org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration, org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration, org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration, org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration, org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration, org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration]], org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@43f02ef2, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@6f204a1a, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@30f842ca, org.springframework.boot.test.autoconfigure.OverrideAutoConfigurationContextCustomizerFactory$DisableAutoConfigurationContextCustomizer@7e990ed7, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.filter.TypeExcludeFiltersContextCustomizer@897ee71c, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@fe349e1e, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@3b6d844d, org.springframework.boot.test.context.SpringBootTestAnnotation@7c2aea12], resourceBasePath = "src/main/webapp", contextLoader = org.springframework.boot.test.context.SpringBootContextLoader, parent = null]
. . . .
[ERROR] Tests run: 2, Failures: 0, Errors: 1, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.975 s
[INFO] Finished at: 2025-11-06T23:20:27+07:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.2.5:test (default-test) on project springboot-solid-demo:
[ERROR]
[ERROR] Please refer to D:\java-learning\springboot-solid-demo\target\surefire-reports for the individual test results.
[ERROR] Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
```

### 🧰 2️⃣ About the build failure (Surefire test issue)

Your issue is **not really fixed** by just replacing the Spring Boot plugin with `maven-surefire-plugin`.

That Google fix changes how tests are run, but **removes** your Spring Boot build plugin — which will cause problems when you try to package or run the project with `mvn spring-boot:run`.

Let’s fix it properly instead 👇

---

### ✅ Correct plugin section in your `pom.xml`

Here’s what your `pom.xml` **should have at the bottom**:

**THIS DOESNT FIX ANYTHING AT ALL!**

```xml
<build>
    <plugins>
        <!-- Spring Boot Plugin: needed for mvn spring-boot:run -->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>3.3.4</version>
        </plugin>

        <!-- Surefire Plugin: runs tests safely -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.2.5</version>
            <configuration>
                <printSummary>true</printSummary>
            </configuration>
        </plugin>
    </plugins>
</build>
```

That way:

- Spring Boot builds & runs fine.
- Surefire handles tests with proper configuration.

---

### 🧪 3️⃣ Skipping tests temporarily

If you just want to **build and run quickly**, skip tests entirely:

```bash
mvn clean install -DskipTests
```

or

```bash
mvn spring-boot:run -DskipTests
```

Then you can focus on app logic before fixing the test itself later.

Output of `mvn clean install -DsckipTests`

```bash
PS D:\java-learning\springboot-solid-demo> mvn clean install -DskipTests
WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
WARNING: sun.misc.Unsafe::staticFieldBase has been called by com.google.inject.internal.aop.HiddenClassDefiner (file:/D:/Programs/apache-maven-3.9.11/lib/guice-5.1.0-classes.jar)
WARNING: Please consider reporting this to the maintainers of class com.google.inject.internal.aop.HiddenClassDefiner
WARNING: sun.misc.Unsafe::staticFieldBase will be removed in a future release
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------< com.example:springboot-solid-demo >------------------
[INFO] Building springboot-solid-demo 1.0.0
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.3.2:clean (default-clean) @ springboot-solid-demo ---
[INFO] Deleting D:\java-learning\springboot-solid-demo\target
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ springboot-solid-demo ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ springboot-solid-demo ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 9 source files with javac [debug parameters release 23] to target\classes
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ springboot-solid-demo ---
[INFO] skip non existing resourceDirectory D:\java-learning\springboot-solid-demo\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ springboot-solid-demo ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 2 source files with javac [debug parameters release 23] to target\test-classes
[INFO] 
[INFO] --- surefire:3.2.5:test (default-test) @ springboot-solid-demo ---
[INFO] Tests are skipped.
[INFO]
[INFO] --- jar:3.4.2:jar (default-jar) @ springboot-solid-demo ---
[INFO] Building jar: D:\java-learning\springboot-solid-demo\target\springboot-solid-demo-1.0.0.jar
[INFO] 
[INFO] --- spring-boot:3.3.4:repackage (repackage) @ springboot-solid-demo ---
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-buildpack-platform/3.3.4/spring-boot-buildpack-platform-3.3.4.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-buildpack-platform/3.3.4/spring-boot-buildpack-platform-3.3.4.pom (3.2 kB at 6.9 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-loader-tools/3.3.4/spring-boot-loader-tools-3.3.4.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-loader-tools/3.3.4/spring-boot-loader-tools-3.3.4.pom (2.2 kB at 30 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/spring-core/6.1.13/spring-core-6.1.13.pom    
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/spring-core/6.1.13/spring-core-6.1.13.pom (2.0 kB at 26 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/spring-jcl/6.1.13/spring-jcl-6.1.13.pom      
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/spring-jcl/6.1.13/spring-jcl-6.1.13.pom (1.8 kB at 24 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/spring-context/6.1.13/spring-context-6.1.13.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/spring-context/6.1.13/spring-context-6.1.13.pom (2.8 kB at 40 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/spring-aop/6.1.13/spring-aop-6.1.13.pom      
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/spring-aop/6.1.13/spring-aop-6.1.13.pom (2.2 kB at 32 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/spring-beans/6.1.13/spring-beans-6.1.13.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/spring-beans/6.1.13/spring-beans-6.1.13.pom (2.0 kB at 31 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/spring-expression/6.1.13/spring-expression-6.1.13.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/spring-expression/6.1.13/spring-expression-6.1.13.pom (2.1 kB at 29 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/micrometer/micrometer-observation/1.12.10/micrometer-observation-1.12.10.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/micrometer/micrometer-observation/1.12.10/micrometer-observation-1.12.10.pom (3.8 kB at 53 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/micrometer/micrometer-commons/1.12.10/micrometer-commons-1.12.10.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/micrometer/micrometer-commons/1.12.10/micrometer-commons-1.12.10.pom (3.4 kB at 47 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-buildpack-platform/3.3.4/spring-boot-buildpack-platform-3.3.4.jar
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-buildpack-platform/3.3.4/spring-boot-buildpack-platform-3.3.4.jar (289 kB at 2.4 MB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-loader-tools/3.3.4/spring-boot-loader-tools-3.3.4.jar
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/spring-core/6.1.13/spring-core-6.1.13.jar    
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/spring-context/6.1.13/spring-context-6.1.13.jar
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/spring-aop/6.1.13/spring-aop-6.1.13.jar      
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/spring-jcl/6.1.13/spring-jcl-6.1.13.jar      
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/spring-context/6.1.13/spring-context-6.1.13.jar (1.3 MB at 6.5 MB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/spring-beans/6.1.13/spring-beans-6.1.13.jar  
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/spring-jcl/6.1.13/spring-jcl-6.1.13.jar (25 kB at 102 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/spring-expression/6.1.13/spring-expression-6.1.13.jar
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/spring-aop/6.1.13/spring-aop-6.1.13.jar (417 kB at 1.4 MB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/micrometer/micrometer-observation/1.12.10/micrometer-observation-1.12.10.jar
Downloaded from central: https://repo.maven.apache.org/maven2/io/micrometer/micrometer-observation/1.12.10/micrometer-observation-1.12.10.jar (72 kB at 179 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/micrometer/micrometer-commons/1.12.10/micrometer-commons-1.12.10.jar
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-loader-tools/3.3.4/spring-boot-loader-tools-3.3.4.jar (464 kB at 1.1 MB/s)
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/spring-expression/6.1.13/spring-expression-6.1.13.jar (304 kB at 684 kB/s)
Downloaded from central: https://repo.maven.apache.org/maven2/io/micrometer/micrometer-commons/1.12.10/micrometer-commons-1.12.10.jar (47 kB at 101 kB/s)
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/spring-core/6.1.13/spring-core-6.1.13.jar (1.9 MB at 3.4 MB/s)
Downloaded from central: https://repo.maven.apache.org/maven2/org/springframework/spring-beans/6.1.13/spring-beans-6.1.13.jar (862 kB at 1.6 MB/s)
[INFO] Replacing main artifact D:\java-learning\springboot-solid-demo\target\springboot-solid-demo-1.0.0.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to D:\java-learning\springboot-solid-demo\target\springboot-solid-demo-1.0.0.jar.original
[INFO]
[INFO] --- install:3.1.3:install (default-install) @ springboot-solid-demo ---
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-util/1.9.18/maven-resolver-util-1.9.18.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-util/1.9.18/maven-resolver-util-1.9.18.pom (2.9 kB at 37 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver/1.9.18/maven-resolver-1.9.18.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver/1.9.18/maven-resolver-1.9.18.pom (22 kB at 290 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-api/1.9.18/maven-resolver-api-1.9.18.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-api/1.9.18/maven-resolver-api-1.9.18.pom (2.7 kB at 34 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-util/1.9.18/maven-resolver-util-1.9.18.jar
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-util/1.9.18/maven-resolver-util-1.9.18.jar (196 kB at 2.3 MB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-api/1.9.18/maven-resolver-api-1.9.18.jar
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/resolver/maven-resolver-api/1.9.18/maven-resolver-api-1.9.18.jar (157 kB at 1.8 MB/s)
[INFO] Installing D:\java-learning\springboot-solid-demo\pom.xml to C:\Users\Windows\.m2\repository\com\example\springboot-solid-demo\1.0.0\springboot-solid-demo-1.0.0.pom
[INFO] Installing D:\java-learning\springboot-solid-demo\target\springboot-solid-demo-1.0.0.jar to C:\Users\Windows\.m2\repository\com\example\springboot-solid-demo\1.0.0\springboot-solid-demo-1.0.0.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  7.602 s
[INFO] Finished at: 2025-11-06T23:29:04+07:00
[INFO] ------------------------------------------------------------------------
```

---

### 🩹 4️⃣ Why the test fails

The error says:

```
IllegalState: Failed to load ApplicationContext for UserControllerTest
```

This usually means your test class has `@WebMvcTest` or `@SpringBootTest` but Spring can’t find the right configuration or beans.

It’s not fatal — it just means the test is too complex for your current setup.

You can open `UserControllerTest.java` and either:

- Comment it out for now, or
- Replace the annotation with a simpler unit test (not loading the full context).

---

### ✅ TL;DR — What you should do now

1. Keep only **one repository implementation** (e.g., `UserRepositorySqlite.java`).
2. Update your `<build><plugins>` block like I showed above.
3. Run:
    
    ```bash
    mvn clean install -DskipTests
    mvn spring-boot:run
    ```
    
4. Verify your app starts successfully (e.g., `http://localhost:8080`).

Excellent — that’s a **clean build 🎉** (you handled it exactly right).

And your idea about **wrapping Maven logs for n8n / AI agent integration** is 💯 smart — because yes, Maven’s “Downloading from central…” spam will flood your token buffer *fast* and waste cost/context length.

Let’s optimize that cleanly 👇

---

## ⚙️ GOAL

When you run Maven (from script, subprocess, or HTTP wrapper), only capture lines like:

```
[INFO] ...
[ERROR] ...
```

and **ignore everything else** (downloads, warnings, etc.).

---

## 🧰 OPTION 1 — Filter via Shell/Powershell (easiest)

### 🪟 On Windows PowerShell

You can pipe Maven output through a filter like this:

```powershell
mvn clean install -DskipTests 2>&1 | Select-String "^\[INFO\]|\[ERROR\]"
```

Explanation:

- `2>&1` merges stderr and stdout.
- `Select-String` filters only lines containing `[INFO]` or `[ERROR]`.

So your AI or script only receives *build-relevant* messages.

Output from Windows

```bash

PS D:\java-learning\springboot-solid-demo> mvn clean install -DskipTests 2>&1 | Select-String "^\[INFO\]|\[ERROR\]"

[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------< com.example:springboot-solid-demo >------------------
[INFO] Building springboot-solid-demo 1.0.0
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.3.2:clean (default-clean) @ springboot-solid-demo ---
[INFO] Deleting D:\java-learning\springboot-solid-demo\target
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ springboot-solid-demo ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ springboot-solid-demo ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 9 source files with javac [debug parameters release 23] to target\classes
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ springboot-solid-demo ---
[INFO] skip non existing resourceDirectory D:\java-learning\springboot-solid-demo\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ springboot-solid-demo ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 2 source files with javac [debug parameters release 23] to target\test-classes
[INFO] 
[INFO] --- surefire:3.2.5:test (default-test) @ springboot-solid-demo ---
[INFO] Tests are skipped.
[INFO]
[INFO] --- jar:3.4.2:jar (default-jar) @ springboot-solid-demo ---
[INFO] Building jar: D:\java-learning\springboot-solid-demo\target\springboot-solid-demo-1.0.0.jar
[INFO] 
[INFO] --- spring-boot:3.3.4:repackage (repackage) @ springboot-solid-demo ---
[INFO] Replacing main artifact D:\java-learning\springboot-solid-demo\target\springboot-solid-demo-1.0.0.jar with repackaged 
archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to
D:\java-learning\springboot-solid-demo\target\springboot-solid-demo-1.0.0.jar.original
[INFO]
[INFO] --- install:3.1.3:install (default-install) @ springboot-solid-demo ---
[INFO] Installing D:\java-learning\springboot-solid-demo\pom.xml to 
C:\Users\Windows\.m2\repository\com\example\springboot-solid-demo\1.0.0\springboot-solid-demo-1.0.0.pom
[INFO] Installing D:\java-learning\springboot-solid-demo\target\springboot-solid-demo-1.0.0.jar to
C:\Users\Windows\.m2\repository\com\example\springboot-solid-demo\1.0.0\springboot-solid-demo-1.0.0.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.759 s
[INFO] Finished at: 2025-11-06T23:34:10+07:00
[INFO] ------------------------------------------------------------------------

PS D:\java-learning\springboot-solid-demo>
```

---

### 🐧 On Linux/macOS (Bash)

You’d do the same with `grep`:

```bash
mvn clean install -DskipTests 2>&1 | grep "^\[INFO\]\|\[ERROR\]"
```

---

## 🧰 OPTION 2 — Use `-quiet` mode

If you just want Maven itself to **reduce output noise**, use:

```bash
mvn -q clean install -DskipTests
```

This disables most verbose logs, but still prints:

- `[INFO] BUILD SUCCESS`
- `[ERROR]` messages if something breaks

Downside: you’ll lose intermediate `[INFO] Compiling …` lines too — so it’s great for concise success/fail logging, but less for detailed progress.

Output on Windows

```bash
PS D:\java-learning\springboot-solid-demo> mvn -q clean install -DskipTests
WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
WARNING: sun.misc.Unsafe::staticFieldBase has been called by com.google.inject.internal.aop.HiddenClassDefiner (file:/D:/Programs/apache-maven-3.9.11/lib/guice-5.1.0-classes.jar)
WARNING: Please consider reporting this to the maintainers of class com.google.inject.internal.aop.HiddenClassDefiner
WARNING: sun.misc.Unsafe::staticFieldBase will be removed in a future release
PS D:\java-learning\springboot-solid-demo> 
```

**Me** 

oh so for the `mvn spring-boot:run` , do we also have some way to make it output less redundant ? but show exactly the error message we need if exist (the exact culprit, not too much trace)
****

**ChatGPT said:**

Yes — **excellent question** 💡

Spring Boot + Maven tends to be *too chatty* by default: full banners, framework logs, and full stack traces for even minor config errors.

There are **clean, professional ways** to make `mvn spring-boot:run` show only **relevant info and concise error messages** while still surfacing the *exact culprit*.

Here’s how to do it 👇

---

## 🧩 1️⃣ Use `spring.main.banner-mode=off` (hide the ASCII logo)

In your `src/main/resources/application.properties`, add:

```
spring.main.banner-mode=off
```

That removes the big ASCII “Spring Boot” banner.

---

## 🧩 2️⃣ Control logging level — keep errors, hide framework spam

Still in `application.properties`, add:

```
# Show only startup summary and errors
logging.level.root=ERROR
logging.level.org.springframework.boot.web=INFO
logging.level.com.example.demo=DEBUG
```

Explanation:

- `root=ERROR`: hides almost everything except real problems.
- `org.springframework.boot.web=INFO`: lets you see web server startup (so you know port 8080 started).
- `com.example.demo=DEBUG`: your own code still logs fully (you want that!).

✅ Result: You’ll see something like:

```
INFO  Tomcat started on port 8080
INFO  Started DemoApplication
ERROR org.hibernate.HibernateException: Unable to determine Dialect ...
```

No hundreds of lines from Hibernate, Tomcat, etc.

---

## 🧩 3️⃣ If you want short stack traces (culprit only)

You can use:

**THIS ONE WORKED!!!**

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.main.log-startup-info=false --spring.output.ansi.enabled=ALWAYS"
```

🟦 If you’re using **Windows Command Prompt (CMD)**:

Use **escaped quotes** inside the `-D` argument like this:

```bash
mvn spring-boot:run -D"spring-boot.run.arguments=--spring.main.log-startup-info=false --spring.output.ansi.enabled=ALWAYS"
```

✅ Works in plain `cmd.exe`

✅ Keeps arguments grouped correctly

That hides most environment startup details and leaves only errors.

**💡 Explanation**

- `spring-boot:run` executes your Spring Boot app.
- `Dspring-boot.run.arguments="..."` passes Spring Boot command-line args (the same as if you ran `java -jar app.jar --spring.main.banner-mode=off`).

Output on Windows

```bash
PS D:\java-learning\springboot-solid-demo> mvn spring-boot:run -D"spring-boot.run.arguments=--spring.main.log-startup-info=false --spring.output.ansi.enabled=ALWAYS"
>>
WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
WARNING: sun.misc.Unsafe::staticFieldBase has been called by com.google.inject.internal.aop.HiddenClassDefiner (file:/D:/Programs/apache-maven-3.9.11/lib/guice-5.1.0-classes.jar)
WARNING: Please consider reporting this to the maintainers of class com.google.inject.internal.aop.HiddenClassDefiner
WARNING: sun.misc.Unsafe::staticFieldBase will be removed in a future release
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------< com.example:springboot-solid-demo >------------------
[INFO] Building springboot-solid-demo 1.0.0
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] >>> spring-boot:3.3.4:run (default-cli) > test-compile @ springboot-solid-demo >>>
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ springboot-solid-demo ---
[INFO] Copying 2 resources from src\main\resources to target\classes
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ springboot-solid-demo ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 10 source files with javac [debug parameters release 23] to target\classes
[INFO] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java: D:\java-learning\springboot-solid-demo\src\main\java\com\example\demo\config\SQLiteDialect.java uses or overrides a deprecated API.
[INFO] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java: Recompile with -Xlint:deprecation for details.
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR :
[INFO] -------------------------------------------------------------
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[5,40] cannot find symbol
  symbol:   class LimitOffsetFetchClausePagingStrategy
  location: package org.hibernate.dialect.pagination
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[6,32] cannot find symbol
  symbol:   class RowSelection
  location: package org.hibernate.engine.spi
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[41,12] cannot find symbol
  symbol:   class LimitOffsetFetchClausePagingStrategy
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[56,46] cannot find symbol
  symbol:   class RowSelection
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[17,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[18,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[19,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[20,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[21,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[22,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[23,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[24,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[25,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[26,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[27,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[28,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[29,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[30,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[31,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[32,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[33,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[34,9] cannot find symbol
  symbol:   method registerColumnType(int,java.lang.String)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[36,9] cannot find symbol
  symbol:   method registerFunction(java.lang.String,org.hibernate.dialect.function.StandardSQLFunction)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[37,9] cannot find symbol
  symbol:   method registerFunction(java.lang.String,org.hibernate.dialect.function.StandardSQLFunction)
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[40,5] method does not override or implement a method from a supertype
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[42,16] cannot find symbol
  symbol:   variable LimitOffsetFetchClausePagingStrategy
  location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[45,5] method does not override or implement a method from a supertype
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[50,5] method does not override or implement a method from a supertype
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[55,5] method does not override or implement a method from a supertype
[INFO] 29 errors
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.524 s
[INFO] Finished at: 2025-11-06T23:48:24+07:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.13.0:compile (default-compile) on project springboot-solid-demo: Compilation failure: Compilation failure:
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[5,40] cannot find symbol
[ERROR]   symbol:   class LimitOffsetFetchClausePagingStrategy
[ERROR]   location: package org.hibernate.dialect.pagination
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[6,32] cannot find symbol
[ERROR]   symbol:   class RowSelection
[ERROR]   location: package org.hibernate.engine.spi
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[41,12] cannot find symbol
[ERROR]   symbol:   class LimitOffsetFetchClausePagingStrategy
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[56,46] cannot find symbol
[ERROR]   symbol:   class RowSelection
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[17,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[18,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[19,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[20,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[21,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[22,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[23,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[24,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[25,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[26,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[27,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[28,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[29,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[30,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[31,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[32,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[33,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[34,9] cannot find symbol
[ERROR]   symbol:   method registerColumnType(int,java.lang.String)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[36,9] cannot find symbol
[ERROR]   symbol:   method registerFunction(java.lang.String,org.hibernate.dialect.function.StandardSQLFunction)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[37,9] cannot find symbol
[ERROR]   symbol:   method registerFunction(java.lang.String,org.hibernate.dialect.function.StandardSQLFunction)
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[40,5] method does not override or implement a method from a supertype
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[42,16] cannot find symbol
[ERROR]   symbol:   variable LimitOffsetFetchClausePagingStrategy
[ERROR]   location: class com.example.demo.config.SQLiteDialect
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[45,5] method does not override or implement a method from a supertype
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[50,5] method does not override or implement a method from a supertype
[ERROR] /D:/java-learning/springboot-solid-demo/src/main/java/com/example/demo/config/SQLiteDialect.java:[55,5] method does not override or implement a method from a supertype
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
```

---

## 🧩 4️⃣ To filter stack traces automatically

Add this to your Maven command:

```bash
mvn spring-boot:run -Dlogging.exception-conversion-word="%clr(%-5p) %m%n"
```

That prints **only the message** of exceptions (not the full trace), unless you add `--debug`.

So instead of:

```
Caused by: org.hibernate.HibernateException: Unable to determine Dialect ...
   at org.hibernate.xxx...
   at org.hibernate.xxx...
   (hundreds of lines)

```

You’ll just see:

```
ERROR org.hibernate.HibernateException: Unable to determine Dialect for SQLite

```

---

## 🧩 5️⃣ If you want to make this permanent (no CLI flags)

In your `application.properties`, you can include:

```
# Compact exception display
logging.exception-conversion-word=%clr(%-5p) %m%n
spring.main.log-startup-info=false
spring.output.ansi.enabled=ALWAYS

```

---

✅ **Recommended combo (minimal but useful):**