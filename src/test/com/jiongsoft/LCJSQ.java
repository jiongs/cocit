package com.jiongsoft;

import org.junit.Test;

public class LCJSQ {
	/**
	 * 租房投资理财
	 * 
	 * @return
	 */
	public double calFZLC() {
		return 0.0;
	}

	@Test
	public void testHarfYearFee() {

		// 两个人每年的住房公积金
		// double yearGJJ = 520 * 12 + 2500 * 12;
		double[] yearGJJs = new double[] { 520 * 12, 520 * 12 + 2500 * 12 };
		double[] rateLCs = new double[] { 0.040, 0.045, 0.048, 0.050 };// 理财年收益率5%
		double[] rateFZZFs = new double[] { 0.020, 0.025, 0.030 };// 房租半年涨幅2%

		System.out.println("理财年收益率，房租半年涨幅，每年可提取公积金(元), 25年资产总额(元)");

		for (double yearGJJ : yearGJJs) {
			for (double rateLC : rateLCs) {
				for (double rateFZZF : rateFZZFs) {

					double total = 0;// 房租费+理财收益合计
					for (int i = 1; i <= 50; i++) {

						// 半年房租费
						int fee = calHarfYearFee(15000, i, rateFZZF);
						// System.out.println("半年租金" + i + "：" + fee + ", 月租金：" + fee / 6);

						total += fee;

						// 半年理财本金收益和
						total = total + total * rateLC / 2;

						// 每年取出住房公积金
						if (i % 2 == 0) {
							total += yearGJJ;
							// /System.out.println("理财年收益率：" + rateLC + "，房租半年涨幅：" + rateFZZF + "，每年可提取公积金：" + yearGJJ + "元, 第 " + i / 2 + " 年度累计资产：" + new Double(total).intValue() + "元");
						}
					}

					if (yearGJJ < 30000) {
						total += 750000;
					}

					total += 1000000;

					System.out.println("\t\t" + rateLC * 100 + "%\t\t\t" + rateFZZF * 100 + "%\t\t\t\t\t" + yearGJJ + "\t\t\t\t\t" + new Double(total).intValue());
				}
			}
		}
	}

	/**
	 * 计算第noYear年的租金
	 * 
	 * @param harfYearBase
	 *            半年租金基数
	 * @param noYear
	 *            第几年
	 * @param per
	 * @return
	 */
	int calHarfYearFee(int harfYearFee, int harfYearNO, double per) {
		if (harfYearNO == 1) {
			return harfYearFee;
		}

		return new Double(calHarfYearFee(harfYearFee, harfYearNO - 1, per) * (1 + per)).intValue();
	}
}
