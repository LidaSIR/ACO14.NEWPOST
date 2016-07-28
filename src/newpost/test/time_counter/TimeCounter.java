package newpost.test.time_counter;

import java.io.IOException;

/**
 * Created by gorobec on 25.06.16.
 */
public class TimeCounter {

    public long count(Action action) {
        long start = System.currentTimeMillis();
        try {
            action.run();
        } catch (IOException e) {
//            NOP
        }
        long finish = System.currentTimeMillis();

        return finish - start;
    }


}
