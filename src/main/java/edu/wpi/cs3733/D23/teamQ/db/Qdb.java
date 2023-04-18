package edu.wpi.cs3733.D23.teamQ.db;

import edu.wpi.cs3733.D23.teamQ.db.impl.*;
import edu.wpi.cs3733.D23.teamQ.db.obj.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

public class Qdb {
  private AccountDaoImpl accountTable;

  public LocationDaoImpl locationTable;

  public NodeDaoImpl nodeTable;
  public EdgeDaoImpl edgeTable;
  public MoveDaoImpl moveTable;

  private QuestionDaoImpl questionTable;

  private ConferenceRequestDaoImpl conferenceRequestTable;
  private FlowerRequestDaoImpl flowerRequestTable;
  private MealRequestDaoImpl mealRequestTable;
  private FurnitureRequestDaoImpl furnitureRequestTable;
  private PatientTransportRequestDaoImpl patientTransportRequestTable;
  private OfficeSuppliesRequestDaoImpl officeSuppliesRequestTable;
  private MedicalSuppliesRequestDaoImpl medicalSuppliesRequestTable;
  private ServiceRequestDaoImpl serviceRequestTable;

  private static Qdb single_instance = null;

  public static synchronized Qdb getInstance() {
    if (single_instance == null) single_instance = new Qdb();
    return single_instance;
  }

  private Qdb() {
    accountTable = AccountDaoImpl.getInstance();
    locationTable = LocationDaoImpl.getInstance();
    nodeTable = NodeDaoImpl.getInstance(locationTable);
    edgeTable = EdgeDaoImpl.getInstance(nodeTable);
    moveTable = MoveDaoImpl.getInstance(nodeTable);
    questionTable = QuestionDaoImpl.getInstance();
    conferenceRequestTable = ConferenceRequestDaoImpl.getInstance(accountTable, nodeTable);
    flowerRequestTable = FlowerRequestDaoImpl.getInstance(accountTable, nodeTable);
    mealRequestTable = MealRequestDaoImpl.getInstance(accountTable, nodeTable);
    furnitureRequestTable = FurnitureRequestDaoImpl.getInstance(accountTable, nodeTable);
    patientTransportRequestTable =
        PatientTransportRequestDaoImpl.getInstance(accountTable, nodeTable);
    officeSuppliesRequestTable = OfficeSuppliesRequestDaoImpl.getInstance(accountTable, nodeTable);
    medicalSuppliesRequestTable =
        MedicalSuppliesRequestDaoImpl.getInstance(accountTable, nodeTable);
    serviceRequestTable = ServiceRequestDaoImpl.getInstance(accountTable, nodeTable);
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
    serviceRequestTable.deleteRow(requestID);
    return conferenceRequestTable.deleteRow(requestID);
  }

  public boolean addConferenceRequest(ConferenceRequest cr) {
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
    serviceRequestTable.deleteRow(requestID);
    return flowerRequestTable.deleteRow(requestID);
  }

  public boolean addFlowerRequest(FlowerRequest fr) {
    return flowerRequestTable.addRow(fr);
  }

  public ArrayList<FlowerRequest> retrieveAllFlowerRequests() {
    return (ArrayList<FlowerRequest>) flowerRequestTable.getAllRows();
  }

  public List<ServiceRequest> retrieveAllServiceRequests() {
    return serviceRequestTable.getAllRows();
  }

  public List<ServiceRequest> retrieveUserServiceRequests(String user) {
    return serviceRequestTable.getUserRows(user);
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
    serviceRequestTable.deleteRow(requestID);
    return mealRequestTable.deleteRow(requestID);
  }

  public boolean addMealRequest(MealRequest mr) {
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
    serviceRequestTable.deleteRow(requestID);
    return furnitureRequestTable.deleteRow(requestID);
  }

  public boolean addFurnitureRequest(FurnitureRequest fr) {
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
    serviceRequestTable.deleteRow(requestID);
    return patientTransportRequestTable.deleteRow(requestID);
  }

  public boolean addPatientTransportRequest(PatientTransportRequest ptr) {
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
    serviceRequestTable.deleteRow(requestID);
    return officeSuppliesRequestTable.deleteRow(requestID);
  }

  public boolean addOfficeSuppliesRequest(OfficeSuppliesRequest osr) {
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
    serviceRequestTable.deleteRow(requestID);
    return medicalSuppliesRequestTable.deleteRow(requestID);
  }

  public boolean addMedicalSuppliesRequest(MedicalSuppliesRequest msr) {
    return medicalSuppliesRequestTable.addRow(msr);
  }

  public ArrayList<MedicalSuppliesRequest> retrieveAllMedicalSuppliesRequests() {
    return (ArrayList<MedicalSuppliesRequest>) medicalSuppliesRequestTable.getAllRows();
  }

  public ServiceRequest retrieveLastRequest() {
    serviceRequestTable.populate();
    return serviceRequestTable.getAllRows().get(serviceRequestTable.getAllRows().size() - 1);
  }
}
