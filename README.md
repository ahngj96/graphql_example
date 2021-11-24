# graphql_example
## 각 프로젝트 실행법
database 연결 없는 아주 간단한 GraphQL 예제로 java만 설치되어 있으면 돌아갈 것

## demo

```
chmod +x gradlew
./gradlew bootRun
```

## demo2

```
chmod +x mvnw
./mvnw spring-boot:run
```
```
demo2 프로젝트 GraphQL Mutation 쿼리 예시
mutatiion {
savePost(post:{
id: 123
title: "test"
relatePosts:[{
  id:123123
  title: "test_depth_1"
  relatePosts:[{
    id: 123123123
    title: "test_depth_2"
  }]
},{
  id:123124
  title: "test_depth_2"
}]}){
    id
    title
    relatePosts{
      id
      title
      relatePosts{
        id
        title
      }
    }
  }
}
}
```
