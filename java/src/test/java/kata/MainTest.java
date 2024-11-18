package kata;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {
  @Test
  void somethingRandom() throws Exception {
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print some output: goes to your special stream
    Main.main(new String[]{});
    // Put things back
    System.out.flush();
    System.setOut(old);
    // Show what happened
    Approvals.verify(baos.toString());
  }

  @Test
  void testPrintHeaderApproval() throws Exception {
      // Create a stream to hold the output
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream ps = new PrintStream(baos);
      // IMPORTANT: Save the old System.out!
      PrintStream old = System.out;
      // Tell Java to use your special stream
      System.setOut(ps);
      // Print some output: goes to your special stream
      CommandLinePrinter.printHeader();
      // Put things back
      System.out.flush();
      System.setOut(old);
      // Show what happened
      Approvals.verify(baos.toString());
  }
}
