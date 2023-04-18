package edu.wpi.cs3733.D23.teamQ.db;

import edu.wpi.cs3733.D23.teamQ.db.impl.*;
import edu.wpi.cs3733.D23.teamQ.db.obj.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

public class Qdb {

  public LocationDaoImpl locationTable = LocationDaoImpl.getInstance();

  public NodeDaoImpl nodeTable = NodeDaoImpl.getInstance(locationTable);
  public EdgeDaoImpl edgeTable = EdgeDaoImpl.getInstance(nodeTable);
  public MoveDaoImpl moveTable = MoveDaoImpl.getInstance(nodeTable);

  private QuestionDaoImpl questionTable = QuestionDaoImpl.getInstance();
  private AccountDaoImpl accountTable = AccountDaoImpl.getInstance();
  private ConferenceRequestDaoImpl conferenceRequestTable =
      ConferenceRequestDaoImpl.getInstance(accountTable, nodeTable);
  private FlowerRequestDaoImpl flowerRequestTable =
      FlowerRequestDaoImpl.getInstance(accountTable, nodeTable);
  private MealRequestDaoImpl mealRequestTable =
      MealRequestDaoImpl.getInstance(accountTable, nodeTable);
  private FurnitureRequestDaoImpl furnitureRequestTable =
      FurnitureRequestDaoImpl.getInstance(accountTable, nodeTable);
  private PatientTransportRequestDaoImpl patientTransportRequestTable =
      PatientTransportRequestDaoImpl.getInstance(accountTable, nodeTable);
  private OfficeSuppliesRequestDaoImpl officeSuppliesRequestTable =
      OfficeSuppliesRequestDaoImpl.getInstance(accountTable, nodeTable);
  private MedicalSuppliesRequestDaoImpl medicalSuppliesRequestTable =
      MedicalSuppliesRequestDaoImpl.getInstance(accountTable, nodeTable);
  private ServiceRequestDaoImpl serviceRequestTable =
      ServiceRequestDaoImpl.getInstance(
          conferenceRequestTable,
          flowerRequestTable,
          mealRequestTable,
          furnitureRequestTable,
          officeSuppliesRequestTable,
          patientTransportRequestTable,
          medicalSuppliesRequestTable);

  private static Qdb single_instance = null;

  public static synchronized Qdb getInstance() {
    if (single_instance == null) single_instance = new Qdb();
    return single_instance;
  }

  public boolean addServiceRequest(ServiceRequest sr) {
    return serviceRequestTable.addRow(sr);
  }

  public Account retrieveAccount(String username) {
    return accountTable.retrieveRow(username);
  }

  public ArrayList<Account> retrieveAccounts(String email) {
    return (ArrayList<Account>) accountTable.retrieveRows(email);
  }

  public boolean updateAccount(String username, Account a) {
    return accountTable.updateRow(username, a);
  }

  public boolean deleteAccount(String username) {
    return accountTable.deleteRow(username);
  }

  public boolean addAccount(Account a) {
    return accountTable.addRow(a);
  }

  public int getAccountIndex(String username) {
    return accountTable.getIndex(username);
  }

  public ObservableList<String> getAllNames() {
    return accountTable.getAllNames();
  }

  public List<Integer> getAccountIndexes(String email) {
    return accountTable.getIndexes(email);
  }

  public ArrayList<Account> retrieveAllAccounts() {
    return (ArrayList<Account>) accountTable.getAllRows();
  }

  public ConferenceRequest retrieveConferenceRequest(int requestID) {
    return conferenceRequestTable.retrieveRow(requestID);
  }

  public boolean updateConferenceRequest(int requestID, ConferenceRequest cr) {
    return conferenceRequestTable.updateRow(requestID, cr);
  }

  public boolean deleteConferenceRequest(int requestID) {
    return conferenceRequestTable.deleteRow(requestID);
  }

  public boolean addConferenceRequest(ConferenceRequest cr) {
    serviceRequestTable.addRow(cr);
    return conferenceRequestTable.addRow(cr);
  }

  public ArrayList<ConferenceRequest> retrieveAllConferenceRequests() {
    return (ArrayList<ConferenceRequest>) conferenceRequestTable.getAllRows();
  }

  public FlowerRequest retrieveFlowerRequest(int requestID) {
    return flowerRequestTable.retrieveRow(requestID);
  }

