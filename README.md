# 캐시 예제

캐시를 구현하여 걸린 시간을 비교한다.

여기서는 스프링 에서의 캐시를 구현했지만

정리는 전체적인 웹 캐시에 대해 정리하였다.

[바로가기](https://lsj8367.github.io/web/Web-cache/)

## 의존성

```gradle
dependencies {
    implementation (
            'org.springframework.boot:spring-boot-starter-web',
            'org.springframework.boot:spring-boot-starter-cache',
            'org.projectlombok:lombok'
    )
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

`starter-cache` 의존성 추가

그다음에 전역으로 캐시를 적용해주려면

```java
@SpringBootApplication
@EnableCaching
public class CacheApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

}
```

애플리케이션에 위와 같이 설정을 해주면 되지만, 이는 전체를 실행시킬 때,

의존성이 필요없는 객체들도 캐시 기능을 로드하게 되어버린다.

> User.java

```java
@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
}
```
일단 이런 유저가 있다고 예로 가정을 하고 여기서 생성자로 인스턴스를 만드는 것이

db에서 조회한 값이라고 가정을 한다.

> ExService.java

```java
@Service
public class ExService {

    @Cacheable("user")
    public User getuser(final Long id) {
        try {
            System.out.println("user를 db에서 찾는다");
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new User(id, "홍길동");
    }
}

```

이 서비스에서 찾는다는 것을 output 으로 콘솔을 찍고 5초를 딜레이 시키고 `User`를 인스턴스화 하는데

`@Cacheable("user")` 유저라는 키로 캐시를 생성하는 것이다.

> ExController.java

```java
@Slf4j
@Component
@RequiredArgsConstructor
public class ExController {

    private final ExService exService;

    public void run() {
        log.info("User1" + exService.getuser(1L));
        log.info("User1" + exService.getuser(1L));
        log.info("User1" + exService.getuser(1L));
        log.info("User2" + exService.getuser(2L));
        log.info("User2" + exService.getuser(2L));
        log.info("User2" + exService.getuser(2L));
    }
}
```

![스크린샷 2021-12-10 오후 4 02 59](https://user-images.githubusercontent.com/74235102/145531563-dd02a2ce-3083-43ea-ae3c-8353732a0909.png)

결과를 보면 첫줄부터 db를 호출한다음 시간을 보면 5초 뒤에 호출하고 그 다음 유저를 생성하는것 부터는

캐시가 적용되어서 잡아둔 5초를 지나지 않고 바로 캐시 데이터를 가져오게 된다.