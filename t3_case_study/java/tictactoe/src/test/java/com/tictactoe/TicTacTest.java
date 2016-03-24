import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TicTacTest {
    public static void main(String[] args) {
        System.out.print("Testing:");
        Result result = JUnitCore.runClasses(TicTacTestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
