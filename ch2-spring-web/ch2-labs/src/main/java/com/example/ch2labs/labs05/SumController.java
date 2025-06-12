package com.example.ch2labs.labs05;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumController {
    @GetMapping("/sum-digits")
    public ResponseEntity<String> sumDigits(String number) {
        if (number == null || number.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("number 파라미터는 필수입니다.");
        } else if (number.charAt(0) == '-') {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("음수는 지원하지 않습니다. 양의 정수를 입력해주세요.");
        } else if (number.contains(".")) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("정수만 입력 가능합니다. 예: /sum-digits?number=1234");
        }

        int num = Integer.parseInt(number);
        int result = 0;
        while (num > 0) {
            result += num % 10;
            num /= 10;
        }

        return ResponseEntity.status(HttpStatus.OK).body("각 자리수 합: " + result);
    }
}
