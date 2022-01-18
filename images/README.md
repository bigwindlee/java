### Spring Boot Consuming and Producing JSON
**要点**
 - 本例演示Spring Boot的请求和响应中都使用json的情况。
 - 使用@RequestBody Map<String, Object> payload作为参数获取request中的json payload。
 - 用作返回json的对象必须实现了至少一个Getter，返回那些实现了Getter的属性组成的json字符串。否则会抛出异常：  
   [org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation]
 - 使用@Data注解添加Getter