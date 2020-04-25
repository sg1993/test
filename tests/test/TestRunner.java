package test;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;

import config.Config;

public class TestRunner {

    static Scanner scanner = null;
    static String uiServerURL = null, catalogServerURL = null;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        if (args.length < 2) {
            System.out.println("Need at least CatalogServer And UIServer URLs to be specified");
            System.exit(1);
        }

        catalogServerURL = args[0] + ":" + Config.CATALOG_SERVER_PORT;
        uiServerURL = args[1] + ":" + Config.UI_SERVER_PORT;

        if (!uiServerURL.startsWith("http://")) {
            uiServerURL = "http://" + uiServerURL;
        }

        if (!catalogServerURL.startsWith("http://")) {
            catalogServerURL = "http://" + catalogServerURL;
        }

        if (args.length == 2) {
            System.out.println("Entering interactive mode");
            interactiveMode();
        } else if (args.length == 3) {
            try {
                invokeTest(Integer.parseInt(args[2]));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static void invokeTest(int test) throws JSONException, IOException {
        switch (test) {
        case 1:
            showBanner(test + "");
            SanityTests.testSearchAndLookup(catalogServerURL, uiServerURL);
            showSuccessBanner(test + "");
            break;
        case 2:
            showBanner(test + "");
            SanityTests.testMultiBuyAndUpdate(catalogServerURL, uiServerURL);
            showSuccessBanner(test + "");
            break;

        case 3:
            showBanner(test + "");
            ConsecutiveTests.testConsecutiveBuy(catalogServerURL, uiServerURL,
                    false);
            showSuccessBanner(test + "");
            break;
        case 4:
            showBanner(test + "");
            ConsecutiveTests.testConsecutiveBuy(catalogServerURL, uiServerURL,
                    true);
            showSuccessBanner(test + "");
            break;
        case 5:
            showBanner(test + "");
            ConcurrentTests.testConcurrentBuy(catalogServerURL, uiServerURL);
            showSuccessBanner(test + "");
            break;
        case 6:
            showBanner("");
            FaultToleranceTests.testSearchAndLookup(catalogServerURL, uiServerURL, false);
            showSuccessBanner("");
            break;
        case 7:
            showBanner("");
            FaultToleranceTests.testMultiBuy(catalogServerURL, uiServerURL, false);
            showSuccessBanner("");
            break;
        case 8:
            showBanner("");
            FaultToleranceTests.testSearchAndLookup(catalogServerURL, uiServerURL, true);
            showSuccessBanner("");
            break;
        case 9:
            showBanner("");
            FaultToleranceTests.testMultiBuy(catalogServerURL, uiServerURL, true);
            showSuccessBanner("");
            break;
        }
    }

    private static void showBanner(String test) {
        System.out.println(
                "\n\n===============================================================================");
        System.out.println("Starting test #" + test);
        System.out.println(
                "===============================================================================\n");
    }

    public static void showSuccessBanner(String test) {
        System.out.println("\nPassed test #" + test);
        System.out.println(
                "===============================================================================\n");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void interactiveMode() {

    }
}
