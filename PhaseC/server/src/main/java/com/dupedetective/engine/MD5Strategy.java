package com.dupedetective.engine;

import com.dupedetective.data.ReportLine;
import com.dupedetective.engine.md5.MD5Generator;

import java.util.ArrayList;
import java.util.List;

/**
 * AST Strategy for MD5
 */
public class MD5Strategy implements PDStrategy {

	/**
	 * Default constructor
	 */
	public MD5Strategy() {}

	/**
	 * Method to invoke the plagiarism detection inside the given assignment
	 */
	@Override
	public List<ReportLine> checkPlagiarism(String code1, String code2) {
		List<ReportLine> l = new ArrayList<>();
		ReportLine ri = new ReportLine();
		ri.setModel(Model.MD5.getValue());
		ri.setScore(checkMD5(code1, code2)? 1f:0f);
		l.add(ri);
		return l;
	}

	/**
	 * Checks the MD5 checksums of two files
	 * @param testCode1 First code to check
	 * @param testCode2 Second code to check
	 * @return if the MD5 checksum is same or not
	 */
	public boolean checkMD5(String testCode1, String testCode2) {
		return MD5Generator.getMD5String(testCode1).equals(MD5Generator.getMD5String(testCode2));
	}
}