  public boolean updateFlowerRequest(int requestID, FlowerRequest fr) {
    return flowerRequestTable.updateRow(requestID, fr);
  }

  public boolean deleteFlowerRequest(int requestID) {
    return flowerRequestTable.deleteRow(requestID);
  }

  public boolean addFlowerRequest(FlowerRequest fr) {
    serviceRequestTable.addRow(fr);
    return flowerRequestTable.addRow(fr);
  }

  public ArrayList<FlowerRequest> retrieveAllFlowerRequests() {
    return (ArrayList<FlowerRequest>) flowerRequestTable.getAllRows();
  }

  public List<ServiceRequest> retrieveAllServiceRequests() {
    return serviceRequestTable.getAllRows();
  }

  public ServiceRequest retrieveServiceRequest(int requestID) {
    return serviceRequestTable.retrieveRow(requestID);
  }

  public Edge retrieveEdge(int edgeID) {
    return edgeTable.retrieveRow(edgeID);
  }

  public boolean updateEdge(int edgeID, Edge e) {
    return edgeTable.updateRow(edgeID, e);
  }

  public boolean deleteEdge(int edgeID) {
    return edgeTable.deleteRow(edgeID);
  }

  public boolean addEdge(Edge e) {
    return edgeTable.addRow(e);
  }

  public List<Edge> getEdges(int nodeID) {
    return edgeTable.getEdges(nodeID);
  }

  public ArrayList<Edge> retrieveAllEdges() {
    return (ArrayList<Edge>) edgeTable.getAllRows();
  }

  public Node retrieveNode(int nodeID) {
    return nodeTable.retrieveRow(nodeID);
  }

  public boolean updateNode(int nodeID, Node n) {
    return nodeTable.updateRow(nodeID, n);
  }

  public boolean deleteNode(int nodeID) {
    return nodeTable.deleteRow(nodeID);
  }

  public boolean addNode(Node n) {
    return nodeTable.addRow(n);
  }

  public ArrayList<Node> retrieveAllNodes() {
    return (ArrayList<Node>) nodeTable.getAllRows();
  }

  public Location retrieveLocation(int nodeID) {
    return locationTable.retrieveRow(nodeID);
  }

  public boolean updateLocation(int nodeID, Location l) {
    return locationTable.updateRow(nodeID, l);
  }

  public boolean deleteLocation(int nodeID) {
    return locationTable.deleteRow(nodeID);
  }

  public boolean addLocation(Location l) {
    return locationTable.addRow(l);
  }

  public ArrayList<Location> retrieveAllLocations() {
    return (ArrayList<Location>) locationTable.getAllRows();
  }

  public ObservableList<String> getAllLongNames(String[] nodeTypes) {
    return locationTable.getAllLongNames(nodeTypes);
  }

  public ObservableList<String> getAllLongNames() {
    return locationTable.getAllLongNames();
  }

  public Move retrieveMove(int moveID) {
    return moveTable.retrieveRow(moveID);
  }

  public boolean updateMove(int moveID, Move m) {
    return moveTable.updateRow(moveID, m);
  }

  public boolean deleteMove(int moveID) {
    return moveTable.deleteRow(moveID);
  }

  public boolean addMove(Move m) {
    return moveTable.addRow(m);
  }

  public ArrayList<Move> retrieveAllMoves() {
    return (ArrayList<Move>) moveTable.getAllRows();
  }

  public Question retrieveQuestion(int ID) {
    return questionTable.retrieveRow(ID);
  }

  public Question retrieveQuestion(String question) {
    return questionTable.retrieveRow(question);
  }

  public boolean updateQuestion(int ID, Question q) {
    return questionTable.updateRow(ID, q);
  }

  public boolean deleteQuestion(int ID) {
    return questionTable.deleteRow(ID);
  }

  public ArrayList<Question> retrieveAllQuestions() {
    return (ArrayList<Question>) questionTable.getAllRows();
  }

  public boolean addQuestion(Question q) {
    return questionTable.addRow(q);
  }

  public int getQuestionIndex(int ID) {
    return questionTable.getIndex(ID);
  }

  public int getQuestionIndex(String question) {
    return questionTable.getIndex(question);
  }

  public MealRequest retrieveMealRequest(int requestID) {
    return mealRequestTable.retrieveRow(requestID);
  }

