package com.jiongsoft;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	com.jiongsoft.cocit.TestAll.class,
	com.jiongsoft.ynby.TestAll.class,
})
public class TestAll {
}
