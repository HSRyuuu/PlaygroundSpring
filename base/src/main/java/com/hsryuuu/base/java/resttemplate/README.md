# RestApi Examples

## RestApiService
- 가장 중심이 되는 클래스
- `GET` / `POST` / `PUT` / `DELETE` 를 지원한다.
- `RestTemplate`을 사용한다. Spring Boot 프로젝트 일 경우 따로 Bean으로 등록하여 사용하는걸 권장.

## request
### ExtraHeaders
- `Map<String, Spring>`을 감싸고 있는 클래스이다.
- Http Header에 들어갈 값들을 세팅할 수 있다.

### QueryParams
- `Map<String, Spring>`을 감싸고 있는 클래스이다.
- Query Parameters로 들어갈 값들을 세팅할 수 있다.

#### (validation)
- 위의 두개 클래스 모두 `add()` 과정에서 유효성 검증을 진행하여, RestApiService에서 유효성 검증 로직을 제거했다.

## response
### ApiResponse
- `Http Status`, `Error Message`, `Response Body`를 이 객체에 세팅하여 반환한다.


## util
### RestApiUtils
- HttpHeaders 객체 생성, QueryParams를 queryString으로  변환하는 등의 Util성 로직 포함

## test/RestApiServiceTest
- 각 get, post, put, delete 에 대한 예제 코드가 작성되어있다.