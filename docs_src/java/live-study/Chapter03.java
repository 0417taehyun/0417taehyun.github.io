class Chapter03 {
    public static void main(String[] args) {
        // 증감 연산자
        int x = 5;
        int y = 5;
        int tmp1 = 0;
        int tmp2 = 0;

        tmp1 = x++;
        tmp2 = ++y;

        System.out.println(tmp1); // > 5
        System.out.println(tmp2); // > 6

        
        // 자동 형변환
        int a = 10;
        int b = 4;
        System.out.println(a / b); // > 2


        // 산술 연산자 오버 플로우
        int firstNum = 1_000_000;
        int secondNum = 2_000_000;        

        long res = firstNum * secondNum;
        System.out.println(res); // > -1454759936


        // 리터럴 연산
        char c1 = 'a';
        char c2 = c1 + 1; // > Type mismatch: cannot convert from int to char
        char c3 = 'a' + 1;


        // 나머지 연산자의 부호
        System.out.println(3 % 2); // > 1
        System.out.println(3 % -2); // > 1
        System.out.println(-3 % 2); // > -1
        System.out.println(-3 % -2); // > -1


        // 대입 연산자 lvalue
        final int MAX = 10;
        MAX = 10; // > Cannot assign a value to final variable 'MAX'        


        // 조건 연산자
        int result = 5 > 10 ? 1 : 0;
        System.out.println(result); // > 0
    }

    public boolean isOddFirst(int num) {
        return num % 2 == -1;
    }


    public boolean isOddSecond(int num) {
        return num % 2 != 0;
    }


    public boolean isOddThird(int num) {
        return (num & 1) != 0;
    }
}
