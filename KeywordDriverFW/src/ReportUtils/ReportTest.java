package ReportUtils;

import TestUtils.Utils;




public class ReportTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String startTime = Utils.now("dd.MMMM.yyyy hh.mm.ss aaa");
		ReportUtil.startTesting("D://reports//index1.html", startTime, "Test", "1.5");

		ReportUtil.startSuite("Suite1");

		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);

		ReportUtil.addTestCase("TopNavigation", startTime, Utils.now("dd.MMMM.yyyy hh.mm.ss aaa"), "Pass");

		ReportUtil.endSuite();
		ReportUtil.updateEndTime(Utils.now("dd.MMMM.yyyy hh.mm.ss aaa"));

	}
}
