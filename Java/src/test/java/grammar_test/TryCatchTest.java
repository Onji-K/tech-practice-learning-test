package grammar_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("try-catch 관련 테스트")
public class TryCatchTest {

    int globalInt;

    /**
     * try 문 안에서 리턴을 하면 finally 가 실행된다.
     * 하지만 리턴 값은 finally 의 작업에 영향을 받지 않는다.
     * @see <a href="https://stackoverflow.com/questions/65035/does-a-return-statement-in-a-try-block-finally-execute">Does a finally block always run?</a>
     */
    @Test
    @DisplayName("try문 안에서 리턴을 하면 finally가 실행된다.")
    void tryCatchTest(){
        globalInt = 0;
        int returnValue = increaseWithFinally();

        assertSame(returnValue, 1);
        assertSame(globalInt, 2);
    }

    private int increaseWithFinally(){
        try {
            globalInt++;
            return globalInt;
        } finally {
            globalInt++;
        }
    }

}
