package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private final ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void field() {
        /*
        ThreadLocalService에서 ThreadLocal에 저장했기 때문에, 동시성 문제가 발생하지 않는다.
         */
        log.info("main start");
        Runnable userA = () -> threadLocalService.logic("userA");
        Runnable userB = () -> threadLocalService.logic("userB");


        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(1000);
        threadB.start();


        sleep(2000); // Main Thread 대기
        log.info("main exit");
    }

    private void sleep(int i) {

        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
