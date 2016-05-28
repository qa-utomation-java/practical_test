= Practical test

1. Run practical service
2. Run tests ./gradlew clean test
3. You can find test report in folder ${project.root}/build/allure-report (better to open in firefox locally

Optionally:
If practical application endpoint is not to at http://localhost:8080/service, you can change it by adding -DbaseUrl=

./gradlew clean test -DbaseUrl=http://host:port/service 
