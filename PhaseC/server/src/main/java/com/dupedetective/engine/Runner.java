package com.dupedetective.engine;

import com.dupedetective.data.ModelReport;
import com.dupedetective.data.MongoOperation;
import com.dupedetective.data.Report;
import com.dupedetective.data.Submission;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main driver for the PDetection
 */
public class Runner {

  /*
  Logger for error or info messages
   */
  final static Logger logger = Logger.getLogger(Runner.class);
  private static Map<Integer, Float> weights;

  /**
   * Read the weights from file into a hashmap
   */
  private static void readWeights() {
    weights = new HashMap<>();
    weights.put(Model.ASTLoop.getValue(), 0.2f);
    weights.put(Model.ASTMethod.getValue(), 0.2f);
    weights.put(Model.ASTStructure.getValue(), 0.3f);
    weights.put(Model.WINNOWING.getValue(), 0.3f);
  }

  /**
   * Start analysis of documents
   *
   * @param assignmentId assignment id to analyze
   */
  public static void analyze(String assignmentId) {
    if (assignmentId == null) {
      return;
    }
    readWeights();
    MongoOperation mongo = new MongoOperation();
    List<Submission> submissions = mongo.getSubmissions(assignmentId);

    // Iterate through all the files and compare each one side by side for the given assignment
    for (int i = 0; i < submissions.size(); i++) {
      for (int j = i + 1; j < submissions.size(); j++) {
        logger.info("Comparing submission " + i + " with " + j);
        String code1 = submissions.get(i).getFilecontent();
        String code2 = submissions.get(j).getFilecontent();

        Report report1 = new Report(submissions.get(i).getId(), submissions.get(i).getId(),
            submissions.get(j).getId());
        Report report2 = new Report(submissions.get(j).getId(), submissions.get(j).getId(),
            submissions.get(i).getId());

        // Get results of MD5 comparison
        PDContext md5 = new PDContext(new MD5Strategy());
        Boolean md5Result = md5.executeStrategy(code1, code2).get(0).getScore() == 1;

        // Update the report objects with the results from models
        updateReport(report1, md5Result, code1, code2);
        updateReport(report2, md5Result, code1, code2);

        logger.info("Saving to mongo.");
        mongo.saveReport(report1);
        mongo.saveReport(report2);
      }
    }
    mongo.updateAnalyzedAssignment(assignmentId);
  }

  /**
   * Get the Report for the Structure
   *
   * @param code1 first code to compare
   * @param code2 second code to compare
   * @param model model Id
   */
  private static ModelReport getModelReport(String code1, String code2, Integer model) {
    ModelReport mr = new ModelReport(model);
    PDContext context = getContext(model);
    mr.setLines(context.executeStrategy(code1, code2));
    mr.computeScore();
    logger.info(model + " score: " + mr.getScore());
    return mr;
  }

  /**
   * Update the report object with results for various models
   *
   * @param report the report object
   * @param md5Result result of md5 comparison
   * @param code1 first code to compare
   * @param code2 second code to compare
   */
  private static void updateReport(Report report, boolean md5Result, String code1, String code2) {
    report.setMd5Result(md5Result);
    report.addModel(getModelReport(code1, code2, Model.ASTStructure.getValue()));
    report.addModel(getModelReport(code1, code2, Model.ASTMethod.getValue()));
    report.addModel(getModelReport(code1, code2, Model.ASTLoop.getValue()));
    report.addModel(getModelReport(code1, code2, Model.WINNOWING.getValue()));
    report.computeScore(weights);
  }

  /**
   * @param model the model id
   * @return a PD strategy for this model
   */
  public static PDContext getContext(Integer model) {
    if (model == Model.ASTStructure.getValue()) {
      return new PDContext(new ASTStructureStrategy());
    } else if (model == Model.ASTMethod.getValue()) {
      return new PDContext(new ASTMethodStrategy());
    } else if (model == Model.ASTLoop.getValue()) {
      return new PDContext(new ASTLoopStrategy());
    } else if (model == Model.WINNOWING.getValue()) {
      return new PDContext(new WinnowStrategy());
    } else {
      return new PDContext(new MD5Strategy());
    }
  }

  /**
   * Main method to test run
   *
   * @param args args for main
   */
  public static void main(String[] args) {
    analyze("5a24f2b6c6bb70590f6cb3ac");
  }

}
