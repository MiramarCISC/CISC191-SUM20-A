package com.groupA;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * @author Mark Lucernas
 * Date: 2020-07-12
 */
@RunWith(JUnitPlatform.class)
@SelectPackages("com.groupA")
class GameTestSuite {}
