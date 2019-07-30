package com.zheng.common.util;

import static org.mockito.Matchers.anyDouble;
import com.diffblue.deeptestutils.Reflector;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.lang.reflect.InvocationTargetException;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
public class PaginatorTest {

	/* testedClasses: Paginator */
	@Test
	public void initTotalPageOutputVoid() {
		final Paginator paginator = new Paginator();
		paginator.initTotalPage();
		Assert.assertEquals(0L, paginator.getTotalPage());
	}

	@PrepareForTest({Paginator.class, Math.class})
	@Test
	public void getHtmlOutputNotNull() throws Exception,
			InvocationTargetException {
		PowerMockito.mockStatic(Math.class);
		final Paginator paginator = new Paginator();
		Reflector.setField(paginator, "total", 0L);
		paginator.setStep(0);
		paginator.setUrl(null);
		paginator.setQuery(null);
		paginator.setParam(null);
		paginator.setPage(0);
		paginator.setTotalPage(0L);
		Reflector.setField(paginator, "rows", 2);
		PowerMockito.when(Math.ceil(anyDouble()))
				.thenReturn(-0x1.ffffffffffffep+31 /* -4.29497e+09 */);
		Assert.assertEquals("", paginator.getHtml());
	}

	@Test
	public void getHtmlOutputNotNull2() throws Exception,
			InvocationTargetException {
		PowerMockito.mockStatic(Math.class);
		final Paginator paginator = new Paginator();
		Reflector.setField(paginator, "total", 0L);
		paginator.setStep(0);
		paginator.setUrl(null);
		paginator.setQuery("foo");
		paginator.setParam("foo");
		paginator.setPage(0);
		paginator.setTotalPage(0L);
		Reflector.setField(paginator, "rows", -786_432);
		PowerMockito.when(Math.ceil(anyDouble()))
				.thenReturn(-0x1.ffffffffffffep+31 /* -4.29497e+09 */);
		Assert.assertEquals("", paginator.getHtml());
	}
}
