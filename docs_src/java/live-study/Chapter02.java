class Chapter02 {
    public static void main(String[] args){
        // 변수 선언과 초기화
        int a;
        a = 3;
        int b = 4;
        System.out.println(c); // > cannot find symbol error


        // 유니코드
        char ch = 65;
        System.out.println(ch); // > A


        // 오버플로우
        byte byteMax = 127;
        byte byteMin = -128;
        System.out.println(byteMax + 1); // > 128
        System.out.println(byteMin - 1); // > -129
        System.out.println((byte)(byteMax + 1)); // > -128
        System.out.println((byte)(byteMin - 1)); // > 127


        // 부동 소수점 방식의 오류
        double doubleNum = 1;
        for (int i = 0; i < 10000; i++) {
            doubleNum += 0.1;
        }
        System.out.println(doubleNum); // > 1001.000000000159

        
        // 변수의 기본값
        Test test = new Test();
        int testNum2;
        System.out.println(testNum2); // > variable testNum2 might not have been initialized
        System.out.println(test.testNum); // > 0


        // 리터럴의 타입과 접미사
        float floatNum = 3.14; // > incompatible types: possible lossy conversion from double to float
        float floatNum = 3.14F;
        System.out.println(floatNum); // > 3.14


        // 타입 추론
        var i = 10;
        int j = 10;
        char k = 10;
        i = 'A';
        j = 'A';
        k = 'A';
        System.out.println(i); // > 65
        System.out.println(j); // > 65
        System.out.println(k); // > A
    }
}