  public boolean updateMealRequest(int requestID, MealRequest mr) {
    return mealRequestTable.updateRow(requestID, mr);
  }

  public boolean deleteMealRequest(int requestID) {
    return mealRequestTable.deleteRow(requestID);
  }

  public boolean addMealRequest(MealRequest mr) {
    serviceRequestTable.addRow(mr);
    return mealRequestTable.addRow(mr);
  }

  public ArrayList<MealRequest> retrieveAllMealRequests() {
    return (ArrayList<MealRequest>) mealRequestTable.getAllRows();
  }

  public FurnitureRequest retrieveFurnitureRequest(int requestID) {
    return furnitureRequestTable.retrieveRow(requestID);
  }

  public boolean updateFurnitureRequest(int requestID, FurnitureRequest fr) {
    return furnitureRequestTable.updateRow(requestID, fr);
  }

  public boolean deleteFurnitureRequest(int requestID) {
    return furnitureRequestTable.deleteRow(requestID);
  }

  public boolean addFurnitureRequest(FurnitureRequest fr) {
    serviceRequestTable.addRow(fr);
    return furnitureRequestTable.addRow(fr);
  }

  public ArrayList<FurnitureRequest> retrieveAllFurnitureRequests() {
    return (ArrayList<FurnitureRequest>) furnitureRequestTable.getAllRows();
  }

  public PatientTransportRequest retrievePatientTransportRequest(int requestID) {
    return patientTransportRequestTable.retrieveRow(requestID);
  }

  public boolean updatePatientTransportRequest(int requestID, PatientTransportRequest ptr) {
    return patientTransportRequestTable.updateRow(requestID, ptr);
  }

  public boolean deletePatientTransportRequest(int requestID) {
    return patientTransportRequestTable.deleteRow(requestID);
  }

  public boolean addPatientTransportRequest(PatientTransportRequest ptr) {
    serviceRequestTable.addRow(ptr);
    return patientTransportRequestTable.addRow(ptr);
  }

  public ArrayList<PatientTransportRequest> retrieveAllPatientTransportRequests() {
    return (ArrayList<PatientTransportRequest>) patientTransportRequestTable.getAllRows();
  }

  public OfficeSuppliesRequest retrieveOfficeSuppliesRequest(int requestID) {
    return officeSuppliesRequestTable.retrieveRow(requestID);
  }

  public boolean updateOfficeSuppliesRequest(int requestID, OfficeSuppliesRequest osr) {
    return officeSuppliesRequestTable.updateRow(requestID, osr);
  }

  public boolean deleteOfficeSuppliesRequest(int requestID) {
    return officeSuppliesRequestTable.deleteRow(requestID);
  }

  public boolean addOfficeSuppliesRequest(OfficeSuppliesRequest osr) {
    serviceRequestTable.addRow(osr);
    return officeSuppliesRequestTable.addRow(osr);
  }

  public ArrayList<OfficeSuppliesRequest> retrieveAllOfficeSuppliesRequests() {
    return (ArrayList<OfficeSuppliesRequest>) officeSuppliesRequestTable.getAllRows();
  }

  public Node getNodeFromLocation(String lName) {
    return nodeTable.retrieveRow(locationTable.getNodeFromLocation(lName));
  }

  public String getEmailWithAUsername(String username) {
    return accountTable.getEmailWithUsername(username);
  }

  public MedicalSuppliesRequest retrieveMedicalSuppliesRequest(int requestID) {
    return medicalSuppliesRequestTable.retrieveRow(requestID);
  }

  public boolean updateMedicalSuppliesRequest(int requestID, MedicalSuppliesRequest msr) {
    return medicalSuppliesRequestTable.updateRow(requestID, msr);
  }

  public boolean deleteMedicalSuppliesRequest(int requestID) {
    return medicalSuppliesRequestTable.deleteRow(requestID);
  }

  public boolean addMedicalSuppliesRequest(MedicalSuppliesRequest msr) {
    serviceRequestTable.addRow(msr);
    return medicalSuppliesRequestTable.addRow(msr);
  }

  public ArrayList<MedicalSuppliesRequest> retrieveAllMedicalSuppliesRequests() {
    return (ArrayList<MedicalSuppliesRequest>) medicalSuppliesRequestTable.getAllRows();
  }
}
