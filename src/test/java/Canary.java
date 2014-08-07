import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class Canary {

       @Test
       public void canary() {
               assertThat(true, is(equalTo(true)));
       }

}
