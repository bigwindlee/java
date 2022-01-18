package com.taikang.images;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/images_split")
public class HelloController {
    @RequestMapping(method = RequestMethod.POST)
    public ReturnValue hello(@RequestBody Map<String, Object> payload) {
        System.out.println("Length: " + payload.size());
        for (Map.Entry<String, Object> entry : payload.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        ReturnValue retValue = new ReturnValue(0, "ok");

        /* 用作返回json的对象必须实现了至少一个Getter，返回那些实现了Getter的属性组成的json字符串。
         * 否则会抛出异常：
         * [org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation]
         */
        return retValue;
    }
}
