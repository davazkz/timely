package timely.test.integration;

import org.junit.*;
import org.junit.experimental.categories.Category;

import timely.Server;
import timely.test.IntegrationTest;

@Category(IntegrationTest.class)
public class StaticFileServerIT extends OneWaySSLBase {

    private static Server s = null;

    @Before
    public void before() throws Exception {
        s = new Server(conf);
        s.run();
    }

    @After
    public void after() throws Exception {
        s.shutdown();
    }

    @Test(expected = NotSuccessfulException.class)
    public void testGetFavIconRequest() throws Exception {
        query("https://127.0.0.1:54322/favicon.ico", 404, "application/json");
    }

    @Test(expected = NotSuccessfulException.class)
    public void testGetBadPath() throws Exception {
        query("https://127.0.0.1:54322/index.html", 403, "application/json");
    }

    @Test(expected = NotSuccessfulException.class)
    public void testGetGoodPath() throws Exception {
        query("https://127.0.0.1:54322/webapp/test.html", 404, "application/json");
    }

}
