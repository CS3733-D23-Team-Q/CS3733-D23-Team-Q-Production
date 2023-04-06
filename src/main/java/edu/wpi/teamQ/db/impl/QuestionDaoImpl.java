package edu.wpi.teamQ.db.impl;

import edu.wpi.teamQ.db.dao.GenDao;
import edu.wpi.teamQ.db.obj.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements GenDao<Question, Integer> {
  private List<Question> questions = new ArrayList<Question>();
  private static QuestionDaoImpl single_instance = null;

  private QuestionDaoImpl() {
    populate();
  }

  public static synchronized QuestionDaoImpl getInstance() {
    if (single_instance == null) single_instance = new QuestionDaoImpl();

    return single_instance;
  }

  public Question retrieveRow(Integer id) {
    int index = this.getIndex(id);
    return questions.get(index);
  }

  public Question retrieveRow(String question) {
    int index = this.getIndex(question);
    return questions.get(index);
  }

  public boolean updateRow(Integer id, Question questionWithNewChanges) {
    boolean result = false;
    Connection con = connect();
    String question = questionWithNewChanges.getQuestion();
    try {
      String query = "UPDATE security_question SET question = ? WHERE id = ?";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setString(1, question);
      pst.setInt(2, id);
      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        int index = this.getIndex(id);
        questions.get(index).setQuestion(question);
        System.out.println("Question updated successful!");
      } else {
        System.out.println("Failed to update the question.");
      }
      con.close();
      pst.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public boolean deleteRow(Integer id) {
    boolean result = false;
    Connection con = connect();
    try {
      String query = "DELETE FROM security_question WHERE id = ?";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setInt(1, id);
      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        int index = this.getIndex(id);
        questions.remove(index);
        System.out.println("Question deleted successful!");
      } else {
        System.out.println("Failed to delete the question.");
      }
      con.close();
      pst.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  @Override
  public List<Question> getAllRows() {
    return questions;
  }

  public boolean addRow(Question q) {
    String question = q.getQuestion();
    boolean result = false;
    Connection con = connect();
    try {
      String query = "INSERT INTO security_question (question) VALUES(?)";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setString(1, question);
      int rs = pst.executeUpdate();
      if (rs == 1) {
        result = true;
        questions.add(q);
        System.out.println("Question added successful!");
      } else {
        System.out.println("Failed to add the question.");
      }
      con.close();
      pst.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public boolean populate() {
    Connection con = connect();
    try {
      String query = "SELECT * FROM security_question";
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      while (rs.next()) {
        Question q;
        q = new Question(rs.getInt("id"), rs.getString("question"));
        questions.add(q);
      }
      con.close();
      st.close();
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

  public int getIndex(int id) {
    for (int i = 0; i < questions.size(); i++) {
      Question q = questions.get(i);
      if (q.getId() == id) {
        return i;
      }
    }
    return -1;
  }

  public int getIndex(String question) {
    for (int i = 0; i < questions.size(); i++) {
      Question q = questions.get(i);
      if (q.getQuestion() == question) {
        return i;
      }
    }
    return -1;
  }
}
