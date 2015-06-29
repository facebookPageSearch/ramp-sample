package ramp.sample.emoji;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:spring-beans.xml" })
@TransactionConfiguration(defaultRollback = false)
public class UserDAOTest extends AbstractTransactionalTestNGSpringContextTests {

	private static final Charset UTF_8 = Charset.forName("UTF-8");

	// These tests are not mocked, as we want to test it end to end, saving to
	// DB and then reading it from it back
	@Autowired
	private UsersDAO usersDAO;

	@DataProvider
	public Object[][] dpSave() {
		return new Object[][] { {
				"emoji-as-text" + System.currentTimeMillis(),
						 "😁" }, };
	}

	@Test(dataProvider = "dpSave")
	@Transactional
	public void testSave(String emailId, String avatar) {
		usersDAO.save(emailId, convertToUTF8(avatar));
	}

	@DataProvider
	public Object[][] dpSaveHex() throws InterruptedException {
		Object[][] ret = new Object[80][2];
		for (int i = 0; i < 80; i++) {
			// 0x1F601 = 128512 in decimal
			int emoji = 128512 + i;
			ret[i][0] = Long.toString(128512 + i);
			ret[i][1] = emoji;
		}
		return ret;
	}

	@Test(dataProvider = "dpSaveHex")
	@Transactional
	public void testSaveHex(String emailId, int avatar) {
		usersDAO.save(emailId, convertToUTF8(avatar));
	}

	@Test(dependsOnMethods= {"testSaveHex", "testSave"})
	@Transactional
	public void testFindAll() {
		System.out.println(usersDAO.find());
		usersDAO.truncate();
	}

	private String convertToUTF8(String str) {
		byte[] byteArray = str.getBytes(UTF_8);
		return new String(byteArray, UTF_8);
	}

	private String convertToUTF8(int hexString) {
		char[] emojiCharArray = Character.toChars(hexString);
		return new String(emojiCharArray);
	}

}
